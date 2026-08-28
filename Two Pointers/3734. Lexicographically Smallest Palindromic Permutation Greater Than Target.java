class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int odd = 0;
        int mid = -1;

        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 == 1) {
                odd++;
                mid = i;
            }
        }

        if (odd > 1) return "";

        int m = n / 2;
        int[] half = new int[26];

        for (int i = 0; i < 26; i++) {
            half[i] = cnt[i] / 2;
        }

        String p = target.substring(0, m);

        int[] used = new int[26];
        boolean possible = true;

        for (char c : p.toCharArray()) {
            used[c - 'a']++;
            if (used[c - 'a'] > half[c - 'a']) {
                possible = false;
                break;
            }
        }

        if (possible) {
            boolean exact = true;

            for (int i = 0; i < 26; i++) {
                if (used[i] != half[i]) {
                    exact = false;
                    break;
                }
            }

            if (exact) {
                StringBuilder ans = new StringBuilder(p);

                if (mid != -1) {
                    ans.append((char) ('a' + mid));
                }

                ans.append(new StringBuilder(p).reverse());

                if (ans.toString().compareTo(target) > 0) {
                    return ans.toString();
                }
            }
        }

        for (int pos = m - 1; pos >= 0; pos--) {
            int[] pref = new int[26];
            boolean ok = true;

            for (int i = 0; i < pos; i++) {
                pref[p.charAt(i) - 'a']++;

                if (pref[p.charAt(i) - 'a'] > half[p.charAt(i) - 'a']) {
                    ok = false;
                    break;
                }
            }

            if (!ok) continue;

            for (int c = p.charAt(pos) - 'a' + 1; c < 26; c++) {
                if (pref[c] >= half[c]) continue;

                int[] rem = half.clone();

                for (int j = 0; j < 26; j++) {
                    rem[j] -= pref[j];
                }

                rem[c]--;

                if (rem[c] < 0) continue;

                StringBuilder h = new StringBuilder(p.substring(0, pos));
                h.append((char) ('a' + c));

                for (int j = 0; j < 26; j++) {
                    for (int k = 0; k < rem[j]; k++) {
                        h.append((char) ('a' + j));
                    }
                }

                StringBuilder ans = new StringBuilder(h);

                if (mid != -1) {
                    ans.append((char) ('a' + mid));
                }

                ans.append(new StringBuilder(h).reverse());

                return ans.toString();
            }
        }

        return "";
    }
}
