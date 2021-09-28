package graph;

import java.util.Collection;
import java.util.LinkedList;

public class Queue<T> implements InterfaceCollection<T>{

	private LinkedList<T> rep;
	
	public Queue() {
		rep = new LinkedList<>();
	}

	@Override
	public void add(T element) {
		rep.addLast(element);
	}

	@Override
	public T poll() {
		return rep.removeFirst();
	}

	@Override
	public T peek() {
		return rep.getFirst();
	}

	@Override
	public boolean isEmpty() {
		return rep.isEmpty();
	}

	@Override
	public void addAll(Collection<T> c) {
		rep.addAll(c);
	}

}
