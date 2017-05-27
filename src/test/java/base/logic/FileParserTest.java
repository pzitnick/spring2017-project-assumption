package base.logic;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;

public class FileParserTest extends TestCase {

  @Test
  public void testParseSceneObjects() {
    try {
      FileParser.getInstance().parseSceneObjects("/scenes/scene1.jpt");
    } catch (IOException e) {
      fail();
    }

    assert(true);
  }

}