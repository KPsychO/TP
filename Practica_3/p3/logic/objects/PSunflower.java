package p3.logic.objects;

import p3.logic.game.Game;

public class PSunflower extends Plant{

	public static final int COST = 20;
	
	public PSunflower() {
		
		this.hp = 1;
		this.symbol = "S";	
		this.name = "sunflower";
		this.dmg = 0;
		
	}
	
	public PSunflower (int x, int y, Game game) {
		
		this.game = game;
		this.hp = 1;
		this.x = x;
		this.y = y;
		this.freq = 0;
		this.symbol = "S";	
		this.name = "sunflower";
		this.dmg = 0;
		
	}

	public void update() {

		if (this.freq % 2 == 1)
			game.AddSC(10);
		
		this.freq++;
		
	}

	public void decreaseHP(int dmg) {

		this.hp -= dmg;
		
		if (this.hp <= 0)
			game.deleteObject(this.x, this.y);
		
	}
	
	public int getCost() {
		
		return COST;
		
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
		a +=  2 - ((this.freq) % 2 + 1);
		a += "]";
		
		return a;
		
	}
	
	public String getSaveInfo() {
		
		String str = "";
		
		str += this.symbol + ":" + this.hp + ":" + this.x + ":" + this.y + ":" + (2 - ((this.freq) % 2 + 1)); 
		
		return str;
		
	}
	
}
