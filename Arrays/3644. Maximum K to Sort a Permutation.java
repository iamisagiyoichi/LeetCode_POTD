class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int k0 = Integer.highestOneBit(n - 1) * 2 - 1;
        int k = k0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            k &= (-(x != i ? 1 : 0) & x) | -(x == i ? 1 : 0);
        }

        return k == k0 ? 0 : k;
    }
}
