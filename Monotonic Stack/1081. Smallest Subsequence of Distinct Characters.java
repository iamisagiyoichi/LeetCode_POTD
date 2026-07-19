// Approach 1st :- Monotonic Stack + Last Occurence

class Solution {
    public String smallestSubsequence(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        boolean[] vis = new boolean[26];
        StringBuilder st = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (vis[c - 'a']) continue;

            while (st.length() > 0 &&
                   st.charAt(st.length() - 1) > c &&
                   last[st.charAt(st.length() - 1) - 'a'] > i) {
                vis[st.charAt(st.length() - 1) - 'a'] = false;
                st.deleteCharAt(st.length() - 1);
            }

            st.append(c);
            vis[c - 'a'] = true;
        }

        return st.toString();
    }
}

// Approach 2nd :- Greedy Recursive

class Solution {
    public String smallestSubsequence(String s) {
        if (s.length() == 0) return "";

        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;

        int pos = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            cnt[s.charAt(i) - 'a']--;
            if (cnt[s.charAt(i) - 'a'] == 0) break;
        }

        char ch = s.charAt(pos);
        String rem = s.substring(pos + 1).replace(String.valueOf(ch), "");
        return ch + smallestSubsequence(rem);
    }
}
