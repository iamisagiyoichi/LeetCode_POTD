class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;

        for (int x : nums1) {
            if ((x & 1) == 0) {
                minEven = Math.min(minEven, x);
            } else {
                minOdd = Math.min(minOdd, x);
            }
        }

        if (minEven == Integer.MAX_VALUE) {
            return true;
        }

        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }

        return minOdd < minEven;
    }
}
