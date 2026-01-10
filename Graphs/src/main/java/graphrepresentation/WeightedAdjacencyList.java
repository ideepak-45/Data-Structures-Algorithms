package main.java.graphrepresentation;

import java.util.*;

public class WeightedAdjacencyList {
    private final Map<Integer, List<Edge>> adjacencyList;
    private final int numVertices;

    public static class Edge {
        private final int destination;
        private final int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public int getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }

    public WeightedAdjacencyList(int numVertices) {
        this.numVertices = numVertices;
        this.adjacencyList = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyList.get(source).add(new Edge(destination, weight));
    }

    public boolean hasEdge(int source, int destination) {
        for (Edge edge : adjacencyList.get(source)) {
            if (edge.getDestination() == destination) {
                return true;
            }
        }
        return false;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public Map<Integer, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

}
