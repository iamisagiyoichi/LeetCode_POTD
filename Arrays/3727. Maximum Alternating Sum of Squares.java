import java.util.*;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] a = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            a[i] = 1L * nums[i] * nums[i];
        }

        Arrays.sort(a);

        int n = a.length;
        int h = n / 2;
        long sum = 0, small = 0;

        for (int i = 0; i < n; i++) {
            sum += a[i];
            if (i < h) small += a[i];
        }

        return sum - 2 * small;
    }
}
