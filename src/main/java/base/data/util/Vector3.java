package base.data.util;

public class Vector3 extends Vector {

    private static final int DIMENSIONALITY = 3;

    public Vector3(float x, float y, float z) {
        setDimensionality(DIMENSIONALITY);
        setPoints(new float[]{x, y, z});
    }

    public Vector3() {
        this(0, 0, 0);
    }
}
