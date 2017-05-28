package base.data.util;

import junit.framework.TestCase;
import org.junit.Test;

public class VectorTest extends TestCase {

  @Test
  public void testAdd() {
    Vector v1 = new Vector3(1.0, 2.0, 3.0);
    v1.add(v1);
    assertEquals(v1.getPoint(0), 2.0);
    assertEquals(v1.getPoint(1), 4.0);
    assertEquals(v1.getPoint(2), 6.0);
  }

  @Test
  public void testSub() {
    Vector v1 = new Vector3(1.0, 2.0, 3.0);

    v1.sub(v1);
    assertEquals(v1.getPoint(0), 0.0);
    assertEquals(v1.getPoint(1), 0.0);
    assertEquals(v1.getPoint(2), 0.0);
  }

}