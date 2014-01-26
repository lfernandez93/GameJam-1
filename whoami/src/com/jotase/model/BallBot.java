package com.jotase.model;

import sun.management.Sensor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class BallBot {

	private int sequence;
	private float color;
	private Vector2 position = new Vector2();
	private boolean correct;
	private float Radius;
	private TextureRegion texture;
	private BodyDef ballBodyDef;
	private boolean facingLeft;
	private boolean goingUp;
	private boolean fireball;
	private int velocity;
	
	
	
	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public boolean isFireball() {
		return fireball;
	}

	public void setFireball(boolean fireball) {
		this.fireball = fireball;
	}

	public boolean isGoingUp() {
		return goingUp;
	}

	public void setGoingUp(boolean goingUp) {
		this.goingUp = goingUp;
	}

	public boolean isFacingLeft() {
		return facingLeft;
	}

	public void setFacingLeft(boolean facingLeft) {
		this.facingLeft = facingLeft;
	}

	public BodyDef getBallBodyDef() {
		return ballBodyDef;
	}
	private Boolean[] hints = new Boolean[3];

	public Boolean[] getHints() {
		return hints;
	}

	public void setHints(Boolean[] hints) {
		this.hints = hints;
	}

	public void setBallBodyDef(BodyDef ballBodyDef) {
		this.ballBodyDef = ballBodyDef;
	}
	final float density = 1;
	final float friction = 0.5f;
	final float restitution = 0.5f;
	private CircleShape shape;
	private FixtureDef fd;
	private Body body;
	
   
	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public BallBot() {
		super();
		this.ballBodyDef = new BodyDef();
		this.ballBodyDef.type=BodyType.DynamicBody;
		this.shape = new CircleShape();
		this.fd = new FixtureDef();
		this.position = new Vector2(5,5);
		
		setSettings();
	}

	private void setSettings() {
        fd.density = 1;
        fd.friction = 0.5f;
        fd.restitution = 0.5f;
        fd.shape = shape;
		move();
	}

	private void move() {
		
		
	}

	public enum Hint {
		zero(0), one(1), two(2), three(3), four(4), five(5), six(6), seven(7);
		int n;

		Hint(int n) {
			this.n = n;
		}		
			
	}

	public Boolean[] getSequence(int n) {
		Boolean[] hints = new Boolean[3];
		switch (n) {
		case 0:
			hints[0] = false;
			hints[1] = false;
			hints[2] = false;
			return hints;
		case 1:
			hints[0] = false;
			hints[1] = false;
			hints[2] = true;
			return hints;
		case 2:
			hints[0] = false;
			hints[1] = true;
			hints[2] = false;
			return hints;
		case 3:
			hints[0] = false;
			hints[1] = true;
			hints[2] = true;
			return hints;
		case 4:
			hints[0] = true;
			hints[1] = false;
			hints[2] = false;
			return hints;
		case 5:
			hints[0] = true;
			hints[1] = false;
			hints[2] = true;
			return hints;
		case 6:
			hints[0] = true;
			hints[1] = true;
			hints[2] = false;
			return hints;
		case 7:
			hints[0] = true;
			hints[1] = true;
			hints[2] = true;
			return hints;
		}
		return hints;
	}
	private Hint hint;
	

	public Hint getHint() {
		return hint;
	}

	public void setHint(Hint hint) {
		this.hint = hint;
	}

	public TextureRegion getTexture() {

		return texture;
	}

	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}

	public float getRadius() {
		return Radius;
	}

	public void setRadius(float radius) {
		Radius = radius;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public float getColor() {
		return color;
	}

	public void setColor(float color) {
		this.color = color;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

}
