package labb5.hairdresser;

import labb5.random.ExponentialRandomStream;
import labb5.random.UniformRandomStream;
import labb5.simulator.Event;
import labb5.simulator.State;

public class SaloonState extends State{
	private final int DRESSERS = 2;
	private final int WAITCHAIRS = 2;
	private FIFO waitLine;
	private FIFO cutLine;
	private int customerCounter = 0;
	private HaircutReady hr;
	private int cID = 0;
	private Event currentEvent;
	private double hmin=1, hmax=2;
	private double dmin=1, dmax=2;
	private long seed=1116;
	private double lambda=1.2;
	private ExponentialRandomStream timeNewCustomer;
	private UniformRandomStream timeHairCut;
	private UniformRandomStream timeDissatisfiedReturn;
	private int numberOfUnsatified;
	private int numberOfLostCustomers;
	
	public SaloonState(){
		timeNewCustomer = new ExponentialRandomStream (lambda, seed);
		timeHairCut = new UniformRandomStream(hmin,hmax,seed);
		timeDissatisfiedReturn = new UniformRandomStream(dmin,dmax,seed);
		// Chansen att bli missnöjd hanteras just nu i HairCutReady, bör man flytta vissar delar därifrån?
	}
	
	
	public boolean addLastLine(Customer c, double time){
		if(!cutlineFull()){
			cutLine.add(c);
			customerCounter++;
			return(true);
		}else if(!waitlineFull()){
			waitLine.add(c);
			customerCounter++;
			return(true);
		}else{
			if(c.getSatisfaction()){
				numberOfLostCustomers++;
			}else{
				
			}
			
		}
		return(false);
		
	}
	
	public void addUnsatisfied(Customer c){
		
	}
	
	public boolean isLineFullOfUnSatisfied(){
		Customer t;
		for(int i = 0; i < waitLine.size(); i++){
			t = (Customer) waitLine.getIndex(i);
			if(t.getSatisfaction()){
				return false;
			}
			
		}
		return true;
	}
	
	public boolean waitlineFull(){
		if(waitLine.size() >= WAITCHAIRS){
			return true;
		}
		return false;
	}
	
	public boolean cutlineFull(){
		if(cutLine.size() >= DRESSERS){
			return true;
		}
		return false;
	}
	
	public int getTotalCustomer() {
		return customerCounter;
	}
	
	public Customer createCustomer(){
		Customer c = new Customer(cID);
		cID++;
		return c;
	}
	
	public void createCustomer_enter(){
		Customer_enter event = new Customer_enter(this, createCustomer(), timeNewCustomer.next(), timeHairCut.next());
		//Här ska den på något sätt läggas in i kön.
	}
	
	public void createHairCutReady(Customer c, double time){
		HaircutReady event = new HaircutReady(this, c, seed, time, timeDissatisfiedReturn.next());
		//Här ska den på något sätt läggas in i kön.
	}
	
	public void createDissatisfiedReturn(Customer c, double time ){
		DissatisfiedReturn event = new DissatisfiedReturn(c, time, timeHairCut.next());
		//Här ska den på något sätt läggas in i kön.
	}
	//Kanske en metod för att skicka in ett event i eventkön?
	//FUNKAR INTE :( ska användas i view.
	public void setCurrentEvent(Event currentEvent) {
		this.currentEvent = currentEvent;	
	}
	public Event getCurrentEvent(Event event) {
		return currentEvent;
		
	}
	
	public void addUnsatisfied(){
		numberOfUnsatified++;
	}
	public int getUnsatisfied() {
		return numberOfUnsatified;
	}
	
	public int getWaitChairs(){
		return WAITCHAIRS;
	}
	
	public int getDressers(){
		return DRESSERS;
	}
	
	public double getLambda(){
		return lambda;
	}
	
	public double gethmax(){
		return hmax;
	}
	
	public double gethmin(){
		return hmin;
	}
	
	public double getdmax(){
		return dmax;
	}
	
	public double getdmin(){
		return dmin;
	}
	
	public long getSeed(){
		return seed;
	}
	
	public int getIdle(){
		return(DRESSERS-cutLine.size());
	}
	
	public int getCutLine(){
		return(cutLine.size());
	}
	
	public int getWaitLine(){
		return(waitLine.size());
	}
	
	public int getLostCustomer(){
		return(numberOfLostCustomers);
	}
		
}
