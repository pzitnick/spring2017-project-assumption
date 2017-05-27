package base.data.util;

public class Vector3 extends Vector {

  // Calling vector.x is a lot less verbose than vector.points[0]
  protected float x;
  protected float y;
  protected float z;

  public Vector3(float x, float y, float z) {
    setNumPoints(3);
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Vector3() {
    this(0, 0, 0);
  }

  public float getPoint(int index) {
    switch (index) {
      case 0:
        return x;
      case 1:
        return y;
      case 2:
        return z;
      default:
        throw new IndexOutOfBoundsException();
    }
  }

  public void setPoint(int index, float value) {
    switch (index) {
      case 0:
        x = value;
        break;
      case 1:
        y = value;
        break;
      case 2:
        z = value;
        break;
      default:
        throw new IndexOutOfBoundsException();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Vector3)) return false;
    if (!super.equals(o)) return false;

    Vector3 vector3 = (Vector3) o;

    if (Float.compare(vector3.x, x) != 0) return false;
    if (Float.compare(vector3.y, y) != 0) return false;
    return Float.compare(vector3.z, z) == 0;
  }

  @Override
  public int hashCode() {
    int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
    result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
    result = 31 * result + (z != +0.0f ? Float.floatToIntBits(z) : 0);
    return result;
  }
}
