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
  private static final int MIN_TOKENS = 5;

  private FileParser() {
  }

  public static FileParser getInstance() {
    return INSTANCE;
  }

  public List<SceneObject> parseSceneObjects(String src) throws IOException {
    BufferedReader br = getBufferedReader(src);

    List<SceneObject> objects = new ArrayList<>();

    int curLine = 1;
    String line;
    while ((line = br.readLine()) != null) {
      SceneObject object;
      String[] tokens = line.split("\\s+(?=[^\\])}]*([\\[({]|$))");

      if (tokens.length < MIN_TOKENS) {
        LOGGER.warning("Illegal SceneObject format on line " + curLine + " (not enough tokens). Skipping...");
        ++curLine;
        continue;
      }

      object = getSceneObjectInstance(tokens[0]);
      if (object == null) {
        LOGGER.warning("Illegal SceneObject format on line " + curLine +
            " (no supported SceneObject found). Skipping...");
        ++curLine;
        continue;
      }

      for (int curTok = 1; curTok < MIN_TOKENS; ++curTok) {
        if (!addSceneObjectParams(object, tokens, curTok)) {
          LOGGER.warning("Illegal SceneObject format on line " + curLine +
              " (no supported parameter for SceneObject found). Skipping...");
          ++curLine;
          continue;
        }
      }

      objects.add(object);
      ++curLine;
    }

    return objects;
  }

  private boolean addSceneObjectParams(SceneObject sceneObject, String[] tokens, int curTok) {
    String[] subToken = tokens[curTok].split("\\(");
    String[] valueString = subToken[1].split("\\)");

    if (valueString.length != 0) {
      String[] values = valueString[0].split(",");
      switch (subToken[0]) {
        case "color":
          if (values.length != 3) {
            return false;
          }

          sceneObject.getMaterial().getColor().setRed(Float.parseFloat(values[0].trim()));
          sceneObject.getMaterial().getColor().setGreen(Float.parseFloat(values[1].trim()));
          sceneObject.getMaterial().getColor().setBlue(Float.parseFloat(values[2].trim()));
          break;
        case "emission":
          if (values.length != 3) {
            return false;
          }

          sceneObject.getMaterial().getEmission().setRed(Float.parseFloat(values[0].trim()));
          sceneObject.getMaterial().getEmission().setGreen(Float.parseFloat(values[1].trim()));
          sceneObject.getMaterial().getEmission().setBlue(Float.parseFloat(values[2].trim()));
          break;
        case "position":
          if (values.length != 3) {
            return false;
          }

          sceneObject.getPosition().setPoint(0, Float.parseFloat(values[0].trim()));
          sceneObject.getPosition().setPoint(1, Float.parseFloat(values[1].trim()));
          sceneObject.getPosition().setPoint(2, Float.parseFloat(values[2].trim()));
          break;
        case "radius":
          ((Sphere) sceneObject).setRadius(Float.parseFloat(values[0].trim()));
          break;
        default:
          return false;
      }
    }

    return true;
  }

  private SceneObject getSceneObjectInstance(String object) {
    // To be populated with more object types as they are implemented
    switch (object) {
      case "sphere":
        return new Sphere();
      default:
        return null;
    }
  }

  private BufferedReader getBufferedReader(String src) throws FileNotFoundException {
    return new BufferedReader(new InputStreamReader(FileParser.class.getResourceAsStream(src)));
  }

}
