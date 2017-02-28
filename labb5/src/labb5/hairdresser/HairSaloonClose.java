package labb5.hairdresser;

import labb5.simulator.Event;

public class HairSaloonClose implements Event{
	private boolean isClosed = false;
	
	public void changeClosedState(){
		isClosed = !isClosed;
	}
	
	public boolean getClosedState(){
		return isClosed;
	}
}
