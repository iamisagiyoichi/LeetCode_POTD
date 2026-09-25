import java.util.*;

class Solution {
    Set<String> ans = new TreeSet<>();

    void dfs(String s) {
        int r = s.indexOf('}');

        if (r == -1) {
            ans.add(s);
            return;
        }

        int l = s.lastIndexOf('{', r);

        String left = s.substring(0, l);
        String right = s.substring(r + 1);
        String inside = s.substring(l + 1, r);

        String[] parts = inside.split(",");

        for (String part : parts) {
            dfs(left + part + right);
        }
    }

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        return new ArrayList<>(ans);
    }
}
