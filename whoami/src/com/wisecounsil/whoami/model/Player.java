package com.wisecounsil.whoami.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Player implements Entity{

	Vector2 position;
	Color color;
	float radius;
	
	public Player(){
		init();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		position=new Vector2(400,200);
		color=Color.RED;
		radius = 15f;
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		position.x=Gdx.input.getX();
		position.y=Gdx.input.getY();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer sr) {
		// TODO Auto-generated method stub
		sr.setColor(color);
		//sr.begin(ShapeType.Line);
		sr.circle(position.x, position.y, radius);
	}

}
