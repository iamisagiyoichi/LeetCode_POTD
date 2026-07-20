// 1st Approach :- Extra 2D Matrix (Index Mapping)

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        k %= total;

        int[][] shifted = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int idx = i * n + j;
                int newIdx = (idx + k) % total;

                shifted[newIdx / n][newIdx % n] = grid[i][j];
            }
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(shifted[i][j]);
            }
            ans.add(row);
        }

        return ans;
    }
}

// 2nd Approach :- Flatten the Grid 

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        k %= total;

        int[] arr = new int[total];
        int idx = 0;

        for (int[] row : grid) {
            for (int val : row) {
                arr[idx++] = val;
            }
        }

        int[] shifted = new int[total];

        for (int i = 0; i < total; i++) {
            shifted[(i + k) % total] = arr[i];
        }

        List<List<Integer>> ans = new ArrayList<>();
        idx = 0;

        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(shifted[idx++]);
            }
            ans.add(row);
        }

        return ans;
    }
}

// Approach 3rd :- Direct Index Mapping

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        k %= total;

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                int idx = i * n + j;
                int oldIdx = (idx - k + total) % total;

                row.add(grid[oldIdx / n][oldIdx % n]);
            }

            ans.add(row);
        }

        return ans;
    }
}
