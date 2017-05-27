package base.data.util;

import java.util.ArrayList;
import java.util.List;

public abstract class Vector implements VectorModifier {

  protected int numPoints;

  public Vector add(Vector vector) {
    for (int curPt = 0; curPt < numPoints && curPt < vector.getNumPoints(); ++curPt) {
      setPoint(curPt, getPoint(curPt) + vector.getPoint(curPt));
    }

    return this;
  }

  public Vector sub(Vector vector) {
    for (int curPt = 0; curPt < numPoints && curPt < vector.getNumPoints(); ++curPt) {
      setPoint(curPt, getPoint(curPt) - vector.getPoint(curPt));
    }

    return this;
  }

  public List<Float> toFloatList() {
    List<Float> floats = new ArrayList<>();

    for (int curPt = 0; curPt < numPoints; ++curPt) {
      floats.add(getPoint(curPt));
    }

    floats.add(0f);

    return floats;
  }

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
