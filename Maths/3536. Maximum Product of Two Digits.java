// Approach 1st :- Storing Digits and Checking all pairs

class Solution {
    public int maxProduct(int n) {
        int[] d = new int[10];
        int m = 0;

        while (n > 0) {
            d[m++] = n % 10;
            n /= 10;
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                ans = Math.max(ans, d[i] * d[j]);
            }
        }

        return ans;
    }
}

// Approach 2nd :- By Finding Largest 2 Digits

class Solution {
    public int maxProduct(int n) {
        int first = 0;
        int second = 0;

        while (n > 0) {
            int d = n % 10;

            if (d >= first) {
                second = first;
                first = d;
            } else if (d > second) {
                second = d;
            }

            n /= 10;
        }

        return first * second;
    }
}
