package base;

import base.presentation.Display;

import java.io.IOException;
import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    private Application() {
        throw new UnsupportedOperationException();
    }

    public static void main(String args[]) throws IOException {
        Display display = new Display(300, 300);
    }

}
