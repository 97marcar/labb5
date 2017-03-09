package labb5.simulator;

import labb5.hairdresser.SaloonState;

/**
 * Headclass that runs the events from the eventqueue.
 * Takes the first element in the eventqueue and run its triggerEvent.
 * Removes the element from eventqueue after it has been used.
 * 
 * @author Marcus Carlsson
 * @author Henrik Möller
 * @author Oscar Ferm
 * @since 2017-03-08
 *
 */

public class Simulator {
	
	private EventQueue eventQ;
	private SaloonState s;
	
	/**
	 * @param eventQ 
	 * 
	 */
	
	public Simulator(EventQueue eventQ, SaloonState s){
		this.eventQ = eventQ;
		this.s=s;
	}
	/**
	 * Runs and updates the simulation.
	 */
	public void start(){

		while(s.getBreak() || !eventQ.isEmpty()){
			eventQ.getFirst().triggerEvent();
			eventQ.updateCurrentTime();
			eventQ.removeFirst();
		}
	}

}