package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {

	private static final long serialVersionUID = 1L;
	
	private String[] labels = { "id", "mass", "position", "velocity", "acceleration" };
	private List<Body> _bodies;

	public BodiesTableModel(Controller ctrl) {

		_bodies = new ArrayList<Body>();
		ctrl.addObserver(this);

	}
	
	@Override
	public String getColumnName(int c) {
		
		return labels[c];
		
	}

	@Override
	public int getColumnCount() {
		return labels.length;
	}

	@Override
	public int getRowCount() {
		return _bodies.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		String str = "";
		
		Body b = _bodies.get(rowIndex);
		
		switch (columnIndex) {
		
		case 0:
			str = b._toString().get("id").toString();
			break;
		case 1:
			str = b._toString().get("mass").toString();
			break;
		case 2:
			str = b._toString().get("pos").toString();
			break;
		case 3:
			str = b._toString().get("vel").toString();
			break;
		case 4:
			str = b._toString().get("acc").toString();
			break;
			
		}
		
		return str;
		
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				_bodies = bodies;
				fireTableStructureChanged();

			}

		});

	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				_bodies = bodies;
				fireTableStructureChanged();

			}

		});

	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				_bodies = bodies;
				fireTableStructureChanged();

			}

		});

	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				_bodies = bodies;
				fireTableStructureChanged();

			}

		});

	}

	@Override
	public void onDeltaTimeChanged(double dt) {
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
	}

}
