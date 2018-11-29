/** 
 * Práctica 2 - TP
 * @author Diego Atance Sanz
 * @author Ela Shepherd Arévalo  
 *  */
package p2.PlantsVSZombies;

import p2.control.Controller;
import p2.logic.game.*;

public class PlantsVSZombies {

	/** Well... here's where the nightmare begins... */
	
	public static void main(String[] args){
	
		Controller controller = new Controller(new Game(args));
		controller.Run();
		
	}
	
}