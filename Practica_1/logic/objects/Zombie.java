package logic.objects;

import logic.game.Game;
//import logic.lists.ZombieList;

public class Zombie {
	
	// Atributes
	
	private Game game;
	private int hp;
	private int dmg;
	private int cicleAdded;
	private int spd;
	private int x;
	private int y;
	private int i; // Almacena la posición del zombie en la lista
	
	// Methods
	
	/** Constructor de zombie */
	
	public Zombie(int x, int y, int i, Game game){
		
		this.game = game;
		this.hp = 5;
		this.dmg = 1;
		this.cicleAdded = game.GetCurrentCicle();
		this.spd = 2;
		this.x = 7;
		this.y = y;
		this.i = i;
		
	}
	
	/** Update: Comprueba si hay una planta enfrente del zombie, si la hya, le hade this.dmg daño
	 * si no hay planta, comprueba si el zombie debe avanzar ese turno, y en caso afirmativo, avanza
	 *  */
	
	public void Update(){
		
		boolean mov = true;
		int SFcount, SFx, SFy;
		int PScount, PSx, PSy;
		
		SFcount = game.GetSFcount();
		
		for (int i = 0; i < SFcount; i++){
			
			SFx = game.GetSFx(i);
			SFy = game.GetSFy(i);
			
			if ((this.y == SFy) && ((this.x - 1) == SFx)){
				
				game.DecreaseSFHP(i, this.dmg);
				mov = false;
				
			}
			
		}
		
		PScount = game.GetPScount();
		
		for (int i = 0; i < PScount; i++){
			
			PSx = game.GetPSx(i);
			PSy = game.GetPSy(i);
			
			if ((this.y == PSy) && ((this.x - 1) == PSx)){
				
				game.DecreasePSHP(i, this.dmg);
				mov = false;
				
			}
			
		}
		
		if ((mov) && ((game.GetCurrentCicle() - this.cicleAdded) % this.spd ) == 0){
			
			this.x --;
			
		}
		
	
	}
	
	/** Getters, setter y to String */
	
	public int GetZx (){

		return this.x;
		
	}
	
	public int GetZy (){

		return this.y;
		
	}
	
	public void DecreaseHP (int dmg){
		
		this.hp -= dmg;
		
		if (this.hp == 0){
			
			game.DeleteZ(this.i);
			
		}
				
	}

	public void Changei(int i){
		
		this.i = i;
		
	}
	
	public String toString (){
		
		String a;
		a = "Z [";
		a += this.hp;
		a += "]";
		
		return a;
		
	}
	
}
