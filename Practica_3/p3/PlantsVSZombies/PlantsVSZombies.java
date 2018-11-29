/** 
 * Práctica 2 - TP
 * @author Diego Atance Sanz
 * @author Ela Shepherd Arévalo  
 *  */
package p3.PlantsVSZombies;

import p3.control.Controller;
import p3.logic.game.*;

public class PlantsVSZombies {

	/** Well... here's where the nightmare begins... */
	
	public static void main(String[] args){
	
		Controller controller = new Controller(new Game(args));
		controller.Run();
		
	}
	
}