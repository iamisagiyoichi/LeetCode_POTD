// 1st Approach (Mathematical Observation of Coprime)

class Solution {
    public int gcdOfOddEvenSums(int n) {
        return n;
    }
}

// 2nd Approach (Using Mathematical Sum Formula with GCD)

class Solution {
    public int gcdOfOddEvenSums(int n) {
        int sumOdd = n * n;
        int sumEven = n * (n + 1);

        return gcd(sumOdd, sumEven);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}

// 3rd Approach (Computing Sums + Euclid GCD)

class Solution {
    public int gcdOfOddEvenSums(int n) {
        int sumOdd = 0;
        int sumEven = 0;

        for (int i = 1; i <= n; i++) {
            sumOdd += (2 * i - 1);
            sumEven += (2 * i);
        }

        return gcd(sumOdd, sumEven);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
