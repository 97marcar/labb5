
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

	public UniformRandomStream(double lower, double upper, long seed) {
		rand = new Random(seed);
		this.lower = lower;
		this.width = upper - lower;
	}

	public UniformRandomStream(double lower, double upper) {
		rand = new Random();
		this.lower = lower;
		this.width = upper - lower;
	}

	public double next() {
		return lower + rand.nextDouble() * width;
	}
}
