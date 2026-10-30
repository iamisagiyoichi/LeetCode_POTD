class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        long[][] dist = new long[n][k + 1];

        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();

            long d = curr[0];
            int u = (int) curr[1];
            int cnt = (int) curr[2];

            if (d != dist[u][cnt]) {
                continue;
            }

            if (u == n - 1) {
                return (int) d;
            }

            for (int[] edge : adj[u]) {
                int v = edge[0];
                int w = edge[1];

                int nextCnt = labels.charAt(u) == labels.charAt(v)
                    ? cnt + 1 : 1;

                if (nextCnt > k) {
                    continue;
                }

                long nd = d + w;

                if (nd < dist[v][nextCnt]) {
                    dist[v][nextCnt] = nd;
                    pq.offer(new long[]{nd, v, nextCnt});
                }
            }
        }

        return -1;
    }
}
