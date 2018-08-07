package edu.neu.coe.neuralnetwork.elements;

/*********************************
* Connection.java
* 
* A line in the network.
* A line is simply a connection between two nodes, and contains a weight.
* This weight can be requested and altered. The two nodes that it connects are also
* saved as pointers.
*/

/**
 * @author ketulshukla
 *
 */
public class Connection {
	private double weight = 0;	
	private Neuron fromNeuron;
	private Neuron toNeuron;
	
	public Connection(Neuron fromNeuron, Neuron toNeuron) {
		this.fromNeuron = fromNeuron;
		this.toNeuron = toNeuron;
	}
	
	public Connection(Neuron fromNeuron, Neuron toNeuron, double weight) {
		this.fromNeuron = fromNeuron;
		this.toNeuron = toNeuron;
		
		this.weight = weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public Neuron getFromNeuron() {
		return fromNeuron;
	}
	
	public Neuron getToNeuron() {
		return toNeuron;
	}
}
