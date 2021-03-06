package demos;

import camera.QueasyCam;
import math.Vec3;
import physical.Air;
import physical.Ball;
import physical.GridThreadPointMassSystem;
import processing.core.PApplet;

public class ClothIntegratorComparision extends PApplet {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private QueasyCam queasyCam;

    private GridThreadPointMassSystem gridThreadPointMassSystem;
    private Ball ball;
    private boolean secondOrder = false;

    public void settings() {
        size(WIDTH, HEIGHT, P3D);
    }

    public void setup() {
        surface.setTitle("Processing");
        queasyCam = new QueasyCam(this);
        queasyCam.sensitivity = 2f;
        resetSystem();
    }

    private void resetSystem() {
        gridThreadPointMassSystem = new GridThreadPointMassSystem(
                this,
                30, 30,
                30,
                5, 300, 900f, loadImage("aladdin-s-carpet.jpeg"),
                1f, -20, -40f, -30f,
                ((i, j, m, n) -> (j == 0 && (i % 3 == 0 || i == m - 1))),
                GridThreadPointMassSystem.Layout.ZX);

        gridThreadPointMassSystem.air = new Air(0.06f, 0f, Vec3.of(0, 0, 1), 0);
        ball = new Ball(this, 1, 40, Vec3.of(50, 90, 0), Vec3.of(255, 255, 0), true);
    }

    public void draw() {
        long start = millis();
        // update
        try {
            float dt = 0.012f;
            if (secondOrder) {
                for (int i = 0; i < 90; ++i) {
                    gridThreadPointMassSystem.updateSecondOrder(ball, dt);
                }
            } else {
                for (int i = 0; i < 90; ++i) {
                    gridThreadPointMassSystem.update(ball, dt);
                }
            }
            ball.update(dt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long update = millis();
        // draw
        background(0);
        gridThreadPointMassSystem.draw();
        ball.draw();
        long draw = millis();

        surface.setTitle("Processing - FPS: " + Math.round(frameRate) + " Update: " + (update - start) + "ms Draw " + (draw - update) + "ms" + " wind : " + gridThreadPointMassSystem.air.windSpeed + " integrator: " + (secondOrder ? "second-order" : "first-order"));
    }

    public void keyPressed() {
        if (key == '=') {
            gridThreadPointMassSystem.air.increaseSpeed(1f);
        }
        if (key == '-') {
            gridThreadPointMassSystem.air.decreaseSpeed(1f);
        }
        if (key == 'r') {
            resetSystem();
        }
        if (key == '2') {
            secondOrder = !secondOrder;
        }
    }

    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"demos.ClothIntegratorComparision"};
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }
}