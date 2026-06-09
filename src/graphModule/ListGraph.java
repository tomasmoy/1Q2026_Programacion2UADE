package graphModule;

import dictionary.SimpleArrayDictionary;
import dictionary.SimpleDictionary;
import list.SimpleLinkedList;
import list.SimpleList;

public class ListGraph<T> implements Graph<T> {
	
	private SimpleDictionary<T, SimpleList<Edge<T>>> adjacencyList;
	
	public ListGraph() {
		adjacencyList = new SimpleArrayDictionary<T, SimpleList<Edge<T>>>();
	}

	@Override
	public T[] vertices() {
		return adjacencyList.keys();
	}

	@Override
	public boolean addVertex(T vertex) {
		if(containsVertex(vertex)) return false;
		else {
			adjacencyList.put(vertex, new SimpleLinkedList<Edge<T>>());
			return true;
		}
	}

	@Override
	public boolean removeVertex(T vertex) {
		if (!containsVertex(vertex)) return false;
		adjacencyList.remove(vertex);
		T[] vertices = vertices();
		for (int i = 0; i < vertices.length; i++) {
			removeEdge(vertices[i], vertex);
		}
		return true;
	}

	@Override
	public boolean addEdge(T from, T to, int weight) {
		addVertex(from);
		addVertex(to);
		
		Edge<T> edge = getEdge(from,to);
		if (edge == null) {
			adjacencyList.get(from).add(new Edge<T>(to,weight));
			return true;
		}
		if (edge.weight != weight) {
			edge.weight = weight;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean removeEdge(T from, T to) {
		Edge <T> edge = getEdge(from,to);
		if(edge != null) {
			adjacencyList.get(from).remove(edge);
		}
		return false;
	}

	@Override
	public boolean containsVertex(T vertex) {
		return adjacencyList.containsKey(vertex);
	}

	@Override
	public boolean containsEdge(T from, T to) {
		return getEdge(from,to) != null;
	}

	@Override
	public int getWeight(T from, T to) {
		Edge<T> target = getEdge(from,to);
		if (target != null) return target.weight;
		else return -1;
	}
	
	private Edge<T> getEdge(T from, T to){
		if (!containsVertex(from)) return null;
		SimpleList<Edge<T>> edges = adjacencyList.get(from);
		for (int i = 0; i<edges.size(); i++){
			if(edges.get(i).destination.equals(to)) return edges.get(i);
		}
		return null;
	}

}
