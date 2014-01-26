package com.jotase.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import aurelienribon.bodyeditor.BodyEditorLoader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.jotase.controller.BallController;
import com.jotase.model.Ball;
import com.jotase.model.BallBot;
import com.jotase.model.Heart;
import com.jotase.model.Portal;
import com.jotase.model.WorldScreen;
import com.jotase.model.WorldScreen.States;
import com.jotase.model.levels.Level1;
import com.jotase.model.levels.Level1.Type;
import com.sun.corba.se.spi.orbutil.fsm.State;
import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener;
import com.wisecounsil.whoami.gamestates.GameOverState;
import com.wisecounsil.whoami.gamestates.WinnerState;

public class WorldRenderer {
	private World world;
	final int max = 7;
	// private int MAX_BALLS = 2;
	private int hint;
	private float x, y;
	private String hints;
	private ParticleEffect dustEffect = new ParticleEffect();
	private ParticleEffect portalEffect = new ParticleEffect();
	WorldScreen worldScreen;
	HashSet<Heart> hearts = new HashSet<Heart>();
	// Render
	private TextureAtlas atlas;
	private TextureAtlas atlas2;
	private Texture backgroundImage;
	private Sprite backgroundSprite;
	private TextureRegion ballTexture;
	private TextureRegion heartTexture;
	private TextureRegion portal;
	private Texture texture = new Texture(Gdx.files.internal("data/aceptacion.png"));
	private Music music;
	List<BallBot> ballbots = new ArrayList<BallBot>();
	List<BallBot> evilBots = new ArrayList<BallBot>();
	private Portal port;
	private Ball ball;
	BallController controller;
	Level1 level = new Level1();
	private Game game;
	

	public BallController getController() {
		return controller;
	}

	public void setController(BallController controller) {
		this.controller = controller;
	}

	// Render general
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
	private World mundo;

	// Misc
	private final Random rand = new Random();

	public WorldRenderer(Game game) {
		super();
		this.game=game;
		world = new World(new Vector2(5f, -10), true);
		worldScreen =  new WorldScreen();
		level.setType(Type.Velocity);
		level.setLevel(0);
		Texture.setEnforcePotImages(false);
		init();

		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		/*
		 * float w = Gdx.graphics.getWidth(); float h =
		 * Gdx.graphics.getHeight();
		 * 
		 * camera = new OrthographicCamera(worldScreen.getWIDTH(),
		 * worldScreen.getHEIGHT() * h / w); camera.position.set(0,
		 * camera.viewportHeight / 2, 0); camera.update();
		 */
		// createBalls();
		createSprites();
		music=Gdx.audio.newMusic(
				Gdx.files.internal("data/music/Gameover.MP3"));
		music.play();
		render();

	}

	private void setBackground() {
		atlas2 = new TextureAtlas("data/Textures/menu.pack");
		backgroundImage = new Texture("data/Who Am I.png");
		backgroundSprite = new Sprite(backgroundImage);
	}
	public void init() {
		atlas = new TextureAtlas("data/Textures/Sprites.atlas");
		
		initPortal();
		initBall();
		initBot();
		initEvil();
		setBackground();
		
	}

	private void initPortal() {
		// TODO Auto-generated method stub
		portal = atlas.findRegion("portal");
		port = new Portal(new Vector2(new Random().nextInt(worldScreen.getWIDTH()- 80),
				worldScreen.getHEIGHT()-80));
	}

	private void initEvil() {
		for (int i = 0; i < level.getMAX_Evil(); i++) {
			addEvilBot();
		}
	}

	private void initBot() {

		BallBot bot;
		for (int i = 0; i < level.getMAX_BOT(); i++) {
			bot = new BallBot();

			addBot(bot, i);

		}
		asignCorrectValue();

	}

	private void initBall() {
		ball = new Ball();
		ball.setCorrect(false);
		ball.setRadius(20);
		ball.setLifes(3);
		controller = new BallController(ball, worldScreen);
	}

	private void createSprites() {
		
		dustEffect.load(Gdx.files.internal("data/fuego.pfx"),
				Gdx.files.internal("data"));
		portalEffect.load(Gdx.files.internal("data/Textures/superportal.pfx"),
				Gdx.files.internal("data"));

		ballTexture = atlas.findRegion("idle");
		ball.setFALSE_texture(ballTexture);
		//ballTexture = new Texture(Gdx.files.internal("resource/gfx/ball.png"));
		//ballTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		ball.setTexture(ballTexture);
		ball.setIDLE_texture(ballTexture);
	}

