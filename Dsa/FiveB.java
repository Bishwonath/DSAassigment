import java.util.*;

public class FiveB {
    
    public List<Integer> findImpactedDevices(int[][] edges, int targetDevice) {
        Map<Integer, List<Integer>> graph = buildGraph(edges);
        Set<Integer> visited = new HashSet<>();
        List<Integer> impactedDevices = new ArrayList<>();
        dfs(graph, targetDevice, visited, impactedDevices);
        return impactedDevices;
    }
    
    private Map<Integer, List<Integer>> buildGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
            graph.putIfAbsent(to, new ArrayList<>());
            graph.get(to).add(from); // Assuming undirected graph
        }
        return graph;
    }
    
    private void dfs(Map<Integer, List<Integer>> graph, int current, Set<Integer> visited, List<Integer> impactedDevices) {
        if (visited.contains(current)) {
            return;
        }
        visited.add(current);
        if (current != 4) { // Exclude the target device itself
            impactedDevices.add(current);
        }
        List<Integer> neighbors = graph.getOrDefault(current, new ArrayList<>());
        for (int neighbor : neighbors) {
            dfs(graph, neighbor, visited, impactedDevices);
        }
    }
    
    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{1,3},{1,6},{2,4},{4,6},{4,5},{5,7}};
        int targetDevice = 4;
        FiveB solution = new FiveB();
        List<Integer> impactedDevices = solution.findImpactedDevices(edges, targetDevice);
        System.out.println("Impacted Device List: " + impactedDevices);
    }
}
