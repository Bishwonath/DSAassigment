import java.util.*;

public class fourb {
    static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public static List<Integer> closestValues(TreeNode root, double target, int x) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Map.Entry<Integer, Double>> pq = new PriorityQueue<>(
            (a, b) -> Double.compare(a.getValue(), b.getValue())
        );

        inorderTraversal(root, target, pq, x);

        while (!pq.isEmpty()) {
            result.add(pq.poll().getKey());
        }
        return result;
    }

    private static void inorderTraversal(TreeNode root, double target, PriorityQueue<Map.Entry<Integer, Double>> pq, int x) {
        if (root == null) return;

        inorderTraversal(root.left, target, pq, x);

        double diff = Math.abs(root.val - target);
        pq.offer(new AbstractMap.SimpleEntry<>(root.val, diff));

        if (pq.size() > x) {
            pq.poll();
        }

        inorderTraversal(root.right, target, pq, x);
    }

    public static void main(String[] args) {
        // Construct the binary search tree
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(10);

        // Target value and number of closest values
        double k = 3.8;
        int x = 2;

        // Get the closest values to the target
        List<Integer> closest = closestValues(root, k, x);

        // Output the result
        System.out.println("Closest values to " + k + " with " + x + " values: " + closest);
    }
}

