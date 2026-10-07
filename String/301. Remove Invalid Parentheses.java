class Solution {
    List<String> ans = new ArrayList<>();

    boolean isValid(String s) {
        int cnt = 0;

        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                cnt++;
            } else if(ch == ')') {
                cnt--;
                if(cnt < 0)
                    return false;
            }
        }

        return cnt == 0;
    }

    void solve(String s, int start, int left, int right) {
        if(left == 0 && right == 0) {
            if(isValid(s))
                ans.add(s);
            return;
        }

        for(int i = start; i < s.length(); i++) {
            if(i > start && s.charAt(i) == s.charAt(i - 1))
                continue;

            char ch = s.charAt(i);

            if(ch != '(' && ch != ')')
                continue;

            if(ch == '(' && left > 0) {
                solve(
                    s.substring(0, i) + s.substring(i + 1),
                    i,
                    left - 1,
                    right
                );
            }

            if(ch == ')' && right > 0) {
                solve(
                    s.substring(0, i) + s.substring(i + 1),
                    i,
                    left,
                    right - 1
                );
            }
        }
    }

    public List<String> removeInvalidParentheses(String s) {
        int left = 0;
        int right = 0;

        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                left++;
            } else if(ch == ')') {
                if(left > 0)
                    left--;
                else
                    right++;
            }
        }

        solve(s, 0, left, right);

        return ans;
    }
}
