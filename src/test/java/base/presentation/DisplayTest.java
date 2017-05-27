package base.presentation;

import junit.framework.TestCase;
import org.junit.Test;

public class DisplayTest extends TestCase {

  @Test
  public void testConstructorException() {
    Display display = null;
    try {
      display = new Display(-1, -1);
      fail("Expected an IllegalArgumentException to be thrown.");
    } catch (IllegalArgumentException e) {
      assertNull(display);
    }
  }

  @Test
  public void testSetWidthException() {
    Display display = new Display(300, 300);
    display.setWidth(100);
    assertEquals(display.getWidth(), 100);

    try {
      display.setWidth(-1);
      fail("Expected an IllegalArgumentException to be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals(display.getWidth(), 100);
    }
  }

  @Test
  public void testSetHeightException() {
    Display display = new Display(300, 300);
    display.setHeight(100);
    assertEquals(display.getHeight(), 100);

    try {
      display.setHeight(-1);
      fail("Expected an IllegalArgumentException to be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals(display.getHeight(), 100);
    }
  }

}