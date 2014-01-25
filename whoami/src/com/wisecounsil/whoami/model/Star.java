
package com.wisecounsil.whoami.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Star implements Entity {

	public Vector2 position;
	public float velocity;
	
	public Star(){
		init();
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		position = new Vector2(MathUtils.random(0, 800),MathUtils.random(0,480));
		velocity = MathUtils.random(0f, 50f);
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer sr) {
		// TODO Auto-generated method stub
		//batch.begin();
		//ShapeRenderer sr=new ShapeRenderer();
		sr.setColor(Color.WHITE);
		//sr.begin(ShapeType.Line);
		sr.circle(position.x, position.y, 1);
		//sr.end();
		//batch.end();
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		if(position.x<=800)
		position.x=position.x+velocity*delta;
		else position.x=0;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

}
