
class Solution {
    static final int MOD = 1_000_000_007;

    class SegmentTree {
        int n;
        long[] sum, sq, lazy;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            sq = new long[4 * n];
            lazy = new long[4 * n];
        }

        void apply(int node, int l, int r, long v) {
            long len = r - l + 1;
            sq[node] = (sq[node] + 2 * v % MOD * sum[node] % MOD
                    + len * v % MOD * v % MOD) % MOD;
            sum[node] = (sum[node] + len * v) % MOD;
            lazy[node] += v;
        }

        void push(int node, int l, int r) {
            if (lazy[node] == 0 || l == r) return;
            int mid = (l + r) / 2;
            apply(node * 2, l, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }

        void update(int node, int l, int r, int ql, int qr, int v) {
            if (ql > r || qr < l) return;
            if (ql <= l && r <= qr) {
                apply(node, l, r, v);
                return;
            }
            push(node, l, r);
            int mid = (l + r) / 2;
            update(node * 2, l, mid, ql, qr, v);
            update(node * 2 + 1, mid + 1, r, ql, qr, v);
            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
            sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
        }

        void update(int l, int r, int v) {
            if (l <= r) update(1, 0, n - 1, l, r, v);
        }

        long query() {
            return sq[1];
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n);
        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);
        long ans = 0;

        for (int i = 0; i < n; i++) {
            st.update(last[nums[i]] + 1, i, 1);
            last[nums[i]] = i;
            ans = (ans + st.query()) % MOD;
        }

        return (int) ans;
    }
}
