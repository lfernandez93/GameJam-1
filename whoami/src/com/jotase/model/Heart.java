package com.jotase.model;

import com.badlogic.gdx.graphics.Texture;

public class Heart {
	private Texture texture;

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Heart() {
		super();
	}

	public Heart(Texture texture) {
		super();
		this.texture = texture;
	}
	
	
}
