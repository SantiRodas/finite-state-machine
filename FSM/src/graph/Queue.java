/**
 * Please see the project at GitHub
 * @author Santiago Rodas Rodriguez
 * @author Julian Andres Rivera
 * @date 29/09/2021
 */

package graph;

import java.util.Collection;
import java.util.LinkedList;

public class Queue<T> implements InterfaceCollection<T>{
	
	// --------------------------------------------------------------------------------
	
	// Queue attributes

	private LinkedList<T> rep;
	
	// --------------------------------------------------------------------------------
	
	// Constructor method
	
	/**
	 * Queue constructor method that allows to be called in other classes.
	 */
	
	public Queue() {
		
		rep = new LinkedList<>();
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Add method
	
	/**
	 * Method that allows adding an element to the queue.
	 * @param T Object of generic type with the information of the element
	 */

	@Override
	public void add(T element) {
		
		rep.addLast(element);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Poll method
	
	/**
	 * Method that allows to extract the node at the beginning of the queue.
	 * @return Object of generic type with the information of the element
	 */

	@Override
	public T poll() {
		
		return rep.removeFirst();
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Peek method
	
	/**
	 * Method to know the first element of the queue without eliminating it.
	 * @return Object of generic type with the information of the element
	 */

	@Override
	public T peek() {
		
		return rep.getFirst();
		
	}
	
	// --------------------------------------------------------------------------------
	
	// IsEmpty method
	
	/**
	 * Method that allows to know if the queue is empty or contains information in it.
	 * @return Boolean data where true represents empty, and false the opposite
	 */

	@Override
	public boolean isEmpty() {
		
		return rep.isEmpty();
		
	}
	
	// --------------------------------------------------------------------------------
	
	// AddAll method
	
	/**
	 * Method that allows to add more than one element at the same time in the queue.
	 * @param c Collection of elements of type generic
	 */

	@Override
	public void addAll(Collection<T> c) {
		
		rep.addAll(c);
		
	}
	
	// --------------------------------------------------------------------------------

}
