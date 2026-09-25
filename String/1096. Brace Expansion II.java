import java.util.*;

class Solution {
    int idx = 0;

    Set<String> parse(String s) {
        Set<String> res = new TreeSet<>();
        Set<String> cur = new TreeSet<>();
        cur.add("");

        while (idx < s.length() && s.charAt(idx) != '}') {
            if (s.charAt(idx) == ',') {
                idx++;

                Set<String> next = parse(s);

                res.addAll(cur);
                res.addAll(next);

                cur.clear();
                cur.add("");
            } else {
                Set<String> part = new TreeSet<>();

                if (s.charAt(idx) == '{') {
                    idx++;
                    part = parse(s);
                    idx++;
                } else {
                    part.add(String.valueOf(s.charAt(idx)));
                    idx++;
                }

                Set<String> next = new TreeSet<>();

                for (String a : cur) {
                    for (String b : part) {
                        next.add(a + b);
                    }
                }

                cur = next;
            }
        }

        res.addAll(cur);
        return res;
    }

    public List<String> braceExpansionII(String expression) {
        idx = 0;
        Set<String> ans = parse(expression);
        return new ArrayList<>(ans);
    }
}
