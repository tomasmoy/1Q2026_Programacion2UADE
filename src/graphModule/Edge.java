package graphModule;

public class Edge<T> {
	public T destination;
	public int weight;
	
	public Edge(T destination, int weight) {
		this.destination = destination;
		this.weight = weight;
	};

	public boolean equals(T other) {
		if (other.getClass() != getClass()) return false;
		
	}
}
