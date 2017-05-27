package base.data.util;

public class Ray {

  private Vector origin;
  private Vector direction;

  public Ray(Vector3 origin, Vector3 direction) {
    this.origin = origin;
    this.direction = direction;
  }

  public Ray() {
    this(new Vector3(), new Vector3());
  }

  public Vector getOrigin() {
    return origin;
  }

  public void setOrigin(Vector origin) {
    this.origin = origin;
  }

  public Vector getDirection() {
    return direction;
  }

  public void setDirection(Vector direction) {
    this.direction = direction;
  }
}
