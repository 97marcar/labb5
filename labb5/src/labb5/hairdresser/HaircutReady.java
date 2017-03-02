package labb5.hairdresser;

import java.util.Random;

import labb5.simulator.Event;

public class HaircutReady implements Event {
	private Random randy = new Random();
	private Customer c;

	private void randomSatisfaction() {
		if(c.getSatisfaction() == true) {
			if((randy.nextInt(100) + 1) <= 20) {
			c.changeSatisfaction();
			}
		}else if(c.getSatisfaction() == false) {
			if((randy.nextInt(100) + 1) <= 80) {
				c.changeSatisfaction();
			}
		}
	}
	// Bör något mer göras här

	public boolean pay(boolean isSatisfied) {
		return isSatisfied;
	}

	@Override
	public void triggerEvent() {
		randomSatisfaction();
		
	}
}
