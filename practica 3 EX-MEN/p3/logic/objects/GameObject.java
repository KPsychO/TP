package p3.logic.objects;

import p3.logic.game.*;

/** Class "GameObject":
 * 
 *		Defines some common attributes and method both for plants and zombies
 *
 *  */

public abstract class GameObject {
	
	// GO - GameObject
	// AGO - ActiveGameObject
	// PGO - PassiveGameObject
	// 		Actually... fuck it, PGO is empty, you declare the class so the teacher is happy and then use the parameters in GO 
	
	protected Game game;		// GO
	protected int hp;			// AGO
	protected int dmg;			// AGO
	protected int freq;			// AGO
	protected int x;			// GO
	protected int y;			// GO
	protected String symbol;	// GO

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
	
	/** Method use to gather the info we save about each of the objects in the currrent game. */
	
	public abstract String getSaveInfo();
	
}