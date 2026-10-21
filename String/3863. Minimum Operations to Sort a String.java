class Solution {
    public int minOperations(String s) {
        int n = s.length();

        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                sorted = false;
                break;
            }
        }

        if (sorted) return 0;
        if (n == 2) return -1;

        char min_p = 'z', max_p = 'a';

        for (int i = 1; i < n - 1; i++) {
            min_p = (char) Math.min(min_p, s.charAt(i));
            max_p = (char) Math.max(max_p, s.charAt(i));
        }

        if (s.charAt(0) <= s.charAt(n - 1) &&
            (min_p >= s.charAt(0) || max_p <= s.charAt(n - 1)))
            return 1;

        return (s.charAt(n - 1) < min_p && s.charAt(0) > max_p) ? 3 : 2;
    }
}
