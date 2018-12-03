package p3.logic.objects;

import p3.logic.game.Game;

public class PCherry extends Plant {
	
	public static final int COST = 50;
	
	public PCherry() {
		
		this.hp = 2;
		this.symbol = "C";
		this.name = "cherry";
		this.dmg = 10;
		
	}
	
	public PCherry (int x, int y, Game game) {
		
		this.x = x;
		this.y = y;
		this.freq = 2;
		this.hp = 2;
		this.game = game;
		this.symbol = "C";
		this.name = "cherry";
		this.dmg = 10;
		
	}
	
	public PCherry (int x, int y, int hp, int freq, Game game) {
		
		this.x = x;
		this.y = y;
		this.freq = freq;
		this.hp = hp;
		this.game = game;
		this.symbol = "C";
		this.name = "cherry";
		this.dmg = 1;
		
	}

	public void decreaseHP(int dmg) {

		this.hp -= dmg;
		
		if (this.hp <= 0)
			game.deleteObject(this.x, this.y);
		
	}

	public void update() {
		
		if (this.freq > 0)
			freq--;
		
		else {
			
			game.decreaseObjectHP(this.x-1, this.y, this.dmg);
			game.decreaseObjectHP(this.x+1, this.y, this.dmg);
			game.decreaseObjectHP(this.x, this.y-1, this.dmg);
			game.decreaseObjectHP(this.x, this.y+1, this.dmg);
			game.deleteObject(this.x, this.y);
			
		}
		
	}
	
	public int getCost() {
		
		return PCherry.COST;
		
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
		a += " t: ";
		a += this.freq;
		a += "]";
		
		return a;
		
	}
	
	public String getSaveInfo() {
		
		String str = "";
		
		str += this.symbol + ":" + this.hp + ":" + this.x + ":" + this.y + ":" + this.freq; 
		
		return str;
		
	}

}
