package base;

import base.data.util.Vector3;
import base.data.util.Vector3Modifier;
import base.data.util.Vector4;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    private Application() {
    }

    public static void main(String[] args) {
        Vector3 v = new Vector3(1, 2, 3);
        Vector4 v4 = new Vector4(3, 4, 5, 6);

        Vector3Modifier.getInstance()
                .add(v4, v)
                .add(v4, v4);

        LOGGER.log(Level.INFO, v4.toString());
    }
}
