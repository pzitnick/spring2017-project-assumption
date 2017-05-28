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

      assertEquals(objects.size(), 5);

      for (SceneObject object : objects) {
        assertTrue(object instanceof Sphere);
      }

      Sphere result0 = (Sphere) objects.get(0);
      Sphere correct0 = new Sphere.SphereBuilder()
          .material(new Color(), new Color())
          .position(new Vector3())
          .radius(0)
          .build();
      assertEquals(result0, correct0);

      Sphere result1 = (Sphere) objects.get(1);
      Sphere correct1 = new Sphere.SphereBuilder()
          .material(new Color(1, 1, 1), new Color())
          .position(new Vector3())
          .radius(0)
          .build();
      assertEquals(result1, correct1);

      Sphere result2 = (Sphere) objects.get(2);
      Sphere correct2 = new Sphere.SphereBuilder()
          .material(new Color(1, 1, 1), new Color(2, 2, 2))
          .position(new Vector3())
          .radius(0)
          .build();
      assertEquals(result2, correct2);

      Sphere result3 = (Sphere) objects.get(3);
      Sphere correct3 = new Sphere.SphereBuilder()
          .material(new Color(1, 1, 1), new Color(2, 2, 2))
          .position(new Vector3(3, 3, 3))
          .radius(0)
          .build();
      assertEquals(result3, correct3);

      Sphere result4 = (Sphere) objects.get(4);
      Sphere correct4 = new Sphere.SphereBuilder()
          .material(new Color(1, 1, 1), new Color(2, 2, 2))
          .position(new Vector3(3, 3, 3))
          .radius(4)
          .build();
      assertEquals(result4, correct4);

    } catch (IOException e) {
    }
  }

}