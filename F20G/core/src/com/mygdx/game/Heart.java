package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Heart extends Entity {

    private int heartBoost = 25;

    public Heart(Sprite sprite){ this.sprite = sprite; }

    public void draw(SpriteBatch spritebatch){
        update(Gdx.graphics.getDeltaTime());
        sprite.draw(spritebatch);
    }

    public void update(float delta){
        sprite.setX(sprite.getX());
        sprite.setY(sprite.getY());
    }

    public void setHeartBoost(){

    }



}
