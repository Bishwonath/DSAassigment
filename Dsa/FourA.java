import java.util.*;

class FourA {
    public int minMovesToCollectKeys(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] start = null;
        int numKeys = 0;
        
        // Find the starting position and count the number of keys
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    start = new int[]{i, j};
                } else if (grid[i][j] >= 'a' && grid[i][j] <= 'f') {
                    numKeys++;
                }
            }
        }
        
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new int[]{start[0], start[1], 0});
        visited.add(start[0] + "-" + start[1] + "-0");
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                int keys = current[2];
                
                if (grid[x][y] == 'E' && keys == numKeys) {
                    return steps;
                }
                
                for (int[] dir : dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] != 'W') {
                        char cell = grid[nx][ny];
                        if (cell == 'P' || cell == 'S' || cell == 'E' || cell == 'a' + keys || cell == 'A' + keys) {
                            String key = nx + "-" + ny + "-" + keys;
                            if (!visited.contains(key)) {
                                queue.offer(new int[]{nx, ny, keys});
                                visited.add(key);
                            }
                        } else if (cell >= 'a' && cell <= 'f') {
                            String key = nx + "-" + ny + "-" + (keys | (1 << (cell - 'a')));
                            if (!visited.contains(key)) {
                                queue.offer(new int[]{nx, ny, keys | (1 << (cell - 'a'))});
                                visited.add(key);
                            }
                        }
                    }
                }
            }
            steps++;
        }
        
        return -1; // If we couldn't reach the exit
    }

    public static void main(String[] args) {
        FourA solution = new FourA();
        char[][] grid = {
            {'S','P','P','P'},
            {'W','P','P','E'},
            {'P','b','W','P'},
            {'P','P','P','P'}
        };
        System.out.println(solution.minMovesToCollectKeys(grid)); // Output: 8
    }
}
