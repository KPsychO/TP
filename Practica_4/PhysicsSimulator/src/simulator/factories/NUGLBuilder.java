package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NewtonUniversalGravitation;

public class NUGLBuilder extends Builder<GravityLaws> {
	
	public NUGLBuilder() {
		
		this.typeTag = "nlug";
		this.desc = "Newton universal gravitation law";
		
	}
	
	public GravityLaws createTheInstance(JSONObject info){
		
		return new NewtonUniversalGravitation();
		
	}

}
