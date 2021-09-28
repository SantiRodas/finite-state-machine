package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphAlgorithms<T> {
	
	private static double[] cost;
	private static boolean[] F;
	private static int[] path;
	public static List<Integer> choice;
	
	public static <T> List<T> bfs(InterfaceGraph<T> g, T node){
		return traversal(g, node, new graph.Queue<T>());
	}
	
	public static <T> List<T> dfs (InterfaceGraph<T> g, T node){
		return traversal(g, node, new graph.Stack<T>());
	}

	private static <T> List<T> traversal(InterfaceGraph<T> g, T node, InterfaceCollection<T> ds){
		List<T> trav = new ArrayList<>();
		T vertex = node;
		ds.add(vertex);
		boolean[] visited = new boolean[g.getVertexSize()];
		while(!ds.isEmpty()) {
			vertex = ds.poll();
			int indexV = g.getIndex(vertex);
			if(!visited[indexV]) {
				trav.add(vertex);
				visited[indexV] = true;
				List<T> adjacents = g.vertexAdjacent(vertex);
				ds.addAll(adjacents);
			}
		}
		return trav;
	}
	
	public static<T> void dijkstra(T origin, InterfaceGraph<T> g, int c) {
		double[][] weights = g.weightMatrix(); 
		int index = g.getIndex(origin); 
		int n = g.getVertexSize();
		cost = new double[n];
		F = new boolean[n];
		path = new int[n];
		choice = new ArrayList<Integer>();
		choice.add(g.getIndex(origin));
		for (int i = 0; i < n; i++) {
			F[i] = false;
			cost[i] = weights[index][i];
			path[i] = index;
		}
		F[index] = true;
		cost[index] = 0;
		for (int k = 0; k < n; k++) {
			int v = minimum(n);
			F[v] = true;
			for (int i = 0; i < n; i++) {
				if(!F[i]) {
					if (cost[v] + weights[v][i] < cost[i]) {
						cost[i] = (cost[v] + weights[v][i]);
						path[i] = v;
						if (i == c) {
							choice.add(v);
						}
					}
				}
			}
		}
	}
	
	private static int minimum(int n) {
		double max = Integer.MAX_VALUE;
		int v = 1;
		for (int j = 0; j < n; j++) {
			if (!F[j] && (cost[j] <= max)) {
				max = cost[j];
				v = j;
			}
		}
		return v;
	}
	
	public static <T> double[][] floydWarshall(InterfaceGraph<T> g) {
		double[][] weightsMatrix = g.weightMatrix();
		for (int i = 0; i < weightsMatrix.length; i++) {
			for (int j = 0; j < weightsMatrix.length; j++) {
				for (int k = 0; k < weightsMatrix.length; k++) {
					if (weightsMatrix[j][i] + weightsMatrix[i][k] < weightsMatrix[j][k]) {
						weightsMatrix[j][k] = weightsMatrix[j][i] + weightsMatrix[i][k];
					}
				}
			}
		}
		return weightsMatrix;
	}
	
	public static <T> int prim(T node, InterfaceGraph<T> graph){
		double[][] weights = graph.weightMatrix();
		int n = graph.getVertexSize();
		int index = graph.getIndex(node);
		int minLength = 0;
		int z;
		double min;
		double[] cost = new double[n];
		int[] closer = new int[n];
		boolean[] W = new boolean[n];
		for (int i = 0; i < n; i++) {
			W[i] = false;
		}
		W[index] = true;
		for (int i = 0; i < n; i++) {
			if (i != index) {
				cost[i] = weights[index][i];
				closer[i] = 0;
			}
		}
		for (int i = 0; i < n; i++) {
			if (i != index) {
				min = cost[1];
				z = 1;
				for (int j = 0; j < n; j++) {
					if (j != index) {
						if (cost[j] < min) {
							min = cost[j];
							z = j;
						}
					}
				}
				minLength += min;
				W[z] = true;
				cost[z] = Integer.MAX_VALUE;
				for (int j = 0; j < n; j++) {
					if (j != index) {
						if (weights[z][j] < cost[j] && !W[j]) {
							cost[j] = weights[z][j];
							closer[j] = z;
						}
					}
				}
			}
		}
		return minLength;
	}
	
	public static <T> ArrayList<Edge<T>> kruskal(InterfaceGraph<T> g){
		List<Edge<T>> result = new ArrayList<Edge<T>>();
		int e = 0; 
		int i = 0; 

		List<Edge<T>> edges = (ArrayList<Edge<T>>) g.getEdges();
		Collections.sort(edges);
		UnionFind uf = new UnionFind(g.getVertexSize());
		i = 0; 
		while (e < g.getVertexSize() - 1 && i < edges.size()) {
			Edge<T> edge = edges.get(i);
			i++;

			int x = uf.find(g.getIndex(edge.getSource()));
			int y = uf.find(g.getIndex(edge.getDestination()));

			if (x != y) {
				result.add(edge);
				e++;
				uf.union(x, y);
			}
		}
		return (ArrayList<Edge<T>>) result;
	}

	public static double[] getCost() {
		return cost;
	}
	
	public static int[] getPath() {
		return path;
	}

	public static List<Integer> getChoice() {
		return choice;
	}
	
}
