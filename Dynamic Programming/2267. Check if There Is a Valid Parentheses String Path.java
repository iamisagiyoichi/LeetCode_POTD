class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int len = m + n - 1;

        if (len % 2 == 1) return false;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;

        boolean[][][] dp = new boolean[m][n][len + 1];
        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int balance = 0; balance <= len; balance++) {
                    if (!dp[i][j][balance]) continue;

                    if (i + 1 < m) {
                        int nextBalance = balance + (grid[i + 1][j] == '(' ? 1 : -1);

                        if (nextBalance >= 0 && nextBalance <= len) {
                            dp[i + 1][j][nextBalance] = true;
                        }
                    }

                    if (j + 1 < n) {
                        int nextBalance = balance + (grid[i][j + 1] == '(' ? 1 : -1);

                        if (nextBalance >= 0 && nextBalance <= len) {
                            dp[i][j + 1][nextBalance] = true;
                        }
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}
