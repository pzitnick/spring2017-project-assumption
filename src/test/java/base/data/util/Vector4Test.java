package base.data.util;

import junit.framework.TestCase;
import org.junit.Test;

public class Vector4Test extends TestCase {

    @Test
    public void testGetPoint() {
        Vector4 v = new Vector4(0f, 1f, 2f, 3f);

        assertEquals(v.getPoint(0), 0f);
        assertEquals(v.getPoint(1), 1f);
        assertEquals(v.getPoint(2), 2f);
        assertEquals(v.getPoint(3), 3f);
    }

    @Test
    public void testGetPointException() {
        Vector4 v = new Vector4();

        try {
            v.getPoint(4);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assert (true);
        }
    }

    @Test
    public void testSetPoint() {
        Vector4 v = new Vector4();

        v.setPoint(0, 10f);
        v.setPoint(1, 20f);
        v.setPoint(2, 30f);
        v.setPoint(3, 40f);

        assertEquals(v.getPoint(0), 10f);
        assertEquals(v.getPoint(1), 20f);
        assertEquals(v.getPoint(2), 30f);
        assertEquals(v.getPoint(3), 40f);
    }

    @Test
    public void testSetPointException() {
        Vector4 v = new Vector4();

        try {
            v.setPoint(4, 0f);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assert (true);
        }
    }

}