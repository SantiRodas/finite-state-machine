package graph;

public class Edge<T> implements Comparable<Edge<T>> {
	
	private double weight;
	private T source;
	private T destination;

	public Edge(T source, T destination) {
		this(source, destination, 1D);
	}

	public Edge(T source, T destination, double weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public T getSource() {
		return source;
	}

	public T getDestination() {
		return destination;
	}

	@Override
	public int compareTo(Edge<T> o) {
		return Double.compare(weight, o.weight);
	}
	
	@Override
	public String toString() {
		return ""+source+" "+destination+" "+weight;
	}

}
