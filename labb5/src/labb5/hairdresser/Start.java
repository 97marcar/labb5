package labb5.hairdresser;


import labb5.hairdresser.SaloonState;
import labb5.simulator.Event;

/**
 * Start class. It starts the first event
 * CustomerEnter with time 0.
 * 
 * 
 * 
 * @author Marcus Carlsson
 * @author Henrik Möller
 * @author Oscar Ferm
 * @since 2017-03-08
 *
 */

public class Start implements Event{
	SaloonState state;
	
	/**
	 * @param state Saloonstate
	 */
	public Start(SaloonState state) {
		this.state = state;
		
	}

	/**
	 * Creates a Customer enter
	 */
	public void triggerEvent() {
		state.createCustomer_enter(0);
		state.setChangedAndNotify();
		
	}
	
	/**
	 * @return name of the event
	 */
	public String getName() {
		return "StartHSS";
	}


	/**
	 * @return start time of the event (should always be 0)
	 */
	public double getTime() {
		return 0;
	}


	/**
	 * @return 0, No use in this event
	 */
	public int getCustomerID() {
		return 0;
	}
	
}
