package base.data;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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

    public List<Float> toFloatList() {
        List<Float> floats = color.toFloatList();
        floats.addAll(emission.toFloatList());

        return floats;
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
