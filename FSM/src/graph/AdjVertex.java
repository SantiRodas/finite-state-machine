package graph;

import java.util.LinkedList;
import java.util.List;

public class AdjVertex<T> {
	private List<Edge<T>> adjList;

	public AdjVertex(T value) {
		adjList = new LinkedList<Edge<T>>();
	}

	public List<Edge<T>> getAdjList() {
		return adjList;
	}

	public boolean isAdjacent(AdjVertex<T> vertex) {
		for (int i = 0; i < adjList.size(); i++) {
			if (adjList.get(i).getDestination() == vertex)
				return true;
		}
		return false;
	}
}
