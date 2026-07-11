class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        boolean[] visited = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            visited[i] = true;
            int nodes = 0;
            int degreeSum = 0;
            while (!q.isEmpty()) {
                int cur = q.poll();
                nodes++;
                degreeSum += graph[cur].size();
                for (int next : graph[cur]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
            int edgeCount = degreeSum / 2;
            if (edgeCount == nodes * (nodes - 1) / 2)
                ans++;
        }
        return ans;
    }
}
