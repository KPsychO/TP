package simulator.model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {
	
	private double move_time;
	private GravityLaws law;
	private List<Body> body_list;
	private double current_time;
	private List<SimulatorObserver> observer_list;
	
	public PhysicsSimulator(double t, GravityLaws l) throws IllegalArgumentException {
		
		if (t <= 0)
			throw new IllegalArgumentException ();
		
		this.move_time = t;
		this.law = l;
		this.body_list = new ArrayList<Body>();
		this.observer_list = new ArrayList<SimulatorObserver>();
		this.current_time = 0.0;
		
	}
	
	 public void advance() {
		 
		 this.law.apply(body_list);
		 
		 for(Body body : body_list)
			 body.move(this.move_time);
		 
		 this.current_time += this.move_time;
		 
		 for (SimulatorObserver o : observer_list)
			 o.onAdvance(body_list, current_time);
		 
	 }
	 
	 public void addBody(Body b) throws IllegalArgumentException {
		 
		 if(this.body_list.contains(b))  throw new IllegalArgumentException();
		 
		 this.body_list.add(b);
		 
		 for (SimulatorObserver o : observer_list)
			 o.onBodyAdded(body_list, b);
		 
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
	 
	 public void reset () {
		 
		 this.current_time = 0.0d;
		 this.body_list.clear();
		 
		 for (SimulatorObserver o : observer_list)
			 o.onReset(body_list, current_time, move_time, law.toString());
		 
	 }
	 
	 public void setDeltaTime(double dt) {
		 
		 if(dt <= 0.0d) throw new IllegalArgumentException();
		 
		 this.move_time = dt;
		 
		 for (SimulatorObserver o : observer_list)
			 o.onDeltaTimeChanged(move_time);
		 
	 }
	 
	 public void setGravityLaws(GravityLaws gl) {
		 
		 if(gl == null) throw new IllegalArgumentException();

		 this.law = gl;
		 
		 for (SimulatorObserver o : observer_list)
			 o.onGravityLawChanged(law.toString());
		 
	 }
	 
	 public void addObserver(SimulatorObserver o) {
		 
		 if(!observer_list.contains(o)) observer_list.add(o);
		 o.onRegister(body_list, current_time, move_time, law.toString());
		 
	 }
	 
	 

}
