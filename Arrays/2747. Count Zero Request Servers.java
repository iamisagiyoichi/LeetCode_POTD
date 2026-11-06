class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = logs.length, q = queries.length;
        
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int[][] sortedQueries = new int[q][2];
        for (int i = 0; i < q; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] ans = new int[q];
        int[] active = new int[n + 1];
        int left = 0, right = 0, count = 0;
        
        for (int[] query : sortedQueries) {
            int time = query[0];
            int idx = query[1];
            
            while (right < m && logs[right][1] <= time) {
                int server = logs[right][0];
                if (active[server]++ == 0) count++;
                right++;
            }
            
            while (left < right && logs[left][1] < time - x) {
                int server = logs[left][0];
                if (--active[server] == 0) count--;
                left++;
            }
            
            ans[idx] = n - count;
        }
        
        return ans;
    }
}
