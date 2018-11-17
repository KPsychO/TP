/** 
 * Práctica 1 - TP
 * Diego Atance Sanz
 * Ela Shepherd Aréval  
 *  */
package PlantsVSZombies;

import control.Controller;
import logic.game.*;

public class PlantsVSZombies {

	/** Función main - Crea el controller y ejecuta su función Run */
	public static void main(String[] args){
		
		Controller controller = new Controller(new Game(args));
		controller.Run();
		
	}
	
}