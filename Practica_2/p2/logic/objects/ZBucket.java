package p2.logic.objects;

import p2.logic.game.Game;

public class ZBucket extends Zombie{
	
	public ZBucket() {
		
		this.symbol = "W";
		this.hp = 8;
		this.dmg = 1;
		
	}
	
	public ZBucket(int y, int i, Game game){
		
		this.game = game;
		this.hp = 8;
		this.dmg = 1;
		this.freq = 0;
		this.x = 7;
		this.y = y;
		this.symbol = "W";
		
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
			
			if (this.freq % 4 == 3) {
				
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
		
		return 4;
		
	}

	public void decreaseHP(int dmg) {
		
		this.hp -= dmg;
		
		if (this.hp <= 0){
			
			game.deleteObject(this.x, this.y);
			
		}
		
	}
	
	public String getSymbol() {
		
		return this.symbol;
		
	}

	public int getX() {

		return this.x;
		
	}

	public int getY() {
		
		return this.y;
		
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
		a +=  4 - ((this.freq) % 4 + 1);
		a += "]";
		
		return a;
		
	}
	
}