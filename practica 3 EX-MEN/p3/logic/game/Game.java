package p3.logic.game;

import java.util.*;
import java.io.*;

import p3.Exceptions.*;
import p3.control.*;
import p3.factory.PlantFactory;
import p3.factory.ZombieFactory;
import p3.logic.lists.*;
import p3.logic.managers.*;
import p3.logic.objects.*;

/** Class "Game":
 * 
 *		Unites all the logic of the game. Implements a lot of "access" functions so you can use private methods
 *
 *  */

public class Game {
	
	private Controller controller;
	private long seed;
	private Random rand;
	private int cycleCount;
	private ObjectList objectList;
	private SuncoinManager SCManager;
	private ZombieManager ZManager;
	@SuppressWarnings("unused")
	private GamePrinter GPrinter;
	private Level level;
	
	/** Assigns the starting attributes of the game.
	 * 	takes the execution arguments and assigns the seed and difficulty
	 * @throws ArgumentException 
	 *  */
	
	public Game (String[] args) throws ArgumentException{
		
		if (args.length == 2) {
			
			if(args[1].chars().allMatch(Character::isDigit)) {
				
				if (Long.parseLong(args[1]) != 0)
					this.seed = Long.parseLong(args[1]);
				else
					this.seed = System.currentTimeMillis();
			}
			else
				throw new NumberFormatException("[ERROR]: Seed (args[1]) must be a number.\n");
		}
		
		else if (args.length == 1)
			this.seed = System.currentTimeMillis();
		
		else
			throw new ArgumentException("[ERROR]: Usage: plantsVsZombies < EASY | HARD | INSANE > [seed].\n");
		
		this.rand = new Random(this.seed);
		this.cycleCount = 0;
		this.objectList = new ObjectList(this);
		this.SCManager = new SuncoinManager(this);
		this.level = Level.fromParam(args[0].toUpperCase());
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

		this.cycleCount++;

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
		
		return this.cycleCount;
		
	}
	
	public void setController(Controller controller) {
		
		this.controller = controller;
		
	}
	
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
	
	// All the command executes
	
	public void addPlant(Plant plant) throws CommandExecuteException {
		
		boolean add = true;
		
		if (plant != null) {
		
			if (add) {
				
				if ((plant.getX() < 0 || plant.getX() > 6) || (plant.getY() < 0 || plant.getY() > 3)) {
					
					add = false;
					throw new CommandExecuteException("[ERROR]: Please, choose a cell IN the board range (0-3 / 0-6).\n");
					
				}
				
			}
	
			for (int i = 0; i < this.objectList.getObjectCount(); i++) {
				
				if ((this.objectList.getObjectX(i) == plant.getX()) && (this.objectList.getObjectY(i) == plant.getY())) {
					
					add = false;
					throw new CommandExecuteException("[ERROR]: Please, choose an empty cell.\n");
					
				}
				
			}
			
			
			if (add) {
				
				if (this.SCManager.GetSuns() >= plant.getCost()) {
						
							this.DecreaseSC(plant.getCost());
							this.objectList.addPlant(plant);
							
					}
					else {
						
						this.controller.setNoUpdateGameState();
						throw new CommandExecuteException("[ERROR]: No enought suncoins.\n");
						
					}
					
				}
				
			
			else {
				
				this.controller.setNoPrintGameState();
				this.controller.setNoUpdateGameState();
				
			}
		
		}
		
		else {
			
			throw new CommandExecuteException("[ERROR]: Unknown plant name or symbol. Use the command list (l) to see the avaliablePlants.\n");
			
		}
		
	}
	
	public void exit() {
		
		this.controller.setNoPrintGameState();
		this.controller.setNoUpdateGameState();
		this.controller.exit();
		
	}
	
	public void help () {
		
		this.controller.setNoPrintGameState();
		this.controller.setNoUpdateGameState();
		System.out.println(CommandParser.commandHelp());
		
	}
	
	public void reset() throws ArgumentException {
		
		this.cycleCount = 0;
		this.objectList = new ObjectList(this);
		this.SCManager = new SuncoinManager(this);
		this.level = Level.fromParam(this.GetDif());
		this.ZManager = new ZombieManager(this, level, this.seed);
		
	}
	
	public void list () {
		
		this.controller.setNoPrintGameState();
		this.controller.setNoUpdateGameState();
		
	}
	
