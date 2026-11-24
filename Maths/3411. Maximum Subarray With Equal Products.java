class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            long p = 1, l = 1;
            int g = 0;

            for (int j = i; j < n; j++) {
                p *= nums[j];
                g = gcd(g, nums[j]);
                l = l / gcd(l, nums[j]) * nums[j];

                if (p == (long) g * l) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private int gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return (int) a;
    }
}
