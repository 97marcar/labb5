package labb5.simulator;

import java.util.ArrayList;
import java.util.List;

public class EventQueue {
	
	private List<Event> eventQ = new ArrayList<Event>();
	
	public void add(Event event){
		eventQ.add(event);
	}
	
	public void removeFirst(){
		eventQ.remove(0);
	}
	
	public boolean isEmpty(){
		return(eventQ.isEmpty());
	}
	
	public Event getFirst(){
		return(eventQ.get(0));
	}

}
