class Solution {
    TreeMap<Integer, Integer> lo = new TreeMap<>();
    TreeMap<Integer, Integer> hi = new TreeMap<>();

    long sumLo = 0, sumHi = 0;
    int sizeLo = 0, sizeHi = 0;

    void addLo(int val) {
        lo.put(val, lo.getOrDefault(val, 0) + 1);
        sumLo += val;
        sizeLo++;
    }

    void addHi(int val) {
        hi.put(val, hi.getOrDefault(val, 0) + 1);
        sumHi += val;
        sizeHi++;
    }

    void removeLo(int val) {
        int count = lo.get(val);
        if (count == 1) lo.remove(val);
        else lo.put(val, count - 1);
        sumLo -= val;
        sizeLo--;
    }

    void removeHi(int val) {
        int count = hi.get(val);
        if (count == 1) hi.remove(val);
        else hi.put(val, count - 1);
        sumHi -= val;
        sizeHi--;
    }

    void balance() {
        while (sizeLo > sizeHi + 1) {
            int val = lo.lastKey();
            removeLo(val);
            addHi(val);
        }

        while (sizeLo < sizeHi) {
            int val = hi.firstKey();
            removeHi(val);
            addLo(val);
        }
    }

    void add(int val) {
        if (lo.isEmpty() || val <= lo.lastKey()) addLo(val);
        else addHi(val);
        balance();
    }

    void remove(int val) {
        if (lo.containsKey(val)) removeLo(val);
        else removeHi(val);
        balance();
    }

    long getCost() {
        long median = lo.lastKey();
        return median * sizeLo - sumLo + sumHi - median * sizeHi;
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int m = n - x + 1;

        long[] cost = new long[m];

        lo.clear();
        hi.clear();
        sumLo = sumHi = 0;
        sizeLo = sizeHi = 0;

        for (int i = 0; i < x; i++) add(nums[i]);

        cost[0] = getCost();

        for (int i = x; i < n; i++) {
            remove(nums[i - x]);
            add(nums[i]);
            cost[i - x + 1] = getCost();
        }

        long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            java.util.Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                curr[i] = curr[i - 1];

                if (i >= x && prev[i - x] != INF) {
                    curr[i] = Math.min(curr[i], prev[i - x] + cost[i - x]);
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}
