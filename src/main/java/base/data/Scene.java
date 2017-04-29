package base.data;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    private Camera camera;
    private List<Object> objects;

    public Scene(Camera camera, List<Object> objects) {
        this.camera = camera;
        this.objects = objects;
    }

    public Scene() {
        this(new Camera(), new ArrayList<Object>());
    }

    public Camera getCamera() {
        return camera;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }
}
