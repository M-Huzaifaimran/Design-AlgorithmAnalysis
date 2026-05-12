import java.util.*;

public class TopologicalSort {

    static Map<String, Boolean> visited = new HashMap<>();
    static LinkedList<String> result = new LinkedList<>();
    static Map<String, List<String>> graph = new HashMap<>();

    static void addEdge(String u, String v) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        graph.computeIfAbsent(v, k -> new ArrayList<>());
    }

    static void dfsVisit(String u) {
        visited.put(u, true);
        List<String> neighbors = graph.getOrDefault(u, new ArrayList<>());
        for (String v : neighbors) {
            if (!visited.getOrDefault(v, false)) {
                dfsVisit(v);
            }
        }
        result.addFirst(u);
    }

    static void topologicalSort() {
        for (String vertex : graph.keySet()) {
            visited.put(vertex, false);
        }
        for (String vertex : graph.keySet()) {
            if (!visited.get(vertex)) {
                dfsVisit(vertex);
            }
        }
    }

    public static void main(String[] args) {

        addEdge("m", "q");
        addEdge("m", "t");
        addEdge("m", "x");
        addEdge("m", "r"); 
        addEdge("n", "o");
        addEdge("n", "q");
        addEdge("n", "u");
        addEdge("o", "r");
        addEdge("o", "s");
        addEdge("o", "v");
        addEdge("p", "o");
        addEdge("p", "s");
        addEdge("p", "z");
        addEdge("q", "t");
        addEdge("r", "u");
        addEdge("r", "y");
        addEdge("s", "r");
        addEdge("u", "t");
        addEdge("v", "r");
        addEdge("v", "w");
        addEdge("v", "x");
        addEdge("w", "z");
        addEdge("y", "v");

        System.out.println("\nTopological Sort (DFS-based)");
        System.out.println("Graph edges built from given diagram.");
        System.out.println();

        topologicalSort();

        System.out.println("Topological Order:");
        System.out.println(result);
    }
}
