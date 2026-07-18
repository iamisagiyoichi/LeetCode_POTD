// 1st Approach :- finding Min and Max then applying the Euclidean GCD
class Solution {
    public int findGCD(int[] nums) {
        int min = nums[0], max = nums[0];

        for (int x : nums) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }

        return gcd(min, max);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}

// 2nd Approach :- Bruteforce Checking all Common Divisors

class Solution {
    public int findGCD(int[] nums) {
        int min = nums[0], max = nums[0];

        for (int x : nums) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }

        int ans = 1;

        for (int i = 1; i <= min; i++) {
            if (min % i == 0 && max % i == 0) {
                ans = i;
            }
        }

        return ans;
    }
}
