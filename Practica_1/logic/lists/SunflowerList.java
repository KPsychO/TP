package logic.lists;

import logic.objects.Sunflower;
import logic.game.Game;

public class SunflowerList {
	
	// Atributes
	private Game game;
	private int sunflower_count;
	private Sunflower[] list;
	
	// Methods
	
	/** Crea la lista */
	
	public SunflowerList(Game game){
		
		this.game = game;
		this.sunflower_count = 0;
		this.list = new Sunflower[32];
		
	}
	
	/** Llama al método update de cada elemento de la lista */
	
	public void Update(){
		
		for (int i = 0; i < this.sunflower_count; i ++){
			
			list[i].Update();
			
		}
		
	}
	
	/** Elimina el SF de la posición i */
	
	public void DeleteSF (int i){
		
		this.sunflower_count--;
		
		for (int j = i; j < this.sunflower_count; j++){
			
			this.list[j].Changei(i - 1);
			
		}
		
		for (int j = i; j < this.sunflower_count; j++){
			
			this.list[j] = this.list[j + 1];
			
		}
		
	}
	
	/** Getters, setters y toString */
	
	public void AddSunflower(int x, int y){
		
		this.list[sunflower_count] = new Sunflower(x, y, game, sunflower_count);
		this.sunflower_count ++;
		
	}
	
	
	public int GetSFx (int i){

		return this.list[i].GetSFx();
		
	}
	
	public int GetSFy (int i){
		
		return this.list[i].GetSFy();
		
	}
	
	public int GetSFcount (){
		
		return this.sunflower_count;
		
	}
	
	public void DecreaseSFHP(int i, int dmg){
		
		this.list[i].DecreaseHP (dmg);
		
	}
	
	public String SFtoString(int i) {
		
		return this.list[i].toString();
				
	}
	
}