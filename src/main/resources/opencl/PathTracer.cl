__constant float EPSILON = 0.00003f;
__constant float PI = 3.14159265359f;
__constant int SAMPLES = 512;
__constant int BOUNCES = 8;

typedef struct Sphere {
	float3 color;
	float3 emission;
	float3 pos;
	float radius;
} Sphere;

typedef struct Ray {
    float3 origin;
    float3 dir;
} Ray;

static float get_random(unsigned int *seed0, unsigned int *seed1) {
	*seed0 = 12345 * ((*seed0) & 65535) + ((*seed0) >> 16);
	*seed1 = 54321 * ((*seed1) & 65535) + ((*seed1) >> 16);

	unsigned int ires = ((*seed0) << 16) + (*seed1);

	union {
		float f;
		unsigned int ui;
	} res;

	res.ui = (ires & 0x007fffff) | 0x40000000;

	return (res.f - 2.0f) / 2.0f;
}

Ray createRay(const int xPixel, const int yPixel, const int width, const int height) {

	float fx = (float)xPixel / (float)width;
	float fy = (float)yPixel / (float)height;

	float aspect_ratio = (float)(width) / (float)(height);
	float fx2 = (fx - 0.5f) * aspect_ratio;
	float fy2 = fy - 0.5f;

	float3 pixel_position = (float3)(fx2, -fy2, 0.0f);

	// Camera
	Ray ray;
	ray.origin = (float3)(0.0f, 0.1f, 2.0f);
	ray.dir = normalize(pixel_position - ray.origin);

	return ray;
}

float intersect_sphere(const Sphere* sphere, const Ray* cast) {
	float3 rayToCenter = sphere->pos - cast->origin;
	float b = dot(rayToCenter, cast->dir);
	float c = dot(rayToCenter, rayToCenter) - sphere->radius * sphere->radius;
	float disc = b * b - c;

	if (disc < 0.0f) {
	    return 0.0f;
	}
	disc = sqrt(disc);
	if ((b - disc) > EPSILON) {
	   return b - disc;
	}
	if ((b + disc) > EPSILON) {
	    return b + disc;
	}

	return 0.0f;
}

bool intersects_scene(__constant Sphere* spheres, const int num_spheres, const Ray* cast, float* dist, int* index) {
	float max_dist = 1e20f;
	*dist = max_dist;

	for (int curSphere = 0; curSphere < num_spheres; ++curSphere)  {
		Sphere sphere = spheres[curSphere];

		float intersect_dist = intersect_sphere(&sphere, cast);
		if (intersect_dist != 0.0f && intersect_dist < *dist) {
			*dist = intersect_dist;
			*index = curSphere;
		}
	}

	return *dist < max_dist;
}

float3 trace(__constant Sphere* spheres, const int num_spheres, const Ray* cast, const int* seed0, const int* seed1) {

    float3 color = (float3)(0.0f, 0.0f, 0.0f);
    float3 mask = (float3)(1.0f, 1.0f, 1.0f);

	Ray ray = *cast;

	for (int curBounce = 0; curBounce < BOUNCES; ++curBounce) {

		float dist;
		int index = 0;

		if (!intersects_scene(spheres, num_spheres, &ray, &dist, &index)) {
			return color;
		}

		Sphere collisionSphere = spheres[index];
		float3 collisionPoint = ray.origin + ray.dir * dist;

		float3 normal = normalize(collisionPoint - collisionSphere.pos);
		if (dot(normal, ray.dir) >= 0.0f) {
		    normal *= -1.0f;
		}

		float3 axis;
		if (fabs(normal.x) > 0.1f) {
		    axis = (float3)(0.0f, 1.0f, 0.0f);
		} else {
		    axis = (float3)(1.0f, 0.0f, 0.0f);
		}

		float3 u = normalize(cross(axis, normal));
		float3 v = cross(normal, u);

		float rand1 = 2.0f * PI * get_random(seed0, seed1);
        float rand2 = get_random(seed0, seed1);
        float rootRand2 = sqrt(rand2);

		float3 nextDir = normalize(u * cos(rand1) * rootRand2 + v * sin(rand1) * rootRand2 + normal * sqrt(1.0f - rand2));

		ray.origin = collisionPoint + normal * EPSILON;
		ray.dir = nextDir;

		color += mask * collisionSphere.emission;
		mask *= collisionSphere.color;
		mask *= dot(nextDir, normal);
	}

	return color;
}

__kernel void path_trace(__constant Sphere* spheres, const int num_spheres, const int width, const int height, __global float3* output) {
    unsigned int id = get_global_id(0);

    unsigned int xPixel = id % width;
    unsigned int yPixel = id / width;

    unsigned int seed0 = xPixel;
    unsigned int seed1 = yPixel;

    Ray ray = createRay(xPixel, yPixel, width, height);

    float3 color = (float3)(0.0f, 0.0f, 0.0f);
    float inv = 1.0f / SAMPLES;

    for (int i = 0; i < SAMPLES; ++i) {
        color += trace(spheres, num_spheres, &ray, &seed0, &seed1) * inv;
    }

    color *= 255;
    if (color.x > 255) {
        color.x = 255;
    }
    if (color.y > 255) {
        color.y = 255;
    }
    if (color.z > 255) {
        color.z = 255;
    }

    output[id] = color;
}