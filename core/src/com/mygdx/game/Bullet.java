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
    private CollisionRect collider;

    private static Texture texture;

    public boolean remove = false;

    public Bullet(float x, float y) {
        Random rand = new Random();
        this.x = x;
        this.y = y;
        this.speed = 300;
        this.texture = new Texture("space_shooter_pack/Graphics/spritesheets/laser-bolts/tile000.png");
        this.dx = rand.nextFloat() * (float)0.5 - (float)0.5;
        this.collider = new CollisionRect(x,y,10,10);
    }

    public void update(float deltaTime) {
        this.y = this.y + this.speed * deltaTime;
        this.x = this.x + this.dx;
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
