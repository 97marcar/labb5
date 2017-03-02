package labb5.hairdresser;

import labb5.simulator.Event;

public class HairSaloonClose implements Event{
	private boolean isClosed = false;
	
	private void changeClosedState(){
		isClosed = !isClosed;
	}
	
	public boolean getClosedState(){
		return isClosed;
	}

	@Override
	public void triggerEvent() {
		changeClosedState();
		
	}
}
