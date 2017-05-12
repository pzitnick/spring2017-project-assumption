package base.data;

import base.data.util.Vector3;

import java.util.List;

public class Sphere extends SceneObject {

    private float radius;

    public Sphere(Material material, Vector3 position, float radius) {
        setMaterial(material);
        setPosition(position);
        setNumFloats(10);

        this.radius = radius;
    }

    public Sphere() {
        this(new Material(), new Vector3(), 0);
    }

    @Override
    public List<Float> toFloatList() {
        List<Float> floatList = super.toFloatList();
        floatList.add(radius);

        return floatList;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
