package com.wisecounsil.whoami;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.wisecounsil.whoami.utils.Assets;

public abstract class AbstractGameScreen implements Screen{
	protected Game game;
	protected Music music;

	public AbstractGameScreen(Game game){
		this.game=game;
	}
	
	public abstract void render(float delta);
	public abstract void resize(int width, int height);
	public abstract void show();
	public abstract void hide();
	public abstract void pause();
	
	public void resume(){
		Assets.instance.init(new AssetManager());
		music.play();
	}
	
	public void dispose(){
		Assets.instance.dispose();
		music.dispose();
	}
		
}
