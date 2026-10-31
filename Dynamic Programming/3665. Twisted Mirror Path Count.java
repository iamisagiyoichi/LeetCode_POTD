class Solution {
    static final int MOD = 1_000_000_007;
    static final int[][][] mat = {
        {{1, 1}, {1, 1}},
        {{0, 1}, {1, 0}}
    };

    public int uniquePaths(int[][] A) {
        int n = A[0].length, left = 0;
        int[] dp = new int[n];

        dp[0] = 1;

        for (int i = 0; i < A.length; i++) {
            left = 0;
            for (int j = 0; j < n; j++) {
                int c = A[i][j], top = dp[j];

                dp[j] = (int) ((1L * left * mat[c][1][0]
                        + 1L * top * mat[c][1][1]) % MOD);

                left = (int) ((1L * left * mat[c][0][0]
                        + 1L * top * mat[c][0][1]) % MOD);
            }
        }

        return left;
    }
}
