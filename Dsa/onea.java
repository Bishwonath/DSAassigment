public class onea {
    
    public static int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        
        int n = costs.length;
        int k = costs[0].length;
        
        // dp[i][j] represents the minimum cost of decorating venues up to i with theme j
        int[][] dp = new int[n][k];
        
        // Initialize the first row with the costs of decorating the first venue
        for (int j = 0; j < k; j++) {
            dp[0][j] = costs[0][j];
        }
        
        // Iterate through the venues
        for (int i = 1; i < n; i++) {
            // Iterate through the themes for the current venue
            for (int j = 0; j < k; j++) {
                // Initialize the minimum cost for the current venue and theme
                dp[i][j] = Integer.MAX_VALUE;
                
                // Iterate through the themes for the previous venue
                for (int prevTheme = 0; prevTheme < k; prevTheme++) {
                    // Check if the previous theme is different from the current theme
                    if (prevTheme != j) {
                        // Calculate the cost of decorating the current venue with the current theme
                        // and add it to the minimum cost of decorating the previous venues with the previous theme
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][prevTheme] + costs[i][j]);
                    }
                }
            }
        }
        
        // Find the minimum cost of decorating the last venue
        int minCost = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) {
            minCost = Math.min(minCost, dp[n - 1][j]);
        }
        
        return minCost;
    }
    
    public static void main(String[] args) {
        int[][] costs = {{1, 3, 2}, {4, 6, 8}, {3, 1, 5}};
        System.out.println("Minimum cost: " + minCost(costs)); // Output: 7
    }
}
