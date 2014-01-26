/**package com.jotase.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.jotase.controller.BallController;
import com.jotase.model.Ball;

import com.jotase.view.WorldRenderer;

public class TestScreen implements Screen{

	private InputProcessor input;
	private int width, height;
	private Ball ball;
	private WorldRenderer world;
	private BallController controller;

	public TestScreen() {
		super();
		//world = new WorldRenderer();
		this.input = new Input(world);
		world.render();
	}

	public InputProcessor getInput() {
		return input;
	}


	public void setInput(InputProcessor input) {
		this.input = input;
	}


	@Override
	public void render(float delta) {
		//Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		controller.update(delta);
		world.render();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		
		
		controller = world.getController();
		Gdx.input.setInputProcessor(input);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
*/