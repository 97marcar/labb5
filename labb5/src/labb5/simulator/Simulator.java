package labb5.simulator;

import java.util.Random;

import labb5.hairdresser.Customer;

public class Simulator {
	
	private EventQueue eventQ;
	
	public Simulator(EventQueue eventQ){
		this.eventQ = eventQ;
	}
	
	public void start(){

		while(!eventQ.isEmpty()){
			eventQ.getFirst().triggerEvent();
			eventQ.removeFirst();
		}
	}

}


// Bör lyftas in i ett event. 
/** Random randy = new Random();
Customer casty;

public void setSatisfaction() {
	if(casty.getSatisfaction() == true) {
		if((randy.nextInt(100) + 1) <= 20) {
		casty.changeSatisfaction();
		}
	}else if(casty.getSatisfaction() == false) {
		if((randy.nextInt(100) + 1) <= 80) {
			casty.changeSatisfaction();
		}
	}
}
*/