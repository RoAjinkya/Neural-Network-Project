package edu.neu.coe.neuralnetwork.math;


/*********************************
* SigmoidFunction.java
*
* This is the standard sigmoid function.
*/

/**
 * @author ketulshukla
 *
 */
public final class SigmoidFunction {	
	public SigmoidFunction() { }

	public static final double compute(double val) {
		return 1 / (1 + Math.pow(Math.E, -val));
	}
}
