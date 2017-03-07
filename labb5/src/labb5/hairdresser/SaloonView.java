package labb5.hairdresser;
import labb5.hairdresser.SaloonState;
import labb5.simulator.EventQueue;

import java.util.Observable;

import labb5.simulator.View;

public class SaloonView extends View{
	private HaircutReady hr;
	
	private SaloonState state;
	private EventQueue q;

	public SaloonView(SaloonState state, EventQueue q) {
		this.state = state;
		this.q = q;
		state.addObserver(this);
		saloonInitialLayout();
	}
	
	public void saloonInitialLayout() {
		System.out.println("Closing time of the day ..............: " + state.getClosingTime());
		System.out.println("Total number of chairs ...............: " + state.getDressers());
		System.out.println("Maximum queue size ...................: " + state.getWaitChairs());
		System.out.println("Lambda (Customer/timeunit entering ...: " + state.getLambda());
		System.out.println("hmin and hmax (cutting time interval) : " + "[" + state.gethmin() + "," + state.gethmax() + "]");
		System.out.println("dmin and dmax (return time interval) .: " + "[" + state.getdmin() + "," + state.getdmax() + "]");
		System.out.println("Risk dissatisfied returns ............: " + state.getFails() + "%");
		System.out.println("Seed used in pseudo random generator .: " + state.getSeed());
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("- Time" + "\t" + "Event" +"\t" + " Id" + 
		"\t" + "Idle" +"\t" + " TIdle" + "\t" + "TWait" +"\t" +
				"InQ" + "\t" + "Cut" +"\t" + " Lost" + "\t" + "Ret " +"-" );	
	}

	public void saloonEndLayout() {
		System.out.println("Number of customers" + state.getTotalCustomer());
		System.out.println("Number of unsatisfied customers" + state.getUnsatisfied());
		
	}
	public void update(Observable o, Object arg) {
	System.out.printf("  %.2f  %s %d\t %d\t %.2f\t %.2f\t %d\t %d\t %d\t %d\t", q.getFirst().getTime(), q.getFirst().getName(), q.getFirst().getCustomerID(),
				state.getIdle(), state.getTotalIdle(), state.getTotalWait(), state.getWaitLine(), state.getCutLine(), state.getLostCustomer(), state.getUnsatisfied());
		System.out.println();
	}
	
	

}
