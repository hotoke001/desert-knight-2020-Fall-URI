package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity implements InputProcessor{

    //Takes tilemap in order to move tilemap camera
    //TileMap tileMap;


    //Constructor
    public Player(Sprite sprite){ this.sprite = sprite; isPlayer = true;}

    //Draws the player using superclass Sprite
    public void draw(SpriteBatch spritebatch){
        update(Gdx.graphics.getDeltaTime());
        sprite.draw(spritebatch);
    }

    //The actual code that allows the player to move by setting x and y position values as a function of the velocity
    public void update(float delta){
        sprite.setX(sprite.getX() + velocity.x * delta);
        sprite.setY(sprite.getY() + velocity.y * delta);
        //clampPosition(getX(), getY(), 300f, 100f, 200f, 75f);
    }

    //Velocity getter
    public Vector2 getVelocity(){
        return velocity;
    }

    //Function that takes player input and sets velocity according to key presses
    @Override
    public boolean keyDown(int keycode){
        switch(keycode) {
            case Keys.W:
                velocity.y = speed;
                break;
            case Keys.A:
                velocity.x = -speed;
                break;
            case Keys.S:
                velocity.y = -speed;
                break;
            case Keys.D:
                velocity.x = speed;
        }
        return true;
    }

    //Same as above, but for when the key is released and thus setting a component of velocity to zero
    @Override
    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Keys.W:
            case Keys.S:
                velocity.y = 0;
                break;
            case Keys.A:
            case Keys.D:
                velocity.x = 0;
        }
        return true;
    }

    //DON'T DELETE BELOW FUNCTIONS - they are necessary in order for InputProcessor to run even if they
    //have no functionality yet, or even if they won't at all. But for now, they don't do anything.

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
