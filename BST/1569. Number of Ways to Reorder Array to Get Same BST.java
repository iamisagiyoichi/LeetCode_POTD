class Solution {
    static final int MOD = 1_000_000_007;
    long[][] comb = new long[1001][1001];

    public int numOfWays(int[] nums) {
        int n = nums.length;

        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        return (int) ((solve(nums) - 1 + MOD) % MOD);
    }

    private long solve(java.util.List<Integer> nums) {
        int n = nums.size();
        if (n <= 2) return 1;

        java.util.List<Integer> left = new java.util.ArrayList<>();
        java.util.List<Integer> right = new java.util.ArrayList<>();

        for (int i = 1; i < n; i++) {
            if (nums.get(i) < nums.get(0))
                left.add(nums.get(i));
            else
                right.add(nums.get(i));
        }

        long ways = comb[n - 1][left.size()];
        ways = ways * solve(left) % MOD;
        ways = ways * solve(right) % MOD;

        return ways;
    }

    private long solve(int[] nums) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        for (int num : nums) list.add(num);
        return solve(list);
    }
}
