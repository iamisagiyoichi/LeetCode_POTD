class Solution {
    class Tree {
        int[] parent, tin, tout, order;
        int[][] first;
        int[][] adj;
        int LOG;

        Tree(int n, int[][] edges) {
            parent = new int[n];
            tin = new int[n];
            tout = new int[n];
            order = new int[n];

            int[] degree = new int[n];

            for (int[] e : edges) {
                degree[e[0]]++;
                degree[e[1]]++;
            }

            adj = new int[n][];

            for (int i = 0; i < n; i++) {
                adj[i] = new int[degree[i]];
            }

            Arrays.fill(degree, 0);

            for (int[] e : edges) {
                adj[e[0]][degree[e[0]]++] = e[1];
                adj[e[1]][degree[e[1]]++] = e[0];
            }

            int[] stack = new int[2 * n];
            int top = 0;
            int timer = 0;

            stack[top++] = 0;

            while (top > 0) {
                int x = stack[--top];

                if (x < 0) {
                    tout[~x] = timer;
                    continue;
                }

                tin[x] = timer;
                order[timer++] = x;

                stack[top++] = ~x;

                for (int y : adj[x]) {
                    if (y != parent[x]) {
                        parent[y] = x;
                        stack[top++] = y;
                    }
                }
            }

            LOG = 1;

            while ((1 << LOG) <= n) {
                LOG++;
            }

            first = new int[LOG][];
            first[0] = new int[n];

            for (int i = 0; i < n; i++) {
                first[0][i] = tin[parent[order[i]]];
            }

            for (int k = 1; k < LOG; k++) {
                int len = 1 << k;
                first[k] = new int[n - len + 1];

                for (int i = 0; i + len <= n; i++) {
                    first[k][i] = Math.min(
                        first[k - 1][i],
                        first[k - 1][i + (len >> 1)]
                    );
                }
            }
        }

        int lca(int u, int v) {
            if (u == v) {
                return u;
            }

            int l = Math.min(tin[u], tin[v]);
            int r = Math.max(tin[u], tin[v]);

            int k = 31 - Integer.numberOfLeadingZeros(r - l);

            return order[Math.min(
                first[k][l + 1],
                first[k][r - (1 << k) + 1]
            )];
        }
    }

    class RootPaths {
        Tree tree;
        int[] start, fenwick;
        int n;

        RootPaths(Tree tree, int[] bit) {
            this.tree = tree;
            this.n = bit.length;
            this.start = bit.clone();
            this.fenwick = new int[n + 1];

            for (int x : tree.order) {
                if (x != tree.parent[x]) {
                    start[x] ^= start[tree.parent[x]];
                }
            }
        }

        void flip(int x, int d) {
            for (int idx : new int[]{tree.tin[x] + 1, tree.tout[x] + 1}) {
                for (int i = idx; i < fenwick.length; i += i & -i) {
                    fenwick[i] ^= d;
                }
            }
        }

        int get(int x) {
            int mask = start[x];

            for (int i = tree.tin[x] + 1; i > 0; i -= i & -i) {
                mask ^= fenwick[i];
            }

            return mask;
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        Tree tree = new Tree(n, edges);

        int[] bit = new int[n];

        for (int x = 0; x < n; x++) {
            bit[x] = 1 << (s.charAt(x) - 'a');
        }

        RootPaths paths = new RootPaths(tree, bit);

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            if (q.charAt(0) == 'u') {
                int x = number(q, 7);
                int newBit = 1 << (q.charAt(q.length() - 1) - 'a');

                paths.flip(x, bit[x] ^ newBit);
                bit[x] = newBit;
            } else {
                int u = number(q, 6);
                int v = number(q, q.lastIndexOf(' ') + 1);

                int mask = paths.get(u) ^ paths.get(v) ^ bit[tree.lca(u, v)];

                ans.add(Integer.bitCount(mask) <= 1);
            }
        }

        return ans;
    }

    private int number(String q, int i) {
        int x = 0;

        while (i < q.length() && Character.isDigit(q.charAt(i))) {
            x = x * 10 + q.charAt(i) - '0';
            i++;
        }

        return x;
    }
}
