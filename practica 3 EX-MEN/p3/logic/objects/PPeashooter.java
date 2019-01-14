package p3.logic.objects;

import p3.logic.game.Game;

public class PPeashooter extends Plant {
	
	public static final int COST = 50;
	
	public PPeashooter() {
		
		this.hp = 3;
		this.symbol = "P";
		this.name = "peashooter";
		this.dmg = 1;
		
	}
	
	public PPeashooter (int x, int y, Game game) {
		
		this.x = x;
		this.y = y;
		this.freq = 0;
		this.hp = 3;
		this.game = game;
		this.symbol = "P";
		this.name = "peashooter";
		this.dmg = 1;
		
	}
	
	public PPeashooter (int x, int y, int hp, int freq, Game game) {
		
		this.x = x;
		this.y = y;
		this.freq = freq;
		this.hp = hp;
		this.game = game;
		this.symbol = "P";
		this.name = "peashooter";
		this.dmg = 1;
		
	}

	public void decreaseHP(int dmg) {

		this.hp --;
		
		if (this.hp <= 0)
			game.deleteObject(this.x, this.y);
		
	}

	public void update() {
		
		int auxX = 8;
		int y  = this.y;

		if (this.freq == 0)
			freq++;
		
		else {
			
			for (int i = 0; i < game.getObjectCount(); i++) {
				
				if (game.isZombie(i)) {
					
					if ((game.getObjectY(i) == y) && (game.getObjectX(i) < auxX)) {
							
							auxX = game.getObjectX(i);
						
					}
					
				}
				
			}
			
			if (auxX != 8)
				game.decreaseObjectHP(auxX, y, this.dmg);
			
		}
		
	}
	
	public int getCost() {
		
		return PPeashooter.COST;
		
	}

	public int getX() {

		return this.x;
		
	}

	public int getY() {
		
		return this.y;
		
	}
	
	public int getHP() {
		
		return this.hp;
		
	}
	
	public String getSymbol() {
		
		return this.symbol;
		
	}
	
	public String getName() {
		
		return this.name;
		
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
	
	public String getSaveInfo() {
		
		String str = "";
		
		str += this.symbol + ":" + this.hp + ":" + this.x + ":" + this.y + ":" + "0"; 
		
		return str;
		
	}

}
