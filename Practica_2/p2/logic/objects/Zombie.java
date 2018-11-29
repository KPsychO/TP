package p2.logic.objects;

/** Class "Zombie":
 * 
 *		Object zombie, "the bad guys"
 *
 *  */

public abstract class Zombie extends GameObject {
	
	// getters
	
	public abstract int getDMG();
	public abstract String getSymbol();
	public abstract int getFreq();
	
	/** YEAH, that's a f***ing zombie :O */
	
	public boolean isZombie() {
		
		return true;
		
	}

}