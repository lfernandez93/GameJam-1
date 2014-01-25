package com.wisecounsil.whoami.gamestates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.wisecounsil.whoami.AbstractGameScreen;
import com.wisecounsil.whoami.controller.WorldController;
import com.wisecounsil.whoami.controller.WorldRenderer;

public class GameState extends AbstractGameScreen {

	private WorldController worldController;
	private WorldRenderer worldRenderer;
	
	private static final String TAG = GameState.class.getName();
	
	private boolean paused;   
	
	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public GameState(Game game) {
		super(game);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(float delta) {
		
		if(!paused){
			worldController.update(delta);
		}
		
		Gdx.gl.glClearColor(0,0,0,1);
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		worldRenderer.render();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width,height);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		worldController = new WorldController(game);
		worldRenderer = new WorldRenderer(worldController);
		Gdx.input.setCatchBackKey(true);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		worldRenderer.dispose();
		Gdx.input.setCatchBackKey(false);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		paused = true;
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void resume(){
		super.resume();paused=false;
	}

}
