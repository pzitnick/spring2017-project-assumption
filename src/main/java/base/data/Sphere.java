package base.data;

public class Sphere extends Object {

    public Sphere(Material material) {
        setMaterial(material);
    }

    public Sphere()  {
        this(new Material());
    }

}
