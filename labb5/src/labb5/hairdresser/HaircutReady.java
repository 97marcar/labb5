package labb5.hairdresser;

import java.util.Random;

import labb5.simulator.Event;

public class HaircutReady implements Event {
	private Random randy;
	private Customer c;
	private SaloonState s;
	private double starttime;
	private double endtime;
	private final int FAIL_PROCENT = 20;
	
	public HaircutReady(SaloonState s, Customer c,long seed, 
			double starttime, double endtime){
		this.c = c;
		this.randy = new Random(seed);
		this.s = s;
		this.starttime = starttime;
		this.endtime = starttime+endtime;
	}

	private void randomSatisfaction() {
		if(c.getSatisfaction() == true) {
			if((randy.nextInt(100) + 1) <= FAIL_PROCENT) {
			c.changeSatisfaction();
			s.addUnsatisfied();
			s.createDissatisfiedReturn(c, endtime);
			}
		}else if(c.getSatisfaction() == false) {
			if((randy.nextInt(100) + 1) <= 100-FAIL_PROCENT) {
				c.changeSatisfaction();
				//nowHappy++;
			}
		}
	}
	// Bör något mer göras här

	public boolean pay(boolean isSatisfied) {
		return isSatisfied;
	}
	public int getFailProcent() {
		return FAIL_PROCENT;
		
	}

	@Override
	public void triggerEvent() {
		randomSatisfaction();
		
	}

	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return starttime;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Ready";
	}

	@Override
	public int getCustomerID() {
		// TODO Auto-generated method stub
		return 0;
	}
}
