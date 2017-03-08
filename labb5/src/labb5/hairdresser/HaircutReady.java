package labb5.hairdresser;

import java.util.Random;


import labb5.simulator.Event;

/**
 * 
 * 
 * 
 * @author Marcus Carlsson
 * @author Henrik M�ller
 * @author Oscar Ferm
 * @since 2017-03-08
 *
 */

public class HaircutReady implements Event {
	private Customer c;
	private SaloonState s;
	private double time;
	
	/**
	 * 
	 * @param s SaloonState
	 * @param c Customer
	 * @param starttime start time
	 */
	public HaircutReady(SaloonState s, Customer c,double time){
		this.c = c;
		this.s = s;
		this.time = time;
		
	}

	public void triggerEvent() {
		s.setChangedAndNotify();
		s.removeFromQueue(c);
		s.randomSatisfaction(c, time);
		s.continueQueue(time);		
	}

	/**
	 * @return start time of this event
	 */
	public double getTime() {
		return time;
	}

	/**
	 * @return name of this event	
	 *  */
	public String getName() {
		return "Done";
	}

	/**
	 * @return cutomerID
	 */
	public int getCustomerID() {
		return c.getId();
	}
}
