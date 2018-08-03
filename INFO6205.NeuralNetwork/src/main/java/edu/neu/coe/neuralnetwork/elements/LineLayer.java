package edu.neu.coe.neuralnetwork.elements;


/*********************************
* LineLayer.java
* 
* The LineLayer is simply a collection of lines.
* The size is static (not dynamic like a VectorList), because of efficiency consideration.
* Please read FeedForward.java for more details on this subject.
*/

/**
 * @author ajinkyarode
 *
 */
public class LineLayer {
	private Line[] lines;
	
	public LineLayer(int length) {
		lines = new Line[length];
	}
	
	public void add(Line line, int index) {
		lines[index] = line;
	}
	
	public Line get(int i) {
		return lines[i];
	}
	
	public int size() {
		return lines.length;
	}
}
