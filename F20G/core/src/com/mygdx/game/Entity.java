package com.mygdx.game;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Entity {
    
    // 2D entity velocity as a Vector2
    protected Vector2 velocity = new Vector2();

    // Speed of entity (not sure what the units are, pixels per second would be my guess?)
    protected float speed = 150f;

    // Health of entity
    private int health = 100;

    // Damage the entity can inflict when attacking
    protected int damage = 10;

    // Level of entity (could use this as a multiplier with damage to scale enemies)
    protected int level = 1;

    // Experience of entity (most likely just going to be used for the Player)
    protected int experience;

    public Sprite sprite;
    protected boolean alive = true;



    // Returns the current level of the entity
    private int getLevel() {
        return this.level;
    }

    // Changes the health of the entity by the included offset (+ increases, - decreases)
    protected void setHealth(int offset) {
        this.health += offset;
    }

    // Returns the current health level of the entity
    public int getHealth() {
        return this.health;
    }

    // Increases the incremental experience points of the entity by the included offset
    private void addExperience(int offset) {
        this.experience += offset;
    }

    // Increases the entity overall level by 1
    private void increaseLevel() {
        this.level++;
    }

    // Sets the 'alive' state to false
    protected void killEntity(){
        this.alive = false;
    }

    // Returns whether or not the entity is alive
    protected boolean isAlive(){
        return alive;
    }

}