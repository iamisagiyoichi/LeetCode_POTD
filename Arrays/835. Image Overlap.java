class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int ans = 0;

        for(int dr = -n + 1; dr < n; dr++){
            for(int dc = -n + 1; dc < n; dc++){
                int cnt = 0;

                for(int i = 0; i < n; i++){
                    for(int j = 0; j < n; j++){
                        int x = i + dr;
                        int y = j + dc;

                        if(x >= 0 && x < n && y >= 0 && y < n){
                            cnt += img1[i][j] & img2[x][y];
                        }
                    }
                }

                ans = Math.max(ans, cnt);
            }
        }

        return ans;
    }
}
