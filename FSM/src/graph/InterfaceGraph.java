/**
 * Please see the project at GitHub
 * @author Santiago Rodas Rodriguez
 * @author Julian Andres Rivera
 * @date 29/09/2021
 */

package graph;

import java.util.List;

public interface InterfaceGraph<T> {
	
	// --------------------------------------------------------------------------------
	
	// AddVertex method
	
	/**
	 * @see AdjacencyMatrix and AdjacencyList class
	 * where the related information with the addVertex method 
	 * is specified in a more specific way
	 */

	public boolean addVertex(T node);
	
	// --------------------------------------------------------------------------------
	
	// AddEdge method
	
	/**
	 * @see AdjacencyMatrix and AdjacencyList class 
	 * where the related information with the addEdge method 
	 * is specified in a more specific way
	 */
	
	public void addEdge(T A, T B);
	
	// --------------------------------------------------------------------------------
	
	// AddEdge method 2
	
	/**
	 * @see AdjacencyMatrix and AdjacencyList class 
	 * where the related information with the addEdge method 
	 * is specified in a more specific way
	 */
	
	public void addEdge(T A, T B, double l);
	
	// --------------------------------------------------------------------------------
	
	// RemoveVertex method
	
	/**
	 * @see AdjacencyMatrix and AdjacencyList class 
	 * where the related information with the removeVertex method 
	 * is specified in a more specific way
	 */

	public boolean removeVertex(T node);
	
	// --------------------------------------------------------------------------------
	
	// RemoveEdge method
	
	/**
	 * @see AdjacencyMatrix and AdjacencyList class 
	 * where the related information with the removeEdge method 
	 * is specified in a more specific way
	 */

	public void removeEdge(T A, T B);
	
	// --------------------------------------------------------------------------------
	
	// VertexAdjacent method
	
	/**
	 * @see AdjacencyMatrix and AdjacencyList class 
	 * where the related information with the vertexAdjacent method 
	 * is specified in a more specific way
	 */
	
	public List<T> vertexAdjacent(T node);
	
	// --------------------------------------------------------------------------------
	
	// AreConnected method
	
	/**
	 * @see AdjacencyMatrix and AdjacencyList class 
	 * where the related information with the areConnected method 
	 * is specified in a more specific way
	 */
	
	public boolean areConnected(T A, T B);
	
	// --------------------------------------------------------------------------------
	
	// WeightMatrix method
	
	/**
	 * @see AdjacencyMatrix and AdjacencyList class 
	 * where the related information with the weightMatrix method 
	 * is specified in a more specific way
	 */

	public double[][] weightMatrix();
	
	// --------------------------------------------------------------------------------
	
	// IsDirected Method
	
	/**
	 * @see AdjacencyMatrix and AdjacencyList class 
	 * where the related information with the isDirected method 
	 * is specified in a more specific way
	 */
	
	public boolean isDirected();
	
	// --------------------------------------------------------------------------------
	
	// GetVertexSize method
	
	/**
	 * @see AdjacencyMatrix and AdjacencyList class 
	 * where the related information with the getVertexSize method 
	 * is specified in a more specific way
	 */
	
	public int getVertexSize();
	
	// --------------------------------------------------------------------------------
	
	// IsWeighted method
	
	/**
	 * @see AdjacencyMatrix and AdjacencyList class 
	 * where the related information with the isWeighted method 
	 * is specified in a more specific way
	 */
	
	public boolean isWeighted();

	// --------------------------------------------------------------------------------
	
	// GetIndex method
	
	/**
	 * @see AdjacencyMatrix and AdjacencyList class 
	 * where the related information with the getIndex method 
	 * is specified in a more specific way
	 */
	
	public int getIndex(T vertex);
	
	// --------------------------------------------------------------------------------
	
	// Search method
	
	/**
	 * @see AdjacencyMatrix and AdjacencyList class 
	 * where the related information with the search method 
	 * is specified in a more specific way
	 */
	
	public boolean search(T A);

	// --------------------------------------------------------------------------------
	
	// Search method 2
	
	/**
	 * @see AdjacencyMatrix and AdjacencyList class 
	 * where the related information with the search method 
	 * is specified in a more specific way
	 */
	
	public T search(int index);
	
	// --------------------------------------------------------------------------------
	
	// GetEdges method
	
	/**
	 * @see AdjacencyMatrix and AdjacencyList class 
	 * where the related information with the getEdges method 
	 * is specified in a more specific way
	 */
	
	public List<Edge<T>> getEdges();
	
	// --------------------------------------------------------------------------------
	
}
