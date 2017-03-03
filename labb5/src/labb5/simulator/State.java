package labb5.simulator;

import java.util.Observable;

import labb5.hairdresser.Customer_enter;
import labb5.hairdresser.HairSaloonClose;
import labb5.hairdresser.HaircutReady;

public class State extends Observable{
	
	public Event currentEvent;
}
