class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9L * num) return "";

        StringBuilder ans = new StringBuilder();

        while (num-- > 0) {
            int digit = Math.min(9, sum);
            ans.append(digit);
            sum -= digit;
        }

        return ans.toString();
    }
}
