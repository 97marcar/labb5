package labb5.hairdresser;

import java.util.Observable;

import labb5.simulator.View;

public class SaloonView extends View{
	
	private SaloonState state;
	private Customer_enter enter;
	private FIFO f;
	private HaircutReady ready;
	private Start start;
	public SaloonView(SaloonState state) {
		
	}
	public void SaloonInitialLayout() {
		//System.out.println("Closing time of the day......" + state.getClosingTime);
		//System.out.println("Total number of chairs" + state.getDressers);
		//System.out.println("Maximum queue size" + state.getWaitChairs);
		//System.out.println("Lambda (Customer/timeunit entering" + state.getLambda);
		System.out.println("hmin and hmax (cutting time interval" + "[" + state.getHmin + "," + state.getHmax + "]");
		System.out.println("dmin and dmax (return time interval" + "[" + state.getDmin + "," + state.getDmax + "]");
		System.out.println("Risk dissatisfied returns" + ready.getFailProcent);
		System.out.println("Seed used in pseudo random generator" + state.getSeed);
		System.out.println("-------------------------------------");
		System.out.println("- Time" + "\t" + "Event" +"\t" + " Id" + 
		"\t" + "Idle" +"\t" + " TIdle" + "\t" + "TWait" +"\t" +
				"InQ" + "\t" + "Cut" +"\t" + " Lost" + "\t" + "Ret " +"-" );	
	}

	public void SaloonEndLayout() {
		
	}
	public void update(Observable o, Object arg) {
		
	}

}
