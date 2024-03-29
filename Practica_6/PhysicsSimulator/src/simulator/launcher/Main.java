package simulator.launcher;

import java.io.*;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import org.apache.commons.cli.*;
import org.json.JSONObject;

import simulator.control.Controller;
import simulator.factories.*;
import simulator.model.*;
import simulator.view.MainWindow;

/*
 * Examples of command-line parameters:
 * 
 *  -h
 *  -i resources/examples/ex4.4body.txt -s 100
 *  -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100
 *  -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100 -gl ftcg
 *  -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100 -gl nlug
 *
 */

public class Main {

	// default values for some parameters
	//
	private final static Double _dtimeDefaultValue = 2500.0;

	// some attributes to stores values corresponding to command-line parameters
	//
	private static Double _dtime = null;
	private static String _inFile = null;
	private static String _outFile = null;
	private static String _mode = null;
	private static int _steps = 150;
	private static JSONObject _gravityLawsInfo = null;

	// factories
	private static Factory<Body> _bodyFactory;
	private static Factory<GravityLaws> _gravityLawsFactory;

	private static void init() {

		// initialize the bodies factory
		// ...
		ArrayList<Builder<Body>> bodyBuilders = new ArrayList<>();
		bodyBuilders.add(new BasicBodyBuilder());
		bodyBuilders.add(new MassLosingBodyBuilder());
		_bodyFactory = new FactoryBasedBuilder<Body>(bodyBuilders);

		// initialize the gravity laws factory
		// ...
		ArrayList<Builder<GravityLaws>> gravityLawsBuilders = new ArrayList<>();
		gravityLawsBuilders.add(new FTCGBuilder());
		gravityLawsBuilders.add(new NGBuilder());
		gravityLawsBuilders.add(new NUGLBuilder());
		_gravityLawsFactory = new FactoryBasedBuilder<GravityLaws>(gravityLawsBuilders);
	}

	private static void parseArgs(String[] args) {

		// define the valid command line options
		//
		Options cmdLineOptions = buildOptions();

		// parse the command line as provided in args
		//
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine line = parser.parse(cmdLineOptions, args);
			parseHelpOption(line, cmdLineOptions);
			parseInFileOption(line);
			parseDeltaTimeOption(line);
			parseGravityLawsOption(line);
			parseOutFileOption(line);
			parseStepsOption(line);
			parseModeOption(line);

			// if there are some remaining arguments, then something wrong is
			// provided in the command line!

			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}

	private static Options buildOptions() {

		Options cmdLineOptions = new Options();

		// help
		cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message.").build());

		// input file
		cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg().desc("Bodies JSON input file.").build());

		// delta-time
		cmdLineOptions.addOption(Option.builder("dt").longOpt("delta-time").hasArg()
				.desc("A double representing actual time, in seconds, per simulation step. Default value: "
						+ _dtimeDefaultValue + ".")
				.build());

		// output
		cmdLineOptions.addOption(Option.builder("o").longOpt("output").hasArg().desc("Sets the output file.").build());

		// steps
		cmdLineOptions.addOption(Option.builder("s").longOpt("steps").hasArg()
				.desc("Sets the number of steps for the simulation (default = 150).").build());

		// mode
		cmdLineOptions.addOption(
				Option.builder("m").longOpt("mode").hasArg().desc("Sets the mode for the simulation.").build());

		// gravity laws -- there is a workaround to make it work even when
		// _gravityLawsFactory is null.
		//
		String gravityLawsValues = "N/A";
		String defaultGravityLawsValue = "N/A";
		if (_gravityLawsFactory != null) {
			gravityLawsValues = "";
			for (JSONObject fe : _gravityLawsFactory.getInfo()) {
				if (gravityLawsValues.length() > 0) {
					gravityLawsValues = gravityLawsValues + ", ";
				}
				gravityLawsValues = gravityLawsValues + "'" + fe.getString("type") + "' (" + fe.getString("desc") + ")";
			}
			defaultGravityLawsValue = _gravityLawsFactory.getInfo().get(0).getString("type");
		}
		cmdLineOptions.addOption(Option.builder("gl").longOpt("gravity-laws").hasArg()
				.desc("Gravity laws to be used in the simulator. Possible values: " + gravityLawsValues
						+ ". Default value: '" + defaultGravityLawsValue + "'.")
				.build());

