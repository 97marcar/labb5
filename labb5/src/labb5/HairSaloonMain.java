package labb5;

import labb5.hairdresser.*;
import labb5.simulator.*;

public class HairSaloonMain {
	
	public static void main (String[] args) {
		EventQueue queue = new EventQueue();
		SaloonState state = new SaloonState(queue);
		Start start = new Start(state);
		queue.add(start);
		Stop stopp = new Stop(state, 999);
		queue.add(stopp);
		Simulator sim = new Simulator(queue);
		SaloonView view = new SaloonView(state, queue);
		
		sim.start();
	}

}
