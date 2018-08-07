package edu.neu.coe.neuralnetwork.elements;



/*********************************
* NeuronLayer.java
* 
* The NeuronLayer is simply a collection of neurons.
* The size is static (not dynamic like a VectorList), because of efficiency consideration.
* Please read FeedForward.java for more details on this subject.
*/

/**
 * @author ajinkyarode
 *
 */
public class NeuronLayer {
	private Neuron[] neurons;
	
	public NeuronLayer(int length) {
		neurons = new Neuron[length];
	}
	
	public void add(Neuron neuron, int index) {
		neurons[index] = neuron;
	}
	
	public Neuron get(int i) {
		return neurons[i];
	}
	
	public int size() {
		return neurons.length;
	}
}
