package base;

import base.data.util.Vector3;
import base.data.util.Vector4;
import base.data.util.Vector4Modifier;

public class Application {

    private Application() {
    }

    public static void main(String[] args) {
        Vector3 v = new Vector3(1, 2, 3);
        Vector4 v4 = new Vector4(3, 4, 5, 6);
        System.out.println(Vector4Modifier.mul(v4, v));
    }
}
