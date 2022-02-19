package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public interface Canon {

    List<Bullet> shoot(float x, float y, float angle);

    float getFireSpeed();

    void setFireSpeed(float f);
}
