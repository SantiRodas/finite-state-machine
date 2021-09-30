/**
 * Please see the project at GitHub
 * @author Santiago Rodas Rodriguez
 * @author Julian Andres Rivera
 * @date 29/09/2021
 */

package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphAlgorithms<T> {
	
	// --------------------------------------------------------------------------------
	
	// GraphAlgorithms attributes
	
	private static double[] cost;
	
	private static boolean[] validation;
	
	private static int[] path;
	
	public static List<Integer> choice;
	
	// --------------------------------------------------------------------------------
	
	// B-F-S method
	
	/**
	 * Width search method that validates all nodes starting from the root.
	 * @param ig Information of the InterfaceGraph
	 * @param node Generic node (<T>)
	 * @return Generic list with all the nodes that meet the validation
	 */
	
	public static <T> List<T> bfs(InterfaceGraph<T> ig, T node){
		
		return traversal(ig, node, new graph.Queue<T>());
		
	}
	
	// --------------------------------------------------------------------------------
	
	// D-F-S method
	
	/**
	 * Depth search method that validates all nodes starting from the root.
	 * @param ig Information of the InterfaceGraph
	 * @param node Generic node (<T>)
	 * @return Generic list with all the nodes that meet the validation
	 */
	
	public static <T> List<T> dfs (InterfaceGraph<T> ig, T node){
		
		return traversal(ig, node, new graph.Stack<T>());
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Traversal method
	
	/**
	 * Method that allows to obtain the list of nodes transverse to each other.
	 * @param ig Information of the InterfaceGraph
	 * @param node Generic node (<T>)
	 * @param ic Information of the InterfaceCollection
	 * <pre> ic Must be empty.
	 * @return Generic list with all nodes that are transverse to each other
	 */

	private static <T> List<T> traversal(InterfaceGraph<T> ig, T node, InterfaceCollection<T> ic){
		
		List<T> trav = new ArrayList<>();
		
		T vertex = node;
		
		ic.add(vertex);
		
		boolean[] visited = new boolean[ig.getVertexSize()];
		
		while(!ic.isEmpty()) {
		
			vertex = ic.poll();
			
			int indexV = ig.getIndex(vertex);
			
			if(!visited[indexV]) {
			
				trav.add(vertex);
				
				visited[indexV] = true;
				
				List<T> adjacents = ig.vertexAdjacent(vertex);
				
				ic.addAll(adjacents);
			
			}
		
		}
		
		return trav;
	
	}
	
	// --------------------------------------------------------------------------------
	
	// Dijkstra method
	
	/**
	 * Method that determines the shortest path within the network.
	 * @param origin Generic node (<T>) that represent the origin of the way
	 * @param ig Information of the InterfaceGraph
	 * @param count Number with the information of the final position
	 */
	
	public static<T> void dijkstra(T origin, InterfaceGraph<T> ig, int count) {
		
		double[][] weights = ig.weightMatrix(); 
		
		int index = ig.getIndex(origin); 
		
		int number = ig.getVertexSize();
		
		cost = new double[number];
		
		validation = new boolean[number];
		
		path = new int[number];
		
		choice = new ArrayList<Integer>();
		
		choice.add(ig.getIndex(origin));
		
		for (int i = 0; i < number; i++) {
			
			validation[i] = false;
			
			cost[i] = weights[index][i];
			
			path[i] = index;
		
		}
		
		validation[index] = true;
		
		cost[index] = 0;
		
		for (int k = 0; k < number; k++) {
			
			int v = minimum(number);
			
			validation[v] = true;
			
			for (int i = 0; i < number; i++) {
			
				if(!validation[i]) {
				
					if (cost[v] + weights[v][i] < cost[i]) {
					
						cost[i] = (cost[v] + weights[v][i]);
						
						path[i] = v;
						
						if (i == count) {
						
							choice.add(v);
						
						}
					
					}
				
				}
			
			}
		
		}
	
	}
	
	// --------------------------------------------------------------------------------
	
	// Minimum method
	
	/**
	 * Method to determine the minimum value of a path.
	 * @param limit Number with the maximum limit of the graph
	 * @return Number with the minimum value of the path
	 */
	
	private static int minimum(int limit) {
		
		double max = Integer.MAX_VALUE;
		
		int vertex = 1;
		
		for (int j = 0; j < limit; j++) {
			
			if (!validation[j] && (cost[j] <= max)) {
				
				max = cost[j];
				
				vertex = j;
				
			}
			
		}
		
		return vertex;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// FloydWarshall method
	
	/**
	 * Method that finds the minimum path in the directed graph.
	 * @param ig Information of the InterfaceGraph
	 * @return Array (double) with the minimum path from one node to another
	 */
	
	public static <T> double[][] floydWarshall(InterfaceGraph<T> ig) {
		
		double[][] weightsMatrix = ig.weightMatrix();
		
		for (int i = 0; i < weightsMatrix.length; i++) {
		
			for (int j = 0; j < weightsMatrix.length; j++) {
			
				for (int k = 0; k < weightsMatrix.length; k++) {
				
					if (weightsMatrix[j][i] + weightsMatrix[i][k] < weightsMatrix[j][k]) {
					
						weightsMatrix[j][k] = weightsMatrix[j][i] + weightsMatrix[i][k];
					}
					
				
				}
			
			}
		
		}
		
		return weightsMatrix;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Prim method
	
	/**
	 * Method that determines the minimum overlapping tree in the connected network.
	 * @param node Generic node (<T>)
	 * @param ig Information of the InterfaceGraph
	 * @return Generic node (<T>) with all the information of this object
	 */
	
	public static <T> int prim(T node, InterfaceGraph<T> ig){
		
		double[][] weights = ig.weightMatrix();
		
		int size = ig.getVertexSize();
		
		int index = ig.getIndex(node);
		
		int minLength = 0;
		
		int number;
		
		double min;
		
		double[] cost = new double[size];
		
		int[] closer = new int[size];
		
		boolean[] W = new boolean[size];
		
		for (int i = 0; i < size; i++) {
		
			W[i] = false;
		
		}
		
		W[index] = true;
		
		for (int i = 0; i < size; i++) {
		
			if (i != index) {
			
				cost[i] = weights[index][i];
				
				closer[i] = 0;
			
			}
			
		}
		
		for (int i = 0; i < size; i++) {
			
			if (i != index) {
			
				min = cost[1];
				
				number = 1;
				
				for (int j = 0; j < size; j++) {
				
					if (j != index) {
					
						if (cost[j] < min) {
						
							min = cost[j];
							
							number = j;
						
						}
					
					}
				
				}
				
				minLength += min;
				
				W[number] = true;
				
				cost[number] = Integer.MAX_VALUE;
				
				for (int j = 0; j < size; j++) {
				
					if (j != index) {
					
						if (weights[number][j] < cost[j] && !W[j]) {
						
							cost[j] = weights[number][j];
							
							closer[j] = number;
						
						}
						
					}
				
				}
			
			}
		
		}
		
		return minLength;
	
	}
	
	// --------------------------------------------------------------------------------
	
	// Kruskal method
	
	/**
	 * Method that determines the minimum overlapping tree in the weighted network.
	 * @param ig Information of the InterfaceGraph
	 * @return ArrayList with all the nodes of the minimum overlapping
	 */
	
	public static <T> ArrayList<Edge<T>> kruskal(InterfaceGraph<T> ig){
		
		List<Edge<T>> result = new ArrayList<Edge<T>>();
		
		int limitMin = 0; 
		
		int limitMax = 0; 

		List<Edge<T>> edges = (ArrayList<Edge<T>>) ig.getEdges();
		
		Collections.sort(edges);
		
		UnionFind uf = new UnionFind(ig.getVertexSize());
		
		limitMax = 0; 
		
		while (limitMin < ig.getVertexSize() - 1 && limitMax < edges.size()) {
			
			Edge<T> edge = edges.get(limitMax);
			
			limitMax ++;

			int x = uf.find(ig.getIndex(edge.getSource()));
			
			int y = uf.find(ig.getIndex(edge.getDestination()));

			if (x != y) {
				
				result.add(edge);
				
				limitMin ++;
				
				uf.union(x, y);
			
			}
			
		}
		
		return (ArrayList<Edge<T>>) result;
	
	}
	
	// --------------------------------------------------------------------------------
	
	// GetCost
	
	/**
	 * Method that returns the cost of the operation in the network).
	 * @return Array (double) with all the information that represent the cost of the operation
	 */

	public static double[] getCost() {
		
		return cost;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetPath method
	
	/**
	 * Method that returns the path of the operation in the network).
	 * @return Array (Integer) with all the information that represent the path of the operation
	 */
	
	public static int[] getPath() {
		
		return path;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetChoise method
	
	/**
	 * Method that returns the choice of the operation in the network).
	 * @return List (Integer) with all the information that represent the choice of the operation
	 */

	public static List<Integer> getChoice() {
		
		return choice;
		
	}
	
	// --------------------------------------------------------------------------------
	
}
