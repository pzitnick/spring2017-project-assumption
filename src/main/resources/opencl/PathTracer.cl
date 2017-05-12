
typedef struct Vector3 {
    float x;
    float y;
    float z;
} Vector3;

typedef struct Sphere {
	Vector3 color;
	Vector3 emission;
	Vector3 position;
	float radius;
} Sphere;

__kernel void path_trace(const __global Sphere* spheres, const int num_spheres, const int width, const int height, __global Vector3* output) {
    int id = get_global_id(0);

    if (id >= num_spheres) {
        return;
    }

    output[id] = spheres[id].position;
}