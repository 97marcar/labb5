package labb5.hairdresser;

import java.awt.EventQueue;

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
		//state.setCurrentEvent(this);
		
	}
	public String getName() {
		return "StartHSS";
	}


	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
