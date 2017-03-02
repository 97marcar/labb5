package labb5.hairdresser;

import labb5.simulator.Event;

public class Customer_enter implements Event{
	SaloonState s;
	Customer c;
	HaircutReady r;
	public Customer_enter(SaloonState s, Customer c){
		this.s = s;
		this.c = c;
	}
	
	public void triggerEvent(){
		if(c.getSatisfaction()){
			s.addLastLine(c);
		}else{
			if(!s.isLineFullOfUnSatisfied()){
				s.addUnsatisfied(c);
			}else{
				r.randomSatisfaction();
			}
		}
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