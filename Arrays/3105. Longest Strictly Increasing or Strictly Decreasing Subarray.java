class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int inc = 1, dec = 1, ans = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1])
                inc++;
            else
                inc = 1;

            if (nums[i] < nums[i - 1])
                dec++;
            else
                dec = 1;

            ans = Math.max(ans, Math.max(inc, dec));
        }

        return ans;
    }
}
