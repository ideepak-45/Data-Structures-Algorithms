package main.java.topologicalsort;

import main.java.graphrepresentation.AdjacencyList;
import java.util.*;

public class TopologicalSort {
    public static List<Integer> topologicalSort(AdjacencyList graph, int numVertices) {
        boolean[] visited = new boolean[numVertices + 1];
        Stack<Integer> stack = new Stack<>();

        for (int node = 1; node <= numVertices; node++) {
            if (!visited[node]) {
                topologicalSortUtil(graph, node, visited, stack);
            }
        }

        List<Integer> sortedOrder = new ArrayList<>();
        while (!stack.isEmpty()) {
            sortedOrder.add(stack.pop());
        }
        return sortedOrder;
    }

    private static void topologicalSortUtil(AdjacencyList graph, int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;

        for (int neighbor : graph.getAdjacencyList().get(node)) {
            if (!visited[neighbor]) {
                topologicalSortUtil(graph, neighbor, visited, stack);
            }
        }

        stack.push(node);
    }

    public static void main(String[] args) {
        int numVertices = 6;
        int[][] edges = {{2, 1}, {3, 1}, {5, 1}, {4, 5}};
        AdjacencyList graph = new AdjacencyList(numVertices + 1); // +1 for 1-based indexing
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]); // Directed graph
        }

        List<Integer> sortedOrder = topologicalSort(graph, numVertices);
        System.out.println("Topological Sort Order: " + sortedOrder);
    }
}
