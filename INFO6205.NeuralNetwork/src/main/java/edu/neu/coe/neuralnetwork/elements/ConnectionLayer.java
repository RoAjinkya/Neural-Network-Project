package edu.neu.coe.neuralnetwork.elements;

/*********************************
* ConnectionLayer.java
* 
* The ConnectionLayer is simply a collection of connections.
* The size is static (not dynamic like a VectorList), because of efficiency consideration.
* Please read FeedForward.java for more details on this subject.
*/

/**
 * @author ketulshukla
 *
 */

public class ConnectionLayer {
	private Connection[] connections;
	
	public ConnectionLayer(int length) {
		connections = new Connection[length];
	}
	
	public void add(Connection connection, int index) {
		connections[index] = connection;
	}
	
	public Connection get(int i) {
		return connections[i];
	}
	
	public int size() {
		return connections.length;
	}
}
