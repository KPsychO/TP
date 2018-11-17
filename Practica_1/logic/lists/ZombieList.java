package logic.lists;

import logic.objects.Zombie;
import logic.game.Game;

public class ZombieList {
	
	// Atributes

	private Game game;
	private int zombie_count;
	private Zombie[] list;
	
	// Methods
	
	/** Crea la lista */
	
	public ZombieList(Game game){
		
		this.game = game;
		this.zombie_count = 0;
		this.list = new Zombie[32];
		
	}
	
	/** HAce update de los X de la lista */
	
	public void Update(){
		
		for (int i = 0; i < this.zombie_count; i ++){
			
			list[i].Update();
			
		}
		
	}
	
	/** Añade un elmento a la lista */
	
	public void AddZombie(int x, int y){
		
		this.list[zombie_count] = new Zombie(x, y, zombie_count, game);
		this.zombie_count ++;
		
	}
	
	/** Getters y setters de la lsita */
	
	public int GetZx (int i){

		return this.list[i].GetZx();
		
	}
	
	public int GetZy (int i){
		
		return this.list[i].GetZy();
		
	}
		
	public int GetZcount (){
		
		return this.zombie_count;
		
	}
	
	public void DecreaseZHP(int i, int dmg){
		
		this.list[i].DecreaseHP(dmg);
		
	}
	
	public void DeleteZ (int i){
		
		this.zombie_count--;	
				
		for (int j = i; j < this.zombie_count; j++){
			
			this.list[j] = this.list[j + 1];
			
		}
		
		for (int j = i; j < this.zombie_count; j++){
			
			this.list[j].Changei(j);
			
		}
		
	}
	
	public String ZtoString (int i) {
		
		return this.list[i].toString();
		
	}
	
}