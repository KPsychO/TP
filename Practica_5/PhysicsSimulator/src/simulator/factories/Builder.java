package simulator.factories;

import org.json.JSONObject;

public abstract class Builder<T> {
	
	protected String typeTag;
	protected String desc;

	public abstract T createTheInstance(JSONObject info) throws IllegalArgumentException;

	public JSONObject getBuilderInfo() {
		
		JSONObject json = new JSONObject();
		 
		json.put("type", this.typeTag);
		json.put("desc", this.desc);
		json.put("data", this.getData());
		
		return json;
		
	}
	
	public JSONObject getData() {
		
		return new JSONObject();
		
	}
	
}
