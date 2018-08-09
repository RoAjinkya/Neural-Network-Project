package edu.neu.coe.neuralnetwork.driver;



import edu.neu.coe.neuralnetwork.backpropagation.BackPropagation;

import java.util.Scanner;

/**
 * @author ketulshukla
 *
 */
public class Driver {

	// the initial values of the setting of the neural network.
    
    // these are the values that are being presented to the user when the application starts
    
    // and array of int where each number represents the amount of nodes in that layer.
    // the first number represents the input layer, and the last one the hidden.
    // the number of hidden layers is undetermined and unlimited.
    private final int[] NODES = { 400, 250, 10 };

    private final double MOMENTUM = 0.3;

    // the boundaries of the initial values of the weights of the lines.
    private final double INIT_WEIGHT[] = { -0.05, 0.05 };
    
    // files to use as image database, both for training and testing
    private final String DIGIT_PATH = "digit_data/";
    private final String FILE_TRAIN[] = { DIGIT_PATH + "train-images-idx3-ubyte", DIGIT_PATH + "train-labels-idx1-ubyte" };
    private final String FILE_TEST[] = { DIGIT_PATH + "t10k-images-idx3-ubyte", DIGIT_PATH + "t10k-labels-idx1-ubyte" };
    
    // number of examples that the network should traing/test
    private final int NR_EXAMPLES_TRAIN = 60000;
	private final int NR_EXAMPLES_TEST = 10000;

	// how often these examples should be inserted into the netwerk
    private int NR_ROUNDS   = 3;
    
    // variables for program flow
    
    // whether we are doing a training. if false: we are testing the network.
    private boolean training  = true;
    
    // how many steps to perform before printing and plotting the error value.
    private int stepSize = 100;
    
    private int networkLayers[];

	private String choiceForRun;

	private Scanner scan = new Scanner(System.in);

    // neural network variables
    // an object that represents the network we are using: backpropagation
    //BackPropagation network;
    private BackPropagation network;
    
    // the object that actually does the calculations.
    // since this is a driver, we want the user to be able to stop the simulation (or
    // do other things) while the simulation is running. therefore this object is
    // a thread; it will run in the background and communicate with the [network]
    // object but send results to this class.
    private Runner runThread;

    /************************************
     * Log(String log)
     *---------------------------------------
     * logging function for writing to console to verify the result and errors.
     */

    public void Log(String log) {
		System.out.println(log);
    }

    /************************************
     * startTraining()
     *---------------------------------------
     * run the training.
     */
    private void startTraining() {
		networkLayers = NODES;

		// if this is the first time we are doing a training, or if
		// the reset just has been pressed: create a new neural network
		if (network == null) {
			//network = new BackPropagation(networkLayers);
			network = new BackPropagation(networkLayers);
			
			// and initiate it with the driver values
			network.setStepSize(stepSize);
			network.setInitWeights(INIT_WEIGHT);
			network.setMomentum(MOMENTUM);
			
			network.printTopology();
		}
    	
		// if we were previously doing a test
		if (!training) {
			training = true;
		}

		// now start train the network as a thread
    	runThread = new Runner(this, network);
    	runThread.setTrain(true);
    	runThread.start();
    }
    
    /************************************
     * startTest
     *---------------------------------------
     * test the network
     */
    private void startTest() {
    	// make sure that we have a network to test
    	if (network == null) {
    		Log("Please train your model first!");
    		Log("Start Test or Train, Enter 1");
    	} else {
    		// if we were previously doing a training
    		if (training) {
    			training = false;
    		}
    		// run the test in a thread
    		runThread = new Runner(this, network);
        	runThread.setTrain(false);
        	runThread.start();
    	}
    }

    /************************************
     * getStepSize()
     *---------------------------------------
     * returns step size
     */
    public int getStepSize() {
    	return stepSize;
    }

	public String[] getFiles() {
    	String[] files = new String[2];
    	if(choiceForRun.equalsIgnoreCase("Train")){
			files[0] = FILE_TRAIN[0];
			files[1] = FILE_TRAIN[1];
		}else{
			files[0] = FILE_TEST[0];
			files[1] = FILE_TEST[1];
		}
    	return files;
    }

    public int getExamples() {
		if(choiceForRun.equalsIgnoreCase("Train")){
			return NR_EXAMPLES_TRAIN;
		}else{
			return NR_EXAMPLES_TEST;
		}
    }
    
    public int getRuns() {
		return NR_ROUNDS;
	}
    
	//start of system run
	public void startSystem() {
		Log("Please make an selection:");
		Log("1. Start Test or Train, Enter 1");
		Log("2. Stop the run, Enter 2");
		while(scan.hasNext()){
			int choice = scan.nextInt();
			switch (choice){
				case 1:
					Log("Please enter Train or Test:");
					choiceForRun = scan.next();
					if (choiceForRun.equalsIgnoreCase("Train")) {
						startTraining();
					} else {
						Log("We have 10K data to test; Please enter number of runs:");
						NR_ROUNDS = scan.nextInt();
						startTest();					}
					break;
				case 2:
					runThread.interrupt();
					Log("Start Test or Train, Enter 1");
					break;
			}
		}
	}

	public static void main(String[] args){
		Driver driver = new Driver();
		driver.startSystem();
	}
}