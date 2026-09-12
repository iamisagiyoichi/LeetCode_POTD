import java.util.*;

class Solution {

    static class Node {
        int l, r, w, idx;

        Node(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Node[] a = new Node[n];

        for (int i = 0; i < n; i++) {
            a[i] = new Node(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        Arrays.sort(a, (x, y) -> {
            if (x.r != y.r) return Integer.compare(x.r, y.r);
            return Integer.compare(x.idx, y.idx);
        });

        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            ends[i] = a[i].r;
        }

        int[] prev = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int left = a[i - 1].l;
            int lo = 0, hi = i - 1;

            while (lo < hi) {
                int mid = (lo + hi + 1) >>> 1;

                if (ends[mid - 1] < left) {
                    lo = mid;
                } else {
                    hi = mid - 1;
                }
            }

            prev[i] = lo;
        }

        long[][] dp = new long[n + 1][5];
        int[][][] best = new int[n + 1][5][];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                best[i][k] = new int[0];
            }
        }

        for (int i = 1; i <= n; i++) {
            int w = a[i - 1].w;
            int idx = a[i - 1].idx;

            for (int k = 1; k <= 4; k++) {
                dp[i][k] = dp[i - 1][k];
                best[i][k] = best[i - 1][k];

                int p = prev[i];
                long takeScore = dp[p][k - 1] + w;
                int[] takeIds = add(best[p][k - 1], idx);

                if (takeScore > dp[i][k] ||
                    (takeScore == dp[i][k] &&
                     compare(takeIds, best[i][k]) < 0)) {
                    dp[i][k] = takeScore;
                    best[i][k] = takeIds;
                }
            }
        }

        return best[n][4];
    }

    static int[] add(int[] arr, int idx) {
        int[] res = new int[arr.length + 1];
        int pos = 0;

        while (pos < arr.length && arr[pos] < idx) {
            pos++;
        }

        for (int i = 0; i < pos; i++) {
            res[i] = arr[i];
        }

        res[pos] = idx;

        for (int i = pos; i < arr.length; i++) {
            res[i + 1] = arr[i];
        }

        return res;
    }

    static int compare(int[] a, int[] b) {
        int n = Math.min(a.length, b.length);

        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }

        return Integer.compare(a.length, b.length);
    }
}
