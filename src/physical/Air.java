package physical;

import math.Vec3;

public class Air {
    float frictionCoefficient;
    float dragCoefficient;
    Vec3 windDirection;
    public float windSpeed;

    public Air(float dragCoefficient, float frictionCoefficient, Vec3 windDirection, float windSpeed) {
        this.frictionCoefficient = frictionCoefficient;
        this.dragCoefficient = dragCoefficient;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }
    
    public Air() {
        this.frictionCoefficient = 0.042f;
        this.dragCoefficient = 0.03f;
        this.windDirection = Vec3.of(0,0,1);
        this.windSpeed = 0;
    }

    public void increaseSpeed(float dv) {
        this.windSpeed += dv;
    }

    public void decreaseSpeed(float dv) {
        this.windSpeed -= dv;
    }
}
