package com.wisecounsil.whoami.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.wisecounsil.whoami.gamestates.MenuState;
import com.wisecounsil.whoami.utils.CameraHelper;

public class WorldController extends InputAdapter {
	
	private static final String TAG = WorldController.class.getName();
	public CameraHelper cameraHelper;
	public Game game;
	
	public WorldController(){
		init();
	}
	
	public WorldController(Game game) {
		this.game=game;
		init();
		// TODO Auto-generated constructor stub
	}

	private void init(){
		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
	}
	
	public void update(float delta){
		handleDebugInput(delta);
		updateWorldAndEntities(delta);
		cameraHelper.update(delta);
	}
	
	private void updateWorldAndEntities(float delta) {
		// TODO Auto-generated method stub
		
	}

	private void handleDebugInput(float deltaTime){
		
	}
	
	 @Override
	 public boolean keyUp(int keycode){
		 if (keycode == Keys.ESCAPE || keycode == Keys.BACK) {
			 backToMenu();
			 }
			 return false;
	 }

	private void backToMenu() {
		// TODO Auto-generated method stub
		game.setScreen(new MenuState(game));
	}
}
