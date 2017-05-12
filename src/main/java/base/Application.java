package base;

import base.data.Scene;
import base.logic.PathTracer;
import base.presentation.Display;

import java.io.IOException;
import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    private Application() {
        throw new UnsupportedOperationException();
    }

    public static void main(String args[]) throws IOException {
        Scene scene = new Scene();
        Display display = new Display(300, 300);
        display.initDisplay();
        display.getjFrame().setVisible(true);
        PathTracer pt = new PathTracer(scene, display);
        pt.render();
    }

}
