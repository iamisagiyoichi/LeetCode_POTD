class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] sa = new int[n];
        int[] r = new int[n];
        int[] nr = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
            r[i] = nums[i];
        }

        for (int k = 1; k < n; k *= 2) {
            final int[] rank = r;
            final int len = k;

            Integer[] idx = new Integer[n];

            for (int i = 0; i < n; i++) {
                idx[i] = sa[i];
            }

            Arrays.sort(idx, (x, y) -> {
                if (rank[x] != rank[y]) {
                    return Integer.compare(rank[x], rank[y]);
                }

                int a = x + len < n ? rank[x + len] : -1;
                int b = y + len < n ? rank[y + len] : -1;

                return Integer.compare(a, b);
            });

            for (int i = 0; i < n; i++) {
                sa[i] = idx[i];
            }

            nr[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int x = sa[i - 1];
                int y = sa[i];

                boolean diff = r[x] != r[y];

                if (!diff) {
                    int a = x + k < n ? r[x + k] : -1;
                    int b = y + k < n ? r[y + k] : -1;
                    diff = a != b;
                }

                nr[y] = nr[x] + (diff ? 1 : 0);
            }

            int[] temp = r;
            r = nr;
            nr = temp;

            if (r[sa[n - 1]] == n - 1) {
                break;
            }
        }

        int[] pos = new int[n];
        int[] lcp = new int[n];

        for (int i = 0; i < n; i++) {
            pos[sa[i]] = i;
        }

        int h = 0;

        for (int i = 0; i < n; i++) {
            int x = pos[i];

            if (x == 0) continue;

            int j = sa[x - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[x] = h;

            if (h > 0) h--;
        }

        int[] best = new int[n];

        for (int i = 1; i < n; i++) {
            best[sa[i]] = Math.max(best[sa[i]], lcp[i]);
            best[sa[i - 1]] = Math.max(best[sa[i - 1]], lcp[i]);
        }

        int ans = n;

        for (int i = 0; i < n; i++) {
            int len = best[i] + 1;

            if (i + len <= n) {
                ans = Math.min(ans, len);
            }
        }

        return ans;
    }
}
