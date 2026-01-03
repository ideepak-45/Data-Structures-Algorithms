package main.java.cycledetection.directedgraph;

import main.java.graphrepresentation.AdjacencyList;
import java.util.*;
    

public class CycleDetectionDFS {

    public static Boolean isCyclic(AdjacencyList graph, int numVertices) {
        int[] visited = new int[numVertices + 1];

        for (int node = 1; node <= numVertices; node++) {
            if (detectCycleUtil(graph, node, visited)) {
                return true;
            }
        }
        return false;
    }

    private static Boolean detectCycleUtil(AdjacencyList graph, int node, int[] visited) {

        visited[node] = 1; // Mark node as being visited

        for (int neighbor : graph.getAdjacencyList().get(node)) {
            if (visited[neighbor] == 1) {
                // A back edge found, cycle detected
                return true;
            }
            if (visited[neighbor] == 0) {
                if (detectCycleUtil(graph, neighbor, visited)) {
                    return true;
                }
            }
        }

        visited[node] = 2; // Mark node as fully processed
        return false;
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