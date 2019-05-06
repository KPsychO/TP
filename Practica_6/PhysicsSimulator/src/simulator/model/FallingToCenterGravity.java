package simulator.model;

import java.util.List;

public class FallingToCenterGravity implements GravityLaws{
	
	private final double g = 9.81d;

	public void apply(List<Body> bodies) {

		for(Body b : bodies) {
			
			b.setAcceleration(b.pos.direction().scale(-g));
			
		}
		
	}
	
	public String toString() {
		
		return "Falling to center gravity";
		
	}

}
