package base.data.util;

public abstract class Vector {

    protected int numPoints;

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().toString());
        sb.append("(" + hashCode() + ")[");
        for (int curPt = 0; curPt < numPoints; ++curPt) {
            if (curPt < numPoints - 1) {
                sb.append(String.format("%.2f, ", getPoint(curPt)));
            } else {
                sb.append(String.format("%.2f]", getPoint(curPt)));
            }
        }

        return sb.toString();
    }

    public abstract float getPoint(int index);

    public abstract void setPoint(int index, float value);

    public int getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }
}
