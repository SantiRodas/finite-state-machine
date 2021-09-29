/**
 * Please see the project at GitHub
 * @author Santiago Rodas Rodriguez
 * @author Julian Andres Rivera
 * @date 29/09/2021
 */

package graph;

import java.util.Collection;

public class Stack<T> implements InterfaceCollection<T>{
	
	// --------------------------------------------------------------------------------
	
	// Stack attributes
	
	private java.util.Stack<T> myStack;
	
	// --------------------------------------------------------------------------------
	
	// Constructor method
	
	/**
	 * Stack class constructor method that allows to be called in another one.
	 */

	public Stack() {
		
		myStack = new java.util.Stack<T>();
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Add method
	
	/**
	 * Method to add an element to the stack structure.
	 * @param T Element with all the information
	 */

	@Override
	public void add(T element) {
		
		myStack.push(element);
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Poll method
	
	/**
	 * Method that allows to extract the first element of the stack.
	 * @return T Item to be removed
	 */

	@Override
	public T poll() {
		
		return myStack.pop();
		
	}
	
	// --------------------------------------------------------------------------------
	
	// Peek method
	
	/**
	 * Method that allows to obtain the information of the last method of the stack.
	 * @return Information of the element
	 */

	@Override
	public T peek() {
		
		return myStack.peek();
		
	}
	
	// --------------------------------------------------------------------------------
	
	// IsEmpty method
	
	/**
	 * Method that allows to know if the stack is empty or contains information in it.
	 * @return Boolean data where true represents empty, and false the opposite
	 */

	@Override
	public boolean isEmpty() {
		
		return myStack.empty();
		
	}
	
	// --------------------------------------------------------------------------------
	
	// AddAll method
	
	/**
	 * Method that allows to add more than one element at the same time in the stack.
	 * @param c Collection of elements of type generic
	 */

	@Override
	public void addAll(Collection<T> c) {
		
		myStack.addAll(c);
		
	}
	
	// --------------------------------------------------------------------------------

}
