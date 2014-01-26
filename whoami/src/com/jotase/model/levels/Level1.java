package com.jotase.model.levels;

public class Level1 {
	public enum Type {
		Velocity, Size
	}

	private int MAX_LVL;
	private int MAX_BOT;
	private int MAX_Evil;
	private int[] velocities;
	private int currentLevel;
	
	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	private Type type;

	public int getMAX_LVL() {
		return MAX_LVL;
	}

	public void setMAX_LVL(int mAX_LVL) {
		MAX_LVL = mAX_LVL;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {

		this.type = type;
		setMaxLVL();
	}

	private void setMaxLVL() {
		switch (type) {
		case Velocity:
			setMAX_LVL(7);
			break;
		case Size:
			setMAX_LVL(6);

		default:
			break;
		}

	}

	public int[] getVelocities() {
		return velocities;
	}

	public void setVelocities(int[] velocities) {
		this.velocities = velocities;
	}

	public int getMAX_BOT() {
		return MAX_BOT;
	}

	public void setMAX_BOT(int mAX_BOT) {
		MAX_BOT = mAX_BOT;
	}

	public int getMAX_Evil() {
		return MAX_Evil;
	}

	public void setMAX_Evil(int mAX_Evil) {
		MAX_Evil = mAX_Evil;
	}

	public void setLevel(int lvl) {
		switch (type) {
		case Velocity:

			setVelocityLVL(lvl);
			break;

		default:
			break;
		}
		setCurrentLevel(lvl);
	}

	private void setVelocityLVL(int lvl) {
		switch (lvl) {
		case 0: {
			setMAX_BOT(3);
			setMAX_Evil(0);
			int[] v = { 1, 2,3 };
			setVelocities(v);
			break;
		}
		case 1:
		{
			setMAX_BOT(4);
			setMAX_Evil(1);
			int[] v = {1, 2,3,4};
			setVelocities(v);
			break;
		}
		case 2:
		{
			setMAX_BOT(5);
			setMAX_Evil(2);
			int[] v ={1, 2,3,4,5};
			setVelocities(v);
			break;
		}
		case 3:
		{
			setMAX_BOT(6);
			setMAX_Evil(3);
			int[] v ={1, 2,3,4,5,6};
			setVelocities(v);
			break;
		}
		case 4:
		{
			setMAX_BOT(7);
			setMAX_Evil(4);
			int[] v ={1, 2,3,4,5,7};
			setVelocities(v);
			break;
		}
			

		default:
			break;
		}
	}

}
