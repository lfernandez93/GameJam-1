package com.wisecounsil.whoami.gamestates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.wisecounsil.whoami.AbstractGameScreen;
import com.wisecounsil.whoami.utils.Constants;

public class GameOverState extends AbstractGameScreen {
	private Stage stage;
	private Skin skinWhoAmI;
	private Image imgBackground;
	private Image imgLogo;
	private Image text;

	private Button btnMenuPlay;
	public static String TAG = MenuState.class.getName();
	private final float DEBUG_REBUILD_INTERVAL = 5.0f;
	private boolean debugEnabled = false;
	private float debugRebuildStage;

	public GameOverState(Game game) {
		super(game);
		music=Gdx.audio.newMusic(
				Gdx.files.internal("data/music/Gameover.MP3"));
		music.play();
	}

	private void rebuildStage() {
		skinWhoAmI = new Skin(Gdx.files.internal(Constants.SKIN_CANYONBUNNY_UI),
				new TextureAtlas(Constants.TEXTURE_ATLAS_UI));
		Table layerBackground = buildBackgroundLayer();
		Table layerLogos = buildLogosLayer();
		Table layerControls = buildControlsLayer();
		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Constants.VIEWPORT_GUI_WIDTH,
				Constants.VIEWPORT_GUI_HEIGHT);
		stack.add(layerBackground);
		stack.add(layerControls);
		stack.add(layerLogos);
	}

	private Table buildBackgroundLayer() {
		Table layer = new Table();
		imgBackground = new Image(skinWhoAmI, "GameScreen");
		layer.add(imgBackground);
		return layer;
	}

	private Table buildLogosLayer() {
		Table layer = new Table();
		layer.center();
		// + Game Logo
		imgLogo = new Image(skinWhoAmI, "gameover");
		layer.add(imgLogo);
		layer.row().expandY();
		// + Info Logos
		//layer.center().bottom();
		// + Game Logo
		
		if (debugEnabled) layer.debug();
		return layer;
	}

	private Table buildControlsLayer() {
		Table layer = new Table();
		layer.right().bottom();
		btnMenuPlay = new Button(skinWhoAmI, "again");
		btnMenuPlay.addListener(new ChangeListener() {
		
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				onPlayClicked();
			}
		});
		imgLogo = new Image(skinWhoAmI, "text");
		layer.add(imgLogo);
		//layer.row().expandY();
		layer.add(btnMenuPlay);

		layer.row();
		
		if (debugEnabled) layer.debug();
		return layer;
	}

	protected void onExitClicked() {
		// TODO Auto-generated method stub
		Gdx.app.exit();
	}

	protected void onHelpClicked() {
		// TODO Auto-generated method stub
		game.setScreen(new HelpState(game));
	}

	protected void onPlayClicked() {
		game.setScreen(new GameState(game));		
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if (debugEnabled) {
			debugRebuildStage -= delta;
			if (debugRebuildStage <= 0) {
				debugRebuildStage = DEBUG_REBUILD_INTERVAL;
				rebuildStage();
			}
		}
		
		
		
		stage.act(delta);
		stage.draw();
		Table.drawDebug(stage);
		/*
		 * if (Gdx.input.isTouched()) { Gdx.app.debug(TAG, "Touched");
		 * game.setScreen(new GameState(game)); }
		 */
		// TODO Auto-generated method stub
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(Constants.VIEWPORT_GUI_WIDTH,
				Constants.VIEWPORT_GUI_HEIGHT, false);
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		rebuildStage();
	}

	@Override
	public void hide() {
		stage.dispose();
		skinWhoAmI.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

}

