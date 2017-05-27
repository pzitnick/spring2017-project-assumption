package base.data;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Material)) {
      return false;
    }

    Material material = (Material) o;

    if (getColor() != null ? !getColor().equals(material.getColor()) : material.getColor() != null) {
      return false;
    }
    return getEmission() != null ? getEmission().equals(material.getEmission()) : material.getEmission() == null;
  }

}
