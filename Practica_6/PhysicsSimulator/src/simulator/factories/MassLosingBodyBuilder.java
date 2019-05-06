package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.MassLosingBody;

public class MassLosingBodyBuilder extends Builder<Body> {
	
	public MassLosingBodyBuilder() {
		
		this.typeTag = "mlb";
		this.desc = "Mass losing body";
		
	}
	
	public Body createTheInstance(JSONObject info) {

		double[] z = {0.0, 0.0};
		return new MassLosingBody(info.getString("id"), info.getDouble("mass"), new Vector(this.createArray(info.getJSONArray("pos"))), new Vector(new Vector(this.createArray(info.getJSONArray("vel")))), new Vector(z), info.getDouble("freq"), info.getDouble("factor"));
		
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
		json.put("factor", "Mass loss facotr");
		json.put("freq", "Mass loss freq");
		 
		return json;
	}

}