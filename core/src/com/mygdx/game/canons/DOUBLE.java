package com.mygdx.game.canons;

import com.mygdx.game.Bullet;
import com.mygdx.game.Canon;

import java.util.ArrayList;
import java.util.List;

public class DOUBLE implements Canon {
    private float fireSpeed;
    private float bulletSpeed;

    public DOUBLE(float bulletSpeed) {
        this.fireSpeed = 10;
        this.bulletSpeed = bulletSpeed;
    }

    @Override
    public List<Bullet> shoot(float x, float y, float angle) {
        List<Bullet> output = new ArrayList<Bullet>();
        output.add(new Bullet((x + (float)Math.cos(angle) * 25 + (float)Math.sin(angle) * 10) + (float)((-1) * Math.sin(angle)),y + (float)Math.sin(angle) * 25 + (float)Math.cos(angle) * -10,angle, bulletSpeed));
        output.add(new Bullet((x + (float)Math.cos(angle) * 25 - (float)Math.sin(angle) * 10) + (float)((-1) * Math.sin(angle)),y + (float)Math.sin(angle) * 25 - (float)Math.cos(angle) * -10,angle, bulletSpeed));
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
