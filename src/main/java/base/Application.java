package base;

import base.data.Scene;
import base.logic.PathTracer;
import base.presentation.Display;

import java.io.IOException;
import java.util.logging.Logger;

public class Application {

  private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

  private Application() {
  }

  public static void main(String[] args) throws IOException {
    Scene scene = new Scene();
    scene.loadScene("/scenes/scene1.jpt");

    Display display = new Display(1920, 1080);
    display.initDisplay();

    PathTracer pt = new PathTracer(scene, display);
    pt.init();
    pt.render(true);
  }

}
