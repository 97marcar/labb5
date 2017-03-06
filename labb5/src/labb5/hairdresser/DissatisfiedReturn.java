package labb5.hairdresser;

import labb5.simulator.Event;

public class DissatisfiedReturn implements Event {
	private SaloonState s;
	private Customer c;
	private double starttime;
	private double endtime;
	
	public DissatisfiedReturn(Customer c, double starttime, double endtime){
		this.c = c;
		this.starttime = starttime;
		this.endtime = starttime+endtime;
		//kvar att skapa endtime
	}
	
	public void triggerEvent() {
		if(!s.isLineFullOfUnSatisfied()){
			s.addUnsatisfied(c);
			s.createHairCutReady(c, endtime); 
		}else{
			//Om kön är full gör man ett nytt objekt om en viss tid.
			s.createDissatisfiedReturn(c, endtime);
		}
		
	}
	
	public double getTime(){
		return starttime;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Return";
	}

	@Override
	public int getCustomerID() {
		// TODO Auto-generated method stub
		return 0;
	}
}
