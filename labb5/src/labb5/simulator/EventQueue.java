package labb5.simulator;

import java.util.ArrayList;
import java.util.List;


public class EventQueue {
	
	private List<Event> eventQ = new ArrayList<Event>();
	
	public void add(Event event){
		eventQ.add(event);
		sort();
	}
	
	
	//Byggd på metoden Insertsort enl HJ IF14
	public void sort() {
		int n = eventQ.size();
		for (int j = 1; j < n; j++) {
			Event k = eventQ.get(j);
			int i = j - 1;
			while (i >= 0 && eventQ.get(j).getTime()>k.getTime()) {
				eventQ.add(i+1, eventQ.get(i));
				i--;
			}
			eventQ.add(i+1,k);
		}
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
