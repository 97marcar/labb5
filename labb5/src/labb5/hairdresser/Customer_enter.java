package labb5.hairdresser;

import java.io.IOException;

import labb5.simulator.Event;

/**
 * Eventclass that creates a customer and places the customers in the queue or
 * directly to be cut (createHairCutReady).
 * 
 * @author Marcus Carlsson
 * @author Henrik Möller
 * @author Oscar Ferm
 * @since 2017-03-08
 *
 */
public class Customer_enter implements Event {
	private SaloonState s;
	private Customer c;
	private double starttime;

	/**
	 * Constructor where the private variables(s,c,time) are set as well as the
	 * time of when this event is supposed to end.
	 * 
	 * @param s
	 *            SaloonState
	 * @param c
	 *            Customer
	 * @param time
	 *            the time of which this is supposed to happen
	 */
	public Customer_enter(SaloonState s, Customer c, double starttime) {
		this.s = s;
		this.c = c;
		this.starttime = starttime;
		// kvar att skapa endtime
	}

	/**
	 * Adds a customer to the line. This event should only contain satisfied
	 * customers this method throws an exception otherwise.
	 */
	public void triggerEvent() {
		if (c.getSatisfaction()) {
			s.setChangedAndNotify();
			s.createCustomer_enter(starttime);
			
			if (s.addLastLine(c, starttime)) {
				s.createHairCutReady(c, starttime + s.getNextHair());
			}

		} else {
			try {
				throw new IOException("Fel i enter");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return start time of this event
	 */
	public double getTime() {
		return starttime;
	}

	/**
	 * @return name of the event
	 */
	public String getName() {
		return "Enter";
	}

	/**
	 * @return customerID
	 */
	public int getCustomerID() {
		return c.getId();
	}

}
