package base.data;

import java.util.ArrayList;
import java.util.List;

public class Color {

  private double red;
  private double green;
  private double blue;

  public Color(double red, double green, double blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public Color() {
    this(0, 0, 0);
  }

  public List<Float> toFloatList() {
    List<Float> floats = new ArrayList<>();
    floats.add((float)red);
    floats.add((float)green);
    floats.add((float)blue);
    floats.add(0f);

    return floats;
  }

  public double getRed() {
    return red;
  }

  public void setRed(double red) {
    this.red = red;
  }

  public double getGreen() {
    return green;
  }

  public void setGreen(double green) {
    this.green = green;
  }

  public double getBlue() {
    return blue;
  }

  public void setBlue(double blue) {
    this.blue = blue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Color)) return false;

    Color color = (Color) o;

    if (Double.compare(color.getRed(), getRed()) != 0) return false;
    if (Double.compare(color.getGreen(), getGreen()) != 0) return false;
    return Double.compare(color.getBlue(), getBlue()) == 0;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(getRed());
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(getGreen());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(getBlue());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}
