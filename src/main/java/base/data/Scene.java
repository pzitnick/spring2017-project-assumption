package base.data;

import com.jogamp.opencl.CLBuffer;
import com.jogamp.opencl.CLContext;
import com.jogamp.opencl.CLMemory;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class Scene {

  private Camera camera;
  private List<SceneObject> sceneObjects;

  public Scene(Camera camera, List<SceneObject> sceneObjects) {
    this.camera = camera;
    this.sceneObjects = sceneObjects;
  }

  public Scene() {
    this(new Camera(), new ArrayList<>());
  }

  public CLBuffer<FloatBuffer> createFloatBuffer(CLContext context) {
    CLBuffer<FloatBuffer> buffer = context.createFloatBuffer(
        sceneObjects.get(0).getNumFloats() * sceneObjects.size(), CLMemory.Mem.READ_ONLY);

    for (int i = 0; i < sceneObjects.size(); ++i) {
      for (float f : sceneObjects.get(i).toFloatList()) {
        buffer.getBuffer().put(f);
      }
      buffer.getBuffer().put(0f);
      buffer.getBuffer().put(0f);
      buffer.getBuffer().put(0f);
    }
    buffer.getBuffer().rewind();

    return buffer;
  }

  public void add(SceneObject object) {
    sceneObjects.add(object);
  }

  public Camera getCamera() {
    return camera;
  }

  public void setCamera(Camera camera) {
    this.camera = camera;
  }

  public List<SceneObject> getSceneObjects() {
    return sceneObjects;
  }

  public void setSceneObjects(List<SceneObject> sceneObjects) {
    this.sceneObjects = sceneObjects;
  }
}
