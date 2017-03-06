package labb5.hairdresser;

import java.awt.EventQueue;

import labb5.hairdresser.SaloonState;
import labb5.simulator.Event;

public class Start implements Event{
	SaloonState state;
	
	public Start(SaloonState state) {
		this.state = state;
		
	}

	
	public void triggerEvent() {
		state.createCustomer_enter();
		
	}
	public String getName() {
		return "StartHSS";
	}


	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getCustomerID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
