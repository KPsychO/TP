package simulator.model;

import org.json.JSONObject;

import simulator.misc.Vector;

public class Body {
	
	protected String id;
	protected double mass;
	protected Vector pos;
	protected Vector vel;
	protected Vector acc;
	
	public Body(String id, double mass, Vector pos, Vector vel, Vector acc) {
		
		this.id = id;
		this.mass = mass;
		this.pos = pos;
		this.vel = vel;
		this.acc = acc;
		
	}
	
	 public String getId() {

		 return this.id;
		 
	 }
	 
	 public Vector getVelocity() {
		 
		 return this.vel;
		 
	 }
	 
	 public Vector getAcceleration() {
		 
		 return this.acc;
		 
	 }
	 
	 public Vector getPosition() {
		 
		 return this.pos;
		 
	 }
	 
	 double getMass() {
		 
		 return this.mass;
		 
	 }
	 
	 void setVelocity(Vector v) {
		 
		 this.vel = v;
		 
	 }
	 
	 void setAcceleration(Vector a) {
		 
		 this.acc = a;
		 
	 }
	 
	 void setPosition(Vector p) {
		 
		 this.pos = p;
		 
	 }
	 
	 void move(double t) {
		 
		this.pos = this.pos.plus(this.vel.scale(t)).plus(this.acc.scale(1.0d / 2.0d).scale(t*t));
		this.vel = this.vel.plus(this.acc.scale(t));
		 
	 }
	 
	 public boolean equals(Object obj) {
		 
		 if (this == obj)
			 return true;
		 
		 if (obj == null)
			 return false;
		 
		 if(getClass() != obj.getClass())
			 return false;
		 
		 Body aux = (Body) obj;
		 
		 if(id == null) {
			 
			 if(aux.id != null)
				 return false;
			 
		 }
		 else if (!id.equals(aux.id))
			 return false;
		 
		 return true;
		 
	 }
	 
	 public JSONObject _toString() {
		 
		 JSONObject json = new JSONObject();
		 
		 json.put("id", this.id);
		 json.put("mass", this.mass);
		 json.put("pos", this.pos._toString());
		 json.put("vel", this.vel._toString());
		 json.put("acc", this.acc._toString());
		 
		 return json;
		 
	 }
	
}
