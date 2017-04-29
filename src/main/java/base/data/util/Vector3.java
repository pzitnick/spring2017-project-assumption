package base.data.util;

public class Vector3 extends Vector {

    public Vector3(float x, float y, float z) {
        this.dimensionality = 3;
        points = new float[]{x, y, z};
    }

    public Vector3() {
        this(0, 0, 0);
    }
}
