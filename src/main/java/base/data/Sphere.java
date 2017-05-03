package base.data;

public class Sphere extends SceneObject {

    public Sphere(Material material) {
        setMaterial(material);
    }

    public Sphere() {
        this(new Material());
    }

}
