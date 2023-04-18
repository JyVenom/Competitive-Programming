import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class RepeatingSubstring4 {
    private static int ALPHABET_SZ = 123, N;
    private static int[] T;
    private static int[] lcp;
    private static int[] sa;
    private static int[] sa2;
    private static int[] rank;
    private static int[] c;

    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();

        T = ir.nextIntArr();
        N = T.length;
        sa = new int[N];
        sa2 = new int[N];
        rank = new int[N];
        c = new int[Math.max(ALPHABET_SZ, N)];
        construct();
        kasai();

        for (int i = 0; i < N; i++) {
            if (lcp[i] > 0) {
                int max = i++;
                for (; i < N; i++) {
                    if (lcp[i] > 0 && lcp[i] > lcp[max]) {
                        max = i;
                    }
                }
                System.out.print(new String(T, sa[max], lcp[max]));
                return;
            }
        }
        System.out.print("-1");
    }

    private static void construct() {
        int i, p, r;
        for (i = 0; i < N; ++i) c[rank[i] = T[i]]++;
        for (i = 1; i < ALPHABET_SZ; ++i) c[i] += c[i - 1];
        for (i = N - 1; i >= 0; --i) sa[--c[T[i]]] = i;
        for (p = 1; p < N; p <<= 1) {
            for (r = 0, i = N - p; i < N; ++i) sa2[r++] = i;
            for (i = 0; i < N; ++i) if (sa[i] >= p) sa2[r++] = sa[i] - p;
            Arrays.fill(c, 0, ALPHABET_SZ, 0);
            for (i = 0; i < N; ++i) c[rank[i]]++;
            for (i = 1; i < ALPHABET_SZ; ++i) c[i] += c[i - 1];
            for (i = N - 1; i >= 0; --i) sa[--c[rank[sa2[i]]]] = sa2[i];
            for (sa2[sa[0]] = r = 0, i = 1; i < N; ++i) {
                if (!(rank[sa[i - 1]] == rank[sa[i]]
                        && sa[i - 1] + p < N
                        && sa[i] + p < N
                        && rank[sa[i - 1] + p] == rank[sa[i] + p])) r++;
                sa2[sa[i]] = r;
            }
            int[] tmp = rank;
            rank = sa2;
            sa2 = tmp;
            if (r == N - 1) break;
            ALPHABET_SZ = r + 1;
        }
    }

    private static void kasai() {
        lcp = new int[N];
        int[] inv = new int[N];
        for (int i = 0; i < N; i++) inv[sa[i]] = i;
        for (int i = 0, len = 0; i < N; i++) {
            if (inv[i] > 0) {
                int k = sa[inv[i] - 1];
                while ((i + len < N) && (k + len < N) && T[i + len] == T[k + len]) len++;
                lcp[inv[i] - 1] = len;
                if (len > 0) len--;
            }
        }
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[100002];
        private int curChar;
        private int numChars;

        public InputReader() {
            this.stream = System.in;
        }

        private int read() throws IOException {
            if (curChar >= numChars) {
                curChar = -1;
                numChars = stream.read(buf);
            }
            return buf[++curChar];
        }

        private int[] nextIntArr() throws IOException {
            int c = read();
            while (!(c == ' ' || c == '\n' || c == -1)) {
                c = read();
            }
            int end = curChar;
            curChar = -1;
            int[] res = new int[end];
            for (int i = 0; i < end; i++) {
                res[i] = read();
            }
            return res;
        }
    }
}
