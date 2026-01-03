package main.java.cycledetection.undirectedgraph;

import main.java.graphrepresentation.AdjacencyList;
import java.util.*;

public class CycleDetectionBFS {
    
    public static boolean BFS(AdjacencyList graph, int startVertex, int parent) {
        boolean[] visited = new boolean[graph.getNumVertices() + 1]; // +1 for 1-based indexing
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int neighbor : graph.getAdjacencyList().get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                } else if (neighbor != parent) {
                    // A visited neighbor that is not the parent indicates a cycle
                    return true;
                }
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

        System.out.println("Graph has cycle: " + BFS(graph, 1, -1));
    }
}
