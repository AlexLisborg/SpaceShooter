package com.mygdx.game.canons;

import com.mygdx.game.Bullet;
import com.mygdx.game.Canon;

import java.util.ArrayList;
import java.util.List;

public class DEFAULT implements Canon {

    private float fireSpeed;
    private float bulletSpeed;

    public DEFAULT(float bulletSpeed) {
        this.fireSpeed = 5;
        this.bulletSpeed = bulletSpeed;
    }

    @Override
    public List<Bullet> shoot(float x, float y, float angle) {
        List<Bullet> output = new ArrayList<Bullet>();
        output.add(new Bullet(x,y,angle, bulletSpeed));
        return output;
    }

    @Override
    public float getFireSpeed() {
        return fireSpeed;
    }
    public void setFireSpeed(float fireSpeed) {
        this.fireSpeed = fireSpeed;
    }
}
