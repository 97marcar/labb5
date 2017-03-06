package labb5.hairdresser;

import java.io.IOException;

import labb5.simulator.Event;

public class Customer_enter implements Event{	
	private SaloonState s;
	private Customer c;
	private double starttime;
	private double endtime;
	
	/**
	 * Constructor where the private variables(s,c,time) are set
	 * as well as the time of when this event is supposed to end.
	 * @param s SaloonState
	 * @param c Customer
	 * @param time the time of which this is supposed to happen
	 */
	public Customer_enter(SaloonState s, Customer c, double starttime, double endtime){
		this.s = s;
		this.c = c;
		this.starttime = starttime;
		this.endtime = starttime+endtime;
		//kvar att skapa endtime
	}
	
	/*
	 * (non-Javadoc)
	 * @see labb5.simulator.Event#triggerEvent()
	 */
	public void triggerEvent(){
		//funkar inte, why?
		s.setCurrentEvent(this);
		if(c.getSatisfaction()){
			
			if(s.addLastLine(c, endtime)){
				s.createHairCutReady(c, endtime);
			}
			 
		}else{
			try {throw new IOException("Fel i enter");
			}catch (IOException e) {e.printStackTrace();}
		}
	}
	
	public double getTime(){
		return starttime;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Enter";
	}

	@Override
	public int getCustomerID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
//Ska till state
/*
public int getDressers() {
	return dressers;
	
}
public int getWaitChairs() {
	return waitchairs;
	
}
public boolean isFull() {
	if(f.size() > waitchairs) {
		return true;
	}
	return false;
	
}
public void addLastLine(Object item) {
	s.add(item);
}
public void removeFromQueue() {
	f.removeFirst();
	
}
public void addFrontLine(Object item) {
	f.addFirst(item);
	
	
}
public void removeLast() {
	f.removeBack();
	
}*/