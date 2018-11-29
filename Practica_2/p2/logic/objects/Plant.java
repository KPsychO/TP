package p2.logic.objects;

/** Class "Plant":
 * 
 *		Object plant, "the gud guys"
 *
 *  */

public abstract class Plant extends GameObject {
	
	protected String name;
	
	/** decreases the plant hp by the given value */
	
	public abstract void decreaseHP(int dmg);
	
	/** Returns "the plant" if it attributes correspond to the user input (AddCommand) */
	
	public Plant parse(String plantName) {
		
		if (plantName.equals(this.symbol) || plantName.equals(this.name)) {
			
			return this;
			
		}
		
		else {
			
			return null;
			
		}
		
	}
	
	/** Don't shoot me! I'm not an otaku! (nor a zombie) */
	
	public boolean isZombie() {
		
		return false;
		
	}
	
	/** Returns a printable plant for the board */
	
	public String plantInfo() {
		
		return " [" + this.symbol + "]: " + this.name + ": HP = " + this.hp + " DMG = " + this.dmg +"\n";
				
	}
	
	// getters

	public abstract int getCost();
	public abstract String getName();
	public abstract String getSymbol();
	
}