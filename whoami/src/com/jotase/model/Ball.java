package com.jotase.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.jotase.model.WorldScreen.States;

public class Ball {
	private BodyDef ballBodyDef;
	final float density = 1;
	final float friction = 0.5f;
	final float restitution = 0.5f;
	private TextureRegion FALSE_texture;
	
	public States state;
	
	public States getState() {
		return state;
	}


	public void setState(States state) {
		this.state = state;
	}


	public TextureRegion getFALSE_texture() {
		return FALSE_texture;
	}


	public void setFALSE_texture(TextureRegion fALSE_texture) {
		FALSE_texture = fALSE_texture;
	}

	private TextureRegion IDLE_texture; 
	public TextureRegion getIDLE_texture() {
		return IDLE_texture;
	}


	public void setIDLE_texture(TextureRegion iDLE_texture) {
		IDLE_texture = iDLE_texture;
	}

	private int lifes;
	private float color;
	private boolean correct;
	private TextureRegion texture;
	
	public TextureRegion getTexture() {
		return texture;
	}


	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}

	private Boolean[] sequence;
	
	public Boolean[] getSequence() {
		return sequence;
	}


	public void setSequence(Boolean[] booleans) {
		this.sequence = booleans;
	}
	private boolean WINNER;
	

	public boolean isWINNER() {
		return WINNER;
	}

	public void setWINNER(boolean wINNER) {
		WINNER = wINNER;
	}
	private float radius;
	private CircleShape shape;
	private FixtureDef fd;
	private Vector2 position = new Vector2();
	private Vector2 acceleration = new Vector2();
	private Vector2 velocity = new Vector2();
	public enum states {
		LEFT, RIGHT, UP, DOWN
	}
	
	public int getLifes() {
		return lifes;
	}


	public void setLifes(int lifes) {
		this.lifes = lifes;
	}


	public float getColor() {
		return color;
	}


	public void setColor(float color) {
		this.color = color;
	}


	public boolean isCorrect() {
		return correct;
	}


	public void setCorrect(boolean correct) {
		this.correct = correct;
	}


	public float getRadius() {
		return radius;
	}


	public void setRadius(float radius) {
		this.radius = radius;
	}


	public Vector2 getAcceleration() {
		return acceleration;
	}


	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}


	public Vector2 getVelocity() {
		return velocity;
	}


	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}


	public BodyDef getBallBodyDef() {
		return ballBodyDef;
	}


	public void setBallBodyDef(BodyDef ballBodyDef) {
		this.ballBodyDef = ballBodyDef;
	}


	public CircleShape getShape() {
		return shape;
	}


	public void setShape(CircleShape shape) {
		this.shape = shape;
	}


	public FixtureDef getFd() {
		return fd;
	}


	public void setFd(FixtureDef fd) {
		this.fd = fd;
	}


	public Vector2 getPosition() {
		return position;
	}


	public void setPosition(Vector2 position) {
		this.position = position;
	}


	public float getDensity() {
		return density;
	}


	public float getFriction() {
		return friction;
	}


	public float getRestitution() {
		return restitution;
	}


	public Ball() {
		super();
		this.ballBodyDef = new BodyDef();
		this.shape = new CircleShape();
		this.fd = new FixtureDef();
		this.position = new Vector2(5,5);
		setState(States.Gaming);
		
		
		setSettings();
	}


	private void setSettings() {
        fd.density = 1;
        fd.friction = 0.5f;
        fd.restitution = 0.5f;
        fd.shape = shape;
		
	}

}
