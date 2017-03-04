package labb5.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class EventQueue {
	
	private List<Event> eventQ = new ArrayList<Event>();
	
	/**
	 * Add event to event queue and sort the queue rising 
	 * depending on the time which the event is supposed to happen.
	 * @param event
	 */
	public void add(Event event){
		eventQ.add(event);
		sort();
	}
	
	/**
	 * Help method to sort the list rising depending on time. 
	 */
	private void sort() {
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
	
	/**
	 * Remove first event in queue
	 */
	public void removeFirst(){
		eventQ.remove(0);
	}
	/**
	 * Clear the whole queue
	 */
	public void clear() {
		eventQ.clear();
	}

	/**
	 * @return size of the queue
	 */
	public int size() {
		return eventQ.size();
	}
	
	/**
	 * @return true if queue is empty
	 */
	public boolean isEmpty(){
		return(eventQ.isEmpty());
	}
	
	/**
	 * @return first element in queue if no element exist
	 * exception is thrown 
	 */
	public Event getFirst(){
		if(eventQ.isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			return(eventQ.get(0));
	}
		
	}

}
