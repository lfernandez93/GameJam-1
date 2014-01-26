package com.jotase.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.jotase.model.Ball;
import com.jotase.model.BallBot;
import com.jotase.model.WorldScreen;
import com.jotase.model.WorldScreen.States;

public class BallController {
	private static final long LONG_JUMP_PRESS = 150l;
	private static final float ACCELERATION = 3f;
	private static final float GRAVITY = -25f;
	private static final float MAX_JUMP_SPEED = 4f;
	private static final float DAMP = 0.3f;
	private static final float MAX_VEL = 2f;
	private static final float WIDTH = 10f;
	private boolean upPressing, downPressing, leftPressing, rightPressing;
	private Ball ball;
	private WorldScreen worldScreen;
	Audio audio = Gdx.audio;
	final Sound explosion = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/efectoExplosion.wav"));
	final Sound Laser = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/efectoLaser.wav"));
	public BallController(Ball ball, WorldScreen worldScreen) {
		super();
		this.ball = ball;
		this.worldScreen = worldScreen;
	}

	public Ball getBall() {
		return ball;
	}

	public WorldScreen getWorldScreen() {
		return worldScreen;
	}

	public void setWorldScreen(WorldScreen worldScreen) {
		this.worldScreen = worldScreen;
	}

	public BallController(Ball ball) {
		super();
		this.ball = ball;
	}

	public boolean isUpPressing() {
		return upPressing;
	}

	public void setUpPressing(boolean upPressing) {
		this.upPressing = upPressing;
	}

	public boolean isDownPressing() {
		return downPressing;
	}

	public void setDownPressing(boolean downPressing) {
		this.downPressing = downPressing;
	}

	public boolean isLeftPressing() {
		return leftPressing;
	}

	public void setLeftPressing(boolean leftPressing) {
		this.leftPressing = leftPressing;
	}

	public boolean isRightPressing() {
		return rightPressing;
	}

	public void setRightPressing(boolean rightPressing) {
		this.rightPressing = rightPressing;
	}

	public static Map<Keys, Boolean> getKeys() {
		return keys;
	}

	public static void setKeys(Map<Keys, Boolean> keys) {
		BallController.keys = keys;
	}

	enum Keys {
		LEFT, RIGHT, UP, DOWN
	}

	public static long getLongJumpPress() {
		return LONG_JUMP_PRESS;
	}

	public static float getAcceleration() {
		return ACCELERATION;
	}

	public static float getGravity() {
		return GRAVITY;
	}

	public static float getMaxJumpSpeed() {
		return MAX_JUMP_SPEED;
	}

	public static float getDamp() {
		return DAMP;
	}

	public static float getMaxVel() {
		return MAX_VEL;
	}

	public static float getWidth() {
		return WIDTH;
	}

