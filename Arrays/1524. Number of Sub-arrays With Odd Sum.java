class Solution {
    public int numOfSubarrays(int[] arr) {
        int mod = 1000000007;
        long odd = 0, even = 1, sum = 0, ans = 0;

        for (int x : arr) {
            sum += x;

            if (sum % 2 == 1) {
                ans = (ans + even) % mod;
                odd++;
            } else {
                ans = (ans + odd) % mod;
                even++;
            }
        }

        return (int) ans;
    }
}
