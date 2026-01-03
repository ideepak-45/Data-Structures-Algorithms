package main.java.bipartitegraph;

import main.java.graphrepresentation.AdjacencyList;
import java.util.*;

public class BipartiteCheckDFS {
    public static boolean DFS(AdjacencyList graph, int vertex, int[] colors, int currentColor) {
        colors[vertex] = currentColor;

        for (int neighbor : graph.getAdjacencyList().get(vertex)) {
            if (colors[neighbor] == -1) {
                // Assign alternate color to the neighbor
                if (!DFS(graph, neighbor, colors, 1 - currentColor)) {
                    return false;
                }
            } else if (colors[neighbor] == currentColor) {
                // A neighbor has the same color, not bipartite
                return false;
            }
        }
        return true;
    }

    public static boolean isBipartite(AdjacencyList graph, int numVertices) {
        int[] colors = new int[numVertices+1];
        Arrays.fill(colors, -1); // -1 indicates uncolored

        for (int v = 1; v <= numVertices; v++) { // Assuming 1-based indexing
            if (colors[v] == -1) {
                if (!DFS(graph, v, colors, 0)) {
                    return false;
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
