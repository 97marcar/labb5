package labb5.hairdresser;

import java.util.Random;

import labb5.simulator.Event;

public class HaircutReady implements Event {
	private Random randy;
	private Customer c;
	private SaloonState s;
	private double starttime;
	private double endtime;
	public final int FAIL_PROCENT; //Change of failiure
	
	/**
	 * 
	 * @param s SaloonState
	 * @param c Customer
	 * @param seed randomseed
	 * @param starttime start time
	 * @param endtime end time
	 */
	public HaircutReady(SaloonState s, Customer c,long seed, 
			double starttime, double endtime, int FAIL_PROCENT){
		this.FAIL_PROCENT = FAIL_PROCENT;
		this.c = c;
		this.randy = new Random(seed);
		this.s = s;
		this.starttime = starttime;
		this.endtime = starttime+endtime;
	}

	private void randomSatisfaction() {
		if(c.getSatisfaction() == true) {
			if((randy.nextInt(100) + 1) <= FAIL_PROCENT) {
			c.changeSatisfaction();
			s.createDissatisfiedReturn(c, endtime);
			}
		}else if(c.getSatisfaction() == false) {
			if((randy.nextInt(100) + 1) <= 100-FAIL_PROCENT) {
				c.changeSatisfaction();
			}
		}
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
		randomSatisfaction();
		if(c.getSatisfaction()){
			s.removeFromQueue(c);
			s.continueQueue();
		}
		
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
		return "Ready";
	}

	/**
	 * @return cutomerID
	 */
	public int getCustomerID() {
		return c.getId();
	}
}
