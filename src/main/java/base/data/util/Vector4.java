package base.data.util;

public class Vector4 extends Vector {

    private float w;
    private float x;
    private float y;
    private float z;

    public Vector4(float w, float x, float y, float z) {
        setNumPoints(4);
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector4() {
        this(0, 0, 0, 0);
    }

    public float getPoint(int index) throws IndexOutOfBoundsException {
        switch (index) {
            case 0:
                return w;
            case 1:
                return x;
            case 2:
                return y;
            case 3:
                return z;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    public void setPoint(int index, float value) throws IndexOutOfBoundsException {
        switch (index) {
            case 0:
                w = value;
                break;
            case 1:
                x = value;
                break;
            case 2:
                y = value;
                break;
            case 3:
                z = value;
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    public Vector4 getConjugate() {
        return new Vector4(w, -x, -y, -z);
    }
}
