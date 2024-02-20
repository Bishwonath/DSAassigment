import java.util.*;

public class threeb {
    private static class Edge implements Comparable<Edge> {
        int src, dest;
        double weight;

        public Edge(int src, int dest, double weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Double.compare(this.weight, other.weight);
        }
    }

    private int[] parent;

    public threeb(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        parent[rootX] = rootY;
    }

    public double kruskalMST(int n, List<Edge> edges) {
        Collections.sort(edges);

        double minCost = 0;
        int edgeCount = 0;

        for (Edge edge : edges) {
            if (edgeCount == n - 1) {
                break;
            }

            int srcRoot = find(edge.src);
            int destRoot = find(edge.dest);

            if (srcRoot != destRoot) {
                union(srcRoot, destRoot);
                minCost += edge.weight;
                edgeCount++;
            }
        }

        return minCost;
    }

    public static void main(String[] args) {
        int n = 5;
        threeb scoreTracker = new threeb(n);

        PriorityQueue<Double> minHeap = new PriorityQueue<>();
        minHeap.offer(85.5);
        minHeap.offer(92.3);
        minHeap.offer(77.8);
        minHeap.offer(90.1);
        
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!minHeap.isEmpty()) {
                    double weight = Math.abs(minHeap.poll() - minHeap.poll()); // Calculate weight using absolute difference
                    edges.add(new Edge(i, j, weight));
                } else {
                    System.out.println("Not enough elements in the minHeap.");
                    // Handle this case accordingly
                }
            }
        }

        double minSpanningTreeCost = scoreTracker.kruskalMST(n, edges);
        System.out.println("Minimum Spanning Tree Cost: " + minSpanningTreeCost);
    }
}
 