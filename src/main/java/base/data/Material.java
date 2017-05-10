package base.data;

public class Material {

    private Color color;
    private Color emission;

    public Material(Color color, Color emission) {
        this.color = color;
        this.emission = emission;
    }

    public Material() {
        this(new Color(), new Color());
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getEmission() {
        return emission;
    }

    public void setEmission(Color emission) {
        this.emission = emission;
    }
}
