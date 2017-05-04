package base.data;

public class Material {

    private float glossiness;

    public Material(float glossiness) {
        this.glossiness = glossiness;
    }

    public Material() {
        this(0);
    }

    public float getGlossiness() {
        return glossiness;
    }

    public void setGlossiness(float glossiness) {
        this.glossiness = glossiness;
    }
}
