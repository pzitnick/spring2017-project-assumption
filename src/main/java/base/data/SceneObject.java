package base.data;

import base.data.util.Vector;

import java.util.ArrayList;
import java.util.List;

public abstract class SceneObject {

  private int numFloats = 0;

  private Material material;
  private Vector position;

  public List<Float> toFloatList() {
    List<Float> floatList = new ArrayList<>();

    floatList.addAll(material.toFloatList());
    floatList.addAll(position.toFloatList());

    return floatList;
  }

  public Material getMaterial() {
    return material;
  }

  public void setMaterial(Material material) {
    this.material = material;
  }

  public Vector getPosition() {
    return position;
  }

  public void setPosition(Vector position) {
    this.position = position;
  }

  public int getNumFloats() {
    return numFloats;
  }

  public void setNumFloats(int numFloats) {
    this.numFloats = numFloats;
  }
}
