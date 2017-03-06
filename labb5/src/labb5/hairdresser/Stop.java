package labb5.hairdresser;

import java.awt.EventQueue;




import labb5.simulator.Event;

public class Stop implements Event{
	private SaloonState state;
	private double time;
	
	public Stop(SaloonState state, double time) {
		this.state = state;
		this.time = time;
		
	}

	
	public void triggerEvent() {
		//state.setCurrentEvent(this);
		
	}
	public String getName() {
		return "StopHSS";
	}

	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return time;
	}


	@Override
	public int getCustomerID() {
		// TODO Auto-generated method stub
		return 0;
	}


}
