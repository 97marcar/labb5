package labb5.hairdresser;


import labb5.simulator.Event;

/**
 * Eventclass that removes customers from the queue if the customer was
 * satisfied and prints out the status.
 * 
 * 
 * @author Marcus Carlsson
 * @author Henrik Möller
 * @author Oscar Ferm
 * @since 2017-03-08
 *
 */

public class HaircutReady implements Event {
	private Customer c;
	private SaloonState s;
	private double time;
	private double diff;

	/**
	 * 
	 * @param s
	 *            SaloonState
	 * @param c
	 *            Customer
	 * @param starttime
	 *            start time
	 */
	public HaircutReady(SaloonState s, Customer c, double time, double diff) {
		this.c = c;
		this.s = s;
		this.time = time;
		this.diff = diff;
	}
	/**
	 * Which events that are used.
	 */
	public void triggerEvent() {
		s.increaseTIdle(time-s.getCurrentTime());
		s.setChangedAndNotify();
		s.removeFromQueue(c);
		s.randomSatisfaction(c, time);
		s.increaseCuttingTime(diff);
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
	 */
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
