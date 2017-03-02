package labb5.hairdresser;

import labb5.simulator.Event;

public class DissatisfiedReturn implements Event {
	private SaloonState s;
	private Customer c;
	private int time;
	private int endtime;
	
	public DissatisfiedReturn(Customer c, int time){
		this.c = c;
		this.time = time;
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
	
	public int getTime(){
		return time;
	}
}
