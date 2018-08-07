package edu.neu.coe.neuralnetwork.backpropagation;



/*********************************
* HalfConnected.java
* 
* An example extension of the backpropagation class, not implemented in the GUI.
* The connectLayers class is overwritten from the FeedForward class, and instead
* of connecting every node of one layer to all nodes of the previous layer, we
* connect it to only half of the previous layer Nodes, randomly chosen.
* 
* Interesting, because double line connections are possible then.
* 
* To implement it: replace every occurrence of BackPropagation in GUI and
* Runner to HalfConnected.
* 
*/

import edu.neu.coe.neuralnetwork.elements.*;

import java.util.Random;

/**
 * @author ketulshukla
 *
 */
public class HalfConnected extends BackPropagation {
	public HalfConnected(int[] networkLayers) {
		super(networkLayers);
	}

	public ConnectionLayer connectLayers(NeuronLayer layer1, NeuronLayer layer2) {
		// first determine the size of the ConnectionLayer
		ConnectionLayer tempConnectionLayer = new ConnectionLayer(layer1.size() * (layer2.size()/2));
		
		Random generator = new Random();

		for (int i=0; i<layer2.size(); i++) {
			for (int j=0; j<layer1.size()/2; j++) {
				
				int curPos = layer1.size()/2 * i + j;
				// connect layer2 to a random node in layer 1
				tempConnectionLayer.add(new Connection(layer1.get(generator.nextInt(layer1.size())), layer2.get(i)), curPos);
			}	
		}
		
		return tempConnectionLayer;
	}
	
}
