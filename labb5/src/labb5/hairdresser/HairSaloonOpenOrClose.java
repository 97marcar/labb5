package labb5.hairdresser;

import labb5.simulator.Event;

/**
 * Class that checks the status of the shop.
 * 
 * I
 * 
 * @author Marcus Carlsson
 * @author Henrik Möller
 * @author Oscar Ferm
 * @since 2017-03-08
 *
 */

public class HairSaloonOpenOrClose implements Event{
	private SaloonState s;
	private double time;
	
	/**
	 * @param s state
	 * @param time time this occurs
	 */
	public HairSaloonOpenOrClose(SaloonState s, double time){
		this.s = s;
		this.time = time;
	}
	
	/**
	 * Changes the openState in the SaloonState
	 */
	public void triggerEvent() {
		s.changeOpenState();
		s.setChangedAndNotify();
		
	}


	/**
	 * @return start time of this event
	 */
	public double getTime() {
		return time;
	}
	
	/**
	 * @return what the event is doing
	 */
	public String getName() {
		if(s.getOpenState()){
			return "(Opening)";
		}else{
			return "(Closing)";
		}
	}

	/**
	 * No purpose in this event
	 */
	public int getCustomerID() {
		return 0;
	}
}
