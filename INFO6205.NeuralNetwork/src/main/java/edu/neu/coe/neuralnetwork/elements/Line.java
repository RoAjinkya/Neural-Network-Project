package edu.neu.coe.neuralnetwork.elements;



/*********************************
* Line.java
* 
* A line in the network.
* A line is simply a connection between two nodes, and contains a weight.
* This weight can be requested and altered. The two nodes that it connects are also
* saved as pointers.
*/

/**
 * @author ajinkyarode
 *
 */
public class Line {
	private double weight = 0;	
	private Node   fromNode;
	private Node   toNode;
	
	public Line(Node fromNode, Node toNode) {
		this.fromNode = fromNode;
		this.toNode   = toNode;
	}
	
	public Line(Node fromNode, Node toNode, double weight) {
		this.fromNode = fromNode;
		this.toNode   = toNode;
		
		this.weight = weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public Node getFromNode() {
		return fromNode;
	}
	
	public Node getToNode() {
		return toNode;
	}
}
