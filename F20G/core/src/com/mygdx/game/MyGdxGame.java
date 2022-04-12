package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MyGdxGame extends ApplicationAdapter {
	//batch and player object created
	SpriteBatch batch;
	private Player player;
	private Enemy enemy1;
	private Enemy enemy2;

	// Power-ups
	private Heart heart;
	private Damage damage;
	private Speed speed;

	//Creates tilemap of desert and a camera to follow the player (cameras of tilemap and in this main file are not the same)
	TileMap tilemap;
	private OrthographicCamera camera;

	@Override
	public void create () {
		//Creates Spritebatch for rendering, one player object, and two enemy objects with different parameters
		//This also sets their position
		batch = new SpriteBatch();
		player = new Player(new Sprite(new Texture("playerSpriteFront.png")));
		enemy1 = new Enemy(new Sprite(new Texture("EnemySkeleton.png")), 50, 70, player);
		enemy2 = new Enemy(new Sprite(new Texture("EnemySkeleton.png")), 120, 20, player);

		// Power-ups
		heart = new Heart(new Sprite(new Texture("heart.png")));
		damage = new Damage(new Sprite(new Texture("damage.png")));
		speed = new Speed(new Sprite(new Texture("speed.png")));

		player.sprite.setPosition(300,200);
		enemy1.sprite.setPosition(400, 300);
		enemy2.sprite.setPosition(100, 450);

		//Power-ups (positioned near each other for now)
		heart.sprite.setPosition(500,500);
		damage.sprite.setPosition(500,400);
		speed.sprite.setPosition(500,300);

		//Creates tilemap and camera using basic constructors
		tilemap = new TileMap();
		camera = new OrthographicCamera();
		camera.setToOrtho(false);

		//Sets camera position
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		//Input only works on one class at a time, so the player class derives the controls
		Gdx.input.setInputProcessor(player);
	}

	@Override
	public void render () {
		//calculates intended position of camera and updates camera position
		cameraFollowPlayer(camera, player, 100f, 75f);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		//Draws the tiles for the map
		tilemap.draw();
		//Draws the enemy sprites and player sprite
		//NOTE: in order for these entities to be drawn in a way where they overlap realistically (foreground above background)
		    //another function must be made to calculate the render order using the entity's y position
		batch.begin();

		player.draw(batch);
		if(enemy1.isAlive()==true)
			enemy1.draw(batch);
		if(enemy2.isAlive()==true)
			enemy2.draw(batch);

		// Power-ups
		heart.draw(batch);
		damage.draw(batch);
		speed.draw(batch);

		batch.end();
	}

	//moves camera based on player position
	public void cameraFollowPlayer(OrthographicCamera cam, Player player, float paddingX, float paddingY){
		float goingX = Math.signum(player.getVelocity().x);
		float goingY = Math.signum(player.getVelocity().y);

		if((player.sprite.getX() > cam.position.x + paddingX && goingX == 1f) || (player.sprite.getX() < cam.position.x - paddingX && goingX == -1f)){
			cam.translate(new Vector2(player.getVelocity().x/60f, 0f));
		}
		if((player.sprite.getY() > cam.position.y + paddingY && goingY == 1f) || (player.sprite.getY() < cam.position.y - paddingY && goingY == -1f)){
			cam.translate(new Vector2(0f, player.getVelocity().y/60f));
		}
		//call to function that moves the separate tilemap camera, as this camera (from this main class) is different from the tilemap camera
		tilemap.moveCamera(camera);
	}

	@Override
	public void dispose () {
		batch.dispose();
		player.sprite.getTexture().dispose();
		enemy1.sprite.getTexture().dispose();
		enemy2.sprite.getTexture().dispose();

		// Power-ups
		heart.sprite.getTexture().dispose();
		damage.sprite.getTexture().dispose();
		speed.sprite.getTexture().dispose();
	}
}
