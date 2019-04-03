package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import simulator.control.Controller;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int _WIDTH = 1000;
	private static final int _HEIGHT = 1000;

	private Controller _ctrl;
	
	private ControlPanel controlPanel;
	private BodiesTable bodiesTable;
	private Viewer viewer;
	private StatusBar statusBar;

	public MainWindow(Controller ctrl) {

		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();

	}

	private void initGUI() {

		setSize(_WIDTH, _HEIGHT);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel table_viewer = new JPanel();
		table_viewer.setLayout(new BoxLayout(table_viewer, BoxLayout.Y_AXIS));
		
		setContentPane(mainPanel);
		this.addControlPanel(mainPanel);
		this.addBodiesTable(table_viewer);
		this.addViewer(table_viewer);
		mainPanel.add(table_viewer);
		this.addStatusBar(mainPanel);
		this.pack();
		
	}
	
	private void addStatusBar(JPanel mainPanel) {
		
		this.statusBar = new StatusBar(_ctrl);
		mainPanel.add(this.statusBar, BorderLayout.PAGE_END);
		
	}

	private void addViewer(JPanel table_viewer) {

		this.viewer = new Viewer(_ctrl);
		this.viewer.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		this.viewer.setMaximumSize(new Dimension(this.getWidth(), this.getHeight()));
		table_viewer.add(this.viewer);

	}

	private void addBodiesTable(JPanel table_viewer) {

		this.bodiesTable = new BodiesTable(_ctrl);
		this.bodiesTable.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()/4));
		this.bodiesTable.setMaximumSize(new Dimension(this.getWidth(), this.getHeight()));
		table_viewer.add(this.bodiesTable);

	}

	private void addControlPanel(JPanel mainPanel) {

		this.controlPanel = new ControlPanel(_ctrl);
		mainPanel.add(this.controlPanel, BorderLayout.PAGE_START);

	}

}
