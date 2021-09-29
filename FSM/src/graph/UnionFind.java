/**
 * Please see the project at GitHub
 * @author Santiago Rodas Rodriguez
 * @author Julian Andres Rivera
 * @date 29/09/2021
 */

package graph;

import java.util.Arrays;

public class UnionFind {
	
	// --------------------------------------------------------------------------------
	
	// UnionFind class attributes
	
	private int[] parent;
	
	private int[] rank;
	
	// --------------------------------------------------------------------------------
	
	// Find method
	
	/**
	 * Method that returns the numeric value of the union when it is found
	 * @param A number with the exact position of the union
	 * @return A number with the union between that node and its own parent
	 */

	public int find(int i) {
		
		int p = parent[i];
		
		if (i == p) {
			
			return i;
			
		}
		
		return parent[i] = find(p);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Union method
	
	/**
	 * Method that performs the union between one node and another.
	 * @param Position of node 1
	 * @param Position of node 2
	 */

	public void union(int i, int j) {
		
		int root1 = find(i);
		
		int root2 = find(j);
		
		if (root2 == root1)
			return;
		
		if (rank[root1] > rank[root2]) {
			
			parent[root2] = root1;
			
		} else if (rank[root2] > rank[root1]) {
			
			parent[root1] = root2;
			
		} else {
			
			parent[root2] = root1;
			
			rank[root1]++;
			
		}
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Constructor method
	
	/**
	 * Constructor method of the UnionFind class that can be called in other classes.
	 * @param An integer with the maximum value it can take
	 */

	public UnionFind(int max) {
		
		parent = new int[max];
		
		rank = new int[max];
		
		for (int i = 0; i < max; i++) {
			
			parent[i] = i;
			
		}
		
	}
	
	// --------------------------------------------------------------------------------
	
	// toString method
	
	/**
	 * Method that allows to join some data so that it can be printed or used in a successful way
	 * @return String with data concatenation
	 */

	public String toString() {
		
		return "<UnionFind\np " + Arrays.toString(parent) + "\nr " + Arrays.toString(rank) + "\n>";
	
	}
	
	// --------------------------------------------------------------------------------
	
}