package main.java.cycledetection.directedgraph;

import main.java.graphrepresentation.AdjacencyList;
import java.util.*;

public class CycleDetectionTopoSort {
    public static Boolean isCyclic(AdjacencyList graph, int numVertices) {
        int[] inDegree = new int[numVertices + 1];

        // Calculate in-degrees of all vertices
        for (int node = 1; node <= numVertices; node++) {
            for (int neighbor : graph.getAdjacencyList().get(node)) {
                inDegree[neighbor]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        // Enqueue all vertices with in-degree 0
        for (int node = 1; node <= numVertices; node++) {
            if (inDegree[node] == 0) {
                queue.add(node);
            }
        }

        int visitedCount = 0;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            visitedCount++;

            for (int neighbor : graph.getAdjacencyList().get(currentNode)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // If visited count is not equal to number of vertices, there is a cycle
        return visitedCount != numVertices;
    }

    public static void main(String[] args) {
        int numVertices = 10;
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {3, 7}, {7, 5}, {8, 9}, {8, 2}, {9, 10}, {10, 8}}; // Example with a cycle
        AdjacencyList graph = new AdjacencyList(numVertices + 1); // +1 for 1-based indexing
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]); // Directed graph
        }

        System.out.println("Graph has cycle: " + isCyclic(graph, numVertices));
    }
}
