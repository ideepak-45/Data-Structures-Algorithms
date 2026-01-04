package main.java.topologicalsort;

import main.java.graphrepresentation.AdjacencyList;
import java.util.*;

// Topological Sort using BFS (Kahn's Algorithm)
public class TopologicalSortBFS {
    public static List<Integer> topologicalSort(AdjacencyList graph, int numVertices) {
        int[] inDegree = new int[numVertices];
        for (int node = 0; node < numVertices; node++) {
            for (int neighbor : graph.getAdjacencyList().get(node)) {
                inDegree[neighbor]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int node = 0; node < numVertices; node++) {
            if (inDegree[node] == 0) {
                queue.add(node);
            }
        }

        List<Integer> sortedOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            sortedOrder.add(currentNode);

            for (int neighbor : graph.getAdjacencyList().get(currentNode)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (sortedOrder.size() != numVertices) {
            System.out.println("Graph has at least one cycle, topological sort not possible.");
            throw new IllegalArgumentException("Graph has at least one cycle, topological sort not possible.");
        }

        return sortedOrder;
    }

    public static void main(String[] args) {
        int numVertices = 6;
        int[][] edges = {{5, 2}, {2, 3}, {3, 1}, {5, 0}, {4, 0}, {4, 1}};
        AdjacencyList graph = new AdjacencyList(numVertices);
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]); // Directed graph
        }

        List<Integer> sortedOrder = topologicalSort(graph, numVertices);
        System.out.println("Topological Sort Order: " + sortedOrder);
    }
}