		return cmdLineOptions;
	}

	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}

	private static void parseInFileOption(CommandLine line) throws ParseException {
		if (line.hasOption("o")) {
			_inFile = line.getOptionValue("i");
			if (_inFile == null) {
				throw new ParseException("An input file of bodies is required");
		}
		}
	}

	private static void parseDeltaTimeOption(CommandLine line) throws ParseException {
		String dt = line.getOptionValue("dt", _dtimeDefaultValue.toString());
		try {
			_dtime = Double.parseDouble(dt);
			assert (_dtime > 0);
		} catch (Exception e) {
			throw new ParseException("Invalid delta-time value: " + dt);
		}
	}

	private static void parseGravityLawsOption(CommandLine line) throws ParseException {

		// this line is just a work around to make it work even when _gravityLawsFactory
		// is null, you can remove it when've defined _gravityLawsFactory
		if (_gravityLawsFactory == null)
			return;

		String gl = line.getOptionValue("gl");
		if (gl != null) {
			for (JSONObject fe : _gravityLawsFactory.getInfo()) {
				if (gl.equals(fe.getString("type"))) {
					_gravityLawsInfo = fe;
					break;
				}
			}
			if (_gravityLawsInfo == null) {
				throw new ParseException("Invalid gravity laws: " + gl);
			}
		} else {
			_gravityLawsInfo = _gravityLawsFactory.getInfo().get(0);
		}
	}

	private static void parseOutFileOption(CommandLine line) throws ParseException {
		if (line.hasOption("o")) {
			_outFile = line.getOptionValue("o");
			if (_outFile == null) {
				throw new ParseException("An output file of bodies is required");
			}
		}
	}

	private static void parseStepsOption(CommandLine line) throws ParseException {
		if (line.hasOption("s")) {
			_steps = Integer.valueOf(line.getOptionValue("s"));
			if (_steps == 150) {
				throw new ParseException("Default value = 150");
			}
		}
	}

	private static void parseModeOption(CommandLine line) throws ParseException {
		_mode = line.getOptionValue("m");
		if (_mode == null) {
			throw new ParseException("An execution mode is required");
		}
	}

	private static void startBatchMode() throws Exception {

		// create and connect components, then start the simulator
		GravityLaws l = _gravityLawsFactory.createInstance(_gravityLawsInfo);
		PhysicsSimulator ps = new PhysicsSimulator(_dtime, l);
		Controller c = new Controller(ps, _bodyFactory, _gravityLawsFactory);

		InputStream input = new FileInputStream(_inFile);
		OutputStream output = new FileOutputStream(_outFile);

		c.loadBodies(input);
		c.run(_steps, output);

	}

	private static void startGUIMode() throws Exception {

		Controller c;
		// create and connect components, then start the simulator
		if(_gravityLawsInfo != null) {
			GravityLaws l = _gravityLawsFactory.createInstance(_gravityLawsInfo);
			PhysicsSimulator ps = new PhysicsSimulator(_dtime, l);
			c = new Controller(ps, _bodyFactory, _gravityLawsFactory);
		}
		else {
			PhysicsSimulator ps = new PhysicsSimulator(_dtime, null);
			c = new Controller(ps, _bodyFactory, _gravityLawsFactory);	
		}

		if (_inFile != null) {
			InputStream input = new FileInputStream(_inFile);
			c.loadBodies(input);
		}

		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				new MainWindow(c);
			}
		});

	}

	private static void start(String[] args) throws Exception {

		parseArgs(args);

		if (_mode.equals("batch"))
			startBatchMode();
		else
			startGUIMode();

	}

	public static void main(String[] args) {
		try {
			init();
			start(args);
		} catch (Exception e) {
			System.err.println("Something went wrong ...");
			System.err.println();
			e.printStackTrace();
		}
	}

}
