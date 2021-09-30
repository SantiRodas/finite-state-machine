/**
 * Please see the project at GitHub
 * @author Santiago Rodas Rodriguez
 * @author Julian Andres Rivera
 * @date 30/09/2021
 */

package graph;

public class Edge<T> implements Comparable<Edge<T>> {
	
	// --------------------------------------------------------------------------------
	
	// Edge attributes
	
	private double weight;
	
	private T source;
	
	private T destination;
	
	// --------------------------------------------------------------------------------
	
	// Constructor method
	
	/**
	 * Constructor method of the Edge class.
	 * @param source Generic element with a information of a object
	 * @param destination Generic element with the final object of the way
	 */
	
	public Edge(T source, T destination) {
		
		this(source, destination, 1D);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Constructor method
	
	/**
	 * Second constructor method of the Edge class.
	 * @param source Generic element with a information of a object
	 * @param destination Generic element with the final object of the way
	 * @param weight Double data with the weight of the Edge
	 */

	public Edge(T source, T destination, double weight) {
		
		this.source = source;
		
		this.destination = destination;
		
		this.weight = weight;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetWeight method
	
	/**
	 * Method to have the weight of the object Edge.
	 * @return Double data with the weight of the Edge
	 */
	
	public double getWeight() {
		
		return weight;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// SetWeight method
	
	/**
	 * Method to change the weight of the object Edge.
	 * @param Double data with the new weight of the Edge
	 */
	
	public void setWeight(double weight) {
		
		this.weight = weight;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetSource method
	
	/**
	 * Method to have the source of the object Edge.
	 * @return <T> Element with the information of that object
	 */

	public T getSource() {
		
		return source;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// GetDestination method
	
	/**
	 * Method to have the destination of the object Edge.
	 * @return <T> Element with the information of that object
	 */
	
	public T getDestination() {
		
		return destination;
		
	}
	
	// --------------------------------------------------------------------------------
	
	// CompareTo method
	
	/**
	 * Method to compare the information of the Edge object with another one.
	 * @return Number with the different between a object an another one
	 */
	
	@Override
	public int compareTo(Edge<T> o) {
		
		return Double.compare(weight, o.weight);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// ToString method
	
	/**
	 * Method to have only one String with all the information of one object.
	 *@return String with the information of the object Edge
	 */
	
	@Override
	public String toString() {
		
		return "" + source + " " + destination + " " + weight;
		
	}
	
	// --------------------------------------------------------------------------------

}
