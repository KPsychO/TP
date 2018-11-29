package p3.logic.objects;

import p3.logic.game.*;

/** Class "GameObject":
 * 
 *		Defines some common attributes and method both for plants and zombies
 *
 *  */

public abstract class GameObject {
	
	protected Game game;
	protected int hp;
	protected int dmg;
	protected int freq;
	protected int x;
	protected int y;
	protected String symbol;

	/** Executes (or not) some actions according to the cycle */
	
	public abstract void update();
	
	/** returns the printable version of the object (Release version) */
	
	public abstract String toString();
	
	/** returns the printable version of the object (""Debug"" version) */
	
	public abstract String debugToString();
	
	/** Decreases the object HP by the given value */
	
	public abstract void decreaseHP(int dmg);
	
	/** Checks if the object is a zombie */
	
	public abstract boolean isZombie();
	
	// getters
	
	public abstract int getX();
	public abstract int getY();
	public abstract int getHP();
	
}