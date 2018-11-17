package logic.objects;

import logic.game.Game;

public class Sunflower {
	
	// Atributes
	
	public static final int COST = 20;
	private int i;
	private Game game;
	private int hp;
	private int cicle_added; // Si no va, usar freq y ResetFreq() cada vez que actue
	private int x;
	private int y;
	
	// Methods
	
	/** Constructor de Sunflower */
	
	public Sunflower(int x, int y,  Game game, int i){
		
		this.i = i;
		this.game = game;
		this.hp = 1;
		this.cicle_added = game.GetCurrentCicle();
		this.x = x;
		this.y = y;
		
	}
	
	/** Añade suncoins al juego si el turno el apropiado */
	
	public void Update(){
		
		if (((game.GetCurrentCicle() - this.cicle_added) % 2) == 0){
			
			game.AddSC(10);
			
		}
		
	}
	
	/** Getters, stters y toString */
	
	public int GetSFx (){

		return this.x;
		
	}
	
	public int GetSFy (){

		return this.y;
		
	}
	
	public void DecreaseHP (int dmg){
		
		this.hp -= dmg;
		
		if (this.hp == 0){
			
			game.DeleteSF(this.i);
			
		}
		
	}
	
	public void Changei(int i){
		
		this.i = i;
		
	}
	
	public String toString (){
		
		String a;
		a = "S [";
		a += this.hp;
		a += "]";
		
		return a;
		
	}
	
}