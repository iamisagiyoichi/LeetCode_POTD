import java.util.*;

class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;

        boolean[][] vis = new boolean[m][n];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 || vis[i][j]) continue;

                long sum = 0;
                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[]{i, j});
                vis[i][j] = true;

                while (!q.isEmpty()) {
                    int[] cell = q.poll();
                    int x = cell[0], y = cell[1];
                    sum += grid[x][y];

                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if (nx >= 0 && nx < m && ny >= 0 && ny < n
                                && grid[nx][ny] > 0 && !vis[nx][ny]) {
                            vis[nx][ny] = true;
                            q.offer(new int[]{nx, ny});
                        }
                    }
                }

                if (sum % k == 0) ans++;
            }
        }

        return ans;
    }
}
