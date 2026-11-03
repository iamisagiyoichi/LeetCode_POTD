class Solution {
    public int minMoves(int[] nums) {
        int mx = 0;
        int ans = 0;

        for (int x : nums) {
            mx = Math.max(mx, x);
        }

        for (int x : nums) {
            ans += mx - x;
        }

        return ans;
    }
}
