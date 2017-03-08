package labb5;

import labb5.hairdresser.*;
import labb5.simulator.*;

/**
 * Main class, runs the simulator. Indata variables are declared here (created
 * in SaloonState). Main controls which variables that will be used in the
 * indata aswell as starting the simulation.
 * 
 * @author Marcus Carlsson
 * @author Henrik Möller
 * @author Oscar Ferm
 * @since 2017-03-08
 *
 */

public class HairSaloonMain {

	public static void main(String[] args) {
//		double hmin = 0.8, hmax = 1.2;
//		double dmin = 2.0, dmax = 3.0;
//		long seed = 1116;
//		double lambda = 3;
//		int dressers = 3;
//		int waitchairs = 4;
//		double closingtime = 8.00;
//		int fail_procent = 25;

		
		  double hmin = 1, hmax = 2; double dmin = 1.0, dmax = 2.0; long seed =
		  1116; double lambda = 1.2; int dressers = 2; int waitchairs = 2;
		  double closingtime = 7.00; int fail_procent = 50;
		 

		EventQueue queue = new EventQueue();
		SaloonState state = new SaloonState(queue, closingtime, fail_procent, dressers, waitchairs, hmin, hmax, dmin,
				dmax, lambda, seed);

		Start start = new Start(state);
		queue.add(start);
		Stop stopp = new Stop(state, 999);
		queue.add(stopp);
		Simulator sim = new Simulator(queue);
		SaloonView view = new SaloonView(state, queue);

		sim.start();
		view.saloonEndLayout();
	}

}
