package base.data.util;

public abstract class Vector {
    protected float[] points;
    private int dimensionality;

    public int getDimensionality() {
        return dimensionality;
    }

    public void setDimensionality(int dimensionality) {
        this.dimensionality = dimensionality;
    }

    public float[] getPoints() {
        return points;
    }

    public void setPoints(float[] points) {
        this.points = points;
    }

    public float getPoint(int index) {
        if (index < dimensionality) {
            return points[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void setPoint(float val, int index) {
        if (index < dimensionality) {
            points[index] = val;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().toString());
        sb.append("(" + hashCode() + ")[");
        for (int curPt = 0; curPt < dimensionality; ++curPt) {
            if (curPt < dimensionality - 1) {
                sb.append(String.format("%.2f, ", points[curPt]));
            } else {
                sb.append(String.format("%.2f]", points[curPt]));
            }
        }

        return sb.toString();
    }
}
