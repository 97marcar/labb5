package labb5.hairdresser;

import java.io.IOException;
import java.util.Random;

import labb5.random.ExponentialRandomStream;
import labb5.random.UniformRandomStream;
import labb5.simulator.Event;
import labb5.simulator.EventQueue;
import labb5.simulator.State;

public class SaloonState extends State{
	private final int DRESSERS = 2;
	private final int WAITCHAIRS = 2;
	public FIFO waitLine = new FIFO();
	public FIFO cutLine = new FIFO();
	private int customerCounter = 0;
	private int cID = 0;
	private double hmin=1, hmax=2;
	private double dmin=1, dmax=2;
	private long seed=1116;
	private double lambda=1.2;
	private ExponentialRandomStream timeNewCustomer;
	private UniformRandomStream timeHairCut;
	private UniformRandomStream timeDissatisfiedReturn;
	private int numberOfUnsatified;
	private int numberOfLostCustomers;
	private EventQueue q;
	private boolean openState = true;
	private double totalIdle = 0;
	private double totalWait = 0;
	private final double CLOSINGTIME = 7.00;
	private int FAIL_PROCENT = 50;
	private Random randomNum = new Random(seed);
	
	/**
	 * Creates objects from the random package. These are used for time.
	 * Also adds a Close event at CLOSINGTIME
	 * @param q EventQueue
	 */
	public SaloonState(EventQueue q){
		timeNewCustomer = new ExponentialRandomStream (lambda, seed);
		timeHairCut = new UniformRandomStream(hmin,hmax,seed);
		timeDissatisfiedReturn = new UniformRandomStream(dmin,dmax,seed);
		// Chansen att bli missn�jd hanteras just nu i HairCutReady, b�r man flytta vissar delar d�rifr�n?
		this.q = q;
		Event HairSaloonOpenOrClose = new HairSaloonOpenOrClose(this, CLOSINGTIME);
		q.add(HairSaloonOpenOrClose);
		
		
	}
	
	/**
	 * Adds a customer to the end of the line. If the line is full the customer leaves. If the customer
	 * is unsatisfied he/she will return later, else not.
	 * @param c Customer
	 * @param time if the queue is full of unsatisfied customers this is the start time of the new DR object
	 * @return true if it successfully added a customer to the line else false
	 */
	public boolean addLastLine(Customer c){
		if(!cutlineFull()){
			cutLine.add(c);
			if(c.getSatisfaction()) customerCounter++;
			return(true);
		}else if(!waitlineFull()){
			waitLine.add(c);
			if(c.getSatisfaction()){
				customerCounter++;
			}else{
				addUnsatisfiedFirst(c);
			}
			return(true);
		}else{
			if(c.getSatisfaction()){
				numberOfLostCustomers++;
			}else{
				addUnsatisfiedFirst(c);
			}
		}
		return(false);
	}
	
	//L�gger till en missn�jd kund framf�r n�jda och bakom andra missn�jda
	//�r k�n redan full tas g�r den l�ngst bak som alltid ska vara n�jd
	//Skulle den l�ngst bak mot f�rmodan vara n�jd kastas en Exception.
	private boolean addUnsatisfiedFirst(Customer c){
		Customer t;
		for(int i = 0; i < waitLine.size(); i++){
			t = (Customer) waitLine.getIndex(i);
			if(t.getSatisfaction()){
				waitLine.addObjectMiddle(i, c);
				return true;
			}
		}
		if(waitLine.size() > WAITCHAIRS){
			if(!isLineFullOfUnSatisfied()){
				waitLine.removeBack();
				numberOfLostCustomers++;
				customerCounter--;
			}else{
				try {throw new IOException("Fel i addUnsatisfiedFirst");
				}catch (IOException e) {e.printStackTrace();}
			}
		}
		return false;
	}
	
	/**
	 * Removes a customer from the cutLine queue
	 * @param c
	 */
	public void removeFromQueue(Customer c){
		cutLine.removeSpecific(c);
	}
	
	/**
	 * Removes thefirst 
	 */
	public void continueQueue(){
		if(!cutlineFull()){
			if(!waitLine.isEmpty()) cutLine.add(waitLine.removeFirst());
		}else{
			try {throw new IOException("Fel i continueQueue");
			}catch (IOException e) {e.printStackTrace();}
		}
	}
	/**
	 * Checks if the line is full of unsatisfied customers
	 * @return true if it is else false
	 */
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
	
