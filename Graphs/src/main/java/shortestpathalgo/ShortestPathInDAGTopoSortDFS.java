package main.java.shortestpathalgo;

import main.java.graphrepresentation.WeightedAdjacencyList;
import java.util.*;

public class ShortestPathInDAGTopoSortDFS {
    public static void topologicalSortUtil(WeightedAdjacencyList graph, int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;

        for (WeightedAdjacencyList.Edge edge : graph.getAdjacencyList().get(node)) {
            int neighbor = edge.getDestination();
            if (!visited[neighbor]) {
                topologicalSortUtil(graph, neighbor, visited, stack);
            }
        }

        stack.push(node);
    }

    public static int[] shortestPath(WeightedAdjacencyList graph, int source) {
        int numVertices = graph.getNumVertices();
        boolean[] visited = new boolean[numVertices];
        Stack<Integer> stack = new Stack<>();
        int[] distance = new int[numVertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        // Perform topological sort
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                topologicalSortUtil(graph, i, visited, stack);
            }
        }

        // Process vertices in topological order
        while (!stack.isEmpty()) {
            int currentNode = stack.pop();

            if (distance[currentNode] != Integer.MAX_VALUE) {
                for (WeightedAdjacencyList.Edge edge : graph.getAdjacencyList().get(currentNode)) {
                    int neighbor = edge.getDestination();
                    int weight = edge.getWeight();

                    // Relaxation step
                    if (distance[currentNode] + weight < distance[neighbor]) {
                        distance[neighbor] = distance[currentNode] + weight;
                    }
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
            graph.addEdge(edge[0], edge[1], edge[2]); // Directed graph
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