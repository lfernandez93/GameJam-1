package com.wisecounsil.whoami.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Entity {
	
	void init();
	void render(SpriteBatch batch);
	void update(float delta);
	void dispose();
	void render(SpriteBatch batch, ShapeRenderer sr);
	
}
