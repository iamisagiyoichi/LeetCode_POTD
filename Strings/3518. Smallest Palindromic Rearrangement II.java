class Solution {
    long LIM = 1000000L;

    long ways(int[] cnt) {
        int n = 0;
        for (int x : cnt) n += x;

        long res = 1;
        int rem = n;

        for (int c : cnt) {
            if (c == 0) continue;
            long cur = 1;
            for (int i = 1; i <= c; i++) {
                cur = cur * (rem - c + i) / i;
                if (cur > LIM) cur = LIM;
            }
            res *= cur;
            if (res > LIM) res = LIM;
            rem -= c;
        }
        return res;
    }

    public String smallestPalindrome(String s, int k) {
        int[] f = new int[26];
        for (char ch : s.toCharArray()) f[ch - 'a']++;

        int[] cnt = new int[26];
        String mid = "";

        for (int i = 0; i < 26; i++) {
            cnt[i] = f[i] / 2;
            if ((f[i] & 1) == 1) mid = String.valueOf((char) ('a' + i));
        }

        if (ways(cnt) < k) return "";

        int len = s.length() / 2;
        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < len; pos++) {
            for (int c = 0; c < 26; c++) {
                if (cnt[c] == 0) continue;
                cnt[c]--;
                long w = ways(cnt);
                if (w >= k) {
                    left.append((char) ('a' + c));
                    break;
                }
                k -= w;
                cnt[c]++;
            }
        }

        String right = new StringBuilder(left).reverse().toString();
        return left.toString() + mid + right;
    }
}
