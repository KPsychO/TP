package logic.game;

import java.util.*; 
import logic.lists.*;
import logic.game.GamePrinter;
import logic.managers.*;
import logic.objects.*;

public class Game {
	
	// Atributes

	private long seed;
	private boolean isGameActive;
	private Random rand;
	private int cicleCount;
	private SunflowerList SFlist;
	private PeashooterList PSlist;
	private ZombieList Zlist;
	private SuncoinManager SCManager;
	private ZombieManager ZManager;
	@SuppressWarnings("unused")
	private GamePrinter GPrinter;
	private Level level;
	
	// Methods
	/** Constructor de Game, recibe los argumentos como array de string y hace las asignaciones necesarias
	 * 		seed: Si no exite el segundo argumento o este está vacio, genera una nueva seed a partir de Syste.curentTimeMillis()
	 * 		rand: genera un nuevo Random a partir de la seed
	 * 
	 * 		inicializa todas las listas, controllers y asigna el nivel usando la función fromParam del enumerado
	 * 
	 *  	*/
	public Game (String[] args){
		
		if (args.length > 1) {
			if (Long.parseLong(args[1]) != 0)
					this.seed = Long.parseLong(args[1]);
			else
				this.seed = System.currentTimeMillis();
		}
		
		else
			this.seed = System.currentTimeMillis();
		
		this.rand = new Random(this.seed);
		this.cicleCount = 0;
		this.isGameActive = true;
		this.SFlist = new SunflowerList(this);
		this.PSlist = new PeashooterList(this);
		this.Zlist = new ZombieList(this);
		this.SCManager = new SuncoinManager(this);
		this.level = Level.fromParam(args[0]);
		this.ZManager = new ZombieManager(this, level, this.seed);
				
	}
	
	/** Actualiza game.
	 * 
	 * 		actualiza Sunflowers, peashooters y zombies por ese orden
	 * 		comprueba si debe o no añadir un zombie
	 * 		imprime toda la información del juego (seed solo en el ciclo 0)
	 * 		aumenta el contador de ciclos
	 * 
	 *  */
	
	public void update (){

		this.SFlist.Update();
		this.PSlist.Update();
		this.Zlist.Update();
		
		if (this.GetCurrentCicle() != 0)
			this.ZManager.AddZombie();
		
		if (this.GetCurrentCicle() == 0)
			System.out.println("Seed : " + this.seed);
		
		System.out.println("Current cicle : " + this.GetCurrentCicle());
		System.out.println("SunCoins : " + this.GetSuns());
		System.out.println("Zombies remaining : " + this.GetZombiesRemaining());
		System.out.println("###########################");
		
		System.out.println(this.GPrinter = new GamePrinter (this, 4, 8));
		
		System.out.print("Command > ");
		
		this.endGame();
		this.cicleCount++;

	}
	
	/** Comprueba si el juego ha finalizado y modifica la variable boolena gameIsActive, lo que finaliza la ejecución del bucle de controller.Run() */
	
	public void endGame () {
		
		boolean Zwins = false;
		
		for (int i = 0; i < this.GetZcount(); i++) {
			
			if (this.GetZx(i) == 0)
				Zwins = true;
			
		}
		
		if ((this.GetZcount() == 0) && (this.ZManager.GetZRemaining() == 0)) {
			
			this.isGameActive = false;
			for(int i  = 0; i < 40; i++) {
				
				System.out.println("\n");
			
			}
			System.out.println("GAME OVER!");
			System.out.println("Player wins!");
			
		}
		
		else if (Zwins) {
			
			this.isGameActive = false;
			for(int i  = 0; i < 40; i++) {
				
				System.out.println("\n");
			
			}
			System.out.println("GAME OVER!");
			System.out.println("Zombies win!");

		}
		
		
	}
	
	/** Getters y setters de game  y llamadas a funciones en SFlist, PSlist y Zlist */
	
	public long GetSeed() {
		
		return this.seed;
		
	}
	
	public void exitGame() {
		
		this.isGameActive = false;
		
	}
	
