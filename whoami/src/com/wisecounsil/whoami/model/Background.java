package com.wisecounsil.whoami.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.wisecounsil.whoami.utils.Assets;

public class Background implements Entity {

	private TextureRegion back;
	
	public Background(){
		init();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		back=Assets.instance.menuBackground.back;
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.begin();
		batch.draw(back.getTexture(), 0,0);
		batch.end();
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer sr) {
		// TODO Auto-generated method stub
		
	}

}
