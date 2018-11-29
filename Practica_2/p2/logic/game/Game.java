package p2.logic.game;

import java.util.*; 
import p2.logic.managers.*;
import p2.logic.objects.*;
import p2.logic.lists.*;
import p2.control.*;
import p2.factory.ZombieFactory;

/** Class "Game":
 * 
 *		Unites all the logic of the game. Implements a lot of "access" functions so you can use private methods
 *
 *  */

public class Game {
	
	private long seed;
	private Random rand;
	private int cicleCount;
	private ObjectList objectList;
	private SuncoinManager SCManager;
	private ZombieManager ZManager;
	@SuppressWarnings("unused")
	private GamePrinter GPrinter;
	private Level level;
	
	/** Assigns the starting attributes of the game.
	 * 	takes the execution arguments and assigns the seed and difficulty
	 *  */
	
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
		this.objectList = new ObjectList(this);
		this.SCManager = new SuncoinManager(this);
		this.level = Level.fromParam(args[0]);
		this.ZManager = new ZombieManager(this, level, this.seed);
				
	}
	
	/** Updates the object list by the order the objects were added to the board.
	 * 	If it's the starting turn, prints the seed.
	 * 	Adds a new zombie when corresponds.
	 * 	Increases the cycle count.
	 *  */
	
	public void update (){

		this.objectList.update();
		
		if (this.GetCurrentCicle() != 0)
			this.ZManager.AddZombie();
		
		if (this.GetCurrentCicle() == 0)
			System.out.println("Seed : " + this.seed);

		this.cicleCount++;

	}
	
	/** Checks if the game is ended and chooses the winner */
	
	public boolean endGame () {
		
		boolean Zwins = false;
		int nZombies = 0;
		
		for (int i = 0; i < this.objectList.getObjectCount(); i++) {
			
			if (this.objectList.isZombie(i)) {
				
				nZombies++;
				
				if (this.objectList.getObjectX(i) == 0)
					Zwins = true;
				
			}
			
		}
		
		if (Zwins) {
			
			for(int i  = 0; i < 40; i++) {
				
				System.out.println("\n");
			
			}
			System.out.println("GAME OVER!");
			System.out.println("Zombies win!");
			
			return true;

		}
		
		else if ((nZombies == 0) && (this.ZManager.GetZRemaining() == 0)) {
			
			for(int i  = 0; i < 40; i++) {
				
				System.out.println("\n");
			
			}
			System.out.println("GAME OVER!");
			System.out.println("Player wins!");
			
			return true;
			
		}
		
		return false;
		
		
	}
	
	/** Given a position and a dmg, decrease the object hp */
	
	public void decreaseObjectHP(int x, int y, int dmg) {
		
		this.objectList.decreaseObjectHP(x, y, dmg);
		
	}
	
	/** Given a position, deletes an object */
	
	public void deleteObject (int x, int y) {
		
		this.objectList.deleteObject(x, y);
		
	}
	
	/** Stupid boolean function to check whether an object is a plant or a zombie */
	
	public boolean isZombie (int i) {
		
		return this.objectList.isZombie(i);
		
	}
	
	/** Gives the info of an object for the release mode */
	
	public String objectToString (int i) {
		
		return this.objectList.objectToString(i);
		
	}
	
	/** Gives the info of an object for the ""debug"" mode */
	
	public String objectDebugToString(int i) {
		
		return this.objectList.objectDebugToString(i);
		
	}
	
	/** Decreases the current suncoins by the given number */
	
	public void DecreaseSC (int suns) {
		
		this.SCManager.DecreaseSC(suns);
		
	}
	
	/** Adds suncoins to the game (used by sunflower) */
	
	public void AddSC (int suns){
		
		SCManager.AddSC(suns);
		
	}
	
	// Getter functions
	
	public long GetSeed() {
		
		return this.seed;
		
	}
	
	public String GetDif() {
		
		return this.level.GetDif();
		
	}
	
	public int getObjectCount() {
		
		return this.objectList.getObjectCount();
		
	}
	
	public int getObjectX(int i) {
		
		return this.objectList.getObjectX(i);
		
	}
	
	public int getObjectY(int i) {
		
		return this.objectList.getObjectY(i);
		
	}
	
	public int GetSuns (){
		
		return SCManager.GetSuns();
		
	}
	
	public int GetZombiesRemaining() {
		
		return this.ZManager.GetZRemaining();
		
	}
	
	public int GetCurrentCicle(){
		
		return this.cicleCount;
		
	}
	
	// All the command executes
	
	public void AddZombie (){
		
		int y = this.rand.nextInt(4);
		int x = 7;
		int aux;
		String zombieSymbol = "";
		boolean add = true;
		Zombie zombie;
		
		for (int i = 0; i < this.objectList.getObjectCount(); i++) {
			
			if ((this.objectList.getObjectX(i) == x) && (this.objectList.getObjectY(i) == y)) {
				
				add = false;
				
			}
			
		}
				
		if (add) {
			
			aux = (this.rand.nextInt(ZombieFactory.getAvaliableZombies()));
			zombieSymbol = ZombieFactory.getSymbol(aux);
			zombie = ZombieFactory.getZombie(zombieSymbol, y, this.objectList.getObjectCount(), this);

			this.objectList.addZombie(zombie);

		}
	}
	
	public void addPlant(Controller controller, Plant plant) {
		
		boolean add = true;
		
		if (plant != null) {
		
			if (add) {
				
				if ((plant.getX() < 0 || plant.getX() > 6) || (plant.getY() < 0 || plant.getY() > 3)) {
					
					add = false;
					System.out.println("[ERROR]: Please, choose a cell IN the board range (0-3 / 0-6).\n");
					
				}
				
			}
	
			for (int i = 0; i < this.objectList.getObjectCount(); i++) {
				
				if ((this.objectList.getObjectX(i) == plant.getX()) && (this.objectList.getObjectY(i) == plant.getY())) {
					
					add = false;
					System.out.println("[ERROR]: Please, choose an empty cell.\n");
					
				}
				
			}
			
			
			if (add) {
				
				if (this.SCManager.GetSuns() >= plant.getCost()) {
						
							this.DecreaseSC(plant.getCost());
							this.objectList.addPlant(plant);
							
					}
					else {
						
						System.out.print("[ERROR]: No enought suncoins.\n");
						controller.setNoUpdateGameState();
						
					}
					
				}
				
			
			else {
				
				controller.setNoPrintGameState();
				controller.setNoUpdateGameState();
			}
		
		}
		
		else {
			
			System.out.print("Plant name or symbol not recognised\n");
			
		}
		
	}
	
	public void exit(Controller controller) {
		
		controller.setNoPrintGameState();
		controller.setNoUpdateGameState();
		controller.exit();
		
	}
	
	public void help (Controller controller) {
		
		controller.setNoPrintGameState();
		controller.setNoUpdateGameState();
		System.out.println(CommandParser.commandHelp());
		
	}
	
	public void reset() {
		
		this.cicleCount = 0;
		this.objectList = new ObjectList(this);
		this.SCManager = new SuncoinManager(this);
		this.level = Level.fromParam(this.GetDif());
		this.ZManager = new ZombieManager(this, level, this.seed);
		
	}
	
	public void list (Controller controller) {
		
		controller.setNoPrintGameState();
		controller.setNoUpdateGameState();
		
	}
	
	public void listZombies (Controller controller) {
		
		controller.setNoPrintGameState();
		controller.setNoUpdateGameState();
		System.out.println(ZombieFactory.listAvaliableZombies());
		
	}
	
	public void none(Controller controller) {}
	
	public void changePrintMode(Controller controller, String mode){
		
		controller.setPrintMode(mode);
		controller.setNoUpdateGameState();
		
	}
	
}
