package base.data.util;

public class Vector3 extends Vector {

    // Calling vector.x is a lot less verbose than vector.points[0]
    protected float x;
    protected float y;
    protected float z;

    public Vector3(float x, float y, float z) {
        setNumPoints(3);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3() {
        this(0, 0, 0);
    }

    public float getPoint(int index) {
        switch (index) {
            case 0:
                return x;
            case 1:
                return y;
            case 2:
                return z;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    public void setPoint(int index, float value) {
        switch (index) {
            case 0:
                x = value;
                break;
            case 1:
                y = value;
                break;
            case 2:
                z = value;
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
    }
}
