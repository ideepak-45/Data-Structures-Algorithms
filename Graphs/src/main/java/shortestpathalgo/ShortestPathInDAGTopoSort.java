package main.java.shortestpathalgo;

import main.java.graphrepresentation.WeightedAdjacencyList;
import java.util.*;

public class ShortestPathInDAGTopoSort {
    public static int[] shortestPath(WeightedAdjacencyList graph, int source) {
        int numVertices = graph.getNumVertices();
        int[] inDegree = new int[numVertices];
        int[] distance = new int[numVertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        // Calculate in-degrees of all vertices
        for (int node = 0; node < numVertices; node++) {
            for (WeightedAdjacencyList.Edge edge : graph.getAdjacencyList().get(node)) {
                inDegree[edge.getDestination()]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        // Enqueue all vertices with in-degree 0
        for (int node = 0; node < numVertices; node++) {
            if (inDegree[node] == 0) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            for (WeightedAdjacencyList.Edge edge : graph.getAdjacencyList().get(currentNode)) {
                int neighbor = edge.getDestination();
                int weight = edge.getWeight();

                // Relaxation step
                if (distance[currentNode] != Integer.MAX_VALUE && distance[currentNode] + weight < distance[neighbor]) {
                    distance[neighbor] = distance[currentNode] + weight;
                }

                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        int numVertices = 6;
        int[][] edges = {
            {0, 1, 5},
            {0, 2, 3},
            {1, 3, 6},
            {1, 2, 2},
            {2, 4, 4},
            {2, 5, 2},
            {2, 3, 7},
            {3, 5, 1},
            {4, 5, -2}
        };

        WeightedAdjacencyList graph = new WeightedAdjacencyList(numVertices);
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1], edge[2]); // Directed graph with weights
        }

        int source = 1;
        int[] distances = shortestPath(graph, source);
        System.out.println("Shortest distances from source vertex " + source + ":");
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println("Vertex " + i + ": Infinity");
            } else {
                System.out.println("Vertex " + i + ": " + distances[i]);
            }
        }
    }
}
