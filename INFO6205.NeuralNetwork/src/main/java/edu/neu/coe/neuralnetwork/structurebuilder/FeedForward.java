package edu.neu.coe.neuralnetwork.structurebuilder;

import edu.neu.coe.neuralnetwork.elements.*;
import edu.neu.coe.neuralnetwork.elements.ConnectionLayer;

// FFANN = feed forward artificial neural network
/**
 * @author ajinkyarode
 *
 */
public abstract class FeedForward implements NeuralNetwork {
	// the layers of the network
	protected NeuronLayer inputLayer;
	protected NeuronLayer[] hiddenLayers;
	protected NeuronLayer outputLayer;
	
	protected ConnectionLayer[] connectionLayers;
	
	// inserts an image into the network
	// abstract: to be implemented by the algorithm that inherits this class
	public abstract void train(double[] image, int answer);
	public abstract void test(double[] image, int answer);
		
	// constructor: build the network
	public FeedForward(int[] networkLayers) {
		addNodes(networkLayers);
		connectNodes();
	}

	public void addNodes(int[] networkLayers) {
		// first initialize all the layers with their corresponding size (this is
		// what i hate about arrays)
		inputLayer = new NeuronLayer(networkLayers[0]);
		hiddenLayers = new NeuronLayer[networkLayers.length-2];
		outputLayer = new NeuronLayer(networkLayers[networkLayers.length-1]);
		
		// now add the Nodes to the input layer
		for (int i=0; i<networkLayers[0]; i++) {
			inputLayer.add(new Neuron(), i);
		}

		// then all hidden nodes to their corresponding layer
		for (int i=1; i<networkLayers.length - 1; i++) {
			NeuronLayer tempNeuronLayer = new NeuronLayer(networkLayers[i]);
			
			for (int j=0; j<networkLayers[i]; j++) {
				tempNeuronLayer.add(new Neuron(), j);
			}
			hiddenLayers[i-1] = tempNeuronLayer;
		}
		
		// finally add output nodes
		for (int i=0; i<networkLayers[networkLayers.length-1]; i++) {
			outputLayer.add(new OutputNeuron(), i);
		}
	}
	
	public void connectNodes() {
		NeuronLayer connectingOutputLayer;
		
		// connectionLayers: an array of ConnectionLayer that contain all the layers
		// of the lines. first determine the size of this array
		//
		// connect at least the input to the output (1), for every
		// hidden layer we need an extra line layer
		connectionLayers = new ConnectionLayer[hiddenLayers.length + 1];
		
		// connect input node to next layer, is this a hidden layer?
		if (hiddenLayers.length > 0) {
			// there is at least one hidden node layer, so connect every node in the first hidden layer to all input nodes
			connectionLayers[0] = connectLayers(inputLayer, hiddenLayers[0]);
			
			// now iterate over all hidden layers and connect them to each other
			for (int i=1; i<hiddenLayers.length; i++) {				
				connectionLayers[i] = connectLayers(hiddenLayers[i-1], hiddenLayers[i]);
			}
			
			// make sure that the output layer will connect to the last hidden layer			
			connectingOutputLayer = hiddenLayers[hiddenLayers.length - 1];
		} else {
			// there is no hidden layer, so let the output layer connect to the input layer
			connectingOutputLayer = inputLayer;
		}
		
		// connect every output node to all the nodes in the connectingOutputLayer
		connectionLayers[connectionLayers.length - 1] = connectLayers(connectingOutputLayer, outputLayer);
	}
		
	// the core of the FeedForward algorithm:
	// connect every node of layer1 to all the nodes of layer2
	public ConnectionLayer connectLayers(NeuronLayer layer1, NeuronLayer layer2) {
		// first determine the size of the ConnectionLayer
		ConnectionLayer tempConnectionLayer = new ConnectionLayer(layer1.size() * layer2.size());
		
		for (int i=0; i<layer2.size(); i++) {
			for (int j=0; j<layer1.size(); j++) {
				// iterate over all the Nodes in layer1 and connect them to every node
				// of layer 2. first determine position in the array.
				int curPos = layer1.size() * i + j;
				// then add the line to the ConnectionLayer
				tempConnectionLayer.add(new Connection(layer1.get(j), layer2.get(i)), curPos);
			}	
		}
		
		return tempConnectionLayer;
	}
				
	public void inputExample(double image[]) {	
		for (int i=0; i<image.length; i++) {
			inputLayer.get(i).setOutput(image[i]);
		}
	}
		
	public String printTopology() {
		String str = "";
		
		str += "input nodes: " + inputLayer.size();
		str += "\n connections: " + connectionLayers[0].size();
		for (int i=0; i<hiddenLayers.length; i++) {
			str += "\nhidden layer (" + i + "): " + hiddenLayers[i].size();
			str += "\n connections: " + connectionLayers[i + 1].size();
		}
		str += "\noutput nodes: " + outputLayer.size() + "\n\n";
		return str;
	}
	
	protected void resetNodes() {
		// NOTE: input nodes need no reset.
		//       their ouput value will be equal to the input of the image
		//       and their error value is irrelevant
		
		// iterate over all hidden layers
		for (int i=0; i<hiddenLayers.length; i++) {
			// empty all nodes in this layer
			for (int j=0; j<hiddenLayers[i].size(); j++) {
				hiddenLayers[i].get(j).reset();
			}
		}
		
		// now empty all nodes in the output layer
		for (int i=0; i<outputLayer.size(); i++) {
			this.outputLayer.get(i).reset();
		}
	}
}
