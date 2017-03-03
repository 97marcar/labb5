package labb5.hairdresser;

import java.util.Observable;

import labb5.simulator.View;

public class SaloonView extends View{
	
	private SaloonState state;
	
	public SaloonView(SaloonState state) {
		setState(state);
		
	}
	/**
	 * which view that displays.
	 * @param state
	 */
	public void setState(SaloonState state) {
		this.state = state;
	}
	public void update(Observable o, Object arg) {
		
	}

}
