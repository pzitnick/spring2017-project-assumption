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
    floats.add(0f);

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Color)) return false;

    Color color = (Color) o;

    if (Float.compare(color.getRed(), getRed()) != 0) return false;
    if (Float.compare(color.getGreen(), getGreen()) != 0) return false;
    return Float.compare(color.getBlue(), getBlue()) == 0;
  }

  @Override
  public int hashCode() {
    int result = (getRed() != +0.0f ? Float.floatToIntBits(getRed()) : 0);
    result = 31 * result + (getGreen() != +0.0f ? Float.floatToIntBits(getGreen()) : 0);
    result = 31 * result + (getBlue() != +0.0f ? Float.floatToIntBits(getBlue()) : 0);
    return result;
  }
}
