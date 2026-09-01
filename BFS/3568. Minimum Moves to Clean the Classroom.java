class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0;
        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                }

                if (ch == 'L')
                    litterCount++;
            }
        }

        int[][] id = new int[m][n];
        for (int i = 0; i < m; i++)
            java.util.Arrays.fill(id[i], -1);

        int idx = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (classroom[i].charAt(j) == 'L')
                    id[i][j] = idx++;
            }
        }

        int masks = 1 << litterCount;
        int full = masks - 1;

        int totalStates = m * n * masks * (energy + 1);
        boolean[] vis = new boolean[totalStates];

        java.util.function.IntFunction<Integer> encode = x -> x;

        int start = (((sr * n + sc) * masks) * (energy + 1)) + energy;

        int[] qr = new int[totalStates];
        int[] qc = new int[totalStates];
        int[] qm = new int[totalStates];
        int[] qe = new int[totalStates];

        int head = 0, tail = 0;

        qr[tail] = sr;
        qc[tail] = sc;
        qm[tail] = 0;
        qe[tail++] = energy;

        vis[start] = true;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        int moves = 0;

        while (head < tail) {
            int size = tail - head;

            while (size-- > 0) {
                int r = qr[head];
                int c = qc[head];
                int mask = qm[head];
                int e = qe[head++];

                if (mask == full)
                    return moves;

                if (e == 0)
                    continue;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                        continue;

                    if (classroom[nr].charAt(nc) == 'X')
                        continue;

                    int ne = e - 1;
                    int nmask = mask;

                    if (id[nr][nc] != -1)
                        nmask |= 1 << id[nr][nc];

                    if (classroom[nr].charAt(nc) == 'R')
                        ne = energy;

                    int code = (((nr * n + nc) * masks + nmask) * (energy + 1)) + ne;

                    if (!vis[code]) {
                        vis[code] = true;

                        qr[tail] = nr;
                        qc[tail] = nc;
                        qm[tail] = nmask;
                        qe[tail++] = ne;
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}
