package p3.logic.lists;

import p3.logic.game.Game;
import p3.logic.objects.*;

/** Class "ObjectList":
 * 
 *		Controls the object's list
 *
 *  */

public class ObjectList {
	
	@SuppressWarnings("unused")
	private Game game;
	private int object_count;
	private int capacity;
	private GameObject[] list;
	
	/** Creates a new objectlist with capacity = 4 */
	
	public ObjectList (Game game) {
		
		this.game = game;
		this.object_count = 0;
		this.capacity = 4;
		this.list = new GameObject[capacity];
		
	}
	
	/** Executes the update method of each of the objects inside the list */
	
	public void update() {
		
		for (int i  = 0; i < this.object_count; i++) {
			
			list[i].update();
			
		}
		
	}
	
	/** Searches and decreases the hp of the object on the given position by the given dmg */
	
	public void decreaseObjectHP(int x, int y, int dmg) {
		
		int i = 0;
		boolean found = false;
		
		while (i < this.object_count && !found) {
			
			if ((x == this.list[i].getX()) && (y == this.list[i].getY()))
				found = true;
			
			else
				++i;
			
		}
		
		if (found)
			this.list[i].decreaseHP(dmg);
		
	}
	
	/** see game.objectToString(int i) */
	
	public String objectToString (int i) {
		
		return this.list[i].toString();
		
	}
	
	/** see game.objectDebugToString(int i) */
	
	public String objectDebugToString(int i) {
		
		return this.list[i].debugToString();
		
	}
	
	/** Searches and deletes the object on the given position */
	
	public void deleteObject(int x, int y) {
		
		int i = 0;
		boolean found = false;
		
		while (i < this.object_count && !found) {
			
			if ((x == this.list[i].getX()) && (y == this.list[i].getY()))
				found = true;
			
			else
				++i;
			
		}
		
		for (int j = i; j < this.object_count; j++){
			
			this.list[j] = this.list[j + 1];
			
		}
		
		this.object_count--;
		
		if (this.object_count < (this.capacity - 5))
			decrease();
		
	}
	
	/** Adds the given plant in the given position */
	
	public void addPlant (Plant plant) {
		
		if(this.object_count == (this.capacity - 1))
			resize();
		
		this.list[object_count] = plant;
		this.object_count++;
		
	}
	
	/** Adds the given zombie in the given position */
	
	public void addZombie (Zombie zombie) {
		
		if(this.object_count == (this.capacity - 1))
			resize();
		
		this.list[this.object_count] = zombie;
		this.object_count++;
		
	}
	
	/** see game.isZombie(i) */
	
	public boolean isZombie(int i) {
		
		return this.list[i].isZombie();
		
	}
	
	// getters
	
	public int getObjectCount() {
		
		return this.object_count;
		
	}
	
	public int getObjectX(int i) {

		return this.list[i].getX();
		
	}
	
	public int getObjectY(int i) {
		
		return this.list[i].getY();
		
	}
	
	/** Increases the capacity by 4 */
	
	public void resize() {
		
		this.capacity += 4;
		GameObject[] aux = new GameObject[this.capacity];
		
		for (int i = 0; i < this.object_count; i++) {
			
			aux[i] = this.list[i];
			
		}
		
		this.list = aux;
		
	}
	
	/** Decreases the capacity by 4 when unused */
	
	public void decrease() {
		
		this.capacity -= 4;
		GameObject[] aux = new GameObject[this.capacity];
		
		for (int i = 0; i < this.object_count; i++) {
			
			aux[i] = this.list[i];
			
		}
		
		this.list = aux;
		
	}
	
	public String getSaveInfo(int i) {
		
		return this.list[i].getSaveInfo();
		
	}

}
