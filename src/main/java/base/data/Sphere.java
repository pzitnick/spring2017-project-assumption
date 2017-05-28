package base.data;

import base.data.util.Vector3;

import java.util.List;

public class Sphere extends SceneObject {

  private double radius;

  public Sphere(Material material, Vector3 position, double radius) {
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
    floatList.add((float) radius);

    return floatList;
  }

  public double getRadius() {
    return radius;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Sphere)) return false;
    if (!super.equals(o)) return false;

    Sphere sphere = (Sphere) o;

    return Double.compare(sphere.getRadius(), getRadius()) == 0;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    long temp;
    temp = Double.doubleToLongBits(getRadius());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  public static class SphereBuilder extends SceneObjectBuilder<SphereBuilder> {

    private double radius;

    public SphereBuilder radius(double radius) {
      this.radius = radius;

      return this;
    }

    public Sphere build() {
      return new Sphere(this);
    }

  }
}
