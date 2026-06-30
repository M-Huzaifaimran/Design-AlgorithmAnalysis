import java.util.*;

public class Main {
    static final int V = 7;
    static int[][] c;            
    static int[][] origCapacity; 
    static List<List<Integer>> adj;

    static void init() {
        c = new int[V][V];
        origCapacity = new int[V][V];
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }
    
    static void addEdge(int u, int v, int cap) {
        c[u][v] += cap;
        origCapacity[u][v] += cap;
        
        if (!adj.get(u).contains(v)) adj.get(u).add(v);
        if (!adj.get(v).contains(u)) adj.get(v).add(u);
    }
    
    static int[] BFS(int s, int t) {
        int[] pred = new int[V];
        Arrays.fill(pred, -1);     
        Queue<Integer> Q = new LinkedList<>();
        Q.add(s);
        pred[s] = s;    

        while (!Q.isEmpty()) {
            int u = Q.poll();

            for (int v : adj.get(u)) {
                if (pred[v] == -1 && c[u][v] > 0) {   
                    pred[v] = u;                 

                    if (v == t)
                        return pred;               

                    Q.add(v);
                }
            }
        }
        return null;
    }


    static int edmondsKarp(int s, int t) {
        int maxFlow = 0;
        int[] pred;

        String[] names = {"A", "B", "C", "D", "E", "F", "G"};

        while ((pred = BFS(s, t)) != null) {
            
            int pathFlow = Integer.MAX_VALUE;
            for (int v = t; v != s; v = pred[v]) {
                int u = pred[v];
                pathFlow = Math.min(pathFlow, c[u][v]);
            }

            List<Integer> P = new ArrayList<>();
            for (int v = t; v != s; v = pred[v])
                P.add(v);
            P.add(s);
            Collections.reverse(P);

            System.out.print("Path: ");
            for (int i = 0; i < P.size(); i++) {
                System.out.print(names[P.get(i)]);
                if (i < P.size() - 1) System.out.print(" -> ");
            }
            System.out.println(" | Flow = " + pathFlow);

            for (int v = t; v != s; v = pred[v]) {
                int u = pred[v];
                c[u][v] -= pathFlow;
                c[v][u] += pathFlow;
            }

            maxFlow += pathFlow; 
        }

        return maxFlow;
    }
    
    static void minCut(int s) {
        boolean[] visited = new boolean[V]; 
        Queue<Integer> Q = new LinkedList<>();

        Q.add(s);
        visited[s] = true;

        while (!Q.isEmpty()) {
            int u = Q.poll();

            for (int v : adj.get(u)) {
                if (!visited[v] && c[u][v] > 0) {
                    visited[v] = true;
                    Q.add(v);
                }
            }
        }

        String[] names = {"A", "B", "C", "D", "E", "F", "G"};
        
        System.out.println("\nMin-Cut Edges:");
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                if (visited[u] && !visited[v] && origCapacity[u][v] > 0) {
                    System.out.println(names[u] + " -> " + names[v] + " (cap=" + origCapacity[u][v] + ")");
                }
            }
        }
    }

    public static void main(String[] args) {
        init();

        addEdge(0, 3, 3); 
        addEdge(2, 0, 3); 
        addEdge(0, 1, 3); 
        addEdge(2, 3, 1); 
        addEdge(2, 4, 2); 
        addEdge(1, 2, 4); 
        addEdge(3, 5, 6); 
        addEdge(3, 4, 2); 
        addEdge(4, 1, 1); 
        addEdge(4, 6, 1); 
        addEdge(5, 6, 9); 
        
        System.out.println("----Edmonds-Karp Algorithm----\n");
        int maxFlow = edmondsKarp(0, 6);
        System.out.println("\nMaximum Flow = " + maxFlow);
        minCut(0);
    }
}
