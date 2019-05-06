package simulator.view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar.Separator;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver {

	private static final long serialVersionUID = 1L;
	
	private JLabel time;
	private JLabel law;
	private JLabel bodies;
	
	private JLabel _currTime;
	private JLabel _currLaws;
	private JLabel _numOfBodies;

	StatusBar(Controller ctrl) {
		
		initGUI();
		ctrl.addObserver(this);
		
	}

	private void initGUI() {
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createBevelBorder(1));
		
		initLabels();
		createAndSetLabels();
		addStuff();
		
	}
	
	private void initLabels() {
		
		this._currTime = new JLabel();
		this._numOfBodies = new JLabel();
		this._currLaws = new JLabel();
		
	}
	
	private void createAndSetLabels() {
		
		this.time = new JLabel("Time: ");
		this.law = new JLabel("Law: ");
		this.bodies = new JLabel("Bodies: ");
		
	}
	
	private void addStuff() {
		
		this.add(time);
		this.add(_currTime);
		this.add(new Separator());
		
		this.add(bodies);
		this.add(_numOfBodies);
		this.add(new Separator());
		
		this.add(law);
		this.add(_currLaws);
		this.add(new Separator());
		
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		
		this._currTime.setText(String.valueOf(time));
		this._currLaws.setText(gLawsDesc);
		this._numOfBodies.setText(String.valueOf(bodies.size()));
		
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		
		this._numOfBodies.setText(String.valueOf(bodies.size()));
		this._currTime.setText(String.valueOf(0.0));
		this._currLaws.setText("none");

	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		
		this._numOfBodies.setText(String.valueOf(bodies.size()));

	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		
		this._currTime.setText(String.valueOf(time));

	}

	@Override
	public void onDeltaTimeChanged(double dt) { }

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		
		this._currLaws.setText(gLawsDesc);

	}
	
}
