package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Speed extends Entity {

    private float speedBoost = 2;

    public Speed(Sprite sprite) {
        this.sprite = sprite;
    }

    public void draw(SpriteBatch spritebatch){
        update(Gdx.graphics.getDeltaTime());
        sprite.draw(spritebatch);
    }

    public void update(float delta){
        sprite.setX(sprite.getX());
        sprite.setY(sprite.getY());
    }

    public void setDamageBoost(){

    }
}
