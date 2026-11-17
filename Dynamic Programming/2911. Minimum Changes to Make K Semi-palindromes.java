class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = (int) 1e9;

        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], INF);
        }

        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;

                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;

                    int changes = 0;

                    for (int i = 0; i < d; i++) {
                        int left = l + i;
                        int right = l + len - d + i;

                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    cost[l][r] = Math.min(cost[l][r], changes);
                }
            }
        }

        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int parts = 1; parts <= k; parts++) {
            for (int i = 2 * parts; i <= n; i++) {
                for (int j = 2 * (parts - 1); j <= i - 2; j++) {
                    dp[parts][i] = Math.min(
                        dp[parts][i],
                        dp[parts - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
.java
