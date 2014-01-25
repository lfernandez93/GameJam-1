package com.wisecounsil.whoami.controller;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.wisecounsil.whoami.gamestates.MenuState;
import com.wisecounsil.whoami.utils.CameraHelper;
import com.wisecounsil.whoami.model.Background;
import com.wisecounsil.whoami.model.Ball;
import com.wisecounsil.whoami.model.Entity;
import com.wisecounsil.whoami.model.Player;
import com.wisecounsil.whoami.model.Star;

public class WorldController extends InputAdapter {
	
	private static final String TAG = WorldController.class.getName();
	public CameraHelper cameraHelper;
	public Game game;
	private List<Entity> layerA;
	private List<Entity> layerB;
	private Player player;
	private List<Entity> layerC;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public WorldController(){
		init();
	}
	
	public WorldController(Game game) {
		this.game=game;
		player = new Player();
		layerC=new ArrayList<Entity>();
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
		for(int i=0;i<=7;i++){
		layerC.add(new Ball(Color.YELLOW,new Vector2(MathUtils.random(0, 800), MathUtils.random(0, 480)), new Vector2(MathUtils.random(0.3f, 5),MathUtils.random(0.3f, 5))));
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
		for(Entity e: layerC){
			e.update(delta);
		}

		player.update(delta);
		
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

	public List<Entity> getLayerC() {
		// TODO Auto-generated method stub
		return layerC;
	}
}
