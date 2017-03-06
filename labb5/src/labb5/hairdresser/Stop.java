package labb5.hairdresser;

import java.awt.EventQueue;




import labb5.simulator.Event;

public class Stop implements Event{
	SaloonState state;
	EventQueue event;
	
	public Stop(SaloonState state, EventQueue event) {
		this.state = state;
		this.event = event;
		
	}

	
	public void triggerEvent() {
		//state.setCurrentEvent(this);
		
	}
	public String toString() {
		return "StopHSS";
	}


	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return 0;
	}


}
