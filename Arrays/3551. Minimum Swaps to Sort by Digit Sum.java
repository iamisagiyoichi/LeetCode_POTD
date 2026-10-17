import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            int x = nums[i], sum = 0;

            while (x > 0) {
                sum += x % 10;
                x /= 10;
            }

            arr[i][0] = sum;
            arr[i][1] = nums[i];
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        Map<Integer, Integer> mp = new HashMap<>();

        for (int i = 0; i < n; i++) {
            mp.put(nums[i], i);
        }

        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[i] = mp.get(arr[i][1]);
        }

        boolean[] vis = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (vis[i] || pos[i] == i) {
                continue;
            }

            int cycle = 0, j = i;

            while (!vis[j]) {
                vis[j] = true;
                j = pos[j];
                cycle++;
            }

            swaps += cycle - 1;
        }

        return swaps;
    }
}
