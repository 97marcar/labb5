package labb5.hairdresser;

import java.io.IOException;

import labb5.simulator.Event;

public class Customer_enter implements Event{	
	private SaloonState s;
	private Customer c;
	private int time;
	private int endtime;
	
	public Customer_enter(SaloonState s, Customer c, int time){
		this.s = s;
		this.c = c;
		this.time = time;
	}
	
	public void triggerEvent(){
		if(c.getSatisfaction()){
			s.addLastLine(c);
			//Haricutready 
		}else{
			try {
				throw new IOException();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getTime(){
		return time;
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