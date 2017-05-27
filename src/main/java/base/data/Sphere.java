package base.data;

import base.data.util.Vector3;

import java.util.List;

public class Sphere extends SceneObject {

  private float radius;

  public Sphere(Material material, Vector3 position, float radius) {
    setMaterial(material);
    setPosition(position);
    setNumFloats(16);

    this.radius = radius;
  }

  public Sphere(SphereBuilder builder) {
    this(builder.material, builder.position, builder.radius);
  }

  public Sphere() {
    this(new Material(), new Vector3(), 0);
  }

  @Override
  public List<Float> toFloatList() {
    List<Float> floatList = super.toFloatList();
    floatList.add(radius);

    return floatList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Sphere)) return false;
    if (!super.equals(o)) return false;

    Sphere sphere = (Sphere) o;

    return Float.compare(sphere.getRadius(), getRadius()) == 0;
  }

  @Override
  public int hashCode() {
    return (getRadius() != +0.0f ? Float.floatToIntBits(getRadius()) : 0);
  }

  public float getRadius() {
    return radius;
  }

  public void setRadius(float radius) {
    this.radius = radius;
  }

  public static class SphereBuilder extends SceneObjectBuilder<SphereBuilder> {

    private float radius;

    public SphereBuilder radius(float radius) {
      this.radius = radius;

      return this;
    }

    public Sphere build() {
      return new Sphere(this);
    }

  }
}
