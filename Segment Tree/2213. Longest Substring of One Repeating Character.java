class Solution {
    static class Node {
        int len, pref, suff, best;
        char lc, rc;

        Node() {}

        Node(char c) {
            len = 1;
            pref = 1;
            suff = 1;
            best = 1;
            lc = c;
            rc = c;
        }
    }

    Node[] tree;
    String s;

    Node merge(Node a, Node b) {
        if (a.len == 0) return b;
        if (b.len == 0) return a;

        Node res = new Node();

        res.len = a.len + b.len;
        res.lc = a.lc;
        res.rc = b.rc;
        res.pref = a.pref;
        res.suff = b.suff;
        res.best = Math.max(a.best, b.best);

        if (a.rc == b.lc) {
            res.best = Math.max(res.best, a.suff + b.pref);

            if (a.pref == a.len) {
                res.pref = a.len + b.pref;
            }

            if (b.suff == b.len) {
                res.suff = b.len + a.suff;
            }
        }

        return res;
    }

    void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = new Node(s.charAt(l));
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int l, int r, int idx, char c) {
        if (l == r) {
            tree[node] = new Node(c);
            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid) {
            update(node * 2, l, mid, idx, c);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, c);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        this.s = s;

        int n = s.length();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);

            update(1, 0, n - 1, idx, c);

            ans[i] = tree[1].best;
        }

        return ans;
    }
}
