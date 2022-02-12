package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public class Canon {

    private TYPE type;
    private float fireSpeed;

    private enum TYPE {
        DEFAULT, SHOTGUN, DOUBLE
    }

    public Canon(int type) {
        this.fireSpeed = 5;
        switch (type) {
            case 1:
                this.type = TYPE.SHOTGUN;
                break;
            case 2:
                this. type = TYPE.DOUBLE;
                break;
            default: this.type = TYPE.DEFAULT;
        }

    }
    public Canon() {
        this.fireSpeed = 5;
    }
    public Bullet shoot(float x, float y, float angle) {
        return new Bullet(x,y,angle);
    }

    public float getFireSpeed() {
        return fireSpeed;
    }

    public void setFireSpeed(float fireSpeed) {
        this.fireSpeed = fireSpeed;
    }
}
