import java.util.*;
public class GraphRepresentation {

    static int vertices = 4;
    static int[][] adjacencyMatrix = new int[vertices][vertices];
    static List<List<Integer>> adjacencyList = new ArrayList<>();

    static void addEdgeMatrix(int u, int v) {
        adjacencyMatrix[u][v] = 1;
        adjacencyMatrix[v][u] = 1;
    }

    static void printMatrix() {
        System.out.println("\n Adjacency Matrix ");
        System.out.print("  ");
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + " "); }
        System.out.println();
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void initList() {
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    static void addEdgeList(int u, int v) {
        adjacencyList.get(u).add(v);
        adjacencyList.get(v).add(u); 
    }

    static void printList() {
        System.out.println("\n Adjacency List ");
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vertex " + i + " -> ");
            System.out.println(adjacencyList.get(i));
        }
    }

    public static void main(String[] args) {

        initList();
        int[][] edges = {{0,1},{0,2},{0,3},{1,2},{1,3},{2,3}};

        for (int[] e : edges) {
            addEdgeMatrix(e[0], e[1]);
            addEdgeList(e[0], e[1]);
        }

        printMatrix();
        printList();
    }
}
