package main.java.graphrepresentation;

public class AdjacencyMatrix {
    private final int[][] matrix;
    private final int numVertices;

    public AdjacencyMatrix(int numVertices) {
        this.numVertices = numVertices;
        this.matrix = new int[numVertices][numVertices];
    }

    public void addEdge(int source, int destination) {
        matrix[source][destination] = 1;
    }

    public boolean hasEdge(int source, int destination) {
        return matrix[source][destination] == 1;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public int[][] getAdjacencyMatrix() {
        return matrix;
    }
}