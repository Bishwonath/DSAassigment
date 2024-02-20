import java.util.*;

public class twob {
    
    public static List<Integer> secretSharing(int n, int[][] intervals, int firstPerson) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> known = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        
        // Add the first person who initially possesses the secret
        known.add(firstPerson);
        queue.offer(firstPerson);
        
        // Perform BFS on intervals to find individuals who eventually know the secret
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            while (!queue.isEmpty()) {
                int person = queue.poll();
                if (person >= start && person <= end) {
                    // Share the secret with individuals in the current interval
                    for (int i = 0; i < n; i++) {
                        if (!known.contains(i)) {
                            known.add(i);
                            queue.offer(i);
                        }
                    }
                }
            }
        }
        
        // Add individuals who eventually know the secret to the result list
        result.addAll(known);
        Collections.sort(result);
        
        return result;
    }
    
    public static void main(String[] args) {
        int n = 5;
        int[][] intervals = {{0, 2}, {1, 3}, {2, 4}};
        int firstPerson = 0;
        List<Integer> result = secretSharing(n, intervals, firstPerson);
        System.out.println("Individuals who eventually know the secret: " + result);
    }
}
