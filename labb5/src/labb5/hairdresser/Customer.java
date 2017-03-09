package labb5.hairdresser;

/**
 * Customer class, created with Customer_enter. A customer has an unique ID and
 * the status of isSatisfied is true when created. This variables are used in
 * state with the getMethods found here.
 * 
 * @author Marcus Carlsson
 * @author Henrik Möller
 * @author Oscar Ferm
 * @since 2017-03-08
 *
 */

public class Customer {

	private boolean isSatisfied = true;
	private int id;

	/**
	 * @param id
	 *            unique customer id
	 */
	public Customer(int id) {
		this.id = id;
	}

	/**
	 * Change state of customers satisfaction
	 */
	public void changeSatisfaction() {
		isSatisfied = !isSatisfied;
	}

	/**
	 * @return state of customers satisfaction
	 */
	public boolean getSatisfaction() {
		return isSatisfied;
	}

	/**
	 * @return id of the customer
	 */
	public int getId() {
		return id;
	}
}
