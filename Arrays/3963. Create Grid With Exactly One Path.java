class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];

        for (int i = 0; i < m; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '#');
            row[0] = '.';
            grid[i] = new String(row);
        }

        char[] lastRow = new char[n];
        Arrays.fill(lastRow, '.');
        grid[m - 1] = new String(lastRow);

        return grid;
    }
}
