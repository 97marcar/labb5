package labb5.hairdresser;
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
}
