package main.java.bipartitegraph;

import main.java.graphrepresentation.AdjacencyList;
import java.util.*;

public class BipartiteCheckBFS {

    public static boolean isBipartite(AdjacencyList graph, int numVertices) {
        int[] colors = new int[numVertices + 1]; // 0: uncolored, 1: color1, -1: color2
        Queue<Integer> queue = new LinkedList<>();

        for (int startVertex = 1; startVertex <= numVertices; startVertex++) {
            if (colors[startVertex] != 0) {
                continue; // Already colored
            }

            colors[startVertex] = 1; // Start coloring with color1
            queue.add(startVertex);

            while (!queue.isEmpty()) {
                int vertex = queue.poll();
                for (int neighbor : graph.getAdjacencyList().get(vertex)) {
                    if (colors[neighbor] == 0) {
                        // Color with opposite color
                        colors[neighbor] = -colors[vertex];
                        queue.add(neighbor);
                    } else if (colors[neighbor] == colors[vertex]) {
                        // Conflict in coloring
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int numVertices = 9;
        int[][] edges = {
            {1, 2}, {2, 3}, {3, 4},
            {4, 5}, {5, 6}, {6, 7},
            {7, 2}, {4, 8}, {8, 9}
        };
        AdjacencyList graph = new AdjacencyList(numVertices + 1); // +1 for 1-based indexing
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
            graph.addEdge(edge[1], edge[0]); // Undirected graph
        }

        System.out.println("Graph is bipartite: " + isBipartite(graph, numVertices));
    }
}
