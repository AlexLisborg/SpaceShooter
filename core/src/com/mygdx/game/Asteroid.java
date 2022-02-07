package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Asteroid {
    private float x;
    private float y;
    private float dx;
    private CollisionRect collider;
    private int health;

    public boolean remove = false;

    private static Texture texture;

    public Asteroid () {
        Random rand = new Random();
        this.dx = rand.nextFloat();
        this.x = 50 + rand.nextFloat() * Gdx.graphics.getWidth() - 100;
        this.y = Gdx.graphics.getHeight();
        this.texture = new Texture("textures/asteroid.png");
        this.collider = new CollisionRect(this.y,this.x,50, 50);
        this.health = 5;
    }

    public void update(float deltaTime) {
        this.y = this.y - 100 * deltaTime;
        this.x = this.x + this.dx * 10 * deltaTime;
        if (this.y < 0 || this.health <= 0){
            this.remove = true;
        }
        this.collider.move(this.x,this.y);
    }
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, 50, 50);
    }

    public void damage() {
        this.health -= 1;
    }

    public CollisionRect getCollider() {
        return collider;
    }

    public static Texture getTexture() {
        return texture;
    }
}
