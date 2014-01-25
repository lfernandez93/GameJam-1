package com.wisecounsil.whoami.model;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

abstract class AbstractGameObject {
	private Vector2 velocity;
	private Vector2 terminalVelocity;
	private Vector2 acceleration;
	private Rectangle bounds;
    private Vector2 position;
    private Vector2 friction;
    private Vector2 dimension;
    private Vector2 origin;
    private Vector2 scale;
    private int rotation;
    private Body body;
    
    
	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Vector2 getTerminalVelocity() {
		return terminalVelocity;
	}

	public void setTerminalVelocity(Vector2 terminalVelocity) {
		this.terminalVelocity = terminalVelocity;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getFriction() {
		return friction;
	}

	public void setFriction(Vector2 friction) {
		this.friction = friction;
	}

	public Vector2 getDimension() {
		return dimension;
	}

	public void setDimension(Vector2 dimension) {
		this.dimension = dimension;
	}

	public Vector2 getOrigin() {
		return origin;
	}

	public void setOrigin(Vector2 origin) {
		this.origin = origin;
	}

	public Vector2 getScale() {
		return scale;
	}

	public void setScale(Vector2 scale) {
		this.scale = scale;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public AbstractGameObject(){
		position = new Vector2();
		dimension = new Vector2(1,1);
		origin = new Vector2();
		scale = new Vector2(1,1);
		rotation = 0;
		acceleration = new Vector2();
		bounds = new Rectangle();
	}
	
	protected void updateMotionX(float deltaTime){
		if(velocity.x != 0){
			if(velocity.x>0){
			velocity.x = Math.max(velocity.x - friction.x * deltaTime, 0);
			}else{
				Math.min(velocity.x - friction.x * deltaTime, 0);
			}
		}
		//aplicar aceleracion
		velocity.x+= acceleration.x * deltaTime;
		//asegurarse de que la seguridad del objeto
		//no exceda los valores de la velocidad final.
		velocity.x = MathUtils.clamp(velocity.x, -terminalVelocity.x, terminalVelocity.x);		
	}
	
	protected void updateMotionY (float deltaTime){
		if(velocity.y!=0){
			//aplicar friccion
			if(velocity.y > 0){
				velocity.y = Math.max(velocity.y - friction.y * deltaTime, 0);
			}else{
				velocity.y = Math.min(velocity.y + friction.y * deltaTime, 0);
			}
			
			//aplicar aceleracion
			velocity.y+= acceleration.y * deltaTime;
			//asegurarse que la velocidad no exceda las velocidad final
			velocity.y = MathUtils.clamp(velocity.y, -terminalVelocity.y, terminalVelocity.y);
		}
	}
	
	public void update (float deltaTime) {
		if(body==null){
		updateMotionX(deltaTime);
		updateMotionY(deltaTime);
		// Move to new position
		position.x += velocity.x * deltaTime;
		position.y += velocity.y * deltaTime;
		}else{
			position.set(body.getPosition());
			rotation = (int) (body.getAngle() * MathUtils.radiansToDegrees);
		}
		}
}
