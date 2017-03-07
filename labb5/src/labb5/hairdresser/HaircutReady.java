package labb5.hairdresser;

import java.util.Random;

import labb5.simulator.Event;

public class HaircutReady implements Event {
	private int randomNum;
	private Customer c;
	private SaloonState s;
	private double starttime;
	private double endtime;
	
	/**
	 * 
	 * @param s SaloonState
	 * @param c Customer
	 * @param seed randomseed
	 * @param starttime start time
	 * @param endtime end time
	 */
	public HaircutReady(SaloonState s, Customer c,double starttime){
		this.c = c;
		this.s = s;
		this.starttime = starttime;
		this.endtime = starttime+endtime;
		
	}

	
	
	/**
	 * @return chance(percent) of customer getting unsatisfied
	 */

	/**
	 * Changes the satisfaction of the customer randomly 
	 * If the customer is satisfied it is removed from the queue
	 * and can now leave in peace with a new fresh haircut
	 */
	public void triggerEvent() {
		s.setChangedAndNotify();
		s.removeFromQueue(c);
		s.continueQueue();		
	}

	/**
	 * @return start time of this event
	 */
	public double getTime() {
		return starttime;
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
