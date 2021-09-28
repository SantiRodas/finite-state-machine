package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class AdjacencyMatrix<T> implements InterfaceGraph<T>{
	
    private static final int DEFAULT_CAPACITY = 21;

    private static final double GROWTH_FACTOR = 1.5;

    private int size;

    private boolean isDirected;

    private double[][] adjacencyMatrix;
    
    private double[][] adjacencyMatrixWeight;

    private Map<Integer, T> vertices;

    private Map<T, Integer> verticesIndices;

    private NavigableSet<Integer> emptySlots = new TreeSet<>();

    public AdjacencyMatrix() {
        initialize(DEFAULT_CAPACITY);
    }

    public AdjacencyMatrix(boolean id) {
        initialize(DEFAULT_CAPACITY);
        isDirected = id;
    }

    public AdjacencyMatrix(int capacity) {
        initialize(capacity);
    }

    public AdjacencyMatrix(boolean id, int capacity) {
        initialize(capacity);
        isDirected = id;
    }

    private void initialize(int capacity) {
        isDirected = false;
        size = 0;
        adjacencyMatrix = new double[capacity][capacity];
        vertices = new HashMap<>();
        verticesIndices = new HashMap<>();
        adjacencyMatrixWeight = new double[capacity][capacity];
    }

	@Override
	public boolean addVertex(T node) {
		boolean added = false;
        Integer index;
        if (verticesIndices.get(node) == null) {
            if (emptySlots.isEmpty()) {
                if (size == adjacencyMatrix.length) {
                    double[][] placeHolder = adjacencyMatrix;
                    int newLength = (int) (adjacencyMatrix.length * GROWTH_FACTOR);
                    adjacencyMatrix = new double[newLength][newLength];
                    for (int i = 0; i < placeHolder.length; i++) {
                        System.arraycopy(placeHolder[i], 0, adjacencyMatrix[i], 0, placeHolder.length);
                    }
                }
                size++;
                index = size - 1;
            } else {
                index = emptySlots.pollFirst();//TODO: May assign null?
            }
            vertices.put(index, node);
            verticesIndices.put(node, index);
            added = true;
        }
        return added;
	}

	@Override
	public void addEdge(T A, T B) {
		 Integer x = verticesIndices.get(A);
	        Integer y = verticesIndices.get(B);
	        if (x != null && y != null) {
	            if (!isDirected) {
	                adjacencyMatrix[x][y] = 1;
	                adjacencyMatrix[y][x] = 1;
	            } else {
	                adjacencyMatrix[x][y] = 1;
	            }
	        }else{}//TODO: May need to change return type to boolean for when the edge couldn't be added
	}

	@Override
	public void addEdge(T A, T B, double l) {
		int x = verticesIndices.get(A);//TODO: check pre-conditions
        int y = verticesIndices.get(B);
        if (!isDirected) {
            adjacencyMatrix[x][y] = 1;
            adjacencyMatrix[y][x] = 1;
            adjacencyMatrixWeight[x][y] = l;
            adjacencyMatrixWeight[y][x] = l;
        } else {
            adjacencyMatrix[x][y] = 1;
            adjacencyMatrixWeight[x][y] = l;
        }
	}

	@Override
	public boolean removeVertex(T node) {
		boolean removed = false;
		Integer position = verticesIndices.get(node);
		if (position != null) {
			vertices.remove(position);
			verticesIndices.remove(node);
			emptySlots.add(position);
			for (int i = 0; i < size; i++) {
				adjacencyMatrix[position][i] = 0;
			}
			for (int i = 0; i < size; i++) {
				adjacencyMatrix[i][position] = 0;
			}
			removed = true;
		}
		return removed;
	}

	@Override
	public void removeEdge(T A, T B) {
		 if (!isDirected) {
	            adjacencyMatrix[(int) A][(int) B] = 0;//TODO: check pre-conditions
	            adjacencyMatrix[(int) B][(int) A] = 0;
	        } else {
	            adjacencyMatrix[(int) A][(int) B] = 0;
	        }
	}

	@Override
	public List<T> vertexAdjacent(T node) {
		Integer position = verticesIndices.get(node);
        List<T> adjacentVertices = null;
        if (position != null) {
            Set<Integer> adjacentVerticesPositions = new HashSet<>();
            for (int i = 0; i < size; i++) {
                if (adjacencyMatrix[position][i] != 0) {
                    adjacentVerticesPositions.add(i);
                }
            }
            if (isDirected) {
                for (int i = 0; i < size; i++) {
                    if (adjacencyMatrix[i][position] != 0) {
                        adjacentVerticesPositions.add(i);
                    }
                }
            }
            adjacentVertices = new ArrayList<>();
            for (Integer key : adjacentVerticesPositions
            ) {
                adjacentVertices.add(vertices.get(key));
            }
        }
        return adjacentVertices;
	}

	@Override
	public boolean areConnected(T A, T B) {
		 int uValor = verticesIndices.get(A);//TODO: check if 'u' and 'v' exist in the graph
	        int vValor = verticesIndices.get(B);
	        if (isDirected) {
	            return adjacencyMatrix[uValor][vValor] == 1;
	        } else {
	            return adjacencyMatrix[uValor][vValor] == 1 && adjacencyMatrix[vValor][uValor] == 1;
	        }
	}

	@Override
	public double[][] weightMatrix() {
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix[i].length; j++) {
				if (i != j) {
					if (adjacencyMatrixWeight[i][j] == 0) {
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

	public int getIndex(T vertex) {
		return verticesIndices.get(vertex);
	}

	@Override
	public boolean search(T A) {
		return vertices.containsValue(A);
	}

	@Override
	public T search(int index) {
		return vertices.get(index);
	}

	@Override
	public List<Edge<T>> getEdges() {
		List<Edge<T>> edges = new ArrayList<Edge<T>>();
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix[i].length; j++) {
				if(adjacencyMatrix[i][j] == 1) {
					edges.add(new Edge<T>(vertices.get(i), vertices.get(j), weightMatrix()[i][j]));
				}
			}
		}
		return edges;	
	}

}
