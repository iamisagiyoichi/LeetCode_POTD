class Solution {
    public int distinctSubseqII(String s) {
        long MOD = 1000000007L;
        long dp = 1;
        long[] last = new long[26];

        for (char c : s.toCharArray()) {
            int x = c - 'a';

            long ndp = (2 * dp - last[x] + MOD) % MOD;
            last[x] = dp;
            dp = ndp;
        }

        return (int) ((dp - 1 + MOD) % MOD);
    }
}
