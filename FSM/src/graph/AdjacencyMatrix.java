/**
 * Please see the project at GitHub
 * @author Santiago Rodas Rodriguez
 * @author Julian Andres Rivera
 * @date 30/09/2021
 */

package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class AdjacencyMatrix<T> implements InterfaceGraph<T>{

	// --------------------------------------------------------------------------------
	
	// Constants of the AdjacencyMatrix class

	private static final int DEFAULT_CAPACITY = 21;

	private static final double GROWTH_FACTOR = 1.5;

	// --------------------------------------------------------------------------------
	
	// Primitive data

	private int size;

	private boolean isDirected;

	// --------------------------------------------------------------------------------
	
	// Array data of the AdjacencyMatrix class

	private double[][] adjacencyMatrix;

	private double[][] adjacencyMatrixWeight;
	
	// --------------------------------------------------------------------------------
	
	// Map and NavigableSet data of the AdjacencyMatrix class

	private Map<Integer, T> vertices;

	private Map<T, Integer> verticesIndices;

	private NavigableSet<Integer> emptySlots = new TreeSet<>();
	
	// --------------------------------------------------------------------------------
	
	// Constructor method number one of the AdjacencyMatrix class
	
	/**
	 * This is the first constructor method of this class.
	 * The principal idea is to call the initialize method
	 */

	public AdjacencyMatrix() {
		
		initialize(DEFAULT_CAPACITY);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Constructor method number two of the AdjacencyMatrix class
	
	/**
	 * This is the second constructor method of this class.
	 * @param directed Boolean data with the initial information of the AdjacencyMatrix
	 */

	public AdjacencyMatrix(boolean directed) {
		
		initialize(DEFAULT_CAPACITY);
		
		isDirected = directed;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Constructor method number three of the AdjacencyMatrix class
	
	/**
	 * This is the third constructor method of this class.
	 * @param dimension Represent the maximum capacity of the matrix
	 */

	public AdjacencyMatrix(int dimension) {
		
		initialize(dimension);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Constructor method number four of the AdjacencyMatrix class
	
	/**
	 * This is the fourth constructor method of this class.
	 * @param directed Boolean data with the initial information of the AdjacencyMatrix
	 * @param dimension Represent the maximum capacity of the matrix
	 */

	public AdjacencyMatrix(boolean directed, int dimension) {
		
		initialize(dimension);
		
		isDirected = directed;
		
	}
	
	// --------------------------------------------------------------------------------
	
	/**
	 * Method to initialize the AdjacencyMatrix class.
	 * @param dimension Represent the size of the specify matrix
	 */

	private void initialize(int dimension) {
		
		isDirected = false;
		
		size = 0;
		
		adjacencyMatrix = new double[dimension][dimension];
		
		vertices = new HashMap<>();
		
		verticesIndices = new HashMap<>();
		
		adjacencyMatrixWeight = new double[dimension][dimension];
		
	}
	
	// --------------------------------------------------------------------------------
	
	// AddVertex Method
	
	/**
	 * Method to add a vertex in the AdjacencyMatrix.
	 * @param node Generic object <T> with a original information
	 * @return Boolean data that represent true if was added, and false if not
	 */

	@Override
	public boolean addVertex(T node) {
		
		boolean added = false;
		
		Integer position;
		
		if (verticesIndices.get(node) == null) {
			
			if (emptySlots.isEmpty()) {
				
				if (size == adjacencyMatrix.length) {
					
					double[][] holder = adjacencyMatrix;
					
					int nl = (int) (adjacencyMatrix.length * GROWTH_FACTOR);
					
					adjacencyMatrix = new double[nl][nl];
					
					for (int i = 0; i < holder.length; i++) {
						
						System.arraycopy(holder[i], 0, adjacencyMatrix[i], 0, holder.length);
					
					}
					
				}
				
				size++;
				
				position = size - 1;
				
			} else {
				
				position = emptySlots.pollFirst();//TODO: May assign null?
				
			}
			
			vertices.put(position, node);
			
			verticesIndices.put(node, position);
			
			added = true;
			
		}
		
		return added;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// AddEdge method - 1
	
	/**
	 * Method one to add a specify edge to the matrix.
	 * @param object1 Generic node <T> with a specify information
	 * @param object2 Generic node <T> with a specify information
	 */

	@Override
	public void addEdge(T object1, T object2) {
		
		Integer n1 = verticesIndices.get(object1);
		
		Integer n2 = verticesIndices.get(object2);
		
		if (n1 != null && n2 != null) {
			
			if (!isDirected) {
				
				adjacencyMatrix[n1][n2] = 1;
				
				adjacencyMatrix[n2][n1] = 1;
				
			} else {
				
				adjacencyMatrix[n1][n2] = 1;
				
			}
			
		}else{
			
		} // ¿Can be better if this method return a boolean?
		
	}
	
	// --------------------------------------------------------------------------------
	
	// AddEdge method - 2
	
	/**
	 * Method two to add a specify edge to the matrix.
	 * @param object1 Generic node <T> with a specify information
	 * @param object2 Generic node <T> with a specify information
	 * @param aux Double data (It's better if start in 0 or 1)
	 */

	@Override
	public void addEdge(T object1, T object2, double aux) {
		
		// Check the conditions
		
		int n1 = verticesIndices.get(object1);
		
		int n2 = verticesIndices.get(object2);
		
		if (!isDirected) {
			
			adjacencyMatrix[n1][n2] = 1;
			
			adjacencyMatrix[n2][n1] = 1;
			
			adjacencyMatrixWeight[n1][n2] = aux;
			
			adjacencyMatrixWeight[n2][n1] = aux;
			
		} else {
			
			adjacencyMatrix[n1][n2] = 1;
			
			adjacencyMatrixWeight[n1][n2] = aux;
			
		}
		
	}
	
	// --------------------------------------------------------------------------------
	
	// RemoveVertex method - 1
	
	/**
	 * Method one to remove a specify vertex of the system.
	 * @param object Generic node <T> with a original information
	 * @return Boolean data that represent true if was removed, and false if not
	 */

	@Override
	public boolean removeVertex(T object) {
		
		boolean removed = false;
		
		Integer point = verticesIndices.get(object);
		
		if (point != null) {
			
			vertices.remove(point);
			
			verticesIndices.remove(object);
			
			emptySlots.add(point);
			
			for (int i = 0; i < size; i++) {
				
				adjacencyMatrix[point][i] = 0;
				
			}
			
			for (int i = 0; i < size; i++) {
				
				adjacencyMatrix[i][point] = 0;
				
			}
			
			removed = true;
			
		}
		
		return removed;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// RemoveVertex method - 2
	
	/**
	 * Method two to remove a specify vertex of the system.
	 * @param object1 Generic node <T> with a original information
	 * @param object2 Generic node <T> with a original information
	 */

	@Override
	public void removeEdge(T object1, T object2) {
		
		if (!isDirected) {
			
			// Check the conditions
			
			adjacencyMatrix[(int) object1][(int) object2] = 0;
			
			adjacencyMatrix[(int) object2][(int) object1] = 0;
		
		} else {
			
			adjacencyMatrix[(int) object1][(int) object2] = 0;
			
		}
		
	}
	
	// --------------------------------------------------------------------------------
	
	// VertexAdjacent method
	
	/**
	 * Method to change and take the adjacency object of a vertex.
	 * @param object Generic node <T> with a original information
	 * @return Generic list with all the adjacency objects
	 */

	@Override
	public List<T> vertexAdjacent(T object) {
		
		Integer point = verticesIndices.get(object);
		
		List<T> adjacent = null;
		
		if (point != null) {
			
			Set<Integer> avp = new HashSet<>();
			
			for (int i = 0; i < size; i++) {
				
				if (adjacencyMatrix[point][i] != 0) {
					
					avp.add(i);
					
				}
				
			}
			
			if (isDirected) {
				
				for (int i = 0; i < size; i++) {
					
					if (adjacencyMatrix[i][point] != 0) {
						
						avp.add(i);
						
					}
					
				}
				
			}
			
			adjacent = new ArrayList<>();
			
			for (Integer key : avp) {
				
				adjacent.add(vertices.get(key));
				
			}
			
		}
		
		return adjacent;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// AreConnected method
	
	/**
	 * Method to know if one object and another one are connected inside the matrix.
	 * @param object1 Generic node <T> with a original information
	 * @param object2 Generic node <T> with a original information
	 * @return Boolean data that represent true if was connected, and false if not
	 */

	@Override
	public boolean areConnected(T object1, T object2) {
		
		// Check if the information exists in the graph
		
		int u = verticesIndices.get(object1);
		
		int v = verticesIndices.get(object2);
		
		if (isDirected) {
			
			return adjacencyMatrix[u][v] == 1;
			
		} else {
			
			return adjacencyMatrix[u][v] == 1 && adjacencyMatrix[v][u] == 1;
		
		}
		
	}
	
	// --------------------------------------------------------------------------------
	
	// WeightMatrix method
	
	/**
	 * Method to have the weight matrix of the system.
	 * @return Array type double with all the information that we needed
	 */

	@Override
	public double[][] weightMatrix() {
		
		for (int a = 0; a < adjacencyMatrix.length; a ++) {
			
			for (int b = 0; b < adjacencyMatrix[a].length; b ++) {
				
				if (a != b) {
					
					if (adjacencyMatrixWeight[a][b] == 0) {
						
						adjacencyMatrixWeight[a][b] = Double.MAX_VALUE;
						
					}
					
				}
				
			}
			
		}
		
		return adjacencyMatrixWeight;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// IsDirected method
	
	/**
	 * Method to know if a Matrix is directed.
	 * @return Boolean data that represent true if was directed, and false if not
	 */

	@Override
	public boolean isDirected() {
		
		return isDirected;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetVertexSize method
	
	/**
	 * Method to have the size of the vertex.
	 * @return Integer data that represent the dimension of the system
	 */

	@Override
	public int getVertexSize() {
		
		return vertices.size();
		
	}
	
	// --------------------------------------------------------------------------------
	
	// IsWeighted method
	
	/**
	 * Method to know if a Matrix is weighted.
	 * @return Boolean data that represent true if was weighted, and false if not
	 */

	@Override
	public boolean isWeighted() {
		
		return false;
		
		// TODO Auto-generated method stub
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetIndex method
	
	/**
	 * Method to have the index of the vertex.
	 * @param object Generic node <T> with a original information
	 * @return Integer data that represent the index of the system
	 */

	public int getIndex(T object) {
		
		return verticesIndices.get(object);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Search method - 1
	
	/**
	 * Method one to know if a element exists in the matrix.
	 * @param object Generic node <T> with a original information
	 * @return Boolean data that represent true if exists, and false if not
	 */

	@Override
	public boolean search(T object) {
		
		return vertices.containsValue(object);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Search method - 2
	
	/**
	 * Method two to know if a element exists in the matrix.
	 * @param position Specify index of the element inside the system
	 * @return Generic node <T> with a original information
	 */

	@Override
	public T search(int position) {
		
		return vertices.get(position);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetEdges method
	
	/**
	 * Method to have all the edges of the system.
	 * @return Generic list with the edges that meet certain conditions
	 */

	@Override
	public List<Edge<T>> getEdges() {
		
		List<Edge<T>> e = new ArrayList<Edge<T>>();
		
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			
			for (int j = 0; j < adjacencyMatrix[i].length; j++) {
				
				if(adjacencyMatrix[i][j] == 1) {
					
					e.add(new Edge<T>(vertices.get(i), vertices.get(j), weightMatrix()[i][j]));
				
				}
				
			}
			
		}
		
		return e;	
		
	}
	
	// --------------------------------------------------------------------------------

}
