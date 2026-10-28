class Solution {
    public int minCost(int[][] grid) {
        int n = grid[0].length;
        boolean[][] dp = new boolean[n][1024];
        dp[0][0] = true;

        for (int[] row : grid) {
            for (int j = 0; j < n; j++) {
                boolean[] reach = new boolean[1024];

                for (int x = 0; x < 1024; x++) {
                    if (dp[j][x] || (j > 0 && dp[j - 1][x])) {
                        reach[x] = true;
                    }
                }

                java.util.Arrays.fill(dp[j], false);

                for (int x = 0; x < 1024; x++) {
                    if (reach[x]) {
                        dp[j][x ^ row[j]] = true;
                    }
                }
            }
        }

        for (int x = 0; x < 1024; x++) {
            if (dp[n - 1][x]) {
                return x;
            }
        }

        return 0;
    }
}
