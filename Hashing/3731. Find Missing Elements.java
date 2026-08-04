class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int mn = Integer.MAX_VALUE;
        int mx = Integer.MIN_VALUE;

        HashSet<Integer> set = new HashSet<>();

        for (int x : nums) {
            mn = Math.min(mn, x);
            mx = Math.max(mx, x);
            set.add(x);
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = mn; i <= mx; i++) {
            if (!set.contains(i)) {
                ans.add(i);
            }
        }

        return ans;
    }
}
