package com.wisecounsil.whoami.controller;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.wisecounsil.whoami.gamestates.MenuState;
import com.wisecounsil.whoami.utils.CameraHelper;
import com.wisecounsil.whoami.model.Background;
import com.wisecounsil.whoami.model.Entity;
import com.wisecounsil.whoami.model.Star;

public class WorldController extends InputAdapter {
	
	private static final String TAG = WorldController.class.getName();
	public CameraHelper cameraHelper;
	public Game game;
	private List<Entity> layerA;
	private List<Entity> layerB;
	
	public WorldController(){
		init();
	}
	
	public WorldController(Game game) {
		this.game=game;
		layerA = new ArrayList<Entity>();
		layerB=new  ArrayList<Entity>();
		init();
		// TODO Auto-generated constructor stub
	}

	private void init(){
		layerA.add(new Background());
		for(int i=0;i<=500;i++){
			layerB.add(new Star());
		}
		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
	}
	
	public void update(float delta){
		handleDebugInput(delta);
		updateWorldAndEntities(delta);
		cameraHelper.update(delta);
		for(Entity e: layerA){
			e.update(delta);
		}
		for(Entity e: layerB){
			e.update(delta);
		}
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

	public List<Entity> getLayerA() {
		//System.out.println("layerA ? " + layerA==null);
		// TODO Auto-generated method stub
		return layerA;
	}

	public List<Entity> getLayerB() {
		// TODO Auto-generated method stub
		return layerB;
	}
}
