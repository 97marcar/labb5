package labb5.hairdresser;

import java.util.Random;

import labb5.simulator.Event;

public class HaircutReady implements Event {
	private Random randy = new Random();
	private Customer c;
	private int disCounter;
	private int nowHappy;
	private SaloonState s;
	private int endtime;

	private void randomSatisfaction() {
		if(c.getSatisfaction() == true) {
			if((randy.nextInt(100) + 1) <= 20) {
			c.changeSatisfaction();
			disCounter++;
			s.createDissatisfiedReturn(c, endtime);
			}
		}else if(c.getSatisfaction() == false) {
			if((randy.nextInt(100) + 1) <= 80) {
				c.changeSatisfaction();
				nowHappy++;
			}
		}
	}
	// Bör något mer göras här

	public boolean pay(boolean isSatisfied) {
		return isSatisfied;
	}
	public int reformed(){
		return nowHappy;
	}
	public int diss(){
		return disCounter;
	}

	@Override
	public void triggerEvent() {
		randomSatisfaction();
		
	}

	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return 0;
	}
}
