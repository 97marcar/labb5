package labb5.simulator;

public interface Event {
	public void triggerEvent();
	public double getTime();
	public String getName();
	public int getCustomerID();
}
