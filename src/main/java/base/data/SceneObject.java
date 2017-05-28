package base.data;

import base.data.util.Vector;
import base.data.util.Vector3;

import java.util.ArrayList;
import java.util.List;

public abstract class SceneObject {

  private int numFloats = 0;

  private Material material;
  private Vector3 position;

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

  public Vector3 getPosition() {
    return position;
  }

  public void setPosition(Vector3 position) {
    this.position = position;
  }

  public int getNumFloats() {
    return numFloats;
  }

  public void setNumFloats(int numFloats) {
    this.numFloats = numFloats;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SceneObject)) return false;

    SceneObject that = (SceneObject) o;

    if (getNumFloats() != that.getNumFloats()) return false;
    if (getMaterial() != null ? !getMaterial().equals(that.getMaterial()) : that.getMaterial() != null)
      return false;
    return getPosition() != null ? getPosition().equals(that.getPosition()) : that.getPosition() == null;
  }

  @Override
  public int hashCode() {
    int result = getNumFloats();
    result = 31 * result + (getMaterial() != null ? getMaterial().hashCode() : 0);
    result = 31 * result + (getPosition() != null ? getPosition().hashCode() : 0);
    return result;
  }

  public static class SceneObjectBuilder<T extends SceneObjectBuilder<T>> {

    protected Material material;
    protected Vector3 position;

    public T material(Color color, Color emission) {
      material = new Material(color, emission);

      return (T) this;
    }

    public T position(Vector3 position) {
      this.position = position;

      return (T) this;
    }

  }

}
