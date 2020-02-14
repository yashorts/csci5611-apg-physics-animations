import camera.QueasyCam;
import physical.GridSpringMassSystem;
import processing.core.PApplet;

public class Main extends PApplet {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    //    private LiamCam liamCam;
    private QueasyCam queasyCam;

    private GridSpringMassSystem gridSpringMassSystem;

    public void settings() {
        size(WIDTH, HEIGHT, P3D);
    }

    public void setup() {
        noStroke();
        surface.setTitle("Processing");
//        liamCam = new LiamCam(this);
        queasyCam = new QueasyCam(this);

        gridSpringMassSystem = new GridSpringMassSystem(this, 30, 30, 10, 5, 500, 1000f);
    }

    public void draw() {
//        liamCam.Update(1.0f / frameRate);

        long start = millis();
        try {
            for (int i = 0; i < 140; ++i) {
                gridSpringMassSystem.update(0.002f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long update = millis();

        background(0);
        gridSpringMassSystem.draw();
        long draw = millis();
        surface.setTitle("Processing - FPS: " + frameRate + " Update: " + (update - start) + "ms Draw " + (draw - update) + "ms");
    }

    public void keyPressed() {
//        liamCam.HandleKeyPressed();
    }

    public void keyReleased() {
//        liamCam.HandleKeyReleased();
    }

    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"Main"};
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }
}
