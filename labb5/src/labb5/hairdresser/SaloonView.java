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
		System.out.println("Closing time of the day......" + state.getClosingTime());
		System.out.println("Total number of chairs" + state.getDressers());
		System.out.println("Maximum queue size" + state.getWaitChairs());
		System.out.println("Lambda (Customer/timeunit entering" + state.getLambda());
		System.out.println("hmin and hmax (cutting time interval" + "[" + state.gethmin() + "," + state.gethmax() + "]");
		System.out.println("dmin and dmax (return time interval" + "[" + state.getdmin() + "," + state.getdmax() + "]");
		System.out.println("Risk dissatisfied returns" + ready.getFailProcent() + "%");
		System.out.println("Seed used in pseudo random generator" + state.getSeed());
		System.out.println("-------------------------------------");
		System.out.println("- Time" + "\t" + "Event" +"\t" + " Id" + 
		"\t" + "Idle" +"\t" + " TIdle" + "\t" + "TWait" +"\t" +
				"InQ" + "\t" + "Cut" +"\t" + " Lost" + "\t" + "Ret " +"-" );	
	}

	public void SaloonEndLayout() {
		System.out.println("Number of customers" + state.getTotalCustomer());
		System.out.println("Number of unsatisfied customers" + state.getUnsatisfied());
		
	}
	public void update(Observable o, Object arg) {
		
		if(arg instanceof Start) {
			SaloonInitialLayout();
			System.out.printf("%d, %d, %d, %d, %d, %d, %d")
		}
		
		
	}

}
