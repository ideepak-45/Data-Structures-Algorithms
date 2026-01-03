package main.java.graphtraversal;

import main.java.graphrepresentation.AdjacencyList;

public class DepthFirstSearch {
    public static void DFSUtil(AdjacencyList graph, int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (int neighbor : graph.getAdjacencyList().get(vertex)) {
            if (!visited[neighbor]) {
                DFSUtil(graph, neighbor, visited);
            }
        }
    }

    public static void DFS(AdjacencyList graph, int startVertex) {
        boolean[] visited = new boolean[graph.getNumVertices() + 1]; // +1 for 1-based indexing
        DFSUtil(graph, startVertex, visited);
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
        DFS(graph, 1);
    }
}
