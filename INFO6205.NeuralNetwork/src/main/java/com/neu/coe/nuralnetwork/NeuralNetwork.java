package com.neu.coe.nuralnetwork;

import edu.neu.coe.neuralnetwork.elements.LineLayer;
import edu.neu.coe.neuralnetwork.elements.NodeLayer;

/**
 * @author ajinkyarode
 *
 */
public interface NeuralNetwork {
	
	/************************************
     * addNodes(int[] networkLayers)
     *---------------------------------------
	 * (pre)  int[] networklayers: an array for the layers of the network
	 *                                  for example: 3 5 20 can be a network with 3 input nodes,
	 *                                  5 hidden nodes and 20 output nodes.
	 * (post) the nodes have been created an been put in a collection
	 */
	void addNodes(int[] networkLayers);
	
	/************************************
     * connectNodes()
     *---------------------------------------
	 * (pre)  Nodes are added to the network
	 * (post) connection between the Nodes are made
	 * 
	 * for every layer, the method connectLayers(layer1, layer2) is called
	 */
	void connectNodes();
	
	/************************************
     * connectLayers(NodeLayer layer1, NodeLayer layer2)
     *---------------------------------------
	 * (pre)  NodeLayer layer1 and layer2 are not empty
	 * 
	 * Connect the nodes of the two layers. 
	 * No restriction, this method can be implemented as a FeedForward network
	 * (every node in a layer connects to all the nodes in the previous layer), or a FullyConnected
	 * network, or something else.
	 */
	LineLayer connectLayers(NodeLayer layer1, NodeLayer layer2);
	
	// test and train function
	void train(double[] image, int answer);
	void test(double[] image, int answer);
}
