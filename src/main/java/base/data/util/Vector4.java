package base.data.util;

public class Vector4 extends Vector {

    private static final int DIMENSIONALITY = 4;

    public Vector4(float w, float x, float y, float z) {
        setDimensionality(DIMENSIONALITY);
        setPoints(new float[]{w, x, y, z});
    }

    public Vector4() {
        this(0, 0, 0, 0);
    }

    public Vector4 getConjugate() {
        return new Vector4(points[0], -points[1], -points[2], -points[3]);
    }
}
