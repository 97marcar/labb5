package labb5.simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class that contains all events internally in an arraylist.
 * This class handles all of the events of this simulation with its methods.
 * 
 * @author Marcus Carlsson
 * @author Henrik Möller
 * @author Oscar Ferm
 * @since 2017-03-08
 *
 */

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
		Collections.sort(eventQ, new Comparator<Event>(){
			@Override
			public int compare(Event e1, Event e2)
	        {

	            return  Double.compare(e1.getTime(), e2.getTime());
	        }
		});
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
