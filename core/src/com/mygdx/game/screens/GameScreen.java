package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Asteroid;
import com.mygdx.game.Bullet;
import com.mygdx.game.DaGame;
import com.mygdx.game.PlayerShip;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen implements Screen {
    DaGame game;
    public GameScreen(DaGame game){
        this.game = game;
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
    ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();

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
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && countTime >= this.ship.getCanon().getFireSpeed()) {
            countTime = 0;
        }
        if (countTime == 0) {
            shoot();
        }
        countTime++;
        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
        for(Bullet bullet : bulletList) {
            bullet.update(delta);
            if(bullet.remove) {
                bulletsToRemove.add(bullet);
            }
        }

        // Asteroids
        if (rand.nextFloat() > 0.99) {
            asteroidList.add(new Asteroid());
        }
        ArrayList<Asteroid> asteroidsToRemove = new ArrayList<Asteroid>();
        for (Asteroid asteroid : asteroidList) {
            asteroid.update(delta);
            if(asteroid.remove) {
                asteroidsToRemove.add(asteroid);
            }
        }

        // Background scrolling
        scrollingBackgroundMethod(2);
        scrollingStarsSmallMethod(1);

        // Batch

        game.batch.begin();
        game.batch.draw(backgroundColor,0,0);
        game.batch.draw(starsSmall, 0, scrollingStarsSmall);
        game.batch.draw(starsSmall, 0, scrollingStarsSmallParallel);
        game.batch.draw(background,0, scrollingBackground);
        game.batch.draw(background,0, scrollingBackgroundParallel);
        for (Bullet bullet : bulletList) {
            bullet.render(game.batch);
            for (Asteroid asteroid: asteroidList) {
                if(bullet.getCollider().collidesWith(asteroid.getCollider())){
                    bulletsToRemove.add(bullet);
                    asteroid.damage();
                }
            }
        }
        bulletList.removeAll(bulletsToRemove);
        asteroidList.removeAll(asteroidsToRemove);

        for (Asteroid asteroid : asteroidList) {
            asteroid.render(game.batch);
        }
        ship.render(game.batch);
        game.batch.end();
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
        game.batch.dispose();
        img.dispose();
    }

    // GAME METHODS


    private void shoot() {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            float angle = ship.getMouse().getAngleBetweenCoordinatesAndMouse(ship.getX(),ship.getY());
            bulletList.add(ship.getCanon().shoot(
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
