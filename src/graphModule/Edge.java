package graphModule;

public class Edge<T> {
	public T destination;
	public int weight;
	
	public Edge(T destination, int weight) {
		this.destination = destination;
		this.weight = weight;
	};
	
	@Override
	public boolean equals(Object other) {
		if (other.getClass() != getClass()) return false;
		@SuppressWarnings("unchecked")
		Edge<T> edge = (Edge<T>) other;
        return destination == edge.destination && weight == edge.weight;
    }
}
