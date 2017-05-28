package base.logic;

import base.data.SceneObject;
import base.data.Sphere;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileParser {

  private static final Logger LOGGER = Logger.getLogger(FileParser.class.getName());
  private static final FileParser INSTANCE = new FileParser();

  private FileParser() {
  }

  public static FileParser getInstance() {
    return INSTANCE;
  }

  public List<SceneObject> parseSceneObjects(String src) throws IOException {
    BufferedReader br = getBufferedReader(src);

    List<SceneObject> objects = new ArrayList<>();

    String line;
    while ((line = br.readLine()) != null && line.length() > 0) {
      SceneObject object;
      // Split line by spaces not inside parentheses
      String[] tokens = line.split("\\s+(?=[^\\])}]*([\\[({]|$))");

      object = getSceneObjectInstance(tokens[0]);

      for (int curTok = 1; curTok < tokens.length; ++curTok) {
        addSceneObjectParams(object, tokens[curTok]);
      }

      objects.add(object);
    }

    return objects;
  }

  private boolean addSceneObjectParams(SceneObject sceneObject, String token) {
    String[] subToken = token.split("\\(");
    String[] valueString = subToken[1].split("\\)");

    if (valueString.length != 0) {
      String[] values = valueString[0].split(",");
      switch (subToken[0]) {
        case "color":
          sceneObject.getMaterial().getColor().setRed(Double.parseDouble(values[0].trim()));
          sceneObject.getMaterial().getColor().setGreen(Double.parseDouble(values[1].trim()));
          sceneObject.getMaterial().getColor().setBlue(Double.parseDouble(values[2].trim()));
          break;
        case "emission":
          sceneObject.getMaterial().getEmission().setRed(Double.parseDouble(values[0].trim()));
          sceneObject.getMaterial().getEmission().setGreen(Double.parseDouble(values[1].trim()));
          sceneObject.getMaterial().getEmission().setBlue(Double.parseDouble(values[2].trim()));
          break;
        case "position":
          sceneObject.getPosition().setPoint(0, Double.parseDouble(values[0].trim()));
          sceneObject.getPosition().setPoint(1, Double.parseDouble(values[1].trim()));
          sceneObject.getPosition().setPoint(2, Double.parseDouble(values[2].trim()));
          break;
        case "radius":
          ((Sphere) sceneObject).setRadius(Double.parseDouble(values[0].trim()));
          break;
      }
    }

    return true;
  }

  private SceneObject getSceneObjectInstance(String object) {
    if (object.equals("sphere")) {
      return new Sphere();
    } else {
      return null;
    }
  }

  private BufferedReader getBufferedReader(String src) throws FileNotFoundException {
    return new BufferedReader(new InputStreamReader(FileParser.class.getResourceAsStream(src)));
  }

}
