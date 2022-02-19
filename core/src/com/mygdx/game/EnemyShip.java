package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.canons.DEFAULT;
import com.mygdx.game.canons.DOUBLE;
import com.mygdx.game.canons.SHOTGUN;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyShip implements IPositionable {
    private float x;
    private float angle;
    private float y;
    private float width;
    private float height;

    private CollisionRect collider;
    private int health;

    public boolean remove = false;

    private TextureRegion texture;

    private Canon canon;

    private float countTime;

    Random random = new Random();

    public EnemyShip() {
        this.angle = random.nextFloat() * 360;
        this.x = ((float)Math.sin(angle)+1) * Gdx.graphics.getWidth();
        this.y = Gdx.graphics.getHeight();
        this.width = 16 * 2;
        this.height = 24 * 2;
        this.texture = new TextureRegion(new Texture("space_shooter_pack/Graphics/spritesheets/ship/tile000.png"));
        int randint = random.nextInt(3);

        if(randint == 0) {
            this.canon = new DEFAULT(20);
        }
        else if (randint == 1) {
            this.canon = new DOUBLE(20);
        }
        else if (randint == 2) {
            this.canon = new SHOTGUN(20);
        }
        else {
            this.canon = new DEFAULT(20);
        }

        this.collider = new CollisionRect(this.x,this.y,this.width,this.height);
        this.countTime = 0;
        this.health = 10;
    }


    public void render(SpriteBatch batch) {
        batch.draw(texture, this.x, this.y, width/2, width/2,width,height,1,1, 180);
    }

    public void update(float deltaTime) {
        this.y = this.y - 30 * deltaTime;
        this.x = ((float)Math.sin(angle)+1)  * ((Gdx.graphics.getWidth() - this.width) / 2) ;
        angle +=0.02 * deltaTime * 50;
        if (this.y < 0 || this.health <= 0){
            this.remove = true;
        }
        this.collider.move(this.x,this.y);
        this.countTime ++;
    }
    public List<Bullet> shoot(){
        return canon.shoot(this.x + width/ 2,this.y - height / 2,(float)-Math.PI / 2);
    }

    public void damage() {
        this.health --;

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

    public TextureRegion getTexture() {
        return texture;
    }

    public CollisionRect getCollider() {
        return collider;
    }

    public float getCountTime() {
        return countTime;
    }

    public void setCountTime(float countTime) {
        this.countTime = countTime;
    }
}
