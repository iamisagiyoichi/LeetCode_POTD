// Approach 1st :- Storing Digits and Checking all pairs

class Solution {
    public int maxProduct(int n) {
        int[] d = new int[10];
        int m = 0;

        while (n > 0) {
            d[m++] = n % 10;
            n /= 10;
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                ans = Math.max(ans, d[i] * d[j]);
            }
        }

        return ans;
    }
}