	private void addEvilBot() {
		BallBot evilBot = new BallBot();
		TextureRegion evilTexture = atlas.findRegion("asteorid");
		evilBot.setTexture(evilTexture);
		evilBot.setRadius(20);
		evilBot.setFireball(true);
		evilBot.setVelocity(ballbots.get(ballbots.size() - 1).getVelocity() + 1);
		evilBot.setBody(world.createBody(evilBot.getBallBodyDef()));
		controller.randomOrientation(evilBot);
		controller.randomYOrientation(evilBot);
		evilBots.add(evilBot);
	}

	private void addBot(BallBot bot, int i) {
		int region = i+1;
		System.out.println(region);	
		ballTexture = atlas.findRegion(region+"");
		bot.setTexture(ballTexture);
		bot.setRadius(20);
		bot.setVelocity(level.getVelocities()[i]);
		bot.setBody(world.createBody(bot.getBallBodyDef()));
		controller.randomOrientation(bot);
		controller.randomYOrientation(bot);

		ballbots.add(bot);
		setBallPosition(bot);
	}

	private void setBallPosition(BallBot bot) {
		bot.setPosition(new Vector2(
				new Random().nextInt(worldScreen.getWIDTH() - 20), new Random()
						.nextInt(worldScreen.getHEIGHT() - 20)));
		if (controller.OnCircleTouch(bot, ballbots)) {
			setBallPosition(bot);
		}
	}

	public void render() {
		 verifyStatusBall();
		 controller.isTouch();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		x = Gdx.input.getX();
		y = worldScreen.getHEIGHT() - Gdx.input.getY();
		// Update
		world.step(1 / 60f, 10, 10);

		// Render
		GL10 gl = Gdx.gl10;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		lifeAnimation();
		// batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//System.out.println(worldScreen.getState());
		if(worldScreen.getState().equals(States.Gaming))
		{
			gamingState(h);
		}
		else
		if(worldScreen.getState().equals(States.Dead)){
			deadState();
		}
		
		batch.end();

	}


	private void deadState() {
		music.dispose();
		game.setScreen(new GameOverState(game));
	}
	private void verifyStatusBall()
	{
		worldScreen.setState(ball.getState());
	}

	private void gamingState(float h)
	{
		worldScreen.setTimePortal(worldScreen.getTimePortal()+Gdx.graphics.getDeltaTime());
		backgroundSprite.draw(batch);
		// ballSprites.draw(batch);
		// ballSprites2.draw(batch);
		if (this.ball.isWINNER()) {
			addLevel();
			
			this.ball.setWINNER(false);
		} else {
			if(ball.isCorrect())
			{
				drawPortal();
				
			}
			if (controller.OnCircleTouch(ballbots)) {

			}
			controller.OnCircleTouch(evilBots);
			drawBall();
			drawBots();
			//drawFonts(h);
			drawLife();
			
			//drawParticles();
			controller.moveBot(ballbots);
			controller.moveAsteroid(evilBots, ballbots);
		}

	}
	private void drawPortal() {
		System.out.println("time = "+worldScreen.getTimePortal());
		if(worldScreen.getTimePortal()>=5*level.getCurrentLevel())
		{
			//System.out.println("ola k ase, pintanto el portal o q ase");
			/*port.setPosition(new Vector2(new Random().nextInt(worldScreen.getWIDTH()- 80),
					worldScreen.getHEIGHT()-80));*/
			//batch.draw(portal, port.getPosition().x,port.getPosition().y );
			portalEffect.setPosition(port.getPosition().x, port.getPosition().y);
			portalEffect.draw(batch, Gdx.graphics.getDeltaTime());
			portalEffect.start();
		}
		
	}

	private void drawLife() {
		if(ball.getLifes()>2)
		batch.draw(heartTexture, worldScreen.getWIDTH()-180, worldScreen.getHEIGHT()-80);
		if(ball.getLifes()>1)
		batch.draw(heartTexture, worldScreen.getWIDTH()-120, worldScreen.getHEIGHT()-80);
		if(ball.getLifes()>0)
		batch.draw(heartTexture, worldScreen.getWIDTH()-60, worldScreen.getHEIGHT()-80);
		
		
	}

	private void drawEvils() {
		// TODO Auto-generated method stub

	}

	/*private void drawParticles() {
		dustEffect.setPosition(ball.getPosition().x, ball.getPosition().y);
		dustEffect.draw(batch, Gdx.graphics.getDeltaTime());
		dustEffect.start();
	}*/

