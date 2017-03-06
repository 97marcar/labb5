package labb5.hairdresser;

import labb5.simulator.Event;

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
