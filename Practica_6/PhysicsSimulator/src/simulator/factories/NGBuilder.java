package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NoGravity;

public class NGBuilder extends Builder<GravityLaws> {
	
	public NGBuilder() {
		
		this.typeTag = "ng";
		this.desc = "No gravity law (ng)";
		
	}

	public GravityLaws createTheInstance(JSONObject info) {
		
		return new NoGravity();
		
	}
	
}
