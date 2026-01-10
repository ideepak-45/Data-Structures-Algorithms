package main.java.shortestpathalgo;

import main.java.graphrepresentation.AdjacencyList;
import java.util.*;

public class ShortestPathInUndirectedGraphWithUnitWeight {
    public static int[] shortestPath(AdjacencyList graph, int source) {
        int numVertices = graph.getNumVertices();
        int[] distance = new int[numVertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            for (int neighbor : graph.getAdjacencyList().get(currentNode)) {
                // Since the graph is undirected and has unit weight, we can relax the edge
                if (distance[currentNode] + 1 < distance[neighbor]) {
                    distance[neighbor] = distance[currentNode] + 1;
                    queue.add(neighbor);
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        int numVertices = 5;
        int[][]
            edges = {
                {0, 1},
                {0, 2},
                {1, 3},
                {2, 3},
                {3, 4}
            };
        AdjacencyList graph = new AdjacencyList(numVertices);
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
            graph.addEdge(edge[1], edge[0]); // Undirected graph
        }

        int source = 0;
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
