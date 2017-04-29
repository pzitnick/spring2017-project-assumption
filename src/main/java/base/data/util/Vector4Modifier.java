package base.data.util;

public class Vector4Modifier extends VectorModifier {

    private Vector4Modifier() {
    }

    public static Vector mul(Vector4 v4, Vector3 v3) {
        v4.points[0] = -v4.points[1] * v3.points[0] - v4.points[2] * v3.points[1] - v4.points[3] * v3.points[2];
        v4.points[1] = v4.points[0] * v3.points[0] + v4.points[2] * v3.points[2] - v4.points[3] * v3.points[1];
        v4.points[2] = v4.points[1] * v3.points[1] + v4.points[3] * v3.points[0] - v4.points[1] * v3.points[2];
        v4.points[3] = v4.points[1] * v3.points[2] + v4.points[1] * v3.points[1] - v4.points[2] * v3.points[0];

        return v4;
    }
}
