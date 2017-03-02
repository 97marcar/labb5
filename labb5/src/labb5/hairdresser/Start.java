package labb5.simulator;

import labb5.hairdresser.SaloonState;
import labb5.simulator.Event;

public class Start implements Event{
	SaloonState state;
	EventQueue event;
	
	public Start(SaloonState state, EventQueue event) {
		this.state = state;
		this.event = event;
		
	}

	
	public void triggerEvent() {
		
		
	}
	
}
