package graph;

import java.util.Collection;

public class Stack<T> implements InterfaceCollection<T>{
	
	private java.util.Stack<T> myStack;

	public Stack() {
		myStack = new java.util.Stack<T>();
	}

	@Override
	public void add(T element) {
		myStack.push(element);
	}

	@Override
	public T poll() {
		return myStack.pop();
	}

	@Override
	public T peek() {
		return myStack.peek();
	}

	@Override
	public boolean isEmpty() {
		return myStack.empty();
	}

	@Override
	public void addAll(Collection<T> c) {
		myStack.addAll(c);
	}

}
