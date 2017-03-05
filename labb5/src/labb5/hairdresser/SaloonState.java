package labb5.hairdresser;

import labb5.random.ExponentialRandomStream;
import labb5.random.UniformRandomStream;
import labb5.simulator.Event;
import labb5.simulator.State;

public class SaloonState extends State{
	final int dressers = 5;
	final int waitchairs = 15;
	private FIFO f;
	private int customerCounter = 0;
	private HaircutReady hr;
	private int cID = 0;
	private Event currentEvent;
	private double hmin=1, hmax=2;
	private double dmin=1, dmax=2;
	long seed=1116;
	double lambda=1.2;
	
	public SaloonState(){
		ExponentialRandomStream timeNewCustomer = new ExponentialRandomStream (lambda, seed);
		UniformRandomStream timeHairCut = new UniformRandomStream(hmin,hmax,seed);
		UniformRandomStream timeDissatisfiedReturn = new UniformRandomStream(dmin,dmax,seed);
		// Chansen att bli missnöjd hanteras just nu i HairCutReady, bör man flytta vissar delar därifrån?
	}
	
	
	public void addLastLine(Customer c){
		if(!lineFull()){
			f.add(c);
			customerCounter++;
		}
		
	}
	
	public void addUnsatisfied(Customer c){
		
	}
	
	public boolean isLineFullOfUnSatisfied(){
		Customer t;
		for(int i = 0; i < f.size(); i++){
			t = (Customer) f.getIndex(i);
			if(t.getSatisfaction()){
				return false;
			}
			
		}
		return true;
	}
	
	public boolean lineFull(){
		if(f.size() >= waitchairs){
			return true;
		}
		return false;
	}
	public int getTotalCustomer() {
		return customerCounter;
	}
	public int getDissatisfiedCustomer() {
		return hr.diss();
	}
	public int getReformed(){
		return hr.reformed();
	}
	
	public Customer createCustomer(){
		Customer c = new Customer(cID);
		cID++;
		return c;
	}
	
	public void createCustomer_enter(int time){
		Customer_enter event = new Customer_enter(this, createCustomer(), time);
		//Här ska den på något sätt läggas in i kön.
	}
	
	public void createHairCutReady(Customer c, int time){
		HaircutReady event = new HaircutReady();//Konstruktor ej färdig i klassen
		//Här ska den på något sätt läggas in i kön.
	}
	
	public void createDissatisfiedReturn(Customer c, int time ){
		DissatisfiedReturn event = new DissatisfiedReturn(c, time);
		//Här ska den på något sätt läggas in i kön.
	}
	//Kanske en metod för att skicka in ett event i eventkön?
	//FUNKAR INTE :( ska användas i view.
//	public void setCurrentEvent(Event currentEvent) {
//		this.currentEvent = currentEvent;
//	}
//	public Event getCurrentEvent(Event event) {
//		return currentEvent;
//		
//	}
		
	}
