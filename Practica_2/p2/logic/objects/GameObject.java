package p2.logic.objects;

import p2.logic.game.*;

/** Class "GameObject":
 * 
 *		Defines some common atributes and method both for plants and zombies
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

	/** Executes (or not) some actions acording to the cicle */
	
	public abstract void update();
	
	/** returns the printable version of the object (Relase version) */
	
	public abstract String toString();
	
	/** returns the printable version of the obejct (""Debug"" version) */
	
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