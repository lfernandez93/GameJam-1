package com.wisecounsil.whoami.model;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Ball implements Entity {

	Color color;
	Vector2 position;
	Vector2 velocity;
	float radius;
	
	public Ball(Color color, Vector2 position, Vector2 velocity){
		init();
		this.color=color;
		this.position=position;
		this.velocity=velocity;
		radius=15f;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		if(position.x<0 || position.x>800){
			velocity.x=velocity.x*-1;
		}
		
		if(position.y<0 || position.y>480){
			velocity.y=velocity.y*-1;
		}
						
		position.x=position.x+velocity.x;
		position.y=position.y+velocity.y;
		
	}
	
	public boolean collidesTo(Ball otherBall){
		float squaredistance = (float) (Math.pow((this.position.x - otherBall.position.x), 2) + Math.pow((this.position.y - otherBall.position.y),2));
		float distance = (float) Math.sqrt(squaredistance);
		if(distance <= radius*2){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void doCollides(List<Entity> listBall){
		listBall.remove(this);
		
		for(Entity b: listBall){
			if(b instanceof Ball){
				Ball c = (Ball) b;
				if(this.collidesTo(c)){
					this.changeDirection();
					c.changeDirection();
				}
			}
			
		}
	}

	private void changeDirection(){
		velocity.x=(velocity.x*-1) * MathUtils.random(-1, 1) ;
		velocity.y=(velocity.y*-1) * MathUtils.random(-1, 1) ;

	}
	
	
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer sr) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				sr.setColor(color);
				//sr.begin(ShapeType.Line);
				sr.circle(position.x, position.y, radius);
	}
	
}
