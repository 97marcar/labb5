package labb5.hairdresser;

import labb5.simulator.Event;

public class DissatisfiedReturn implements Event {
	
	/**
	 * Eventclass that places dissatisfied customers back in FIFO.
	 * Keeps track and adds the time of this event when triggered.
	 * 
	 * @author Marcus Carlsson
	 * @author Henrik Möller
	 * @author Oscar Ferm
	 * @since 2017-03-08
	 *
	 */
	private SaloonState s;
	private Customer c;
	private double starttime;
	
	/**
	 * 
	 * @param c customer
	 * @param starttime start time
	 * @param endtime end to,e
	 */
	public DissatisfiedReturn(SaloonState s, Customer c, double starttime){
		this.s = s;
		this.c = c;
		this.starttime = starttime;
	}
	
	/**
	 * Add the returning customer to the line.
	 * Except when it is full of unsatified customers, the it will create
	 * a new event of the same kind but with a new start time.
	 */
	public void triggerEvent() {
		s.setChangedAndNotify();
		
		if(s.addLastLine(c, starttime)){
			s.createHairCutReady(c, starttime+s.getNextHair());
		}
	}
	
	/**
	 * @return start time of this event
	 */
	public double getTime(){
		return starttime;
	}

	/**
	 * @return name of the string
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return "Return";
	}

	/**
	 * @return customerID
	 */
	public int getCustomerID() {
		// TODO Auto-generated method stub
		return c.getId();
	}
}
