package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerShip {
    private int shipTexture;
    private float x;
    private float y;
    private float speed;
    private float width;
    private float height;
    CollisionRect rect;


    private Texture texture;

    public PlayerShip(int texturenr) {
        this.x = Gdx.graphics.getWidth() / 2;
        this.y = 0;
        this.width = 16 * 2;
        this.height = 24 * 2;
        this.speed = 200;
        this.shipTexture = texturenr;
        this.texture = getTexture();
    }

    private Texture getTexture() {
        switch(shipTexture) {
            case 1:
                return new Texture("space_shooter_pack/Graphics/spritesheets/ship/tile000.png");
            default:
                return new Texture("space_shooter_pack/Graphics/spritesheets/ship/tile000.png");
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y,width,height);
    }

    public void move (float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            y += Math.sin(Math.PI / 4) * speed * deltaTime;
            x += Math.cos(Math.PI / 4) * speed * deltaTime;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            y += Math.sin((Math.PI * 3) / 4) * speed * deltaTime;
            x += Math.cos((Math.PI * 3) / 4) * speed * deltaTime;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            y += Math.sin((Math.PI * 7) / 4) * speed * deltaTime;
            x += Math.cos((Math.PI * 7)/ 4) * speed * deltaTime;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            y += Math.sin((Math.PI * 5) / 4) * speed * deltaTime;
            x += Math.cos((Math.PI * 5)/ 4) * speed * deltaTime;
        }
        else {
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                y += speed * deltaTime;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                y -= speed * deltaTime;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                x -= speed * deltaTime;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                x += speed * deltaTime;
            }
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
