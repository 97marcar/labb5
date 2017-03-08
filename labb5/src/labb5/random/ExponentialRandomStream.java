package labb5.random;

import java.util.Random;

/**
 * Class that creates random numbers used in our simulator as timeunits. This
 * time is used to set the time for which a customer is created.
 * 
 * @author Marcus Carlsson
 * @author Henrik Möller
 * @author Oscar Ferm
 * @since 2017-03-08
 *
 */

public class ExponentialRandomStream {
	
	private Random rand;
	private double lambda;
	  
	/**
	 * 
	 * @param lambda used in main.
	 * @param seed used in main.
	 */
	public ExponentialRandomStream(double lambda, long seed) {
	  	rand = new Random(seed);
	  	this.lambda = lambda;
	}
	  /**
	   * 
	   * @param lambda
	   */
	public ExponentialRandomStream(double lambda) {
		rand = new Random();
	    this.lambda = lambda;
	}
	  /**
	   * 
	   * @return next time.
	   */
	public double next() {
	  	return -Math.log(rand.nextDouble())/lambda;
	}
	
}
