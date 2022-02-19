package com.mygdx.game.canons;

import com.mygdx.game.Bullet;
import com.mygdx.game.Canon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SHOTGUN implements Canon {

    private float fireSpeed;
    private float bulletSpeed;

    public SHOTGUN(float bulletSpeed) {
        this.fireSpeed = 50;
        this.bulletSpeed = bulletSpeed;
    }

    @Override
    public List<Bullet> shoot(float x, float y, float angle) {
        Random random = new Random();
        List<Bullet> output = new ArrayList<Bullet>();
        output.add(new Bullet(x,y,angle,bulletSpeed));
        for (int i = 0; i < 10; i++) {
            output.add(new Bullet(x,y + random.nextFloat() * 30,angle+random.nextFloat() * (float)0.35, bulletSpeed));
        }

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
