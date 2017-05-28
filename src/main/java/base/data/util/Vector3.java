package base.data.util;

public class Vector3 extends Vector {

  // Calling vector.x is a lot less verbose than vector.points[0]
  protected double x;
  protected double y;
  protected double z;

  public Vector3(double x, double y, double z) {
    setNumPoints(3);
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Vector3() {
    this(0, 0, 0);
  }

  public double getPoint(int index) {
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

  public void setPoint(int index, double value) {
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

    if (Double.compare(vector3.x, x) != 0) return false;
    if (Double.compare(vector3.y, y) != 0) return false;
    return Double.compare(vector3.z, z) == 0;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    long temp;
    temp = Double.doubleToLongBits(x);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(y);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(z);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}
