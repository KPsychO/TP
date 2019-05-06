package simulator.model;

import org.json.JSONObject;

import simulator.misc.Vector;

public class MassLosingBody extends Body{
	
	private double lossFactor;
	private double lossFreq;
	private double cont;	// Used to know if the MassLossingObject must loss mass.

	public MassLosingBody(String id, double mass, Vector pos, Vector vel, Vector acc, double lossFreq, double lossFactor) {
		
		super(id, mass, pos, vel, acc);
		this.lossFactor = lossFactor;
		this.lossFreq = lossFreq;
		this.cont = 0.0d;
		
	}
	
	public void move(double t) {
		 
		super.move(t);
		
		this.lossMass(t);
		 
	}

	private void lossMass(double t) {
		
		this.cont += t;
		
		if(this.cont >= this.lossFreq) {
			
			this.mass = this.mass * (1 - this.lossFactor);
			this.cont = 0.0d;
			
		}
		
	}
	
	public JSONObject _toString() {
		
		JSONObject json = new JSONObject();
		 
		 json.put("id", this.id);
		 json.put("mass", this.mass);
		 json.put("pos", this.pos._toString());
		 json.put("vel", this.vel._toString());
		 json.put("acc", this.acc._toString());
		 json.put("freq", this.lossFreq);
		 json.put("factor", this.lossFactor);

		return json;
		 
	 }
	
}
