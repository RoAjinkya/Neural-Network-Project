package edu.neu.coe.neuralnetwork.filehandler;

public class ResultData {
	
	int noofExamples;
	double currentStepError; 
	
	public ResultData(int noofExamples, double currentStepError) {
		super();
		this.noofExamples = noofExamples;
		this.currentStepError = currentStepError;
	}

	public int getNoofExamples() {
		return noofExamples;
	}

	public void setNoofExamples(int noofExamples) {
		this.noofExamples = noofExamples;
	}

	public double getCurrentStepError() {
		return currentStepError;
	}

	public void setCurrentStepError(double currentStepError) {
		this.currentStepError = currentStepError;
	}

	public String toFile() {
		return noofExamples+","+currentStepError+"\n";
	}
	
	public String getColName() {
		return "no of Examples, current Step Error\n";
	}
	
	
	
	
	
	
	
	

}
