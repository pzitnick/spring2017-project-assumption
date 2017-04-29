package base.data.util;

public class Vector3 extends Vector {

    public Vector3(float x, float y, float z) {
        setDimensionality(3);
        setPoints(new float[]{x, y, z});
    }

    public Vector3() {
        this(0, 0, 0);
    }
}
