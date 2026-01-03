package main.java.cycledetection.undirectedgraph;

import main.java.graphrepresentation.AdjacencyList;
import java.util.*;

public class CycleDetectionDFS {
    public static boolean DFS(AdjacencyList graph, int vertex, boolean[] visited, int parent) {
        visited[vertex] = true;

        for (int neighbor : graph.getAdjacencyList().get(vertex)) {
            if (!visited[neighbor]) {
                if (DFS(graph, neighbor, visited, vertex)) {
                    return true;
                }
            } else if (neighbor != parent) {
                // A visited neighbor that is not the parent indicates a cycle
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int numVertices = 9;
        int[][] edges = {
            {1, 2}, {2, 3}, {2, 4}, {4, 5},
            {5, 8}, {8, 7}, {7, 6}, {6, 1}, {9, 6}
        };
        AdjacencyList graph = new AdjacencyList(numVertices + 1); // +1 for 1-based indexing
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
            graph.addEdge(edge[1], edge[0]); // Undirected graph
        }

        boolean[] visited = new boolean[graph.getNumVertices() + 1];
        System.out.println("Graph has cycle: " + DFS(graph, 1, visited, -1));
    }
}
