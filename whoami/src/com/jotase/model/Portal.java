package com.jotase.model;

import com.badlogic.gdx.math.Vector2;

public class Portal {
	private Vector2 position;

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Portal(Vector2 position) {
		super();
		this.position = position;
	}
	
}
