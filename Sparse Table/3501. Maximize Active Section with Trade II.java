import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

class Solution {
    private int[] a, b, c;
    private int d;
    private List<int[]> e;

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int f = (int) s.chars().filter(g -> g == '1').count();

        List<Integer> h = new ArrayList<>(), i = new ArrayList<>();
        Matcher j = Pattern.compile("0+").matcher(s);
        while (j.find()) {
            h.add(j.start());
            i.add(j.end() - 1);
        }

        a = h.stream().mapToInt(Integer::intValue).toArray();
        b = i.stream().mapToInt(Integer::intValue).toArray();
        d = a.length;

        c = IntStream.range(0, d - 1)
                .map(k -> (b[k] - a[k] + 1) + (b[k + 1] - a[k + 1] + 1))
                .toArray();

        int l = c.length;
        e = new ArrayList<>();
        e.add(c);

        for (int m = 1; m * 2 <= l; m *= 2) {
            int[] n = e.get(e.size() - 1);
            int[] o = new int[n.length - m];
            for (int p = 0; p < o.length; p++)
                o[p] = Math.max(n[p], n[p + m]);
            e.add(o);
        }

        List<Integer> q = new ArrayList<>(queries.length);
        for (int[] r : queries)
            q.add(f + u(r[0], r[1]));

        return q;
    }

    private int t(int f, int g) {
        int h = 31 - Integer.numberOfLeadingZeros(g - f + 1);
        return Math.max(e.get(h)[f], e.get(h)[g - (1 << h) + 1]);
    }

    private int v(int f, int g, int h) {
        return c[f] - Math.max(0, g - a[f]) - Math.max(0, b[f + 1] - h);
    }

    private int u(int f, int g) {
        if (d < 2)
            return 0;

        int h = w(b, f);
        int i = x(a, g) - 2;

        if (h > i)
            return 0;

        return Math.max(
                Math.max(v(h, f, g), v(i, f, g)),
                i - h >= 2 ? t(h + 1, i - 1) : 0
        );
    }

    private static int w(int[] a, int b) {
        int c = 0, d = a.length;
        while (c < d) {
            int e = (c + d) >>> 1;
            if (a[e] < b)
                c = e + 1;
            else
                d = e;
        }
        return c;
    }

    private static int x(int[] a, int b) {
        int c = 0, d = a.length;
        while (c < d) {
            int e = (c + d) >>> 1;
            if (a[e] <= b)
                c = e + 1;
            else
                d = e;
        }
        return c;
    }
}
