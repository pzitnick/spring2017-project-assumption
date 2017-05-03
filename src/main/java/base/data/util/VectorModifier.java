package base.data.util;

public interface VectorModifier {

    default VectorModifier add(Vector v1, Vector v2) {
        int v1Points = v1.getNumPoints();
        int v2Points = v2.getNumPoints();

        for (int curPt = 0; curPt < v1Points && curPt < v2Points; ++curPt) {
            v1.setPoint(curPt, v1.getPoint(curPt) + v2.getPoint(curPt));
        }

        return this;
    }

    default VectorModifier sub(Vector v1, Vector v2) {
        int v1Points = v1.getNumPoints();
        int v2Points = v2.getNumPoints();

        for (int curPt = 0; curPt < v1Points && curPt < v2Points; ++curPt) {
            v1.setPoint(curPt, v1.getPoint(curPt) - v2.getPoint(curPt));
        }

        return this;
    }
}
