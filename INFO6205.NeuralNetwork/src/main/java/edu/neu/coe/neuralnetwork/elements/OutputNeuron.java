package edu.neu.coe.neuralnetwork.elements;



/*********************************
* OutputNeuron.java
* 
* Simple extension to the Neuron class
* an output node should also contain a target value
*/

/**
 * @author ajinkyarode
 *
 */
public class OutputNeuron extends Neuron {
	private double target  = 0;
	
	public OutputNeuron() { }

	public void setTarget(double target) {
		this.target = target;
	}
	
	public double getTarget() {
		return target;
	}
}
