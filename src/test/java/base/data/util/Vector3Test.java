package base.data.util;

import junit.framework.TestCase;
import org.junit.Test;

public class Vector3Test extends TestCase {

  @Test
  public void testGetPoint() {
    Vector3 v = new Vector3(0f, 1f, 2f);

    assertEquals(v.getPoint(0), 0f);
    assertEquals(v.getPoint(1), 1f);
    assertEquals(v.getPoint(2), 2f);
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

    v.setPoint(0, 10f);
    v.setPoint(1, 20f);
    v.setPoint(2, 30f);

    assertEquals(v.getPoint(0), 10f);
    assertEquals(v.getPoint(1), 20f);
    assertEquals(v.getPoint(2), 30f);
  }

  @Test
  public void testSetPointException() {
    Vector3 v = new Vector3();

    try {
      v.setPoint(4, 0f);
      fail("Expected IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
      assert (true);
    }
  }

}