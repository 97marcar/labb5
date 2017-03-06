package labb5;

import labb5.hairdresser.SaloonState;
import labb5.hairdresser.SaloonView;
import labb5.simulator.EventQueue;
import labb5.simulator.Simulator;

public class HairSaloonMain {
	
	public void main (String[] args) {
		SaloonState state = new SaloonState();
		EventQueue queue = new EventQueue();
		Simulator sim = new Simulator();
		SaloonView view = new SaloonView(state, queue);
		
	}

}
