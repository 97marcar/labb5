package labb5.hairdresser;

import labb5.simulator.State;

public class SaloonState extends State{
	final int dressers = 5;
	final int waitchairs = 15;
	private FIFO f;
	private int customerCounter = 0;
	private HaircutReady hr;
	private int cID = 0;
	
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
		//H�r ska den p� n�got s�tt l�ggas in i k�n.
	}
	
	public void createHairCutReady(Customer c, int time){
		HaircutReady event = new HaircutReady();//Konstruktor ej f�rdig i klassen
		//H�r ska den p� n�got s�tt l�ggas in i k�n.
	}
	
	public void createDissatisfiedReturn(Customer c, int time ){
		DissatisfiedReturn event = new DissatisfiedReturn(c, time);
		//H�r ska den p� n�got s�tt l�ggas in i k�n.
	}
	//Kanske en metod f�r att skicka in ett event i eventk�n?
}
