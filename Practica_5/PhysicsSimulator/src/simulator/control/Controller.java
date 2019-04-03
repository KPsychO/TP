package simulator.control;

import java.io.*;

import org.json.*;

import simulator.factories.Factory;
import simulator.model.*;

public class Controller {
	
	private PhysicsSimulator physicsSimulator;
	private Factory<Body> body_factory;
	private Factory<GravityLaws> gravityLaws_factory;

	public Controller (PhysicsSimulator ps, Factory<Body> bf, Factory<GravityLaws> glf) {
		
		this.physicsSimulator = ps;
		this.body_factory = bf;
		this.gravityLaws_factory = glf;
		
	}
	
	 public void loadBodies(InputStream in) {
		 
		 JSONObject jsonInput = new JSONObject(new JSONTokener(in)); 
		 JSONArray ja = jsonInput.getJSONArray("bodies");
		 
		 for(int i = 0; i < ja.length(); ++i) 
			 this.physicsSimulator.addBody(this.body_factory.createInstance((JSONObject) ja.get(i)));
		 
	 }
	 
	 // Batch run
	 public void run(int n, OutputStream out) {
		 
		 JSONObject json = new JSONObject();
		 JSONArray aux = new JSONArray();
		 
		 aux.put(this.physicsSimulator._toString());
		 
		 for(int i = 0; i < n; ++i) {
			 
			 this.physicsSimulator.advance();
			 aux.put(this.physicsSimulator._toString());
			 
		 }
		 
		 json.put("states", aux);

		 PrintStream ps = new PrintStream(out);
		 ps.println(json.toString());
		 ps.close();
		 
	 }
	 
	 // GUI run
	 public void run(int n) {

		 for(int i = 0; i < n; ++i)			 
			 this.physicsSimulator.advance();
		 
	 }
	 
	 public void reset() { this.physicsSimulator.reset(); }
	 
	 public void setDeltaTime(double dt) { this.physicsSimulator.setDeltaTime(dt); }
	
	 public void addObserver(SimulatorObserver o) { this.physicsSimulator.addObserver(o); }
	 
	 public Factory<GravityLaws> getGravityLawsFactory() { return this.gravityLaws_factory; }
	 
	 public void setGravityLawsFactory(JSONObject info) { this.physicsSimulator.setGravityLaws(gravityLaws_factory.createInstance(info)); }
	 
}
