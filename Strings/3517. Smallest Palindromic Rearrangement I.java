class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder firstHalf = new StringBuilder();
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < freq[i] / 2; j++) {
                firstHalf.append((char) ('a' + i));
            }
            if ((freq[i] & 1) == 1) {
                mid = (char) ('a' + i);
            }
        }

        String secondHalf = new StringBuilder(firstHalf).reverse().toString();

        if (mid != 0) {
            return firstHalf.toString() + mid + secondHalf;
        }

        return firstHalf.toString() + secondHalf;
    }
}
