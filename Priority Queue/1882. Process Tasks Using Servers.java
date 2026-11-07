
class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length, m = tasks.length;
        int[] ans = new int[m];

        PriorityQueue<int[]> freeServers = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]
        );

        PriorityQueue<long[]> busyServers = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }

        long time = 0;

        for (int i = 0; i < m; i++) {
            time = Math.max(time, i);

            while (!busyServers.isEmpty() && busyServers.peek()[0] <= time) {
                long[] server = busyServers.poll();
                freeServers.offer(new int[]{(int)server[1], (int)server[2]});
            }

            if (freeServers.isEmpty()) {
                time = busyServers.peek()[0];

                while (!busyServers.isEmpty() && busyServers.peek()[0] <= time) {
                    long[] server = busyServers.poll();
                    freeServers.offer(new int[]{(int)server[1], (int)server[2]});
                }
            }

            int[] server = freeServers.poll();
            ans[i] = server[1];

            busyServers.offer(new long[]{time + tasks[i], server[0], server[1]});
        }

        return ans;
    }
}
