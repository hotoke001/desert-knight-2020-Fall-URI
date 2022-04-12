package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Entity{

    private Player player;
    float timePassed;
    Animation<TextureRegion>[] runningAnimations;

    //changeDirectionTimer dictates how frequently the enemies check the player to find them (in frames)
    //timer is the increment counter for changeDirectionTimer
    private int changeDirectionTimer;
    private int timer = 0;

    //Constructor
    public Enemy(Sprite sprite, float speed, int timer, Player player){
        this.sprite = sprite;
        this.changeDirectionTimer = timer;
        this.speed = speed;
        //Enemy constructor takes a Player parameter to know where the player is
        this.player = player;
        //This unfortunately giant block of code is creating an animation for each direction the skeleton runs
        //Hopefully there's a way to take all the files in a certain directory and make them into their own array of animations
        runningAnimations = new Animation[]{
                createSpritesheetAnimation(new Texture("SwordSkeletonSRunning.png"), 10, 4, 3, false),
                createSpritesheetAnimation(new Texture("SwordSkeletonUDRunning.png"), 10, 4, 3, false),
                createSpritesheetAnimation(new Texture("SwordSkeletonURunning.png"), 10, 4, 3, false),
                createSpritesheetAnimation(new Texture("SwordSkeletonUDRunning.png"), 10, 4, 3, true),
                createSpritesheetAnimation(new Texture("SwordSkeletonSRunning.png"), 10, 4, 3, true),
                createSpritesheetAnimation(new Texture("SwordSkeletonDDRunning.png"), 10, 4, 3, true),
                createSpritesheetAnimation(new Texture("SwordSkeletonDRunning.png"), 10, 4, 3, false),
                createSpritesheetAnimation(new Texture("SwordSkeletonDDRunning.png"), 10, 4, 3, false),
        };
    }

    //Creates the animation frames from spritesheet and saves it as an animation object
    //It takes the spritesheet, number of frames (not including blank ones), number of rows and columns in the spritesheet, and
    //if the animations should be flipped over the y axis then that is a parameter as well (used above for 3 of the 8 directions)
    private Animation createSpritesheetAnimation(Texture image, int numFrames, int numCol, int numRow, boolean reversedX){
        Animation<TextureRegion> newAnimation;
        //This line collects the frames from the sprites and stores them as an array
        TextureRegion[][] tempFrames = TextureRegion.split(image, 64, 64);
        TextureRegion[] animationFrames = new TextureRegion[numFrames];
        int index = 0;

        //Loops assign the gathered frames into animationFrames (which is a single array of TextureRegions instead of 2D array)
        for(int row=0; row<numRow; row++){
            for(int col=0; col<numCol; col++){
                tempFrames[row][col].flip(reversedX, false);
                animationFrames[index] = tempFrames[row][col];
                index++;
                if(index >= numFrames){break;}
            }
        }
        //Finally, newAnimation is created and returned by accepting the array the frames and setting the framerate to 12fps
        newAnimation = new Animation(1f / 12f, animationFrames);
        return newAnimation;
    }

    //Draws the enemy using superclass Sprite
    public void draw(SpriteBatch spritebatch){
        //timePassed variable keeps track of which frame of an animation to draw
        timePassed += Gdx.graphics.getDeltaTime();
        update(Gdx.graphics.getDeltaTime());
        //The line below transforms the individual animation frames into sprites
        int animationNum = checkDirection();
        sprite.setRegion(runningAnimations[animationNum].getKeyFrame(timePassed, true));
        sprite.draw(spritebatch);
    }

    //Similar to Player without the inputProcessor
    public void update(float delta){
        setSpeed();
        sprite.setX(sprite.getX() + velocity.x * delta);
        sprite.setY(sprite.getY() + velocity.y * delta);
    }

    //CheckDirection returns an integer 0 through 7, representing the facing direction of the entity in question
    //A return value of 0 means the target is facing right + or - 22.5 degrees, 1 is facing up-right + or - 22.5 degrees, continuing counterclockwise.
    private int checkDirection(){
        float angle = velocity.angle();
        int direction = (int) (angle+22.5f)/45;
        return direction%8;
    }
    //Only updates direction of enemy movement on every Nth frame, where N is changeDirectionTimer
    //This is to make some enemies less reactive than others
    private void setSpeed(){
        timer += 1;
        if(timer >= changeDirectionTimer){
            timer = 0;
            followPlayer();
        }
    }
    //Uses some trigonometry to decide which x and y values should be used to go in the direction of the player
    //while maintaining a constant speed
    private void followPlayer(){

        float disX =  this.sprite.getX() - player.sprite.getX();
        float disY = this.sprite.getY() - player.sprite.getY();
        float ratio = disY / disX;
        double newX = Math.sqrt((speed*speed)/((ratio*ratio) + 1));
        double newY = Math.sqrt((speed*speed) - (newX*newX));

        if(player.sprite.getX() < this.sprite.getX()){ newX *= -1; }
        if(player.sprite.getY() > this.sprite.getY()){ newY *= -1; }

        //These two lines actually set the velocity of the enemy using the math above
        velocity.x = (float)newX;
        velocity.y = -1 * (float)newY;

    }

}
