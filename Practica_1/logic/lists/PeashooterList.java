package logic.lists;

import logic.objects.Peashooter;
import logic.game.Game;

public class PeashooterList {
	
	// Atributes

	private Game game;
	private int peashooter_count;
	private Peashooter[] list;
	
	// Methods
	
	/** Crea la lista */
	
	public PeashooterList(Game game){
		
		this.game = game;
		this.peashooter_count = 0;
		this.list = new Peashooter[32];
		
	}
	
	/** Hace update a todos los elementos de la lista */
	
	public void Update (){
		
		for (int i = 0; i < this.peashooter_count; i ++){
			
			list[i].Update();
			
		}
		
	}
	
	/** Elimina el PS en la posición i */
	
	public void DeletePS (int i){
		
		this.peashooter_count--;
		
		for (int j = i; j < this.peashooter_count; j++){
			
			this.list[j].Changei(i - 1);
			
		}
		
		for (int j = i; j < this.peashooter_count; j++){
			
			this.list[j] = this.list[j + 1];
			
		}
		
	}
	
	/** Getters, setters y toString */
	
	public void AddPeashooter(int x, int y){
		
		this.list[peashooter_count] = new Peashooter(x, y, game, this.peashooter_count);
		this.peashooter_count ++;
		
	}

	public int GetPSx (int i){

		return this.list[i].GetPSx();
		
	}
	
	public int GetPSy (int i){

		return this.list[i].GetPSy();
		
	}
	
	public int GetPScount (){

		return this.peashooter_count;
		
	}
	
	public void DecreasePSHP(int i, int dmg){
		
		this.list[i].DecreaseHP (dmg);
		
	}
	
	public String PStoString(int i) {
		
		return this.list[i].toString();
		
	}
	
}