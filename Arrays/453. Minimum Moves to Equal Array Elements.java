class Solution {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        int ans = 0;

        for (int x : nums) {
            min = Math.min(min, x);
        }

        for (int x : nums) {
            ans += x - min;
        }

        return ans;
    }
}
