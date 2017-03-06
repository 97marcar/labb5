package labb5.hairdresser;

import labb5.simulator.Event;

public class DissatisfiedReturn implements Event {
	private SaloonState s;
	private Customer c;
	private double starttime;
	private double endtime;
	
	/**
	 * 
	 * @param c customer
	 * @param starttime start time
	 * @param endtime end to,e
	 */
	public DissatisfiedReturn(Customer c, double starttime, double endtime){
		this.c = c;
		this.starttime = starttime;
		this.endtime = starttime+endtime;
	}
	
	/**
	 * Add the returning customer to the line.
	 * Except when it is full of unsatified customers, the it will create
	 * a new event of the same kind but with a new start time.
	 */
	public void triggerEvent() {
		if(!s.isLineFullOfUnSatisfied()){
			s.addLastLine(c);
			s.createHairCutReady(c, endtime); 
		}else{
			s.createDissatisfiedReturn(c, endtime);
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
