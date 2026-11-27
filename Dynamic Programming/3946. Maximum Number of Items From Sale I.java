class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = 1;
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    w[i]++;
                }
            }
        }

        Integer[] id = new Integer[n];
        for (int i = 0; i < n; i++) id[i] = i;

        Arrays.sort(id, (a, b) -> Integer.compare(items[b][1], items[a][1]));

        int[] dp = new int[budget + 1];
        int ans = 0;

        int k = 0;

        while (k < n) {
            int p = items[id[k]][1];
            int[] ndp = dp.clone();
            int[] yes = new int[budget + 1];
            Arrays.fill(yes, -1000000000);

            while (k < n && items[id[k]][1] == p) {
                int i = id[k++];

                for (int b = budget; b >= items[i][1]; b--) {
                    yes[b] = Math.max(yes[b], yes[b - items[i][1]] + w[i]);
                    yes[b] = Math.max(yes[b], dp[b - items[i][1]] + w[i]);
                }
            }

            for (int b = 0; b <= budget; b++) {
                if (yes[b] >= 0) {
                    ans = Math.max(ans, yes[b] + (budget - b) / p);
                    ndp[b] = Math.max(ndp[b], yes[b]);
                }
            }

            dp = ndp;
        }

        return ans;
    }
}
