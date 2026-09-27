import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] match = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int j = stack.pop();
                match[i] = j;
                match[j] = i;
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0, d = 1; i < n; i += d) {
            char c = s.charAt(i);
            if (c == '(' || c == ')') {
                i = match[i];
                d = -d;
            } else {
                res.append(c);
            }
        }

        return res.toString();
    }
}
