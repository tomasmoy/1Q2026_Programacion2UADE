package graphModule;

public class DijkstraNode<T> {
	int minWeight;
	T prevNode;
	
	public DijkstraNode(T prevNode,int minWeight) {
		this.minWeight = minWeight;
		this.prevNode = prevNode;
	}
}
