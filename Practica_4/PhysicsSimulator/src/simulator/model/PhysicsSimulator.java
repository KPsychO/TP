package simulator.model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {
	
	private double move_time;
	private GravityLaws law;
	private List<Body> body_list;
	private double current_time;
	
	public PhysicsSimulator(double t, GravityLaws l) throws IllegalArgumentException {
		
		if (t <= 0)
			throw new IllegalArgumentException ();
		
		this.move_time = t;
		this.law = l;
		this.body_list = new ArrayList<Body>();
		this.current_time = 0.0;
		
	}
	
	 public void advance() {
		 
		 this.law.apply(body_list);
		 
		 for(Body body : body_list)
			 body.move(this.move_time);
		 
		 this.current_time += this.move_time;
		 
	 }
	 
	 public void addBody(Body b) throws IllegalArgumentException {
		 
		 if(this.body_list.contains(b))  throw new IllegalArgumentException();
		 
		 this.body_list.add(b);
		 
	 }
	 
	 public JSONObject _toString() {
		 
		 JSONObject json = new JSONObject();
		 
		 json.put("time", this.current_time);
		 
		 JSONArray aux = new JSONArray();
		 for(Body b : body_list)
			 aux.put(b._toString());
		 
		 json.put("bodies", aux);

		 return json;
		 
	 }

}
