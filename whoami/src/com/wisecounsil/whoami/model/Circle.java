package com.wisecounsil.whoami.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.wisecounsil.whoami.utils.Assets;

public class Circle extends AbstractGameObject{
	private TextureRegion regCircle;
	private boolean value;
	private Color color;
	
	public Circle(){
		init();
	}
	
	private void init(){
		getDimension().set(0.5f,0.5f);
		getBounds().set(0,0,getDimension().x,getDimension().y);
	    
	}
	
	public void render(SpriteBatch batch){
		TextureRegion reg = null;
		reg = regCircle;
		batch.draw(reg.getTexture(),
				getPosition().x, getPosition().y, 
				getOrigin().x, getOrigin().y, 
				getDimension().x, getDimension().y, 
				getScale().x, getScale().y, 
				getRotation(),
				reg.getRegionX(),reg.getRegionY(),
				reg.getRegionWidth(),reg.getRegionHeight(),
				false,false);
	}
	
	
}
