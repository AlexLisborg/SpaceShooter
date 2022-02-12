package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MouseHandler {
    private float x;
    private float y;
    private float angle;

    public float getAngleBetweenCoordinatesAndMouse(float x,float y) {
        Vector2 cursor = new Vector2(Gdx.input.getX()  ,Gdx.input.getY() );
        return -1 * (float) (MathUtils.radiansToDegrees * Math.atan2(cursor.y - (Gdx.graphics.getHeight() - y) , cursor.x - x));
        // The angle calculation becomes complicated because of the difference in how the different components work.
    }
}