	public void listZombies () {
		
		this.controller.setNoPrintGameState();
		this.controller.setNoUpdateGameState();
		System.out.println(ZombieFactory.listAvaliableZombies());
		
	}
	
	public void none() {}
	
	public void changePrintMode(String mode) throws CommandExecuteException{
		
		this.controller.setPrintMode(mode);
		this.controller.setNoUpdateGameState();
		
	}
	
	/** Method used to save the current game state in the specified file.
	 * 	@throws IOException if there's any problem while trying to create the file.
	 * */

	public void saveState(String fileName) throws IOException {
		
		String str = "";
		
		str += "// PlantsVsZombies v3.0\n\n";
		str += "cycle: " + this.cycleCount + "\n";
		str += "sunCoins: " + this.GetSuns() + "\n";
		str += "level: " + this.level.GetDif() + "\n";
		str += "remZombes: " + this.GetZombiesRemaining() + "\n";
		str += "objectList: ";
		
		for (int i  = 0; i < this.objectList.getObjectCount() - 1; i++) {
			
			str += this.objectList.getSaveInfo(i);
			str += ", ";
			
		}
		
		if (this.objectList.getObjectCount() - 1 > 0)
			str += this.objectList.getSaveInfo(this.objectList.getObjectCount() - 1) + "\n";

				
		FileWriter fw = new FileWriter(fileName + ".dat");
		BufferedWriter bw = new BufferedWriter(fw);
		
		try {
		
			bw.write(str);
		
		} finally {
		
			if (bw != null)
				bw.close();
			
			if (fw != null)
				fw.close();
		
		}
		
		System.out.print("Data saved to: " + fileName + ".dat Use load (ld) " + fileName + " to recover the saved game.\n");
		
		this.controller.setNoPrintGameState();
		this.controller.setNoUpdateGameState();
		
	}
	
	/**	Method used to load a game from a specified file.
	 * 	It makes all the necessary checks in order to avoid trying to load corrupted games or files.
	 * 	It never loads half a game, we create a kind of temporal and spatial vortex where we load the game, check all the possible things that could bring you to a problem, and only when everythin is OK, we change the current game values.
	 * 	Does NOT alter the seed and Random values.
	 * 	@throws IOException, LoadException and ArgumentException
	 * */
	
