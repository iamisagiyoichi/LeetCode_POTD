class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int mn = 0, mx = 0;

        for(int i = 1; i < n; i++) {
            if(nums[i] < nums[mn]) mn = i;
            if(nums[i] > nums[mx]) mx = i;
        }

        if(mn > mx) {
            int t = mn;
            mn = mx;
            mx = t;
        }

        return Math.min(mx + 1,
                Math.min(n - mn, mn + 1 + n - mx));
    }
}
