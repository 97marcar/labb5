package labb5.simulator;

import java.util.Random;

import labb5.hairdresser.Customer;

public class Simulator {
	Random randy = new Random();
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

}
