package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entity;

public class Damage extends Entity {

    private int damageBoost = 25;

    public Damage(Sprite sprite){ this.sprite = sprite; }

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