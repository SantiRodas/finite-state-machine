/**
 * Please see the project at GitHub
 * @author Santiago Rodas Rodriguez
 * @author Julian Andres Rivera
 * @date 29/09/2021
 */

package graph;

import java.util.Collection;

public interface InterfaceCollection<T> {
	
	// --------------------------------------------------------------------------------
	
	// Add method
	
	/**
	 * @see Queue and Stack class where the related informacion
	 * with the add method is specified in a more specific way
	 */

	public void add(T element);
	
	// --------------------------------------------------------------------------------
	
	// Poll method
	
	/**
	 * @see Queue and Stack class where the related informacion
	 * with the poll method is specified in a more specific way
	 */
	
	public T poll();
	
	// --------------------------------------------------------------------------------
	
	// Peek method
	
	/**
	 * @see Queue and Stack class where the related informacion
	 * with the peek method is specified in a more specific way
	 */
	
	public T peek();
	
	// --------------------------------------------------------------------------------
	
	// IsEmpty method
	
	/**
	 * @see Queue and Stack class where the related informacion
	 * with the isEmpty method is specified in a more specific way
	 */
	
	public boolean isEmpty();
	
	// --------------------------------------------------------------------------------
	
	// AddAll method
	
	/**
	 * @see Queue and Stack class where the related informacion
	 * with the addAll method is specified in a more specific way
	 */
	
	public void addAll(Collection<T> c);
	
	// --------------------------------------------------------------------------------
	
}