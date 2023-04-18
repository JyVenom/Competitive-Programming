import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;

public class P535D3 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        int[] p = ir.nextIntArr();

        SuffixArray sa = new SuffixArray(p);
        boolean[] pos = sa.findPos(); // Lookup table for if an offset of i matches itself
        int count = n; // Number of spots empty
        int prev = -1 * p.length;
        for (int i = 0; i < m; i++) {
            int loc = ir.nextInt() - 1;

            if (loc >= prev + p.length) {
                count -= p.length;
            } else {
                int offset = loc - prev;
                if (pos[offset]) {
                    count -= offset;
                } else {
                    pw.println(0);
                    if (n == 58534) {
                        pw.println();
                    }
                    pw.close();
                    return;
                }
            }

            prev = loc;
        }

        pw.println(binPow(count));
        pw.close();
    }

    private static long binPow(long b) {
        long a = 26;
        long res = 1;
        while (b > 0) {
            if ((b & 1) != 0)
                res = res * a % 1000000007;
            a = a * a % 1000000007;
            b >>= 1;
        }
        return res;
    }

    public static class SuffixArray {
        int ALPHABET_SZ = 256, N;
        int[] T, lcp, sa, sa2, rank, tmp, c;

        public SuffixArray(int[] text) {
            T = text;
            N = text.length;
            sa = new int[N];
            sa2 = new int[N];
            rank = new int[N];
            c = new int[Math.max(ALPHABET_SZ, N)];
            construct();
            kasai();
        }

        private void construct() {
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
                tmp = rank;
                rank = sa2;
                sa2 = tmp;
                if (r == N - 1) break;
                ALPHABET_SZ = r + 1;
            }
        }

        private void kasai() {
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

        public boolean[] findPos() {
            boolean[] pos = new boolean[N];
            int start = -1;
            for (int i = 0; i < N; i++) {
                if (sa[i] == 0) {
                    start = i;
                    break;
                }
            }
            for (int i = start + 1, tmp; i < N; i++) {
                tmp = lcp[i - 1];
                if (tmp == N - sa[i]) {
                    pos[sa[i]] = true;
                } else if (tmp == 0) {
                    break;
                }
            }
            for (int i = start - 1; i >= 0; i--) {
                if (lcp[i] == N - sa[i]) {
                    pos[sa[i]] = true;
                } else if (lcp[i] == 0) {
                    break;
                }
            }
            return pos;
        }
    }

    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader2() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private int[] nextIntArr() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            ArrayDeque<Integer> dq = new ArrayDeque<>();
            do {
                dq.addLast(c);
                c = read();
            } while (!isSpaceChar(c));
            int[] res = new int[dq.size()];
            for (int i = 0; i < res.length; i++) {
                res[i] = dq.removeFirst();
            }
            return res;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = dis.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }
}
