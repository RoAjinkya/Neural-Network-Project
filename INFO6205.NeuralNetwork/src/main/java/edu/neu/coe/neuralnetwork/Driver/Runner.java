package edu.neu.coe.neuralnetwork.Driver;


/*********************************
* Runner.java
*
* Thread object that will work the neural network.
* Is capable of both training and testing and does image processing as well.
* It reports results back to its parent, which is a Driver.
* Gets all the required settings for the network from the parent.
*
* The thread dies when finished, or when interrupted.
*/

import edu.neu.coe.neuralnetwork.backpropagation.BackPropagation;
import edu.neu.coe.neuralnetwork.filehandler.*;
import mnist.tools.MnistManager;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author ajinkyarode
 *
 */
public class Runner extends Thread {
	private Driver parent;
	private MnistManager m;
	private boolean train;
	BackPropagation network;

	public Runner(Driver parent, BackPropagation network) {
		// find out who the parent is, and use the network of the parent
		this.parent = parent;
		this.network = network;
	}

	public void run() {
		// open the image files
		String files[] = parent.getFiles();
		try {
			m = new MnistManager(files[0], files[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// get the loop values
		int nrExamples = parent.getExamples();
		int nrRuns     = parent.getRuns();

		// find out what to do with the images
		double image[];

    	// value will contain the correct answers (what the network should answer)
    	int value = 0;

    	// start by sending a message to the parent what we are going to do and
    	// what the network looks like
    	String modeTxt = (train) ? "Training" : "Testing";

    	parent.Log( modeTxt + " mode\n");
    	parent.Log(network.printTopology());
    	parent.Log("The console shows the examples executed and the error rate. Based on which Accuracy Graph is formed in excel.");

    	// if we are testing, we should tell the network to load the optimal weight values
    	// which are saved from the training session.
    	if (!train) {
			network.loadOptimalWeights();
		}

		ArrayList<ResultData> arrayListForCSV = new ArrayList<ResultData>();
		FileHandler<ResultData> fileHandler = new FileHandlerImpl_CSV<ResultData>();
		String fileName = "";
		double accuracy = 0.0;

		for (int runs=0; runs<nrRuns; runs++) {
    		parent.Log("Run: " + (runs+1));
	    	for (int example=1; example<nrExamples; example++) {
	    		// load image
	    		m.setCurrent(example);
				try {
					// pre-process image according to setting defined by user
					image = processImage(m.readImage());
					value = m.readLabel();

					if (train) {
						fileName="Train_Result.csv";
						network.train(image, value);
					} else {
						fileName="Test_Result.csv";
						network.test(image, value);
					}

					// collect the average error value of the network every [stepSize]
					// times and send these values to the plotter and the debug window in the Driver
					if (example % parent.getStepSize() == 0) {
						double currentStepError = network.getCurStepError();
						accuracy += currentStepError;
						parent.Log(example + ": " + currentStepError);
						ResultData resultData = new ResultData(example, currentStepError);
						arrayListForCSV.add(resultData);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				// if the user wants to stop the simulation, we should die.
				if (interrupted()) {
					fileHandler.writecsv(arrayListForCSV.get(0).getColName(),fileName,arrayListForCSV);
					accuracy = 100.00-((accuracy/((runs+1)*example))*100);
					parent.Log("Current Accuracy is: " + accuracy+"%");
					parent.Log("Run stopped.");
					return;
				}
			}
    	}

    	//Calculating the accuracy
    	fileHandler.writecsv(arrayListForCSV.get(0).getColName(),fileName,arrayListForCSV);
		accuracy = 100.00-((accuracy/((nrRuns)*nrExamples))*100);
		parent.Log("Current Accuracy is: " + accuracy+"%");
		parent.Log("The console shows the examples executed and the error rate. Based on which Accuracy Graph is formed in excel.");
    }

	 /************************************
     * processImage(int image[][], boolean[] preProcessor)
     *---------------------------------------
     * preprocess the image according to the values of the preProcessor
     * see Driver.java for explanation about this technique
     */
    private double[] processImage(int image[][]) {
    	int imgCanvas = 4;
    	int dim1 = image.length-(imgCanvas*2);
    	int dim2 = image[0].length-(imgCanvas*2);
		double ret[] = new double[(dim1*dim2)];

		for (int i=0; i<dim1; i++) {
			for (int j=0; j<dim2; j++) {
				// first scale values to [0,1]
				double inputValue = scale(image[i+imgCanvas][j+imgCanvas]);
				// enter values in the input nodes
				ret[j + dim2*i] = inputValue;
			}
		}
		return ret;
	}

    // use this to scale the inputs to [0,1]
	private double scale(double num) {
		return num / 255;
	}

	public void setTrain(boolean train) {
		this.train = train;
	}
}
