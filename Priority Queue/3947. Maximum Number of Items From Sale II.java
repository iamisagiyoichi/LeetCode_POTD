class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length, cheapest = Integer.MAX_VALUE;

        int[] have = new int[n + 1];

        for (int[] item : items) {
            have[item[0]]++;
            cheapest = Math.min(cheapest, item[1]);
        }

        int[] deals = new int[n + 1];
        Arrays.fill(deals, -1);

        PriorityQueue<int[]> heap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        for (int[] item : items) {
            if (item[1] < 2L * cheapest) {
                heap.offer(new int[]{item[1], item[0]});
            }
        }

        int copies = 0;

        while (!heap.isEmpty() && heap.peek()[0] <= budget) {
            int[] cur = heap.poll();

            int price = cur[0];
            int factor = cur[1];

            if (deals[factor] < 0) {
                for (int multiple = factor; multiple <= n; multiple += factor) {
                    deals[factor] += have[multiple];
                }
            }

            int pairs = (int) Math.min(deals[factor], budget / price);

            copies += 2 * pairs;
            budget -= pairs * price;
        }

        return copies + budget / cheapest;
    }
}
