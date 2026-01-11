package main.java.shortestpathalgo;

import main.java.graphrepresentation.WeightedAdjacencyList;
import java.util.*;

public class DijkstraAlgorithm {
    public static int[] dijkstra(WeightedAdjacencyList graph, int source) {
        int numVertices = graph.getNumVertices();
        int[] distance = new int[numVertices];
        boolean[] visited = new boolean[numVertices];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;
        minHeap.offer(new int[]{source, 0});

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int currentNode = current[0];

            if (visited[currentNode]) continue;
            visited[currentNode] = true;

            for (WeightedAdjacencyList.Edge edge : graph.getAdjacencyList().get(currentNode)) {
                int neighbor = edge.getDestination();
                int weight = edge.getWeight();

                // Relaxation step
                if (!visited[neighbor] && distance[currentNode] + weight < distance[neighbor]) {
                    distance[neighbor] = distance[currentNode] + weight;
                    minHeap.offer(new int[]{neighbor, distance[neighbor]});
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        int numVertices = 5;
        int[][] edges = {
            {0, 1, 10},
            {0, 2, 3},
            {1, 2, 1},
            {1, 3, 2},
            {2, 1, 4},
            {2, 3, 8},
            {2, 4, 2},
            {3, 4, 7},
            {4, 3, 9}
        };

        WeightedAdjacencyList graph = new WeightedAdjacencyList(numVertices);
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1], edge[2]);
        }

        int source = 0;
        int[] distances = dijkstra(graph, source);

        System.out.println("Shortest distances from source node " + source + ":");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("To node " + i + " - Distance: " + distances[i]);
        }
    }
}
