package labb5.hairdresser;
import labb5.hairdresser.SaloonState;

import labb5.simulator.EventQueue;

import java.util.Observable;

import labb5.simulator.View;

/**
 * This program runs a simulator of a hairsaloon.
 * This is the viewclass. When the program runs an initial layout is created.
 * It displays relevant indata used in the simulator.
 * After the simulation has ended another layout is created with relevant statistics.
 * 
 * @author Marcus Carlsson
 * @author Henrik M�ller
 * @author Oscar Ferm
 * @since 2017-03-08
 *
 */

public class SaloonView extends View{
	
	private SaloonState state;
	private EventQueue q;
	
	/**
	 * Creates the initial layout when the object is created.
	 * Also contains an observer used for updating the view.
	 * @param state
	 * @param q
	 */

	public SaloonView(SaloonState state, EventQueue q) {
		this.state = state;
		this.q = q;
		state.addObserver(this);
		saloonInitialLayout();
	}
	/**
	 * Relevant indata used in the simulation, they are declared in state.
	 */
	public void saloonInitialLayout() {
		System.out.println("Closing time of the day ..............: " + state.getClosingTime());
		System.out.println("Total number of chairs ...............: " + state.getDressers());
		System.out.println("Maximum queue size ...................: " + state.getWaitChairs());
		System.out.println("Lambda (Customer/timeunit entering ...: " + state.getLambda());
		System.out.println("hmin and hmax (cutting time interval) : " + "[" + state.gethmin() + "," + state.gethmax() + "]");
		System.out.println("dmin and dmax (return time interval) .: " + "[" + state.getdmin() + "," + state.getdmax() + "]");
		System.out.println("Risk dissatisfied returns ............: " + state.getFails() + "%");
		System.out.println("Seed used in pseudo random generator .: " + state.getSeed());
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("- Time" + "\t" + "Event" +"\t" + " Id" + 
		"\t" + "Idle" +"\t" + " TIdle" + "\t" + "TWait" +"\t" +
				"InQ" + "\t" + "Cut" +"\t" + " Lost" + "\t" + "Ret " +"-" );	
	}
	/**
	 * Statistics of the simulation.
	 * uses getMethods found in state.
	 */
	public void saloonEndLayout() {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("Number of customers cut .......: " + state.getTotalCustomer());
//		System.out.printf("Average cutting time ..........: %.2f", state.cutTime / state.getTotalCustomer());
//		System.out.println();
		System.out.printf("Average queuing time ..........: %.2f" , state.getTotalWait() / state.getTotalCustomer());
		System.out.println();
		System.out.println("Largest queue (max NumWaiting) : " + state.getMaxSize());
		System.out.println("Customers not cut(NumLost) ....: " + state.getLostCustomer());
		System.out.println("Dissatisfied customers ........: " + state.getUnsatisfied());
		System.out.printf("Time chairs were idle .........: %.2f " , state.getTotalIdle() /state.getTotalWait());
		System.out.println();
		
		
	}
	public void update(Observable o, Object arg) {
	System.out.printf("  %.2f  %s\t %d\t %d\t %.2f\t %.2f\t %d\t %d\t %d\t %d\t", q.getFirst().getTime(), q.getFirst().getName(), q.getFirst().getCustomerID(),
				state.getIdle(), state.getTotalIdle(), state.getTotalWait(), state.getWaitLine(), state.getCutLine(), state.getLostCustomer(), state.getUnsatisfied());
//	Customer t;
//	for(int i = 0; i < state.waitLine.size(); i++) {
//		t = (Customer) state.waitLine.getIndex(i);
//		System.out.print(" \t waitLine = " + t.getId());
//		}
//	Customer a;
//	for(int j = 0; j < state.cutLine.size(); j++) {
//		a = (Customer) state.cutLine.getIndex(j);
//		System.out.print( "\t cutLine = " + a.getId());
//	}
		System.out.println();
	}
	
	

}
