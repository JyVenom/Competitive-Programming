import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class P1388D8 {
    static int N;
    static long[] a;
    static int[] b;
    static int[] indegree;
    static Queue<Integer> q = new LinkedList<>();
    static Queue<Integer> first = new LinkedList<>();
    static Stack<Integer> last = new Stack<>();
    static int cur;
    static long ans;
    static INPUT in = new INPUT(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        N = in.iscan();
        a = new long[N + 1];
        b = new int[N + 1];
        indegree = new int[N + 1];
        for (int i = 1; i <= N; i++) a[i] = in.lscan();
        for (int i = 1; i <= N; i++) {
            b[i] = in.iscan();
            if (b[i] != -1) {
                indegree[b[i]]++;
            }
        }
        for (int i = 1; i <= N; i++) if (indegree[i] == 0) q.add(i);
        while (!q.isEmpty()) {
            cur = q.poll();
            ans += a[cur];
            if (a[cur] >= 0) {
                first.add(cur);
                if (b[cur] != -1) a[b[cur]] += a[cur];
            } else last.push(cur);
            if (b[cur] == -1) continue;
            indegree[b[cur]]--;
            if (indegree[b[cur]] > 0) continue;
            q.add(b[cur]);
        }
        out.println(ans);
        while (!first.isEmpty()) out.print(first.poll() + " ");
        while (!last.isEmpty()) out.print(last.pop() + " ");
        out.println();
        out.close();
    }

    private static class INPUT {

        private final InputStream stream;
        private final byte[] buf = new byte[1 << 16];
        private int curChar, numChars;

        public INPUT(InputStream stream) {
            this.stream = stream;
        }

        public int cscan() throws IOException {
            if (curChar >= numChars) {
                curChar = 0;
                numChars = stream.read(buf);
            }

            if (numChars == -1)
                return numChars;

            return buf[curChar++];
        }

        public int iscan() throws IOException {
            int c = cscan(), sgn = 1;

            while (space(c))
                c = cscan();

            if (c == '-') {
                sgn = -1;
                c = cscan();
            }

            int res = 0;

            do {
                res = (res << 1) + (res << 3);
                res += c - '0';
                c = cscan();
            }
            while (!space(c));

            return res * sgn;
        }

        public long lscan() throws IOException {
            int c = cscan(), sgn = 1;

            while (space(c))
                c = cscan();

            if (c == '-') {
                sgn = -1;
                c = cscan();
            }

            long res = 0;

            do {
                res = (res << 1) + (res << 3);
                res += c - '0';
                c = cscan();
            }
            while (!space(c));

            return res * sgn;
        }

        public boolean space(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}
