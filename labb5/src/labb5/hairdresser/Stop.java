package labb5.hairdresser;



import labb5.simulator.Event;

/**
 * Stop class. Terminates the simulation.
 * 
 * 
 * 
 * @author Marcus Carlsson
 * @author Henrik Möller
 * @author Oscar Ferm
 * @since 2017-03-08
 *
 */

public class Stop implements Event{
	private SaloonState state;
	private double time;
	
	public Stop(SaloonState state, double time) {
		this.state = state;
		this.time = time;
		
	}

	
	public void triggerEvent() {
		state.activateBreak();
		state.setChangedAndNotify();
		
	}
	public String getName() {
		return "Stop";
	}

	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return time;
	}


	@Override
	public int getCustomerID() {
		// TODO Auto-generated method stub
		return 0;
	}


}
