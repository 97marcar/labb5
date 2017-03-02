package labb5.hairdresser;

import java.util.Random;

/**
 * 
 * @author Marcus, Oscar, Henrik
 *
 */
public class Customer {
	private boolean isSatisfied = true;
	private int id;
	
	/** 
	 * @param id unique customer id
	 */
	public Customer(int id){
		this.id = id;
	}
	
	/**
	 * Change state of customers satisfaction
	 */
	public void changeSatisfaction(){
		isSatisfied = !isSatisfied;
	}
	
	/**
	 * @return state of customers satisfaction
	 */
	public boolean getSatisfaction(){
		return isSatisfied;
	}
	
	/**
	 * @return id of the customer
	 */
	public int getId(){
		return id;
	}
	public void randomSatisfaction() {
		Random randy = new Random();
		if(getSatisfaction() == true) {
			if((randy.nextInt(100) + 1) <= 20) {
				changeSatisfaction();
			}
		}else if(getSatisfaction() == false) {
			if((randy.nextInt(100) + 1) <= 80) {
				changeSatisfaction();
			}
		}
		
	}
}
