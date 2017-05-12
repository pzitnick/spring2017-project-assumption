package base.data;

import java.util.ArrayList;
import java.util.List;

public class Color {

    private float red;
    private float green;
    private float blue;

    public Color(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Color() {
        this(0, 0, 0);
    }

    public List<Float> toFloatList() {
        List<Float> floats = new ArrayList<>();
        floats.add(red);
        floats.add(green);
        floats.add(blue);

        return floats;
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }
}
