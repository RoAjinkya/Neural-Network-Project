package edu.neu.coe.neuralnetwork.elements;



/*********************************
* OutputNode.java
* 
* Simple extension to the Node class
* an output node should also contain a target value
*/

/**
 * @author ajinkyarode
 *
 */
public class OutputNode extends Node  {	
	private double target  = 0;
	
	public OutputNode() { }

	public void setTarget(double target) {
		this.target = target;
	}
	
	public double getTarget() {
		return target;
	}
}
