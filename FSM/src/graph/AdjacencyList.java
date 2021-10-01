/**
 * Please see the project at GitHub
 * @author Santiago Rodas Rodriguez
 * @author Julian Andres Rivera
 * @date 1/10/2021
 */

package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyList<T> implements InterfaceGraph<T>{

	// --------------------------------------------------------------------------------
	
	// Constants of the AdjacencyList

	private static final int DEFAULT_CAPACITY = 21;

	// --------------------------------------------------------------------------------
	
	// Special attributes of the AdjacencyList

	private Map<T, Integer> vertices;	

	private Map<Integer, T> verticesIndices;

	private List<List<T>> adjacencyLists;

	// --------------------------------------------------------------------------------
	
	// Normal attributes of the AdjacencyList

	private boolean isDirected;

	private double[][] adjacencyMatrixWeight;
	
	// --------------------------------------------------------------------------------
	
	// Constructor method - 1
	
	/**
	 * Constructor method to create a object of this class in another part of the system.
	 */

	public AdjacencyList() {
		
		initialize(DEFAULT_CAPACITY);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Constructor method - 2
	
	/**
	 * Constructor method to create a object of this class in another part of the system.
	 * @param directed Boolean data to know if the AdjacencyList is directed
	 */

	public AdjacencyList(boolean directed) {
		
		initialize(DEFAULT_CAPACITY);
		
		isDirected = directed;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Initialize method
	
	/**
	 * Initialize method to assign information when the system create this object.
	 * @param dimension Size of AdjacencyMatrix
	 */

	private final void initialize(int dimension) {
		
		isDirected = false;
		
		adjacencyLists = new ArrayList<List<T>>();
		
		vertices = new HashMap<T, Integer>();
		
		verticesIndices = new HashMap<>();
		
		adjacencyMatrixWeight = new double[dimension][dimension];
		
	}
	
	// --------------------------------------------------------------------------------
	
	// AddVertex method
	
	/**
	 * Method to add a vertex into the system.
	 * @param object Generic node <T> with original information
	 * @return Boolean data where true represent added, and false not
	 */

	@Override
	public boolean addVertex(T object) {
		
		boolean added = false;
		
		if(!search(object)) {
			
			@SuppressWarnings("unchecked")
			List<T> v = (List<T>) new ArrayList<Object>();
			
			int position = adjacencyLists.size();
			
			vertices.put(object, position);
			
			verticesIndices.put(position, object);
			
			adjacencyLists.add(v);
			
			added = true;
			
		}
		
		return added;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// AddEdge method - 1 
	
	/**
	 * Method to add a edge into the system.
	 * @param object1 Generic node <T> with original information
	 * @param object2 Generic node <T> with original information
	 */

	@Override
	public void addEdge(T object1, T object2) {
		
		int va = vertices.get(object1);
		
		int vb = vertices.get(object2);
		
		if(!isDirected) {
			
			adjacencyLists.get(va).add(object2);
			
			adjacencyLists.get(vb).add(object1);
			
		}else {
			
			adjacencyLists.get(va).add(object2);
			
		}
		
	}
	
	// --------------------------------------------------------------------------------
	
	// AddEdge method - 2
	
	/**
	 * Method to add a edge into the system.
	 * @param object1 Generic node <T> with original information
	 * @param object2 Generic node <T> with original information
	 * @param size Dimension of the object edge
	 */

	@Override
	public void addEdge(T object1, T object2, double size) {
		
		// Validate some conditions
		
		int positionx = vertices.get(object1);
		
		int positiony = vertices.get(object2);
		
		Edge<T> edge = new Edge<T>(object1, object2, size);
		
		AdjVertex<T> start = new AdjVertex<T>(object1);
		
		start.getAdjList().add(edge);
		
		if (!isDirected()) {
			
			edge = new Edge<T>(object1, object2, size);
			
			AdjVertex<T> until = new AdjVertex<T>(object2);
			
			until.getAdjList().add(edge);
			
			adjacencyLists.get(positionx).add(object2);
			
			adjacencyLists.get(positiony).add(object1);
			
			adjacencyMatrixWeight[positionx][positiony] = size;
			
			adjacencyMatrixWeight[positiony][positionx] = size;
			
		}
		
	}
	
	// --------------------------------------------------------------------------------
	
	// RemoveVertex method - 1
	
	/**
	 * Method to remove a vertex from the system.
	 * @param object Generic node <T> with original information
	 * @return Boolean data where true represent removed, and false not
	 */

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean removeVertex(T object) {
		
		if (vertices.containsKey(object)) {
			
			adjacencyLists.remove(vertices.get(object));
			
			for (int a = 0; a < adjacencyLists.size(); a ++) {
				
				if (adjacencyLists.get(a).contains(object))
					
					adjacencyLists.get(a).remove(a);
				
			}
			
			vertices.remove(object);
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	// --------------------------------------------------------------------------------
	
	// RemoveEdge method - 2
	
	/**
	 * Method to remove a vertex from the system.
	 * @param object1 Generic node <T> with original information
	 * @param object2 Generic node <T> with original information
	 */

	@Override
	public void removeEdge(T object1, T object2) {
		
		// TODO Auto-generated method stub
		
	}
	
	// --------------------------------------------------------------------------------
	
	// VertexAdjacent method
	
	/**
	 * Method to have all the vertex adjacent to another one.
	 * @param object Generic node <T> with original information
	 * @return Generic list with all the vertex adjacent to another one
	 */

	@Override
	public List<T> vertexAdjacent(T object) {
		
		List<T> n = new ArrayList<>();
		
		AdjVertex<T> v = new AdjVertex<T>(object);
		
		List<Edge<T>> e = v.getAdjList();
		
		for (int i = 0; i < e.size(); i++) {
			
			n.add(e.get(i).getDestination());
			
		}
		
		return n;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// AreConnected method
	
	/**
	 * Method to know if two objects are connected.
	 * @param object1 Generic node <T> with original information
	 * @param object2 Generic node <T> with original information
	 * @return Boolean data where true represents are connected, and false not
	 */

	@Override
	public boolean areConnected(T object1, T object2) {
		
		int a = vertices.get(object1);
		
		int b = vertices.get(object2);
		

		if(isDirected) {
			
			return adjacencyLists.get(a).contains(object2);
			
		}else {
			
			return adjacencyLists.get(a).contains(object2) && adjacencyLists.get(b).contains(object1);
		
		}
		
	}
	
	// --------------------------------------------------------------------------------
	
	// WeightMatrix method
	
	/**
	 * Method to have the weight matrix of the system.
	 * @return Double matrix with all the information
	 */

	@Override
	public double[][] weightMatrix() {
		
		for (int a = 0; a < adjacencyLists.size(); a ++) {
			
			for (int b = 0; b < adjacencyLists.size(); b ++) {
				
				if (adjacencyMatrixWeight[a][b] == 0 ) {
					
					if (a != b) {
						
						adjacencyMatrixWeight[a][b] = Double.MAX_VALUE;
						
					}
					
				}
				
			}
			
		}
		
		return adjacencyMatrixWeight;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// IsDirected Method
	
	/**
	 * Method to know if the AdjacencyList is directed.
	 * @return Boolean data where true represent is directed, and false not
	 */

	@Override
	public boolean isDirected() {
		
		return isDirected;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetVertexSize Method
	
	/**
	 * Method to have the size of the vertex.
	 * @return Integer data with the information of the size
	 */

	@Override
	public int getVertexSize() {
		
		return vertices.size();
		
	}
	
	// --------------------------------------------------------------------------------
	
	// IsWeighted Method
	
	/**
	 * Method to know if the AdjacencyList is weighted.
	 * @return Boolean data where true represent is weighted, and false not
	 */

	@Override
	public boolean isWeighted() {
		
		// TODO Auto-generated method stub
		
		return false;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GerIndex Method
	
	/**
	 * Method to have the index of the vertex.
	 * @param object Vertex of the system (Generic node <T>) 
	 * @return Integer data with the information of the index
	 */

	@Override
	public int getIndex(T object) {
		
		return vertices.get(object);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Search Method
	
	/**
	 * Method to know if one object exists into the system.
	 * @param object Information to use into the search method
	 * @return Boolean data where true represent if the object exists, and false if not
	 */

	@Override
	public boolean search(T object) {
		
		return verticesIndices.containsValue(object);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetVertices Method
	
	/**
	 * Method to have the vertices of the system.
	 * @return Map with the information of the vertices
	 */

	public Map<T, Integer> getVertices() {
		
		return vertices;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetAdjacencyLists Method
	
	/**
	 * Method to have the adjacencyList of the system.
	 * @return List with the information of the adjacencyList
	 */

	public List<List<T>> getAdjacencyLists() {
		
		return adjacencyLists;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Search method
	
	/**
	 * Method to search a object with the index.
	 * @param position Index of the object that we want to have
	 * @return Generic node <T>
	 */

	@Override
	public T search(int position) {
		
		return verticesIndices.get(position);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetEdges method
	
	/**
	 * Method to have a list of the edges.
	 * @return List with all the edges that we need of the system
	 */

	@Override
	public List<Edge<T>> getEdges() {
		
		ArrayList<Edge<T>> e = new ArrayList<>();
		
		for (int a = 0; a < vertices.size(); a ++) {
			
			AdjVertex<T> vertex = new AdjVertex<T>(verticesIndices.get(a));
			
			for (int b = 0; b < vertex.getAdjList().size(); b ++) {
				
				e.add(vertex.getAdjList().get(b));
				
			}
			
		}
		
		return e;
		
	}
	
	// --------------------------------------------------------------------------------

}
