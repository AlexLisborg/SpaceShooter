package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.*;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen implements Screen {

    private BitmapFont font = new BitmapFont();

    SpriteBatch batch;

    public GameScreen(){

        this.batch = new SpriteBatch();
    }

    // Textures:
    Texture img;
    Texture background;
    Texture backgroundColor;
    Texture starsBig;
    Texture starsSmall;
    Texture asteroid;

    // Floats:
    private float deltaTime = 0;

    private float enemySpawnFrequency = (float)0.995;

    private int points = 0;

    // Random
    Random rand = new Random();

    // Settings:
    private int gameWidth;
    private int gameHeight;


    private float countTime;

    // Scrolling Y values
    private float scrollingBackground = 0;
    private float scrollingBackgroundParallel = 1000;
    private float scrollingStarsSmall = 0;
    private float scrollingStarsSmallParallel = 1000;

    // Bullets
    ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    ArrayList<Asteroid> asteroidList = new ArrayList<>();
    ArrayList<EnemyShip> enemyList = new ArrayList<>();
    ArrayList<Bullet> enemyBulletList = new ArrayList<>();

    // Ship
    PlayerShip ship = new PlayerShip(1);

    @Override
    public void show() {
        img = new Texture("space_shooter_pack/Graphics/spritesheets/ship/tile000.png");
        background = new Texture("textures/background.png");
        backgroundColor = new Texture("textures/backgroundColor.png");
        starsBig = new Texture("textures/starsbig.png");
        starsSmall = new Texture("textures/starssmall.png");
        asteroid = new Texture("textures/asteroid.png");

    }

    @Override
    public void render(float delta) {

        deltaTime = Gdx.graphics.getDeltaTime();
        ScreenUtils.clear(1, 0, 0, 1);
        // Move
        ship.move(deltaTime);

        // Bullets
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && countTime >= ship.getCanon().getFireSpeed() * deltaTime * 20) {
            countTime = 0;
            shoot();
        }
        countTime++;

        // Bullets
        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
        for(Bullet bullet : bulletList) {
            bullet.update(delta);
            if(bullet.remove) {
                bulletsToRemove.add(bullet);
            }
        }

        // Asteroids
        if (rand.nextFloat() > 0.985) {
            asteroidList.add(new Asteroid());
        }
        ArrayList<Asteroid> asteroidsToRemove = new ArrayList<Asteroid>();
        for (Asteroid asteroid : asteroidList) {
            asteroid.update(delta);
            if(asteroid.remove) {
                asteroidsToRemove.add(asteroid);
            }
        }

        // Enemies

        ArrayList<Bullet> enemyBulletsToRemove = new ArrayList<>();
        if (rand.nextFloat() > enemySpawnFrequency) {
            enemyList.add(new EnemyShip());
        }
        ArrayList<EnemyShip> enemiesToRemove = new ArrayList<>();
        for (EnemyShip enemy : enemyList) {
            enemy.update(delta);
            if(enemy.getCountTime() >= 25 * deltaTime * 40) {
                enemyBulletList.addAll(enemy.shoot());
                enemy.setCountTime(0);
            }
            enemy.setCountTime(enemy.getCountTime()+1);
            if(enemy.remove) {
                enemiesToRemove.add(enemy);
            }
        }
        for(Bullet bullet : enemyBulletList) {
            bullet.update(delta);
            if (bullet.remove) {
                bulletsToRemove.add(bullet);
            }
        }



        // Background scrolling
        scrollingBackgroundMethod(2);
        scrollingStarsSmallMethod(1);

        // Batch

        batch.begin();
        batch.draw(backgroundColor,0,0);
        batch.draw(starsSmall, 0, scrollingStarsSmall);
        batch.draw(starsSmall, 0, scrollingStarsSmallParallel);
        batch.draw(background,0, scrollingBackground);
        batch.draw(background,0, scrollingBackgroundParallel);
        font.draw(batch, String.valueOf(points),30,30);
        font.draw(batch, "HP",Gdx.graphics.getWidth() - 30,50);
        font.draw(batch, String.valueOf(ship.getHealth()),Gdx.graphics.getWidth() - 30,30);
        for (Bullet bullet : bulletList) {
            bullet.render(batch);
            for (Asteroid asteroid: asteroidList) {
                if(bullet.getCollider().collidesWith(asteroid.getCollider())){
                    bulletsToRemove.add(bullet);
                    asteroid.damage();
                }
            }
            for (EnemyShip enemy : enemyList) {
                if (bullet.getCollider().collidesWith(enemy.getCollider())) {
                    bulletsToRemove.add(bullet);
                    enemy.damage();
                }
            }
        }
        for (Bullet bullet : enemyBulletList) {
            bullet.render(batch);
            if(bullet.getCollider().collidesWith(ship.getCollider())){
                enemyBulletsToRemove.add(bullet);
                ship.damage();
            }
        }
        for (Asteroid asteroid: asteroidList) {
            if(ship.getCollider().collidesWith(asteroid.getCollider())){
                ship.damage();
            }
        }

        if (enemiesToRemove.size() > 0) {
            ship.getCanon().setFireSpeed(ship.getCanon().getFireSpeed() - (float)0.1);
            ship.setHealth(ship.getHealth() + 1);
            enemySpawnFrequency -= 0.0005;
            points +=10;
        }
        if (asteroidsToRemove.size() > 0) {
            ship.getCanon().setFireSpeed(ship.getCanon().getFireSpeed() - (float)0.05);
            points +=5;
            ship.setHealth(ship.getHealth() + 1);
        }
        bulletList.removeAll(bulletsToRemove);
        asteroidList.removeAll(asteroidsToRemove);
        enemyList.removeAll(enemiesToRemove);
        enemyBulletList.removeAll(enemyBulletsToRemove);
        if (ship.getHealth() <= 0) {
            System.exit(0);
        }

        for (Asteroid asteroid : asteroidList) {
            asteroid.render(batch);
        }
        for (EnemyShip enemy : enemyList) {
            enemy.render(batch);
        }
        ship.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    // GAME METHODS


    private void shoot() {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            float angle = ship.getMouse().getAngleBetweenCoordinatesAndMouse(ship.getX(),ship.getY());
            bulletList.addAll(ship.getCanon().shoot(
                    ship.getX() - ship.getTexture().getRegionWidth() / 2,
                    ship.getY() - ship.getTexture().getRegionHeight() / 2,
                    MathUtils.degreesToRadians * angle));
        }
    }

    private void scrollingBackgroundMethod(float speed) {
        if (this.scrollingBackground == -1000) {
            this.scrollingBackground = 0;
        }
        this.scrollingBackground = this.scrollingBackground - speed;
        if (this.scrollingBackgroundParallel == 0) {
            this.scrollingBackgroundParallel = 1000;
        }
        this.scrollingBackgroundParallel = this.scrollingBackgroundParallel - speed;

    }
    private void scrollingStarsSmallMethod(float speed) {
        if (this.scrollingStarsSmall == -1000) {
            this.scrollingStarsSmall = 0;
        }
        this.scrollingStarsSmall = this.scrollingStarsSmall - speed;
        if (this.scrollingStarsSmallParallel == 0) {
            this.scrollingStarsSmallParallel = 1000;
        }
        this.scrollingStarsSmallParallel = this.scrollingStarsSmallParallel - speed;

    }
}
