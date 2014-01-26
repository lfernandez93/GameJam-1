package com.jotase.model;

public class WorldScreen {
	final int WIDTH = 800,HEIGHT=480;
	public enum States{
		Gaming,Dead
	}
	private float timePortal;
	private int correctBot;
	
	
	public int getCorrectBot() {
		return correctBot;
	}

	public void setCorrectBot(int correctBot) {
		this.correctBot = correctBot;
	}

	public float getTimePortal() {
		return timePortal;
	}

	public void setTimePortal(float timePortal) {
		this.timePortal = timePortal;
	}

	private States state ;
	
	public WorldScreen() {
		super();
		setState(States.Gaming);
		setTime(0);
		setCorrectBot(0);
	}

	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}

	private float time=0;
	
		

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public int getWIDTH() {
		return WIDTH;
	}
	
	
}
