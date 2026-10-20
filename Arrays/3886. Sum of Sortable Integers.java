class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] desc = new int[n];
        for (int i = 1; i < n; i++)
            desc[i] = desc[i - 1] + (nums[i - 1] > nums[i] ? 1 : 0);

        int[] premax = new int[n];
        int[] sufmin = new int[n];

        premax[0] = nums[0];
        for (int i = 1; i < n; i++)
            premax[i] = Math.max(premax[i - 1], nums[i]);

        sufmin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--)
            sufmin[i] = Math.min(sufmin[i + 1], nums[i]);

        boolean[] cut = new boolean[n];
        java.util.Arrays.fill(cut, true);

        for (int m = 1; m < n; m++)
            cut[m] = premax[m - 1] <= sufmin[m];

        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0)
                continue;

            boolean valid = true;

            for (int l = 0; l < n; l += k) {
                int r = l + k - 1;
                int descents = desc[r] - desc[l];
                if (!cut[l] || descents + (nums[r] > nums[l] ? 1 : 0) > 1) {
                    valid = false;
                    break;
                }
            }

            if (valid)
                ans += k;
        }

        return ans;
    }
}
