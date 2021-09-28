package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyList<T> implements InterfaceGraph<T>{
	
   private static final int DEFAULT_CAPACITY = 21;
	
	private Map<T, Integer> vertices;	

    private Map<Integer, T> verticesIndices;

	private List<List<T>> adjacencyLists;

	private boolean isDirected;
	
    private double[][] adjacencyMatrixWeight;
	
	public AdjacencyList() {
		initialize(DEFAULT_CAPACITY);
	}

	public AdjacencyList(boolean id) {
		initialize(DEFAULT_CAPACITY);
		isDirected = id;
	}
	
	private final void initialize(int capacity) {
		isDirected = false;
		adjacencyLists = new ArrayList<List<T>>();
		vertices = new HashMap<T, Integer>();
		verticesIndices = new HashMap<>();
		adjacencyMatrixWeight = new double[capacity][capacity];
	}

	@Override
	public boolean addVertex(T node) {
		boolean added = false;
		if(!search(node)) {
			@SuppressWarnings("unchecked")
			List<T> vList = (List<T>) new ArrayList<Object>();
			int index = adjacencyLists.size();
			vertices.put(node, index);
			verticesIndices.put(index, node);
			adjacencyLists.add(vList);
			added = true;
		}
		return added;
	}

	@Override
	public void addEdge(T A, T B) {
		int ValueA = vertices.get(A);
		int ValueB = vertices.get(B);
		if(!isDirected) {
			adjacencyLists.get(ValueA).add(B);
			adjacencyLists.get(ValueB).add(A);
		}else {
			adjacencyLists.get(ValueA).add(B);
		}
	}

	@Override
	public void addEdge(T A, T B, double l) {
		int x = vertices.get(A);//TODO: check pre-conditions
        int y = vertices.get(B);
		Edge<T> edge = new Edge<T>(A, B, l);
		AdjVertex<T> from = new AdjVertex<T>(A);
		from.getAdjList().add(edge);
		if (!isDirected()) {
			edge = new Edge<T>(A, B, l);
			AdjVertex<T> to = new AdjVertex<T>(B);
			to.getAdjList().add(edge);
			adjacencyLists.get(x).add(B);
			adjacencyLists.get(y).add(A);
            adjacencyMatrixWeight[x][y] = l;
            adjacencyMatrixWeight[y][x] = l;
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean removeVertex(T node) {
		if (vertices.containsKey(node)) {
			adjacencyLists.remove(vertices.get(node));
			for (int i = 0; i < adjacencyLists.size(); i++) {
				if (adjacencyLists.get(i).contains(node))
					adjacencyLists.get(i).remove(i);
			}
			vertices.remove(node);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void removeEdge(T A, T B) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<T> vertexAdjacent(T node) {
		List<T> neigh = new ArrayList<>();
		AdjVertex<T> aV = new AdjVertex<T>(node);
		List<Edge<T>> adj = aV.getAdjList();
		for (int i = 0; i < adj.size(); i++) {
			neigh.add(adj.get(i).getDestination());
		}
		return neigh;
	}

	@Override
	public boolean areConnected(T A, T B) {
		int aValue = vertices.get(A);
		int bValue = vertices.get(B);
		
		if(isDirected) {
			return adjacencyLists.get(aValue).contains(B);
		}else {
			return adjacencyLists.get(aValue).contains(B) && adjacencyLists.get(bValue).contains(A);
		}
	}

	@Override
	public double[][] weightMatrix() {
		for (int i = 0; i < adjacencyLists.size(); i++) {
			for (int j = 0; j < adjacencyLists.size(); j++) {
				if (adjacencyMatrixWeight[i][j] == 0 ) {
					if (i != j) {
						adjacencyMatrixWeight[i][j] = Double.MAX_VALUE;
					}
				}
			}
		}
		return adjacencyMatrixWeight;
	}

	@Override
	public boolean isDirected() {
		return isDirected;
	}

	@Override
	public int getVertexSize() {
		return vertices.size();
	}

	@Override
	public boolean isWeighted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getIndex(T vertex) {
		return vertices.get(vertex);
	}

	@Override
	public boolean search(T A) {
		return verticesIndices.containsValue(A);
	}

	public Map<T, Integer> getVertices() {
		return vertices;
	}

	public List<List<T>> getAdjacencyLists() {
		return adjacencyLists;
	}

	@Override
	public T search(int index) {
		return verticesIndices.get(index);
	}

	@Override
	public List<Edge<T>> getEdges() {
		ArrayList<Edge<T>> edges = new ArrayList<>();
		for (int i = 0; i < vertices.size(); i++) {
			AdjVertex<T> v = new AdjVertex<T>(verticesIndices.get(i));
			for (int j = 0; j < v.getAdjList().size(); j++) {
				edges.add(v.getAdjList().get(j));
			}
		}
		return edges;
	}

}
