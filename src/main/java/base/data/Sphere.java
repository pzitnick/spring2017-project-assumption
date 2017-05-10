package base.data;

import base.data.util.Vector3;

public class Sphere extends SceneObject {

    private float radius;

    public Sphere(Material material, Vector3 position, float radius) {
        setMaterial(material);
        setPosition(position);
        this.radius = radius;
    }

    public Sphere() {
        this(new Material(), new Vector3(), 0);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
