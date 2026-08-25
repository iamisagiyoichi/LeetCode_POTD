class Solution {
    public int missingMultiple(int[] nums, int k) {
        boolean[] seen = new boolean[101];

        for (int x : nums) {
            if (x <= 100) {
                seen[x] = true;
            }
        }

        for (int x = k; ; x += k) {
            if (x > 100 || !seen[x]) {
                return x;
            }
        }
    }
}
