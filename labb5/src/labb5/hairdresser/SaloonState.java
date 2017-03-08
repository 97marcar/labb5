package labb5.hairdresser;

import java.io.IOException;
import java.util.Random;

import labb5.random.ExponentialRandomStream;
import labb5.random.UniformRandomStream;
import labb5.simulator.Event;
import labb5.simulator.EventQueue;
import labb5.simulator.State;

public class SaloonState extends State{
	private final int DRESSERS;//2
	private final int WAITCHAIRS;//2
	public FIFO waitLine = new FIFO();
	public FIFO cutLine = new FIFO();
	private int customerCounter = 0;
	private int cID = 0;
	private double hmin, hmax;//1 2
	private double dmin, dmax;//1 2
	private long seed;//1116
	private double lambda;//1,2
	private ExponentialRandomStream timeNewCustomer;
	private UniformRandomStream timeHairCut;
	private UniformRandomStream timeDissatisfiedReturn;
	private int numberOfUnsatified;
	private int numberOfLostCustomers;
	private EventQueue q;
	private boolean openState = true;
	private double totalIdle = 0;
	private double totalWait = 0;
	private final double CLOSINGTIME;//7,00
	private final int FAIL_PROCENT;//50
	private Random randomNum = new Random(seed);
	
	/**
	 * Creates objects from the random package. These are used for time.
	 * Also adds a Close event at CLOSINGTIME
	 * @param q EventQueue
	 */
	public SaloonState(EventQueue q, double CLOSINGTIME, int FAIL_PROCENT, int DRESSERS, int WAITCHAIRS, 
			double hmin, double hmax, double dmin, double dmax, double lambda, long seed){
		
		this.CLOSINGTIME = CLOSINGTIME;
		this.FAIL_PROCENT = FAIL_PROCENT;
		this.DRESSERS = DRESSERS;
		this.WAITCHAIRS = WAITCHAIRS;
		this.hmin = hmin;
		this.hmax = hmax;
		this.dmin = dmin;
		this.dmax = dmax;
		this.lambda = lambda;
		this.seed = seed;
		
		randomNum = new Random(seed);
		timeNewCustomer = new ExponentialRandomStream (lambda, seed);
		timeHairCut = new UniformRandomStream(hmin,hmax,seed);
		timeDissatisfiedReturn = new UniformRandomStream(dmin,dmax,seed);
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
	public boolean addLastLine(Customer c, double time){


		if(!cutlineFull()){
			cutLine.add(c);
			if(c.getSatisfaction()) customerCounter++;
			return(true);
		}else if(!waitlineFull()){
			if(c.getSatisfaction()){
				waitLine.add(c);
				customerCounter++;
			}else{
				if(waitLine.isEmpty()){
					waitLine.add(c);
				}else{
					addUnsatisfiedFirst(c);
				}
				
			}
		}else{
			
			if(c.getSatisfaction()){
				numberOfLostCustomers++;
			}else{
				if(isLineFullOfUnSatisfied()){
					double next = getNextUnsatisfied();
					createDissatisfiedReturn(c, time+next, next);
				}
				
			}
		}
		return(false);
	}
	
	//Lägger till en missnöjd kund framför nöjda och bakom andra missnöjda
	//Är kön redan full tas går den längst bak som alltid ska vara nöjd
	//Skulle den längst bak mot förmodan vara nöjd kastas en Exception.
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
	public void continueQueue(double time){
		if(!cutlineFull()){
			if(!waitLine.isEmpty()){
				Customer c = (Customer) waitLine.removeFirst();
				cutLine.add(c);
				double t = getNextHair();
				createHairCutReady(c, time+t, t);
			}
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
	
	public void increaseIdleAndWait(double time){
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
			Customer_enter event = new Customer_enter(this, createCustomer(), time+next, next);
			//setChangedAndNotify();
			q.add(event);
			
		}
		
	}
	/**
	 * 
	 * @param c
	 * @param time
	 * counter++ every time a customers get dissatisfied,
	 * works if previously dissatisfied.
	 */
	public void randomSatisfaction(Customer c, double time) {
		int r = (randomNum.nextInt(100) + 1);
		if(c.getSatisfaction() == true) {
			if(r <= FAIL_PROCENT) {
			numberOfUnsatified++;
			c.changeSatisfaction();
			double nexttime = timeDissatisfiedReturn.next();
			this.createDissatisfiedReturn(c, time+nexttime, nexttime);
			}
		}else {
			if(r >= FAIL_PROCENT) {
				c.changeSatisfaction();
			}else{
				numberOfUnsatified++;
				double nexttime = timeDissatisfiedReturn.next();
				this.createDissatisfiedReturn(c, time+nexttime, nexttime);
			}
		}
	}
	/**
	 * Creates a new haircutready object and adds it to the eventqueue
	 * It also increase the total Idle time and total Wait time.
	 * @param c customer
	 * @param time start time of the event
	 */
	public void createHairCutReady(Customer c, double time, double diff){
		HaircutReady event = new HaircutReady(this, c, time, diff);
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
	public void createDissatisfiedReturn(Customer c, double time, double diff){
		DissatisfiedReturn event = new DissatisfiedReturn(this, c, time, diff);
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
//	public void addUnsatisfied(){
//		System.out.println("nöjd");
//		numberOfUnsatified++;
//	}
	
	
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
	
	public double getNextHair(){
		return timeHairCut.next();
	}
	
	public double getNextUnsatisfied(){
		return timeDissatisfiedReturn.next();
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
