package com.wisecounsil.whoami.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity {
	
	void init();
	void render(SpriteBatch batch);
	void update(float delta);
	void dispose();
	
}
