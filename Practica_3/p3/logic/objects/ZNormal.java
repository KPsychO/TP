package p3.logic.objects;

import p3.logic.game.Game;

public class ZNormal extends Zombie{
	
	public ZNormal() {
		
		this.symbol = "Z";
		this.hp = 5;
		this.dmg = 1;
		
	}
	
	public ZNormal(int y, int i, Game game){
		
		this.game = game;
		this.hp = 5;
		this.dmg = 1;
		this.freq = 0;
		this.x = 7;
		this.y = y;
		this.symbol = "Z";
		
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
			
			if (this.freq % 2 == 1) {
				
				this.x --;
				
			}
			
			this.freq++;
			
		}
			
	}
	
	public int getDMG() {
		
		return this.dmg;
		
	}

	public int getHP() {
		
		return this.hp;
		
	}
	
	public int getFreq() {
		
		return 2;
		
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
	
	public String getSymbol() {
		
		return this.symbol;
		
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
		a += " t: ";
		a += 2 - ((this.freq % 2) + 1);
		a += "]";
		
		return a;
		
	}
	
}