	private void addLevel() {
		if (level.getCurrentLevel() != level.getMAX_LVL()) {
			ball.setCorrect(false);
			//level.setMAX_BOT(level.getMAX_BOT() + 1);
			System.out.println("Nivel actual = "+level.getCurrentLevel());
			level.setLevel(level.getCurrentLevel()+1);
			System.out.println("Nivel nuevo = "+level.getCurrentLevel());
		//	asignCorrectValue();
			ballbots.clear();
			evilBots.clear();
			initBot();
			initEvil();
			port.setPosition(new Vector2(new Random().nextInt(worldScreen.getWIDTH()- 80),
					worldScreen.getHEIGHT()-80));
			worldScreen.setTimePortal(0);
		} else {
			//System.err.println("NAJARSE");
			music.dispose();
			game.setScreen(new WinnerState(game));
		}

	}

	public void lifeAnimation() {
		worldScreen
				.setTime(worldScreen.getTime() + Gdx.graphics.getDeltaTime());
		if (worldScreen.getTime() >= 0.0)
			heartTexture = new TextureRegion(texture, 332, 734, 60, 65);
		if (worldScreen.getTime() > 0.2)
			heartTexture = new TextureRegion(texture, 399, 734, 60, 65);
		if (worldScreen.getTime() > 0.4)
			heartTexture = new TextureRegion(texture, 458, 734, 60, 65);

		if (worldScreen.getTime() > 0.6) {
			worldScreen.setTime(0);
		}
	}

/*	private void drawFonts(float h) {
		font.draw(batch, "HINT :" + hints, 5, h - 5);
		font.draw(batch, "" + ball.isCorrect(), 10, 60);

	}*/

	private void drawBall() {
		ball.setPosition(new Vector2(x - 20, y - 20));
		batch.draw(ball.getTexture(), x - 20, y - 20);
	}

	private void drawBots() {
		for (BallBot bot : ballbots) {
			batch.draw(bot.getTexture(), bot.getPosition().x,
					bot.getPosition().y);
		}
		for (BallBot evil : evilBots) {
			batch.draw(evil.getTexture(), evil.getPosition().x,
					evil.getPosition().y);
			dustEffect.setPosition(evil.getPosition().x, evil.getPosition().y);
			dustEffect.draw(batch, Gdx.graphics.getDeltaTime());
			dustEffect.start();
		}
	}

	/*private void asignCorrectValue() {
		int id = new Random().nextInt(ballbots.size());
		hint = new Random().nextInt(7);

		BallBot bot = ballbots.get(id);
		bot.setCorrect(true);
		System.err.println(id + " correcto");

		bot.setHints((bot.getSequence(id)));
		ball.setSequence(bot.getSequence(hint));
		setHints();
		setAnotherValues(id);

	}*/
	private void asignCorrectValue() {
		ballbots.get(worldScreen.getCorrectBot()).setCorrect(true);
		System.out.println("Bot correcto ="+worldScreen.getCorrectBot());
		worldScreen.setCorrectBot(worldScreen.getCorrectBot()+1);
		System.out.println("Bot nuevo ="+worldScreen.getCorrectBot());
		/*if(worldScreen.getCorrectBot()!=0)
		{
			worldScreen.setCorrectBot(worldScreen.getCorrectBot()+1);
			ballbots.get(worldScreen.getCorrectBot()).setCorrect(true);
		}
		else
		{
			ballbots.get(worldScreen.getCorrectBot()).setCorrect(true);
			worldScreen.setCorrectBot(worldScreen.getCorrectBot()+1);
		}*/

		

	}

	private void setAnotherValues(int id) {
		BallBot bot;
		int index;
		HashSet h = new HashSet();
		h.add(id);
		List<BallBot> list = ballbots;
		for (int i = 0; i < list.size(); i++) {
			if (i != id) {
				bot = ballbots.get(i);
				if (!bot.isCorrect()) {
					index = new Random().nextInt(7);
					while (h.contains(index)) {
						index = new Random().nextInt(7);
					}
					bot.setHints((bot.getSequence(index)));
					h.add(index);
				}

			}
		}
	}

	private void setHints() {
		Boolean[] seq = ball.getSequence();
		hints = "| ";
		for (int i = 0; i < seq.length; i++) {
			if (seq[i]) {
				hints += "V |";
			} else {
				hints += "X |";
			}
		}
	}

}
