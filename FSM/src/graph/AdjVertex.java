/**
 * Please see the project at GitHub
 * @author Santiago Rodas Rodriguez
 * @author Julian Andres Rivera
 * @date 30/09/2021
 */

package graph;

import java.util.LinkedList;
import java.util.List;

public class AdjVertex<T> {
	
	// --------------------------------------------------------------------------------
	
	// AdjVertex attributes
	
	private List<Edge<T>> adjList;
	
	// --------------------------------------------------------------------------------
	
	// Constructor method of the class AdjVertex
	
	/**
	 * Constructor method to create a object in another class.
	 * @param value Generic object with a original information
	 */

	public AdjVertex(T value) {
		
		adjList = new LinkedList<Edge<T>>();
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetAdjList method
	
	/**
	 * Method to have the information of the Adjacency list.
	 * @return List of a Generic Edge object with all the information of the system
	 */

	public List<Edge<T>> getAdjList() {
		
		return adjList;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// IsAdjacent method
	
	/**
	 * Method to know if a object is Adjacency with another one.
	 * @param vertex Generic object of the AdjVertex class
	 * @return Boolean data where true represents is adjacent, and false not adjacent
	 */

	public boolean isAdjacent(AdjVertex<T> vertex) {
		
		for (int i = 0; i < adjList.size(); i++) {
			
			if (adjList.get(i).getDestination() == vertex)
				return true;
		
		}
		
		return false;
		
	}
	
	// --------------------------------------------------------------------------------
	
}
