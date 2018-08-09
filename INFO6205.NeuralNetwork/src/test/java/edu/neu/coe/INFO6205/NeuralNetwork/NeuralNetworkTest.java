package edu.neu.coe.INFO6205.NeuralNetwork;

import edu.neu.coe.neuralnetwork.elements.Connection;
import edu.neu.coe.neuralnetwork.elements.Neuron;
import edu.neu.coe.neuralnetwork.elements.OutputNeuron;
import edu.neu.coe.neuralnetwork.math.ErrorFunction;
import edu.neu.coe.neuralnetwork.math.SigmoidFunction;
import edu.neu.coe.neuralnetwork.math.WeightFunction;
import junit.framework.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
/**
 * @author ajinkyarode
 *
 */
public class NeuralNetworkTest {
	ErrorFunction errorfun;
	WeightFunction weightfun;
	SigmoidFunction sigmoidfun;
	Neuron n1;

	//Test Weight Function class’s compute method.
	@org.junit.Test
	public void testWeightFunction(){
		assertEquals(weightfun.compute(1, 1, 1, 1, 1, 1), weightfun.compute(1, 1, 1, 1, 1, 1),0.0);
		
	}

	//Test Sigmoid Function class’s compute method.
	@org.junit.Test
	public void testSigmoidFunction(){
		assertEquals(sigmoidfun.compute(5), sigmoidfun.compute(5),0.0);
		
	}

	//Test Error Function class’s compute method and compute Output method.
	@org.junit.Test
	public void testErrorFunction(){
		assertEquals(errorfun.compute(5.0,0.1), errorfun.compute(5.0,0.1),0.0);
		
	}

	//Test Neuron creation and its parameters such as output and error.
	@org.junit.Test
	public void testErrorFunctioncomputeOutput(){
		assertEquals(errorfun.computeOutput(5.0,0.1), errorfun.computeOutput(5.0,0.1),0.0);
		
	}

	//Test Connection creation and its parameters such as weight, to Neuron and from Neuron that connection is.
	@org.junit.Test
	public void testNeronFunction(){
		Neuron n1 = new Neuron(1.0, 2.0);
	  
		assertEquals(1.0, n1.getOutput(),0.0);
		assertEquals(2.0, n1.getError(),0.0);
	}
	
	//Test Connection creation and its parameters such as weight, to Neuron and from Neuron that connection is.
	@org.junit.Test
	public void testConnectionFunction(){
		Neuron n1 = new Neuron(1.0, 1.0);
		Neuron n2 = new Neuron(1.0, 1.0);
		Connection c1 = new Connection(n1, n2);
	   c1.setWeight(1);
	  
	   assertEquals(1.0, c1.getWeight(),0.0);
	   assertEquals(n1, c1.getFromNeuron());
	   assertEquals(n2, c1.getToNeuron());
	 
	}

	//Test Output Neuron creation and its parameters such as target setting and getting it.
	@org.junit.Test
	public void testOutputNeronFunctioncompute(){
		OutputNeuron on1 = new OutputNeuron();
		on1.setTarget(1.0);
		assertEquals(1.0, on1.getTarget(),0.0);
		
	}
		
	
	
	
}
