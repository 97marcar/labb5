package labb5.hairdresser;

import labb5.simulator.Event;

public class Customer_enter implements Event{
	final int dressers = 5;
	final int waitchairs = 15;
	private FIFO f;
	
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
		f.add(item);
	}
	public void removeFromQueue() {
		f.removeFirst();
		
	}
	public void addFrontLine(Object item) {
		f.addFirst(item);
		
		
	}
	public void removeLast() {
		f.removeBack();
		
	}
}
