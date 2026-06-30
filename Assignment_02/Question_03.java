import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int V = 9;
    static List<int[]>[] adj;

    static void initGraph() {
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++)
            adj[i] = new ArrayList<>();
    }

    static void addEdge(int u, int v, int w) {
        adj[u].add(new int[]{v, w});
        adj[v].add(new int[]{u, w});
    }

    static void dijkstra(int src) {
        int[] dist = new int[V];
        int[] parent = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(dist, INF);
        Arrays.fill(parent, -1);

        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[1];

            if (visited[u]) continue;
            visited[u] = true;

            for (int[] edge : adj[u]) {
                int v = edge[0];
                int w = edge[1];

                if (!visited[v] && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        printResult(dist, parent);
    }

    static void printResult(int[] dist, int[] parent) {
        System.out.println("Node   Distance   Path");

        for (int i = 0; i < V; i++) {
            System.out.print(i + "      " + dist[i] + "        ");
            printPath(parent, i);
            System.out.println();
        }

        System.out.println("\nShortest Path Tree:");
        for (int i = 0; i < V; i++) {
            if (parent[i] != -1)
                System.out.println(parent[i] + " -> " + i);
        }
    }

    static void printPath(int[] parent, int j) {
        if (parent[j] == -1) {
            System.out.print(j);
            return;
        }
        printPath(parent, parent[j]);
        System.out.print(" -> " + j);
    }

    public static void main(String[] args) {

        initGraph();

        addEdge(0,1,4);
        addEdge(0,7,8);
        addEdge(1,2,8);
        addEdge(1,7,11);
        addEdge(2,3,7);
        addEdge(2,5,4);
        addEdge(2,8,2);
        addEdge(3,4,9);
        addEdge(3,5,14);
        addEdge(4,5,10);
        addEdge(5,6,2);
        addEdge(6,7,1);
        addEdge(6,8,6);
        addEdge(7,8,7);

        dijkstra(0);
    }
}
