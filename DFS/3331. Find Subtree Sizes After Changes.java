class Solution {
    int[] last, np, ans;
    char[] s;
    List<Integer>[] g, ng;

    void dfs(int u) {
        int c = s[u] - 'a';
        int old = last[c];

        if (old != -1) np[u] = old;

        last[c] = u;

        for (int v : g[u]) dfs(v);

        last[c] = old;
    }

    int calc(int u) {
        int sz = 1;
        for (int v : ng[u]) sz += calc(v);
        return ans[u] = sz;
    }

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        this.s = s.toCharArray();
        np = parent.clone();
        ans = new int[n];
        last = new int[26];

        Arrays.fill(last, -1);

        g = new ArrayList[n];
        ng = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            ng[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++)
            g[parent[i]].add(i);

        dfs(0);

        for (int i = 1; i < n; i++)
            ng[np[i]].add(i);

        calc(0);
        return ans;
    }
}
