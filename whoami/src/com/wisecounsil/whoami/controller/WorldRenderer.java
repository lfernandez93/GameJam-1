package com.wisecounsil.whoami.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.wisecounsil.whoami.utils.Assets;
import com.wisecounsil.whoami.utils.Constants;

public class WorldRenderer implements Disposable {

	private OrthographicCamera camera;
	private OrthographicCamera cameraGUI;
	private SpriteBatch batch;
	private WorldController worldController;
	
	public WorldRenderer(WorldController worldController){
		init();
	}
	
	private void init(){
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,
		Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();
		cameraGUI = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH,
				Constants.VIEWPORT_GUI_HEIGHT);
				cameraGUI.position.set(0, 0, 0);
				cameraGUI.setToOrtho(true); // flip y-axis
				cameraGUI.update();
	}
	
	public void render(){
		
		renderGui(batch);
	}
	
	public void resize(int width, int height){
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT
				/ (float)height) * (float)width;
				camera.update();
				cameraGUI.viewportHeight = Constants.VIEWPORT_GUI_HEIGHT;
				cameraGUI.viewportWidth = (Constants.VIEWPORT_GUI_HEIGHT
				/ (float)height) * (float)width;
				cameraGUI.position.set(cameraGUI.viewportWidth / 2,
				cameraGUI.viewportHeight / 2, 0);
				cameraGUI.update();
	}
	
	@Override
	public void dispose(){
		
	}
	
	private void renderGuiFpsCounter (SpriteBatch batch) {
		float x = cameraGUI.viewportWidth - 55;
		float y = cameraGUI.viewportHeight - 15;
		int fps = Gdx.graphics.getFramesPerSecond();
		BitmapFont fpsFont = Assets.instance.fonts.defaultNormal;
		if (fps >= 45) {
		// 45 or more FPS show up in green
		fpsFont.setColor(0, 1, 0, 1);
		} else if (fps >= 30) {
		// 30 or more FPS show up in yellow
		fpsFont.setColor(1, 1, 0, 1);
		} else {
		// less than 30 FPS show up in red
		fpsFont.setColor(1, 0, 0, 1);
		}
		fpsFont.draw(batch, "FPS: " + fps, x, y);
		fpsFont.setColor(1, 1, 1, 1); // white
		}
	
	private void renderGui (SpriteBatch batch) {
		batch.setProjectionMatrix(cameraGUI.combined);
		batch.begin();
		// draw collected gold coins icon + text
		// (anchored to top left edge)
		//renderGuiScore(batch);
		// draw extra lives icon + text (anchored to top right edge)
		//renderGuiExtraLive(batch);
		// draw FPS text (anchored to bottom right edge)
		renderGuiFpsCounter(batch);
		batch.end();
		}
}
