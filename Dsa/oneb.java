import java.util.*;

class Solution {
    public int minTimeToBuildEngines(int[] engines, int splitCost) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int engine : engines) {
            pq.offer(engine);
        }
        
        int totalTime = 0;
        while (pq.size() > 1) {
            int firstEngine = pq.poll();
            int secondEngine = pq.poll();
            int cost = splitCost * (pq.size() > 0 ? 1 : 0);
            int combinedTime = firstEngine + secondEngine + cost;
            totalTime += combinedTime;
            pq.offer(combinedTime);
        }
        
        return totalTime;
    }
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] engines = {3, 4, 5, 2};
        int splitCost = 2;
        System.out.println(solution.minTimeToBuildEngines(engines, splitCost)); // Output: 4
    }
}
