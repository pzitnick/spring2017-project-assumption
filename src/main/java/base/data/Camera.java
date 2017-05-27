package base.data;

import base.data.util.Vector3;

public class Camera {

  private Vector3 position;

  public Camera(Vector3 position) {
    this.position = position;
  }

  public Camera() {
    this(new Vector3(0, 0, 0));
  }

  public Vector3 getPosition() {
    return position;
  }

  public void setPosition(Vector3 position) {
    this.position = position;
  }
}
