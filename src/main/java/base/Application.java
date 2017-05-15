package base;

import base.data.Color;
import base.data.Material;
import base.data.Scene;
import base.data.Sphere;
import base.data.util.Vector3;
import base.logic.PathTracer;
import base.presentation.Display;

import java.io.IOException;
import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    private Application() {
    }

    public static void main(String args[]) throws IOException {
        // This stuff is temporary for demo purposes!

        // Scene 1
        /*Sphere leftWall = new Sphere(new Material(new Color(0.75f, 0.25f, 0.25f), new Color()), new Vector3(-200.6f, 0f, 0f), 200f);
        Sphere rightWall = new Sphere(new Material(new Color(0.25f, 0.25f, 0.75f), new Color()), new Vector3(200.6f, 0f, 0f), 200f);
        Sphere floor = new Sphere(new Material(new Color(0.9f, 0.8f, 0.7f), new Color()), new Vector3(0f, -200.4f, 0f), 200f);
        Sphere ceil = new Sphere(new Material(new Color(0.9f, 0.8f, 0.7f), new Color()), new Vector3(0f, 200.4f, 0f), 200f);
        Sphere backWall = new Sphere(new Material(new Color(0.9f, 0.8f, 0.7f), new Color()), new Vector3(0f, 0f, -200.4f), 200f);
        Sphere leftSphere = new Sphere(new Material(new Color(0.9f, 0.8f, 0.7f), new Color()), new Vector3(-0.25f, -0.24f, -0.1f), 0.16f);
        Sphere rightSphere = new Sphere(new Material(new Color(0.9f, 0.8f, 0.7f), new Color()), new Vector3(0.25f, -0.24f, 0.1f), 0.16f);
        Sphere lightSource = new Sphere(new Material(new Color(), new Color(9.0f, 8.0f, 6.0f)), new Vector3(0f, 1.36f, 0f), 1f);
        Sphere[] spheres = new Sphere[]{leftWall, rightWall, floor, ceil, backWall, leftSphere, rightSphere, lightSource};*/

        // Scene 2
        Sphere leftWall = new Sphere(new Material(new Color(0.75f, 0.25f, 0.25f), new Color(1f, 1f, 1f)), new Vector3(-201.2f, 0f, 0f), 200f);
        Sphere rightWall = new Sphere(new Material(new Color(0.25f, 0.25f, 0.75f), new Color(1f, 1f, 1f)), new Vector3(201.2f, 0f, 0f), 200f);
        Sphere floor = new Sphere(new Material(new Color(0.9f, 0.8f, 0.7f), new Color()), new Vector3(0f, -200.4f, 0f), 200f);
        //Sphere ceil = new Sphere(new Material(new Color(0.9f, 0.8f, 0.7f), new Color()), new Vector3(0f, 200.4f, 0f), 200f);
        Sphere backWall = new Sphere(new Material(new Color(0.9f, 0.8f, 0.7f), new Color()), new Vector3(0f, 0f, -200.4f), 200f);
        Sphere frontWall = new Sphere(new Material(new Color(0.9f, 0.8f, 0.7f), new Color(1f, 1f, 1f)), new Vector3(0f, 0f, 202f), 200f);
        Sphere leftSphere = new Sphere(new Material(new Color(0.9f, 0.8f, 0.7f), new Color()), new Vector3(-0.20f, -0.3f, -0.20f), 0.16f);
        Sphere rightSphere = new Sphere(new Material(new Color(0.9f, 0.8f, 0.7f), new Color()), new Vector3(0.20f, -0.3f, -0.20f), 0.16f);
        Sphere frontSphere = new Sphere(new Material(new Color(0.9f, 0.8f, 0.7f), new Color()), new Vector3(-0.20f, -0.3f, 0.20f), 0.16f);
        Sphere backSphere = new Sphere(new Material(new Color(0.9f, 0.8f, 0.7f), new Color()), new Vector3(0.20f, -0.3f, 0.20f), 0.16f);
        Sphere topSphere = new Sphere(new Material(new Color(0.9f, 0.8f, 0.7f), new Color()), new Vector3(0f, -0.3f, 0.4f), 0.16f);
        Sphere seemsGoodSphere = new Sphere(new Material(new Color(0.9f, 0.8f, 0.7f), new Color()), new Vector3(0f, -0.3f, -0.4f), 0.16f);
        Sphere centerSphere = new Sphere(new Material(new Color(0.9f, 0.8f, 0.7f), new Color()), new Vector3(0f, 0.1f, 0f), 0.16f);
        Sphere centerLight = new Sphere(new Material(new Color(), new Color(1f, 10f, 10f)), new Vector3(0, -0.5f, 0), 0.16f);
        Sphere[] spheres = new Sphere[]{leftWall, rightWall, floor, backWall, leftSphere, rightSphere, frontSphere, backSphere, topSphere, centerSphere, centerLight, seemsGoodSphere, frontWall};

        Scene scene = new Scene();
        for (Sphere sphere : spheres) {
            scene.add(sphere);
        }

        Display display = new Display(1920, 1080);
        display.initDisplay();

        PathTracer pt = new PathTracer(scene, display);
        pt.init();
        pt.render();
    }

}