	/**
	 * Checks if the waitline (line people waiting to get their hair cut) is full.
	 * @return true if it is else false
	 */
	public boolean waitlineFull(){
		if(waitLine.size() >= WAITCHAIRS){
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the cutline (line people getting their hair cut) is full.
	 * @return true if it is else false
	 */
	public boolean cutlineFull(){
		if(cutLine.size() >= DRESSERS){
			return true;
		}
		return false;
	}
	
	/**
	 * Creates a new customer
	 * @return the freshly made customer
	 */
	public Customer createCustomer(){
		Customer c = new Customer(cID);
		cID++;
		return c;
	}
	
	private void increaseIdleAndWait(double time){
		totalIdle += time * getIdle(); 
		totalWait += time * waitLine.size();
		
	}
	
	/**
	 * If the store is open this method creates a new Customer enter object with a fresh customer 
	 * and add it to the eventqueue.
	 * It also increase the total Idle time and total Wait time.
	 */
	public void createCustomer_enter(double time){
		double next = timeNewCustomer.next();
		if(time+next < CLOSINGTIME){
			increaseIdleAndWait(time);
			Customer_enter event = new Customer_enter(this, createCustomer(), time+next, timeHairCut.next());
			//setChangedAndNotify();
			q.add(event);
			
		}
		
	}
	private void randomSatisfaction(Customer c, double time) {
		if(c.getSatisfaction() == true) {
			if((randomNum.nextInt(100) + 1) <= FAIL_PROCENT) {
			c.changeSatisfaction();
			//System.out.println(c.getId());
			//System.out.println(endtime);
			this.createDissatisfiedReturn(c, time+timeDissatisfiedReturn.next());
			}
		}else if(c.getSatisfaction() == false) {
			if((randomNum.nextInt(100) + 1) <= 100-FAIL_PROCENT) {
				c.changeSatisfaction();
			}else{
				this.createDissatisfiedReturn(c, time+timeDissatisfiedReturn.next());
			}
		}
	}
	/**
	 * Creates a new haircutready object and adds it to the eventqueue
	 * It also increase the total Idle time and total Wait time.
	 * @param c customer
	 * @param time start time of the event
	 */
	public void createHairCutReady(Customer c, double time){
		increaseIdleAndWait(time);
		randomSatisfaction(c, time);
		HaircutReady event = new HaircutReady(this, c, time);
		//setChangedAndNotify();
		q.add(event);
		
	}
	public int getFails() {
		return FAIL_PROCENT;
	}
	public int getMaxSize() {
		return waitLine.maxSize();
	}
	
	/**
	 * Creates a DissatisfiedReturn event and adds it to the eventqueue.
	 * It also increase the total Idle time and total Wait time.
	 * @param c customer 
	 * @param time start time of the event
	 */
	public void createDissatisfiedReturn(Customer c, double time ){
		increaseIdleAndWait(time);
		DissatisfiedReturn event = new DissatisfiedReturn(this, c, time, timeHairCut.next());
		q.add(event);
		//setChangedAndNotify();
	}
	
	/**
	 * Opens or closes the hairsaloon
	 */
	public void changeOpenState(){
		openState = !openState;
	}
	
	/**
	 * Increases the amount of unsatisfied customers 
	 */
	public void addUnsatisfied(){
		numberOfUnsatified++;
	}
	
	
	/**
	 * @return total amount of customers that have or will be cut.
	 */
	public int getTotalCustomer() {
		return customerCounter;
	}
	
	/**
	 * @return true if the hairsaloon is open else false
	 */
	public boolean getOpenState(){
		return openState;
	}

	
	/**
	 * @return number of unsatisfied customers
	 */
	public int getUnsatisfied() {
		return numberOfUnsatified;
	}
	
	/**
	 * @return amount of waitchairs
	 */
	public int getWaitChairs(){
		return WAITCHAIRS;
	}
	
	/**
	 * @return amount of hairdressers
	 */
	public int getDressers(){
		return DRESSERS;
	}
	
	/**
	 * @return lambda (customers/timeunit entering)
	 */
	public double getLambda(){
		return lambda;
	}
	
	/**
	 * @return hmax (part of the cutting time interval)
	 */
	public double gethmax(){
		return hmax;
	}
	
	/**
	 * @return hmin (part of the cutting time interval)
	 */
	public double gethmin(){
		return hmin;
	}
	
	/**
	 * @return dmax (part of the return time interval) 
	 */
	public double getdmax(){
		return dmax;
	}
	
	/**
	 * @return dmin (part of the return time interval)
	 */
	public double getdmin(){
		return dmin;
	}
	
	/**
	 * @return seed (for random generators)
	 */
	public long getSeed(){
		return seed;
	}
	
	/**
	 * @return amount of idling hair dressers
	 */
	public int getIdle(){
		return(DRESSERS-cutLine.size());
	}
	
	/**
	 * @return total of hairdresser idle time
	 */
	public double getTotalIdle(){
		return totalIdle;
	}
	
	/**
	 * @return total customer waiting time
	 */
	public double getTotalWait(){
		return totalWait;
	}
	
	/**
	 * @return amount of people being cut right now.
	 */
	public int getCutLine(){
		return(cutLine.size());
	}
	
	/**
	 * @return amount of people waiting to be cut right now.
	 */
	public int getWaitLine(){
		return(waitLine.size());
	}
	
	/**
	 * @return total amount of lost customers
	 */
	public int getLostCustomer(){
		return(numberOfLostCustomers);
	}
	
	/**
	 * @return closing time
	 */
	public double getClosingTime(){
		return CLOSINGTIME;
	}
	
	public void setChangedAndNotify(){
		setChanged();
		notifyObservers();
	}
		
}
/**
 * public void setCurrentEvent(Event currentEvent) {
		this.currentEvent = currentEvent;	
	}
	public Event getCurrentEvent(Event event) {
		return currentEvent;
		
	}*/
