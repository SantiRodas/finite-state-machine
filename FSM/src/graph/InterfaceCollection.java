package graph;

import java.util.Collection;

public interface InterfaceCollection<T> {

	public void add(T element);
	
	public T poll();
	
	public T peek();
	
	public boolean isEmpty();
	
	public void addAll(Collection<T> c);
	
}