class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        int[][] a = new int[n][2];

        for (int b = 0; b < n; b++) {
            a[b][0] = nums[b];
            a[b][1] = b;
        }

        Arrays.sort(a, (b, c) -> Integer.compare(b[0], c[0]));

        int d = 0;

        while (d < n) {
            int e = d;

            while (e + 1 < n && a[e + 1][0] - a[e][0] <= limit) {
                e++;
            }

            ArrayList<Integer> f = new ArrayList<>();

            for (int b = d; b <= e; b++) {
                f.add(a[b][1]);
            }

            Collections.sort(f);

            for (int b = d; b <= e; b++) {
                nums[f.get(b - d)] = a[b][0];
            }

            d = e + 1;
        }

        return nums;
    }
}
