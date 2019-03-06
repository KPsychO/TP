package simulator.factories;

import org.json.JSONObject;

import simulator.model.FallingToCenterGravity;
import simulator.model.GravityLaws;

public class FTCGBuilder extends Builder<GravityLaws> {

	public FTCGBuilder() {
		
		this.typeTag = "ftcg";
		this.desc = "Falling to center gravity law";
		
	}
	
	public GravityLaws createTheInstance(JSONObject info) {
		
		return new FallingToCenterGravity();
		
	}
	
}
