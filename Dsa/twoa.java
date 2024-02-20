public class twoa {

    public static int minMoves(int[] dresses) {
        int n = dresses.length;
        if (n == 1) {
            return 0; // Already equalized
        }

        int min = Integer.MAX_VALUE;
        int total = 0;
        for (int dress : dresses) {
            total += dress;
        }

        int target = total / n; // Desired number of dresses per machine

        // Calculate min moves for each target number
        for (int i = 0; i < target; i++) {
            int moves = 0;
            for (int dress : dresses) {
                int diff = Math.abs(dress - target);
                // Avoid passing dresses from empty machines
                if (dress < target) {
                    diff = Math.min(diff, target - dress);
                }
                moves += diff;
            }
            min = Math.min(min, moves);
        }

        // Check if equalizing is possible (total not divisible by n)
        if (min == Integer.MAX_VALUE) {
            return -1; // Equalization not possible
        }

        return min;
    }

    public static void main(String[] args) {
        int[] dresses1 = { 2, 1, 3, 0, 2 };

        System.out.println("Minimum moves for dresses: " + minMoves(dresses1));

    }
}
