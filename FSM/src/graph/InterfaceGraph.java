package graph;

import java.util.List;

public interface InterfaceGraph<T> {

	public boolean addVertex(T node);
	
	public void addEdge(T A, T B);
	
	public void addEdge(T A, T B, double l);

	public boolean removeVertex(T node);

	public void removeEdge(T A, T B);
	
	public List<T> vertexAdjacent(T node);
	
	public boolean areConnected(T A, T B);

	public double[][] weightMatrix();
	
	public boolean isDirected();
	
	public int getVertexSize();
	
	public boolean isWeighted();

	public int getIndex(T vertex);
	
	public boolean search(T A);

	public T search(int index);
	
	public List<Edge<T>> getEdges();
	
}
