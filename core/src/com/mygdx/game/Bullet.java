package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Bullet {
    private float speed;
    private float x;
    private float y;
    private float dx;
    private float dy;
    private CollisionRect collider;

    private static Texture texture;

    private MouseHandler mouse;

    public boolean remove = false;

    public Bullet(float x, float y, float angle, float speed) {
        Random rand = new Random();
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texture = new Texture("space_shooter_pack/Graphics/spritesheets/laser-bolts/tile000.png");
        this.dx = (float)Math.cos(angle)  * speed;
        this.dy = (float)Math.sin(angle)  * speed;
        this.collider = new CollisionRect(x,y,10,10);
        this.mouse = new MouseHandler();
    }

    public void update(float deltaTime) {
        this.y = this.y + this.dy * deltaTime * speed;
        this.x = this.x + this.dx * deltaTime * speed;
        if(this.y > Gdx.graphics.getHeight()) {
            this.remove = true;
        }
        this.collider.move(this.x,this.y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public CollisionRect getCollider() {
        return collider;
    }
}
