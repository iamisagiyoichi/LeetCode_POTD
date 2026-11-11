class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long both = Math.min(need1, need2);

        long cost = both * Math.min(costBoth, cost1 + cost2);

        if (costBoth < cost1 + cost2) {
            cost += (need1 - both) * Math.min(costBoth, cost1);
            cost += (need2 - both) * Math.min(costBoth, cost2);
        } else {
            cost += (need1 - both) * cost1;
            cost += (need2 - both) * cost2;
        }

        return cost;
    }
}
