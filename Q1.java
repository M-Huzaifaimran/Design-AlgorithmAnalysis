import java.util.*;

public class GraphRepresentation {

    static int vertices = 4;

    // ─── Adjacency Matrix ───────────────────────────────────────────
    static int[][] adjacencyMatrix = new int[vertices][vertices];

    static void addEdgeMatrix(int u, int v) {
        adjacencyMatrix[u][v] = 1;
        adjacencyMatrix[v][u] = 1; // undirected
    }

    static void printMatrix() {
        System.out.println("\n=== Adjacency Matrix ===");
        System.out.print("  ");
        for (int i = 0; i < vertices; i++) System.out.print(i + " ");
        System.out.println();
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // ─── Adjacency List ─────────────────────────────────────────────
    static List<List<Integer>> adjacencyList = new ArrayList<>();

    static void initList() {
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    static void addEdgeList(int u, int v) {
        adjacencyList.get(u).add(v);
        adjacencyList.get(v).add(u); // undirected
    }

    static void printList() {
        System.out.println("\n=== Adjacency List ===");
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vertex " + i + " -> ");
            System.out.println(adjacencyList.get(i));
        }
    }

    // ─── Edge List ──────────────────────────────────────────────────
    static List<int[]> edgeList = new ArrayList<>();

    static void addEdgeEdgeList(int u, int v) {
        edgeList.add(new int[]{u, v});
    }

    static void printEdgeList() {
        System.out.println("\n=== Edge List ===");
        for (int[] edge : edgeList) {
            System.out.println("(" + edge[0] + ", " + edge[1] + ")");
        }
    }

    // ─── Main ────────────────────────────────────────────────────────
    public static void main(String[] args) {
        initList();

        // Complete graph: each vertex connected to every other vertex
        int[][] edges = {{0,1},{0,2},{0,3},{1,2},{1,3},{2,3}};

        for (int[] e : edges) {
            addEdgeMatrix(e[0], e[1]);
            addEdgeList(e[0], e[1]);
            addEdgeEdgeList(e[0], e[1]);
        }

        printMatrix();
        printList();
        printEdgeList();
    }
}
