package base.data.util;

public abstract class VectorModifier {
    public Vector add(Vector v1, Vector v2) {
        for (int curPt = 0; curPt < v1.getDimensionality(); ++curPt) {
            v1.points[curPt] += v2.points[curPt];
        }

        return v1;
    }

    public Vector sub(Vector v1, Vector v2) {
        for (int curPt = 0; curPt < v1.getDimensionality(); ++curPt) {
            v1.points[curPt] -= v2.points[curPt];
        }

        return v1;
    }
}
