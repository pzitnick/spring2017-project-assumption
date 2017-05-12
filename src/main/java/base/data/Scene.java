package base.data;

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
        this(new Camera(), new ArrayList<SceneObject>());
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
