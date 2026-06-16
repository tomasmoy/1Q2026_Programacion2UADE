package graphModule;

import dictionary.SimpleArrayDictionary;
import dictionary.SimpleDictionary;
import dictionary.SimpleLinkedDictionary;
import list.SimpleList;
import list.SimpleArrayList;
import priorityqueue.SimpleLinkedPriorityQueue;
import priorityqueue.SimplePriorityQueue;
import set.SimpleArraySet;
import set.SimpleSet;

public class DijkstraSolver {
    public static <E> SimpleDictionary<E, Edge<E>> dijkstraAllNodes(Graph<E> graph, E origin){
        SimpleDictionary<E, Edge<E>> result = new SimpleArrayDictionary<>();
        SimpleList<E> vertices = graph.vertices();

        int size = vertices.size();
        for(int i = 0; i < size; i++)
            result.put(vertices.get(i), new Edge<>(null, Integer.MAX_VALUE));

        result.get(origin).weight = 0;
        SimplePriorityQueue<E> unvisited = new SimpleLinkedPriorityQueue<>();
        SimpleSet<E> visited = new SimpleArraySet<>();

        unvisited.enqueue(origin, 0);
        while(!unvisited.isEmpty()){
            E current = unvisited.dequeue();
            if (visited.contains(current)) continue;

            int costToCurrent = result.get(current).weight;
            SimpleList<Edge<E>> neighbors = graph.getNeighbors(current);
            int neighborsCount = neighbors.size();

            for (int i = 0; i < neighborsCount; i++) {
                E neighbor = neighbors.get(i).destination;
                if (visited.contains(neighbor)) continue;

                int totalCost = costToCurrent + neighbors.get(i).weight;

                if (totalCost < result.get(neighbor).weight) {
                    result.get(neighbor).weight = totalCost;
                    result.get(neighbor).destination = current;
                    unvisited.enqueue(neighbor, totalCost);
                }
            }

            visited.add(current);
        }

        return result;
    }

    public static <E> SimpleList<E> findShortestPath(SimpleDictionary<E, Edge<E>> dijkstraResult, E origin, E destination) {
        SimpleList<E> invertedPath = new SimpleArrayList<>();

        E current = destination;

        if (dijkstraResult.get(current) == null || dijkstraResult.get(current).weight == Integer.MAX_VALUE) {
            return new SimpleArrayList<>();
        }

        // recorremos a la inversa
        while (current != null) {
            invertedPath.add(current);
            if (current.equals(origin))
                break;
            current = dijkstraResult.get(current).destination;
        }

        // invierto el camino para poder imprimirlo en orden
        SimpleList<E> actualPath = new SimpleArrayList<>();
        for (int i = invertedPath.size() - 1; i >= 0; i--) {
            actualPath.add(invertedPath.get(i));
        }

        return actualPath;
    }
}
