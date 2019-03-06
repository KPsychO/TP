package simulator.model;

import java.util.List;

import simulator.misc.Vector;

public class NewtonUniversalGravitation implements GravityLaws{

	private final double G = 6.67E-11d;
	
	public void apply(List<Body>bodies) {
		
		for(Body bi : bodies) {
			
			double[] z = new double[bi.acc.dim()];
			
			createZ(z, bi.acc.dim());
			
			Vector aux = new Vector(bi.getPosition().dim());	// dim pa porsi nos meten cuerpos en espacios 3D

			for(Body bj : bodies) {
				
				if(!(bi.getId().equals(bj.getId()))) {
					
					if(bi.getMass() == 0.0d) {
						
						bi.setAcceleration(new Vector(z));
						bi.setVelocity(new Vector(z));
						
					}
					
					else {
						
						aux = aux.plus(bj.getPosition().minus(bi.getPosition()).direction().scale((G * bi.getMass() * bj.getMass() / ((bj.getPosition().distanceTo(bi.getPosition())) * (bj.getPosition().distanceTo(bi.getPosition()))))));
												
					}
					
				}
				
			}
			
			bi.setAcceleration(aux.scale(1.0d / bi.getMass()));
			
		}
		
	}
	
	private void createZ(double[] z, int dim) {
		
		for (int i = 0; i < dim; ++i) {
			
			z[i] = 0.0d;
			
		}
		
	}

}
