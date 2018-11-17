package logic.objects;

import logic.game.Game;

public class Peashooter {
	
	// Atributes
	
	public static final int COST = 50;
	public static final int DMG = 1;
	private Game game;
	private int hp;
	private int cost;
	private int x;
	private int y;
	private int i;
	private int cicleAdded;
	
	// Methods
	
	/** Constructor de Peashooter */
	
	public Peashooter(int x, int y, Game game, int i){
		
		this.i = i;
		this.game = game;
		this.hp = 3;
		this.cost = 50;
		this.x = x;
		this.y = y;
		this.cicleAdded = game.GetCurrentCicle();
		
	}
	
	/** Método Update: Comprueba si debe disparar, en caso afirmativo, comprueba que haya zombies en la misma fila que el PS, busca el primero de la fila (en caso de haber varios) y le hace this.dmg daño */
	
	public void Update(){
		
		int Zxmin = 8, j = 0;
		Boolean found = false, atck = false;
		
		if (game.GetCurrentCicle() != this.cicleAdded) {

			for (int i = 0; i < game.GetZcount(); i++) {
				
				if (this.y == game.GetZy(i)) {
					
					atck = true;
					
					for (int k = 0; k < game.GetZcount(); k++) {
						
						if (game.GetZx(i) < Zxmin) {
							
							Zxmin = game.GetZx(i);
							
						}
						
					}
					
				}
				
			}
			
			if (atck){
			
				while (j < game.GetZcount() && !found){
					
					if ((game.GetZx(j) == Zxmin) && (game.GetZy(j) == this.y)){
						
						game.DecreaseZHP(j, DMG);
						found = true;
						
					}
					
					j++;
				}
			
			}
		
		}
		
	}
	
	
	/** Getters, setter y toString */
	
	public int GetCost (){
		
		return this.cost;
		
	}
	
	public int GetPSx (){

		return this.x;
		
	}
	
	public int GetPSy (){
		
		return this.y;
		
	}
	
	public void DecreaseHP (int dmg){
		
		this.hp -= dmg;
		
		if (this.hp == 0){
			
			game.DeletePS(this.i);
			
		}
		
	}
	
	public void Changei(int i){
		
		this.i = i;
		
	}
	
	public String toString (){
		
		String a;
		a = "P [";
		a += this.hp;
		a += "]";
		
		return a;
		
	}

}