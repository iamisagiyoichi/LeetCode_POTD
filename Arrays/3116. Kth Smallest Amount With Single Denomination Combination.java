class Solution {
    long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public long findKthSmallest(int[] coins, int k) {

        Arrays.sort(coins);

        int[] temp = new int[coins.length];
        int size = 0;

        for (int c : coins) {
            boolean valid = true;

            for (int i = 0; i < size; i++) {
                if (c % temp[i] == 0) {
                    valid = false;
                    break;
                }
            }

            if (valid)
                temp[size++] = c;
        }

        int n = size;
        int[] A = Arrays.copyOf(temp, size);

        long low = k;
        long high = (long) A[0] * k;

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (check(A, n, mid, k))
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }

    boolean check(int[] A, int n, long mid, long k) {
        long total = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            long L = 1;
            int bits = 0;
            boolean valid = true;

            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) != 0) {
                    bits++;

                    L = lcm(L, A[j]);

                    if (L > mid) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid)
                continue;

            long cnt = mid / L;

            if ((bits & 1) == 1)
                total += cnt;
            else
                total -= cnt;
        }

        return total >= k;
    }
}
