// 1st Approach :- Sieve of Multiples + Inclusion Exclusion

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {

        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        int[] cnt = new int[max + 1];
        for (int d = 1; d <= max; d++) {
            for (int j = d; j <= max; j += d) {
                cnt[d] += freq[j];
            }
        }

        long[] exact = new long[max + 1];
        for (int d = max; d >= 1; d--) {
            exact[d] = (long) cnt[d] * (cnt[d] - 1) / 2;
            for (int j = d + d; j <= max; j += d) {
                exact[d] -= exact[j];
            }
        }

        long[] prefix = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + exact[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long k = queries[i] + 1;
            int l = 1, r = max;

            while (l < r) {
                int mid = (l + r) / 2;
                if (prefix[mid] >= k)
                    r = mid;
                else
                    l = mid + 1;
            }
            ans[i] = l;
        }

        return ans;
    }
}

// 2nd Approach :- Divisor Enumeration + Inclusion Exclusion

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {

        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] cnt = new int[max + 1];

        for (int x : nums) {
            for (int d = 1; d * d <= x; d++) {
                if (x % d == 0) {
                    cnt[d]++;
                    if (d != x / d)
                        cnt[x / d]++;
                }
            }
        }

        long[] exact = new long[max + 1];

        for (int d = max; d >= 1; d--) {
            exact[d] = (long) cnt[d] * (cnt[d] - 1) / 2;
            for (int j = d + d; j <= max; j += d) {
                exact[d] -= exact[j];
            }
        }

        long[] prefix = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + exact[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long k = queries[i] + 1;
            int l = 1, r = max;

            while (l < r) {
                int mid = (l + r) / 2;
                if (prefix[mid] >= k)
                    r = mid;
                else
                    l = mid + 1;
            }
            ans[i] = l;
        }

        return ans;
    }
}
