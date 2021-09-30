/**
 * Please see the project at GitHub
 * @author Santiago Rodas Rodriguez
 * @author Julian Andres Rivera
 * @date 30/09/2021
 */

package graph;

public class DisjointSets {
	
	// --------------------------------------------------------------------------------
	
	// DjisjointSets attributes
	
	private int[] information;
	
	// --------------------------------------------------------------------------------
	
	// Constructor method 
	
	/**
	 * Constructor method to create this object in a another class.
	 * @param Size Dimension of the array with all the information
	 */
	
	public DisjointSets(int size) {
		
		information = new int[size];
		
		for (int i = 0; i < information.length; i++) {
			
			information[i] = i;
			
		}
		
	}
	
	// --------------------------------------------------------------------------------
	
	// FindSet method
	
	/**
	 * Method to find where have to be a change inside the system of the DisjointSet.
	 * @param Specify point in the system with the information we need
	 * @return Number with the another position where you can do the change
	 */
	
	public int findSet(int position) {
		
		if(information[position] == position) {
			
			return position;
			
		}else {
			
			information[position] = findSet(information[position]);
			
			return information[position];
			
		}
		
	}
	
	// --------------------------------------------------------------------------------
	
	// IsSame method
	
	/**
	 * Method to know if a position is equals to another one.
	 * @param i Position one of the system
	 * @param j Position two of the system
	 * @return Boolean data where true represent equals, and false not equals
	 */
	
	public boolean isSame(int i, int j) {
		
		return findSet(i) == findSet(j);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Join method
	
	/**
	 * Method to connect one position with another one.
	 * @param i Position one of the system
	 * @param j Position two of the system
	 */
	
	public void join(int i, int j) {
		
		if(!isSame(i, j)) {
			
			int x = findSet(i);
			
			int y = findSet(j);
			
			
			information[x] = y;
			
		}
		
	}
	
	// --------------------------------------------------------------------------------

}