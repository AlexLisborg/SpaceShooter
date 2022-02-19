package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.canons.*;

public class PlayerShip implements IPositionable {
    private int shipTexture;
    private float x;
    private float y;
    private float speed;
    private float width;
    private float height;


    private TextureRegion texture;
    private int health;

    private MouseHandler mouse;

    private Canon canon;

    private CollisionRect collider;

    public PlayerShip(int texturenr) {
        this.x = Gdx.graphics.getWidth() / 2;
        this.y = 50;
        this.width = 16 * 2;
        this.height = 24 * 2;
        this.speed = 200;
        this.shipTexture = texturenr;
        this.texture = new TextureRegion(shipTextureToTexture(texturenr));
        this.mouse = new MouseHandler();
        this.canon = new DOUBLE(30);
        this.health = 20;
        this.collider = new CollisionRect(x - width / 2 , y - height / 2 , width,height);
    }

    private Texture shipTextureToTexture(int shipTexture) {
        switch(shipTexture) {
            case 1:
                return new Texture("space_shooter_pack/Graphics/spritesheets/ship/tile000.png");
            default:
                return new Texture("space_shooter_pack/Graphics/spritesheets/ship/tile000.png");
        }
    }


    public void render(SpriteBatch batch) {
        batch.draw(texture, x- width / 2, y - width / 2, width/2, width/2,width,height,1,1, mouse.getAngleBetweenCoordinatesAndMouse(x,y) + 270);

    }

    public void move (float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.D)) {
            y += Math.sin(Math.PI / 4) * speed * deltaTime;
            x += Math.cos(Math.PI / 4) * speed * deltaTime;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.A)) {
            y += Math.sin((Math.PI * 3) / 4) * speed * deltaTime;
            x += Math.cos((Math.PI * 3) / 4) * speed * deltaTime;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.D)) {
            y += Math.sin((Math.PI * 7) / 4) * speed * deltaTime;
            x += Math.cos((Math.PI * 7)/ 4) * speed * deltaTime;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.A)) {
            y += Math.sin((Math.PI * 5) / 4) * speed * deltaTime;
            x += Math.cos((Math.PI * 5)/ 4) * speed * deltaTime;
        }
        else {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                y += speed * deltaTime;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                y -= speed * deltaTime;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                x -= speed * deltaTime;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                x += speed * deltaTime;
            }
        }
        if(x > Gdx.graphics.getWidth() - width / 2) {
            x = Gdx.graphics.getWidth() - width / 2;
        }
        if(x < 0 + width / 2) {
            x = 0 + width / 2;
        }
        if(y > Gdx.graphics.getHeight() - height / 2) {
            y = Gdx.graphics.getHeight() - height / 2;
        }
        if(y < 0 + height / 2) {
            y = 0 + height / 2;
        }
        this.collider.move(this.x - width / 2,this.y - height / 2);
    }

    public void damage() {
        this.health -= 1;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Canon getCanon() {
        return canon;
    }

    public MouseHandler getMouse() {
        return mouse;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public CollisionRect getCollider() {
        return collider;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
