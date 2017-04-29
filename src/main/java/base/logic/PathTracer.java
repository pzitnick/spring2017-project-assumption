package base.logic;

import base.data.Scene;
import base.presentation.Display;

public class PathTracer {

    private Scene scene;
    private Display display;

    public PathTracer(Scene scene, Display display) {
        this.scene = scene;
    }

    public PathTracer() {
        this(new Scene(), new Display());
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
