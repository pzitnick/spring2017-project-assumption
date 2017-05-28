package base.data.util;

import junit.framework.TestCase;
import org.junit.Test;

public class Vector3Test extends TestCase {

  @Test
  public void testGetPoint() {
    Vector3 v = new Vector3(0.0, 1.0, 2.0);

    assertEquals(v.getPoint(0), 0.0);
    assertEquals(v.getPoint(1), 1.0);
    assertEquals(v.getPoint(2), 2.0);
  }

  @Test
  public void testGetPointException() {
    Vector3 v = new Vector3();

    try {
      v.getPoint(4);
      fail("Expected IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
      assert (true);
    }
  }

  @Test
  public void testSetPoint() {
    Vector3 v = new Vector3();

    v.setPoint(0, 10.0);
    v.setPoint(1, 20.0);
    v.setPoint(2, 30.0);

    assertEquals(v.getPoint(0), 10.0);
    assertEquals(v.getPoint(1), 20.0);
    assertEquals(v.getPoint(2), 30.0);
  }

  @Test
  public void testSetPointException() {
    Vector3 v = new Vector3();

    try {
      v.setPoint(4, 0.0);
      fail("Expected IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
      assert (true);
    }
  }

}