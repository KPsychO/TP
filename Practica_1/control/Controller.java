package control;

import java.util.*;

import logic.game.*;

public class Controller {
	
	// Atributes
	
	private Game game;
		
	// Methods

	/** Contructor de Controller, instancia game */
	public Controller (Game game){
		
		this.game = game;
		game.update();
				
	}
	/** Función Run. Controla la ejecución y los comandos del juego.
	 * hace uso de game.update para pasar turno
	 * 
	 * Recibe una string como comando, usa la función split(" ") para segmentar el comando en argumentos válidos
	 * Realiza todas las comprobaciones de la precondición de cada comando
	 * 
	 * La variable "boolean pasarTurno" controla si se debe o no actalizar juego en cada comando
	 *  */
	public void Run (){
		
		String com;
		String plant;
		int x , y;
		boolean empty, pasarTurno = false;
		int i;
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		com = in.nextLine();
		com = com.trim();
		com = com.toLowerCase();
		
		while ((com != "exit") && (game.GetGameActive())){
			
			String[] comando = com.split(" ");
			
			switch (comando[0]){
			
			case "add": case "a":
				
				if (comando.length != 4) {
					
					System.out.print("[ERROR]: Comando incompleto\n");
					System.out.print("Command > ");
					pasarTurno = false;
					break;
					
				}
				
				plant = comando[1];
				plant = plant.toLowerCase();
				x = Integer.valueOf(comando[2]);
				y = Integer.valueOf(comando[3]);
				empty = true;
				
				if ((x >= 0) && (x <= 7) && (y >= 0) && (y <= 3)) {
					
					i = 0;
					while(i < game.GetSFcount() && empty){
						
						if ((game.GetSFx(i) == x) && (game.GetSFy(i) == y))
							empty = false;		
						
						i++;
					}
					
					if (empty){
					
						i = 0;
						while(i < game.GetPScount() && empty){
							
							if ((game.GetPSx(i) == x) && (game.GetPSy(i) == y))
								empty = false;
							
							i++;
						}
						
					}
					
					if (empty){
						
						i = 0;
						while(i < game.GetZcount() && empty){
							
							if ((game.GetZx(i) == x) && (game.GetZy(i) == y))
								empty = false;
							
							i++;
						}
						
					}
					
					if (empty){
									
						switch (plant){
						
						case "sunflower": case "s":
							
							if (game.GetSuns() >= game.GetSFCost()){
								
								game.AddSunflower(x, y);
								game.DecreaseSC(game.GetSFCost());
								
							}
							
							else {
								
								System.out.println("[ERROR]: No tienes suficientes SunCoins\n");
								System.out.print("Command > ");
								pasarTurno = false;
								
							}
							
							break;
						
						case "peashooter": case "p":
							
							if (game.GetSuns() >= game.GetPSCost()){
							
								game.AddPeashooter(x, y);
								game.DecreaseSC(game.GetPSCost());
								
							}	
							
							else {
								
								System.out.println("[ERROR]: No tienes suficientes SunCoins\n");
								System.out.print("Command > ");
								pasarTurno = false;
								
							}
							
							break;
							
						default:
							
							System.out.print("[ERROR]: Introduzca una planta válida\n");
							System.out.print("Command > ");
							pasarTurno = false;
							break;
						
						}
											
					}
					else {
						
						System.out.println("[ERROR]: Seleccione una casilla válida.\n");
						System.out.print("Command > ");
						pasarTurno = false;
											
					}
					
				}
								
				else {
								
					System.out.println("[ERROR]: Coordenadas no válidas:");
					System.out.println("		  Introduzca una x en el intervalo (0, 7)");
					System.out.println("		  Introduzca una y en el intervalo (0, 3)\n");
					System.out.print("Command > ");
					
					pasarTurno = false;
					
					
				}
				
				break;
								
			case "reset": case "r":
			
					this.game = new Game(new String[]{game.GetDif(), String.valueOf(game.GetSeed())});	
					pasarTurno = false;
				
				break;
				
			case "list": case "l":
				
				System.out.println("[S]unflower:	Cost:  " + game.GetSFCost() + " suncoins	Dmg: 0");
				System.out.println("[P]eashooter:	Cost:  " + game.GetPSCost() + " suncoins	Dmg: " + game.GetPSDmg() + "\n");
				System.out.print("Command > ");
				
				pasarTurno = false;
				
				break;
				
			case "exit": case "e":
				
				game.exitGame();
				
				break;
				
			case "help": case "h":
			
				System.out.println("Add <plant> <x> <y>: Adds a plant in position x, y.");
				System.out.println("List: Prints the list of available plants.");
				System.out.println("Reset: Starts a new game.");
				System.out.println("Help: Prints this help message.");
				System.out.println("Exit: Terminates the program.");
				System.out.println("[none]: Skips cycle.\\n");
				System.out.print("Command > ");

				pasarTurno = false;
				
				break;
				
			case "":
				
				pasarTurno = true;
				
				break;
				
			default:
				
				System.out.println("[ERROR]: Comando \"" + com + "\" desconocido\n");
				System.out.print("Command > ");
				pasarTurno = false;
				
				break;
				
			}
			
			if(pasarTurno) {
				
				this.game.update();
				
			}
			
			com = in.nextLine();
			com = com.toLowerCase();

		}
		
	}
	
}