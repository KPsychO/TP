package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver {

	private static final long serialVersionUID = 1L;
	
	private Controller _ctrl;
	private boolean _stopped;
	private int _steps = 10000;
	private double _dt = 1000;
	
	private JToolBar toolbar;
	private JFileChooser fc;
	
	private JButton load_button;
	private JButton law_button;
	private JButton run_button;
	private JButton stop_button;
	private JButton reset_button;
	private JButton exit_button;
	
	private JLabel deltaTime_label;
	private JLabel steps_label;
	
	private JTextField dt_textField;
	private JSpinner steps_spinner;
	
	ControlPanel (Controller ctrl) {
		
		_ctrl = ctrl;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
		
	}
	
	private void initGUI() {
		
		toolbar = new JToolBar();
		setLayout(new BorderLayout());
		add(toolbar, BorderLayout.PAGE_START);
		
		// Toolbar
		
		// Load
		createLoadButton();
		toolbar.addSeparator();

		// GravityLaw
		createGravityLawButton();
		toolbar.addSeparator();
		
		// Run
		createRunButton();
		
		// Stop
		createStopButton();
		
		// Reset
		createResetButton();
		
		// steps
		steps_label = new JLabel();
		steps_label.setText(" Steps: ");
		toolbar.add(steps_label);
		createStepsSpinner();
		
		// delta time
		deltaTime_label = new JLabel();
		deltaTime_label.setText(" Delta time: ");
		toolbar.add(deltaTime_label);
		createDeltaTimeText();
		toolbar.addSeparator();
		
		// Exit
		createExitButton();
		
			
	}

	private void createLoadButton() {
		
		fc = new JFileChooser();
		fc.setDialogTitle("Choose a file to load the bodies from");
		load_button = new JButton();
		load_button.setToolTipText("Loads a bodies file into the simulator");
		load_button.setIcon(new ImageIcon(this.getClass().getResource("/icons/open.png")));
		load_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				int ret = fc.showOpenDialog(fc);
				if (ret == JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(fc, "You chose: " + fc.getSelectedFile());
				} else {
					JOptionPane.showMessageDialog(fc, "An error has ocurred or you canceled the selection.");
				}
				
				File file = fc.getSelectedFile();
				InputStream inputStream;
				
				try {
					inputStream = new FileInputStream(file);
					_ctrl.reset();
					_ctrl.loadBodies(inputStream);
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "An error has ocurred.");
				}					
				
			}
			
		});		
		
		toolbar.add(load_button);
		
	}
	
	private void createGravityLawButton() {
		
		List<String> laws = new ArrayList<String>();
		
		for (JSONObject f : _ctrl.getGravityLawsFactory().getInfo()) {
			
			String aux = f.getString("desc");
			laws.add(aux);
			
		}
		
		law_button = new JButton();
		law_button.setToolTipText("Select a law to aply in the simulator");
		law_button.setIcon(new ImageIcon(this.getClass().getResource("/icons/physics.png")));
		law_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Object res = JOptionPane.showInputDialog(null, "Choose a gravity law", "Gravity law choosers", JOptionPane.QUESTION_MESSAGE, null, laws.toArray(), laws.get(0));
				
				JSONObject info = new JSONObject();
				
				int i = 0;
				while (!info.has("id") && i < _ctrl.getGravityLawsFactory().getInfo().size()) {
							
					if(res != null && _ctrl.getGravityLawsFactory().getInfo().get(i).get("desc").equals(res))
						info = _ctrl.getGravityLawsFactory().getInfo().get(i);
					
					++i;
					
				}
				
				_ctrl.setGravityLawsFactory(info);

			}
		});
		
		
		toolbar.add(law_button);
		
	}
	
	private void createRunButton() {
		
		run_button = new JButton();
		run_button.setToolTipText("Runs the simulator");
		run_button.setIcon(new ImageIcon(this.getClass().getResource("/icons/run.png")));
		run_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				_stopped = false;
				disableButtonsOnRun();
				_ctrl.setDeltaTime(_dt);
				run_sim(_steps);
				
			}
			
		});		
		
		toolbar.add(run_button);
		
	}
	
	private void createStopButton() {
		
		stop_button = new JButton();
		stop_button.setToolTipText("Stops the simulator");
		stop_button.setIcon(new ImageIcon(this.getClass().getResource("/icons/stop.png")));
		stop_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				_stopped = true;
				
			}
			
		});		
		
		toolbar.add(stop_button);
		
	}
	
	private void createResetButton(){
		
		reset_button = new JButton();
		reset_button.setToolTipText("Resets the simulator");
		reset_button.setIcon(new ImageIcon(this.getClass().getResource("/icons/clear.png")));
		reset_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				_ctrl.reset();
				
			}
			
		});		
		
		toolbar.add(reset_button);
		
	}
	
	private void createExitButton() {
		
		exit_button = new JButton();
		exit_button.setAlignmentX(RIGHT_ALIGNMENT);
		exit_button.setToolTipText("Exits the simulator");
		exit_button.setIcon(new ImageIcon(this.getClass().getResource("/icons/exit.png")));
		exit_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			int res = JOptionPane.showConfirmDialog(null, "Are you sure bro?", "Warning", JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.YES_OPTION) System.exit(0);
				
			}
			
		});		
		
		toolbar.add( Box.createHorizontalGlue() );
		toolbar.add(exit_button);
		
	}
	
	private void createStepsSpinner() {
		
		steps_spinner = new JSpinner();
		steps_spinner.setValue(_steps);
		steps_spinner.setMinimumSize(new Dimension(80, 30));
		steps_spinner.setMaximumSize(new Dimension(200, 30));
		steps_spinner.setPreferredSize(new Dimension(80, 30));
		steps_spinner.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				
				_steps = Integer.valueOf(steps_spinner.getValue().toString());
				
			}
			
		});		
		
		toolbar.add(steps_spinner);
		
	}
	
	private void createDeltaTimeText() {
		
		dt_textField = new JTextField();
		dt_textField.setText(String.valueOf(_dt));
		dt_textField.setMinimumSize(new Dimension(80, 30));
		dt_textField.setMaximumSize(new Dimension(200, 30));
		dt_textField.setPreferredSize(new Dimension(80, 30));	
		dt_textField.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				_dt = Float.parseFloat(dt_textField.getText());
				
			}
			
		});		
		
		toolbar.add(dt_textField);
		
	}
	
	private void disableButtonsOnRun() {
		
		load_button.setEnabled(false);
		law_button.setEnabled(false);
		run_button.setEnabled(false);
		reset_button.setEnabled(false);
		exit_button.setEnabled(false);
		steps_spinner.setEnabled(false);
		dt_textField.setEnabled(false);
		
	}
	
	private void enableButtons() {
		
		load_button.setEnabled(true);
		law_button.setEnabled(true);
		run_button.setEnabled(true);
		reset_button.setEnabled(true);
		exit_button.setEnabled(true);
		steps_spinner.setEnabled(true);
		dt_textField.setEnabled(true);
		
	}
	
	private void run_sim(int n) {
		
		if (n > 0 && !_stopped) {
			
			try {
			
				_ctrl.run(1);
				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Se ha producido un error");
				enableButtons();
				_stopped = true;
				return;
			}
			
			SwingUtilities.invokeLater( new Runnable() { 
				
				@Override 
				public void run() { run_sim(n-1); }
				
			});
		
		} else {
			
			_stopped = true;
			enableButtons();
			
		}
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		
		SwingUtilities.invokeLater( new Runnable() { 
			
			@Override 
			public void run() { _ctrl.setDeltaTime(_dt); }
			
		});
		
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {

		SwingUtilities.invokeLater( new Runnable() { 
			
			@Override 
			public void run() { _ctrl.setDeltaTime(_dt); }
			
		});
		
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		
		SwingUtilities.invokeLater( new Runnable() { 
			
			@Override 
			public void run() { _ctrl.setDeltaTime(_dt); }
			
		});
		
	}
	
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) { }

	@Override
	public void onAdvance(List<Body> bodies, double time) { }

	@Override
	public void onGravityLawChanged(String gLawsDesc) { }

}
