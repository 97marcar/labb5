package labb5.simulator;

/**
 * Interface that all events in labb5.hairdresses implements.
 * 
 * @author Marcus Carlsson
 * @author Henrik Möller
 * @author Oscar Ferm
 * @since 2017-03-08
 *
 */
public interface Event {
	public void triggerEvent();
	public double getTime();
	public String getName();
	public int getCustomerID();
}
