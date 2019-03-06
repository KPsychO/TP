package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body> {
	
	public BasicBodyBuilder() {
		
		this.typeTag = "basic";
		this.desc = "Basic body";
		
	}
	
	public Body createTheInstance(JSONObject info) {

		double[] z = {0.0, 0.0};
		return new Body(info.getString("id"), info.getDouble("mass"), new Vector(this.createArray(info.getJSONArray("pos"))), new Vector(new Vector(this.createArray(info.getJSONArray("vel")))), new Vector(z));
		
	}
	
	private double[] createArray(JSONArray arr) {
		
		double[] ret = new double[arr.length()];
		
		for(int i = 0; i < arr.length(); ++i)
			ret[i] = arr.getDouble(i);
		
		return ret;
		
	}

	public JSONObject getData() {
		
		JSONObject json = new JSONObject();
		
		json.put("id", "id");
		json.put("mass", "mass");
		json.put("pos", "pos");
		json.put("vel", "vel");
		json.put("acc", "acc");
		 
		return json;
	}

}