	public boolean GetGameActive() {
		
		return this.isGameActive;
		
	}
	
	public String GetDif() {
		
		return this.level.GetDif();
		
	}
	
	public void DecreaseSC (int suns) {
		
		this.SCManager.DecreaseSC(suns);
		
	}
	
	public int GetSuns (){
		
		return SCManager.GetSuns();
		
	}
	
	public void AddSC (int suns){
		
		SCManager.AddSC(suns);
		
	}
	
	public int GetSFCost(){
		
		return Sunflower.COST;
		
	}

	public int GetSFx(int i){

		return SFlist.GetSFx(i);
		
	}
	
	public int GetSFy(int i){

		return SFlist.GetSFy(i);
		
	}
	
	public int GetSFcount(){

		return SFlist.GetSFcount();
		
	}
	
	public void DecreaseSFHP (int i, int dmg){
		
		SFlist.DecreaseSFHP(i, dmg);
		
	}
	
	public int GetPSCost(){
		
		return Peashooter.COST;
		
	}
	
	public int GetPSx(int i){

		return PSlist.GetPSx(i);
		
	}
	
	public int GetPSy(int i){

		return PSlist.GetPSy(i);
		
	}
	
	public int GetPSDmg () {
		
		return Peashooter.DMG;
		
	}
	
	public int GetPScount(){

		return PSlist.GetPScount();
		
	}
	
	public void DecreasePSHP (int i, int dmg){
		
		PSlist.DecreasePSHP(i, dmg);
		
	}
	
	public int GetZx(int i){

		return Zlist.GetZx(i);
		
	}
	
	public int GetZy(int i){

		return Zlist.GetZy(i);
		
	}
	
	public int GetZcount(){

		return Zlist.GetZcount();
		
	}
	
	public int GetZombiesRemaining() {
		
		return this.ZManager.GetZRemaining();
		
	}
	
	public void DecreaseZHP (int i, int dmg){
		
		Zlist.DecreaseZHP(i, dmg);
		
	}
	
	public void DeleteZ (int i){
		
		Zlist.DeleteZ(i);
		
	}
	
	public void DeletePS (int i){
		
		PSlist.DeletePS(i);
		
	}
	
	public void DeleteSF (int i) {
		
		SFlist.DeleteSF(i);
		
	}
	
	public int GetCurrentCicle(){
		
		return this.cicleCount;
		
	}
	
	public String PStoString(int i) {
		
		return PSlist.PStoString(i);
		
	}
	
	public String SFtoString(int i) {
		
		return SFlist.SFtoString(i);
		
	}
	
	public String ZtoString (int i) {
	
		return this.Zlist.ZtoString(i);
		
	}
	
	public void AddSunflower (int x, int y){
		
		SFlist.AddSunflower(x, y);
		
	}
	
	public void AddPeashooter (int x, int y){
		
		PSlist.AddPeashooter(x, y);
		
	}
	
	
	/** Hace las comprobaciones necesarias para añadir un zombie respecto al tablero, y, en caso de ser posible, llama  aZombieManager para añadirlo si el turno corresponde */
	
	public void AddZombie (){
		
		int y = this.rand.nextInt(4);
		int x = 7;
		boolean add = true;
		
		for (int i = 0; i < Zlist.GetZcount(); i++) {
			
			if ((Zlist.GetZx(i) == x) && (Zlist.GetZy(i) == y)) {
				
				add = false;
				
			}
			
		}
		
		for (int i = 0; i < PSlist.GetPScount(); i++) {
			
			if ((PSlist.GetPSx(i) == x) && (PSlist.GetPSy(i) == y)) {
				
				PSlist.DeletePS(i);
				
			}
			
		}

		for (int i = 0; i < SFlist.GetSFcount(); i++) {
	
			if ((SFlist.GetSFx(i) == x) && (SFlist.GetSFy(i) == y)) {
		
				SFlist.DeleteSF(i);
		
			}
	
		}
				
		if (add) {
		
			this.Zlist.AddZombie(x, y);
			
		}
		
	}
	
}
