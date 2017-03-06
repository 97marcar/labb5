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
//	public String toString() {
//		return "StartHSS";
//	}


	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