	static Map<Keys, Boolean> keys = new HashMap<BallController.Keys, Boolean>();
	static {
		keys.put(Keys.DOWN, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
	}

	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
		leftPressing = true;

	}

	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
		rightPressing = true;
	}

	public void jumpPressed() {
		keys.get(keys.put(Keys.UP, true));
		upPressing = true;

	}

	public void isTouch() {
		if (Gdx.input.justTouched()) {
			if (ball.isCorrect()) {
				System.out.print("WINNER");
				this.ball.setWINNER(true);
			} else {
				this.ball.setWINNER(false);
				die();

			}
		}

	}

	public void firePressed() {
		keys.get(keys.put(Keys.DOWN, false));
		downPressing = true;
		if (ball.isCorrect()) {
			System.out.print("WINNER");
			this.ball.setWINNER(true);
		} else {
			this.ball.setWINNER(false);
			die();

		}
	}

	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
		leftPressing = false;
	}

	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
		rightPressing = false;
	}

	public void jumpReleased() {
		keys.get(keys.put(Keys.UP, false));
		upPressing = false;

	}

	public void fireReleased() {
		keys.get(keys.put(Keys.DOWN, false));
		downPressing = false;

	}

	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	/*
	 * private void setPosition() { if (ball.getPosition().y < 0) {
	 * ball.getPosition().y = 0f; ball.setPosition(ball.getPosition()); if
	 * (ball.states.equals(State.JUMPING)) { ball.setState(State.IDLE); } } if
	 * (ball.getPosition().x < 0) { ball.getPosition().x = 0;
	 * ball.setPosition(ball.getPosition()); if
	 * (!ball.getState().equals(State.JUMPING)) { ball.setState(State.IDLE); } }
	 * if (ball.getPosition().x > WIDTH - ball.getBounds().width) {
	 * ball.getPosition().x = WIDTH - ball.getBounds().width;
	 * ball.setPosition(ball.getPosition()); if
	 * (!ball.getState().equals(State.JUMPING)) { ball.setState(State.IDLE); } }
	 * 
	 * }
	 */

	private void setAcceleration(float delta) {
		float vel;
		if (upPressing) {
			vel = MAX_VEL / 2;
		} else
			vel = MAX_VEL;
		ball.getAcceleration().y = GRAVITY;
		ball.getAcceleration().mul(delta);
		ball.getVelocity().add(ball.getAcceleration().x,
				ball.getAcceleration().y);

		if (ball.getAcceleration().x == 0)
			ball.getVelocity().x *= DAMP;
		if (ball.getAcceleration().x > vel) {
			ball.getAcceleration().x = vel;
		}
		if (ball.getAcceleration().y < -vel)
			ball.getAcceleration().y = -vel;
	}
	
	
	public boolean OnCircleTouch(List<BallBot> ballbots) {
		boolean touched = false;
		for (BallBot bot : ballbots) {

			if (isTouched(bot)) {
				
				if (bot.isFireball()) {
					explosion.play(0.5f);
					die();
					repel(bot);
				} else {
					Laser.play();
					sumSecuence(bot);

					setBallPosition(bot, ballbots);
				}

				touched = true;
				return true;
			}

		}
		return touched;
	}

	private void sumSecuence(BallBot bot) {
		// if (!this.ball.getTexture().equals(bot.getTexture())) {
		/*
		 * if (bot.getSequence() == 2) { bot.setSequence(0); } else {
		 * bot.setSequence(bot.getSequence() + 1); }
		 */
		// System.err.println(bot.getSequence());
		// System.err.println(bot.getHint());
		// if (bot.getHints()[bot.getSequence()]) {
		this.ball.setTexture(bot.getTexture());
		// } else {
		// this.ball.setTexture(this.ball.getFALSE_texture());
		// }
		System.out.println("Bot correcto ="+ bot.isCorrect() );
		this.ball.setCorrect(bot.isCorrect());
		/*
		 * } else { this.ball.setTexture(ball.getIDLE_texture()); }
		 */

	}

	public boolean OnCircleTouch(BallBot bot, List<BallBot> bots) {

		boolean touched = false;

		if (isTouched(bot) || isTouched(bots, bot)) {
			Laser.play();
			touched = true;
			
		}
		return touched;
	}

	private boolean isTouched(BallBot bot) {
		boolean touched = false;
		float j = bot.getRadius();
		for (int i = 0; i < bot.getRadius(); i++) {
			// if((x-i<(xP+Radio) && x+i>xP-Radio) && (y-j<yP+Radio &&
			// y+j>yP-Radio))
			// System.out.println("botX =" + bot.getPosition().x + ", " +
			// "botY ="
			// + bot.getPosition().y);
			// System.out.println("ballX =" + ball.getPosition().x + ", "
			// + "ballY =" + ball.getPosition().y);
			if ((ball.getPosition().x - i < (bot.getPosition().x + bot.getRadius()) && ball
					.getPosition().x + i > bot.getPosition().x - bot.getRadius())
					&& (ball.getPosition().y - j < bot.getPosition().y +bot.getRadius() && ball
							.getPosition().y + j > bot.getPosition().y - bot.getRadius())) {
				// System.out.println("is touched najarse shike");
				touched = true;
			}
			j--;
		}
		return touched;
	}

	private boolean isTouched(List<BallBot> bots, BallBot bot) {
		boolean touched = false;
		float j = bot.getRadius();
		for (int i = 0; i < bot.getRadius(); i++) {
			for (BallBot ballBot : bots) {
				if (!bot.getTexture().equals(ballBot.getTexture())) {
					if ((ballBot.getPosition().x - i < (bot.getPosition().x + bot.getRadius()) && ballBot
							.getPosition().x + i > bot.getPosition().x - bot.getRadius())
							&& (ballBot.getPosition().y - j < bot.getPosition().y
									+ bot.getRadius() && ballBot.getPosition().y + j > bot
									.getPosition().y - bot.getRadius())) {
						touched = true;
					}
				}

			}
			j--;
		}
		return touched;
	}

	private boolean getTouched(BallBot ballBot, BallBot bot) {
		boolean touched = false;
		int index = 0;
		float j = bot.getRadius();
		for (int i = 0; i < bot.getRadius(); i++) {

			if (!bot.getTexture().equals(ballBot.getTexture())) {
				if ((ballBot.getPosition().x - i < (bot.getPosition().x + bot.getRadius()) && ballBot
						.getPosition().x + i > bot.getPosition().x - bot.getRadius())
						&& (ballBot.getPosition().y - j < bot.getPosition().y
								+ bot.getRadius() && ballBot.getPosition().y + j > bot
								.getPosition().y - bot.getRadius())) {
					touched = true;
					Laser.play(0.1f);
				}
			}

			j--;
		}
		return touched;
	}

	public void setBallPosition(BallBot bot, List<BallBot> ballbots) {
		/*
		 * bot.setPosition(new Vector2(new
		 * Random().nextInt(worldScreen.getWIDTH()-64), new Random()
		 * .nextInt(worldScreen.getHEIGHT()-64))); int d=0; if
		 * (OnCircleTouch(bot, ballbots)) { //setBallPosition(bot, ballbots);
		 * System.err.println(d); d++; }
		 */
		bot.setPosition(new Vector2(
				new Random().nextInt(worldScreen.getWIDTH() - 64), new Random()
						.nextInt(worldScreen.getHEIGHT() - 64)));
		while (OnCircleTouch(bot, ballbots)) {
			bot.setPosition(new Vector2(new Random().nextInt(worldScreen
					.getWIDTH() - 64), new Random().nextInt(worldScreen
					.getHEIGHT() - 64)));
		}
	}

	public void randomOrientation(BallBot bot) {
		if (new Random().nextInt(2) == 0) {
			bot.setFacingLeft(false);
		} else
			bot.setFacingLeft(true);
	}

	public void randomYOrientation(BallBot bot) {
		if (new Random().nextInt(2) == 0) {
			bot.setGoingUp(false);
		} else
			bot.setGoingUp(true);
	}

	private void horizontalMovement(BallBot bot) {
		if (!bot.isFacingLeft()) {
			if (bot.getPosition().x < worldScreen.getWIDTH() - 30) {
				bot.getPosition().x += bot.getVelocity();
			} else {
				randomOrientation(bot);
			}
		}
		if (bot.isFacingLeft()) {
			if (bot.getPosition().x > 30) {
				bot.getPosition().x -= bot.getVelocity();
			} else {
				randomOrientation(bot);
			}
		}
	}

	public void moveAsteroid(List<BallBot> bots, List<BallBot> botCompare) {
		// boolean apoyo = true;
		BallBot bot;
		for (int i = 0; i < bots.size(); i++) {
			bot = bots.get(i);
			horizontalMovement(bot);
			verticalMovement(bot);
			cambiar(bot, bots);
			cambiar(bot, botCompare);
		}
	}

	public void moveBot(List<BallBot> bots) {
		// boolean apoyo = true;
		BallBot bot;
		for (int i = 0; i < bots.size(); i++) {
			bot = bots.get(i);
			horizontalMovement(bot);
			verticalMovement(bot);
			cambiar(bot, bots);

		}
	}

	private void repel(BallBot bot) {
		if (bot.getPosition().y >= ball.getPosition().y) {
			bot.setGoingUp(true);

		} else {
			bot.setGoingUp(false);

		}
		if (bot.getPosition().x <= ball.getPosition().x) {
			bot.setFacingLeft(true);

		} else {
			bot.setFacingLeft(false);

		}
	}

	private void cambiar(BallBot bot, List<BallBot> bots) {
		// if(OnCircleTouch(bot, bots))
		// {

		for (int j = 0; j < bots.size(); j++) {
			// ind = getIndexTouched(bots, bot);
			if (getTouched(bots.get(j), bot)) {
				if (bot.getPosition().y >= bots.get(j).getPosition().y) {
					bot.setGoingUp(true);
					bots.get(j).setGoingUp(false);
				} else {
					bot.setGoingUp(false);
					bots.get(j).setGoingUp(true);
				}
				if (bot.getPosition().x <= bots.get(j).getPosition().x) {
					bot.setFacingLeft(true);
					bots.get(j).setFacingLeft(false);
				} else {
					bot.setFacingLeft(false);
					bots.get(j).setFacingLeft(true);
				}
			}

		}

		// }
	}

	private void verticalMovement(BallBot bot) {

		if (bot.isGoingUp()) {
			if (bot.getPosition().y < worldScreen.getHEIGHT() - 55) {
				bot.getPosition().y += bot.getVelocity();
			} else {
				randomYOrientation(bot);
			}
		}
		if (!bot.isGoingUp()) {
			if (bot.getPosition().y > 30) {
				bot.getPosition().y -= bot.getVelocity();
			} else {
				randomYOrientation(bot);
			}
		}

	}

	private void die() {
		if (this.ball.getLifes() != 0) {
			this.ball.setLifes(this.ball.getLifes() - 1);
			System.err.println("Pierdes una vida te restan "
					+ this.ball.getLifes());

		} else {
			ball.setState(States.Dead);
			System.err.println("PERDISTE");
		}
	}
}
