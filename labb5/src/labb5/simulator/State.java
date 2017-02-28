package labb5.simulator;

import java.util.Observable;

import labb5.hairdresser.Customer_enter;
import labb5.hairdresser.HairSaloonClose;
import labb5.hairdresser.HaircutReady;

public class State extends Observable{
	private int day = 1;
	private String message;
	
	public void enterEvent(){
		
		
	}
	
	public void readyEvent(){
		
	}
	
	public void dissatisfiedEvent(int tid){
		//tiden är vad klockan är
		message = "Store closes at "+tid;
		day++;
		setNotify();
	}


	
	private void setNotify(){
		setChanged();
		notifyObservers();
	}
}