	public void loadState(String fileName) throws IOException, LoadException, ArgumentException {
		
		FileReader fr = new FileReader(fileName + ".dat");
		BufferedReader br = new BufferedReader(fr);
		
		int newGameCycle;
		int newGameSuncoins;
		Level newGameLevel;
		int newGameRemZombies;
		GameObject[] newGameObjectList;
		newGameObjectList = new GameObject[32];
		int newGameObjectCount = 0;
		
		String str;
		String[] aux;
		
		try {
			
			str = br.readLine();
			str = br.readLine();
		
			aux = null;
			str = br.readLine();
			if (str != null) {
				
				aux =  str.trim().split("\\s+");
				if (aux[1].chars().allMatch(Character::isDigit)) {
					
					if (Integer.valueOf(aux[1]) >= 0)
						newGameCycle = Integer.valueOf(aux[1]);
					else
						throw new LoadException("[ERROR]: File " + fileName + ".dat is corrupted (negative cycle value).\n");
					
				} else {
					
					throw new NumberFormatException("[ERROR]: Cycle value in the file" + fileName + ".dat is corrupted.\n");
					
				}
				
			} else {
				
				throw new LoadException("[ERROR]: File " + fileName + ".dat is corrupted (no cycle value).\n");
				
			}
			
			aux = null;
			str = br.readLine();
			if (str != null) {
				
				aux =  str.trim().split("\\s+");
				if (aux[1].chars().allMatch(Character::isDigit)) {
					
					newGameSuncoins = Integer.valueOf(aux[1]);
					
				} else {
					
					throw new NumberFormatException("[ERROR]: Suncoins value in the file " + fileName + ".dat is corrupted.\n");
					
				}
				
			} else {
				
				throw new LoadException("[ERROR]: File " + fileName + ".dat is corrupted (no suncoins value).\n");
				
			}
			
			aux = null;
			str = br.readLine();
			if (str != null) {
				
				aux =  str.trim().split("\\s+");
				newGameLevel = Level.fromParam(aux[1]);
				
			} else {
				
				throw new LoadException("[ERROR]: File " + fileName + ".dat is corrupted (no level value).\n");
				
			}
			
			aux = null;
			str = br.readLine();
			if (str != null) {
				
				aux =  str.trim().split("\\s+");
				if (aux[1].chars().allMatch(Character::isDigit)) {
					
					newGameRemZombies = Integer.valueOf(aux[1]);
					
				} else {
					
					throw new NumberFormatException("[ERROR]: remZombies value in the file " + fileName + ".dat is corrupted.\n");
					
				}
				
			} else {
				
				throw new LoadException("[ERROR]: File " + fileName + ".dat is corrupted (no remZombies value).\n");
				
			}
			
			aux = null;
			str = br.readLine();
			if (str != null) {
				
				aux =  str.trim().split("\\s+");
				String[] objectInfo = null;
				for (int i = 1; i < aux.length; i++) {
					
					objectInfo = aux[i].trim().replaceAll(",", "").split(":");
					
					if (objectInfo[1].chars().allMatch(Character::isDigit) || (objectInfo[2].chars().allMatch(Character::isDigit)) || (objectInfo[3].chars().allMatch(Character::isDigit)) || (objectInfo[4].chars().allMatch(Character::isDigit))) {
					
						if ((Integer.valueOf(objectInfo[2]) < 0 || Integer.valueOf(objectInfo[2]) > 7) || (Integer.valueOf(objectInfo[3]) < 0 || Integer.valueOf(objectInfo[3]) > 3)) {
							
							throw new LoadException("[ERROR]: objectList in file " + fileName + ".dat contains an object out of the board.\n");
							
						}
						
						for (int j = 0; j < newGameObjectCount; j++) {
							
							if ((newGameObjectList[j].getX() == Integer.valueOf(objectInfo[2])) && (newGameObjectList[j].getY() == Integer.valueOf(objectInfo[3]))) {
	
								throw new ArgumentException ("[ERROR]: objectList in file " + fileName + ".dat constains 2 elements in the same position.\n");
								
							}
							
						}
						
						switch (objectInfo[0]) {
						
						case "S": case "P": case "N": case "C": 
	
							Plant plant = PlantFactory.loadPlant(objectInfo[0], Integer.valueOf(objectInfo[2]), Integer.valueOf(objectInfo[3]), Integer.valueOf(objectInfo[1]), Integer.valueOf(objectInfo[4]), this);
							if(plant != null) {
								newGameObjectList[i - 1] = plant;
								newGameObjectCount++;	
							}
							else
								throw new LoadException("[ERROR]: objectList in file " + fileName + ".dat constains an unknown plant.\n");
							
							break;
						
						case "Z": case "X": case "W":
							
							Zombie zombie = ZombieFactory.loadZombie(objectInfo[0], Integer.valueOf(objectInfo[2]), Integer.valueOf(objectInfo[3]), Integer.valueOf(objectInfo[1]), Integer.valueOf(objectInfo[4]), this);
							if(zombie != null) {
								newGameObjectList[i - 1] = zombie;
								newGameObjectCount++;	
							}
							else
								throw new LoadException("[ERROR]: objectList in file " + fileName + ".dat constains an unknown zombie.\n");
							
							break;
	
						default: throw new LoadException("[ERROR]: objectList values in the file " + fileName + ".dat are corrupted (symbol not recognised).\n");
						
						}
						
					} else {
						
						throw new NumberFormatException("[ERROR]: objectList values in the file " + fileName + ".dat are corrupted (objectPosition is not an Integer).\n");
						
					}
					
				}
				
			} else {
				
				throw new LoadException("[ERROR]: File " + fileName + ".dat is corrupted (no objectList).\n");
				
			}
		
		} finally {
				
			if (br != null)
				br.close();
			
			if (fr != null)
				fr.close();
		
		}
		
		this.controller.setNoUpdateGameState();
		
		this.cycleCount = newGameCycle;
		this.objectList = new ObjectList(this);
		this.objectList.loadObjects(newGameObjectList, newGameObjectCount);
		this.SCManager = new SuncoinManager(this, newGameSuncoins);
		this.level = newGameLevel;
		this.ZManager = new ZombieManager(this, level, this.seed, newGameRemZombies);
		
		System.out.print("Game sucesfully loaded from file " + fileName + ".dat, enjoy!\n");
		System.out.print("##############################################################################\n");
				
	}
	
}
