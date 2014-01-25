package com.wisecounsil.whoami;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.wisecounsil.whoami.gamestates.GameState;
import com.wisecounsil.whoami.gamestates.MenuState;
import com.wisecounsil.whoami.utils.Assets;

public class WhoAmI extends Game {
	
	@Override
	public void create() {		
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		Assets.instance.init(new AssetManager());
		// Start game at menu screen
		setScreen(new MenuState(this));
	}


}
