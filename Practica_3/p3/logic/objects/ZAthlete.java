package p3.logic.objects;

import p3.logic.game.Game;

public class ZAthlete extends Zombie{
	
	public ZAthlete() {
		
		this.symbol = "X";
		this.hp = 2;
		this.dmg = 1;
		
	}
	
	public ZAthlete(int y, int i, Game game){
		
		this.game = game;
		this.hp = 2;
		this.dmg = 1;
		this.freq = 0;
		this.x = 7;
		this.y = y;
		this.symbol = "X";
		
	}
	
	public void update() {
		
		boolean empty = true;
		int i = 0;
		
		while (i < game.getObjectCount() && empty) {
			
			if (((this.x - 1) == game.getObjectX(i)) && ((this.y) == game.getObjectY(i))) {
				
				empty = false;
				
				if (this.game.isZombie(i) == false)
					this.game.decreaseObjectHP(this.x - 1, this.y, this.dmg);
			
			}
					
			++i;
			
		}
		
		if (empty) {
			
			this.x --;
			
		}
			
		
	}
	
	public String getSymbol() {
		
		return this.symbol;
		
	}
	
	public int getDMG() {
		
		return this.dmg;
		
	}

	public int getHP() {
		
		return this.hp;
		
	}

	public void decreaseHP(int dmg) {
		
		this.hp -= dmg;
		
		if (this.hp <= 0){
			
			game.deleteObject(this.x, this.y);
			
		}
		
	}

	public int getX() {

		return this.x;
		
	}

	public int getY() {
		
		return this.y;
		
	}
	
	public int getFreq() {
		
		return 1;
		
	}

	@Override
	public String toString() {
		
		String a;
		a = this.symbol;
		a += " [";
		a += this.hp;
		a += "]";
		
		return a;
		
	}	
	
	public String debugToString() {
		
		String a;
		
		a = this.symbol;
		a += " [hp:";
		a += this.hp;
		a += " x: ";
		a += this.x;
		a += " y: ";
		a += this.y;
		a += "]";
		
		return a;
		
	}
	
}