package base.logic;

import base.data.Color;
import base.data.SceneObject;
import base.data.Sphere;
import base.data.util.Vector3;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class FileParserTest extends TestCase {

  @Test
  public void testParseSphereObjects() {
    String filePath = "/scenes/spheretest.jpt";

    try {
      List<SceneObject> objects = FileParser.getInstance().parseSceneObjects(filePath);

      for (SceneObject object : objects) {
        assertTrue(object instanceof Sphere);
      }

      Sphere result0 = (Sphere) objects.get(0);
      Sphere correct0 = new Sphere.SphereBuilder()
          .material(new Color(0, 0, 0), new Color(0, 0, 0))
          .position(new Vector3(0, 0, 0))
          .radius(0)
          .build();
      assertEquals(result0, correct0);

      Sphere result1 = (Sphere) objects.get(1);


      Sphere result2 = (Sphere) objects.get(2);


      Sphere result3 = (Sphere) objects.get(3);


      Sphere result4 = (Sphere) objects.get(4);


    } catch (IOException e) {
      fail("Test resource at " + filePath + " not found.");
    }
  }

}