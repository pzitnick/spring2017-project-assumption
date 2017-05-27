package base.data.util;

import junit.framework.TestCase;
import org.junit.Test;

public class VectorTest extends TestCase {

  @Test
  public void testAdd() {
    Vector v1 = new Vector3(1f, 2f, 3f);
    Vector v2 = new Vector4(1f, 2f, 3f, 4f);

    v1.add(v1);
    assertEquals(v1.getPoint(0), 2.0f);
    assertEquals(v1.getPoint(1), 4.0f);
    assertEquals(v1.getPoint(2), 6.0f);

    v2.add(v2);
    assertEquals(v2.getPoint(0), 2.0f);
    assertEquals(v2.getPoint(1), 4.0f);
    assertEquals(v2.getPoint(2), 6.0f);
    assertEquals(v2.getPoint(3), 8.0f);

    v1.add(v2);
    assertEquals(v1.getPoint(0), 4.0f);
    assertEquals(v1.getPoint(1), 8.0f);
    assertEquals(v1.getPoint(2), 12.0f);

    v2.add(v1);
    assertEquals(v2.getPoint(0), 6.0f);
    assertEquals(v2.getPoint(1), 12.0f);
    assertEquals(v2.getPoint(2), 18.0f);
    assertEquals(v2.getPoint(3), 8.0f);
  }

  @Test
  public void testSub() {
    Vector v1 = new Vector3(1f, 2f, 3f);
    Vector v2 = new Vector4(1f, 2f, 3f, 4f);

    v1.sub(v1);
    assertEquals(v1.getPoint(0), 0f);
    assertEquals(v1.getPoint(1), 0f);
    assertEquals(v1.getPoint(2), 0f);

    v2.sub(v2);
    assertEquals(v2.getPoint(0), 0f);
    assertEquals(v2.getPoint(1), 0f);
    assertEquals(v2.getPoint(2), 0f);
    assertEquals(v2.getPoint(3), 0f);

    v1 = new Vector3(1f, 2f, 3f);
    v2 = new Vector4(1f, 2f, 3f, 4f);

    v1.sub(v2);
    assertEquals(v1.getPoint(0), 0f);
    assertEquals(v1.getPoint(1), 0f);
    assertEquals(v1.getPoint(2), 0f);

    v1 = new Vector3(1f, 2f, 3f);
    v2 = new Vector4(1f, 2f, 3f, 4f);

    v2.sub(v1);
    assertEquals(v2.getPoint(0), 0f);
    assertEquals(v2.getPoint(1), 0f);
    assertEquals(v2.getPoint(2), 0f);
    assertEquals(v2.getPoint(3), 4f);
  }

}