
package labb5.random;

import java.util.Random;

/**
 * Class that creates random numbers used in our simulator as timeunits. This
 * time is used to set the time a haircut takes and the time it takes for a
 * dissatisfied customer to return. (timeHairCut, timeDissatisfiedReturn).
 * 
 * @author Marcus Carlsson
 * @author Henrik Möller
 * @author Oscar Ferm
 * @since 2017-03-08
 *
 */

public class UniformRandomStream {

	private Random rand;
	private double lower, width;
	/** 
	 * @param lower lower parameters of the generator.
	 * @param upper upper parameters of the generator.
	 * @param seed
	 */
	public UniformRandomStream(double lower, double upper, long seed) {
		rand = new Random(seed);
		this.lower = lower;
		this.width = upper - lower;
	}
	/**
	 * 
	 * @param lower parameters of the generator.
	 * @param upper parameters of the generator.
	 */
	public UniformRandomStream(double lower, double upper) {
		rand = new Random();
		this.lower = lower;
		this.width = upper - lower;
	}
	/**
	 * 
	 * @return next time.
	 */
	public double next() {
		return lower + rand.nextDouble() * width;
	}
}
