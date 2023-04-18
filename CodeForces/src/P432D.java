import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class P432D {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        String s = ir.nextLine();
        SuffixArray sa = new SuffixArray(s);

        ArrayList<int[]> ans = sa.findAns();
        ans.sort(Comparator.comparingInt(o -> o[0]));
        pw.println(ans.size());
        for (int[] str : ans) {
            pw.println(str[0] + " " + str[1]);
        }

        pw.close();
    }

    public static class SuffixArray {

        // ALPHABET_SZ is the default alphabet size, this may need to be much larger
        int ALPHABET_SZ = 256, N;
        int[] T, lcp, sa, sa2, rank, tmp, c;

        public SuffixArray(String str) {
            this(toIntArray(str));
        }

        // Designated constructor
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

        private static int[] toIntArray(String s) {
            int[] text = new int[s.length()];
            for (int i = 0; i < s.length(); i++) text[i] = s.charAt(i);
            return text;
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

        // Use Kasai algorithm to build LCP array
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

        public ArrayList<int[]> findAns() {
            ArrayList<int[]> ans = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                if (T[sa[i]] < T[0]) {
                    continue;
                } else if (T[sa[i]] > T[0]) {
                    break;
                }

                int len = 0;
                int tmp = sa[i];
                while ((len < N) && (tmp + len < N) && T[len] == T[tmp + len]) len++;
                if (len > 0 && len == N - tmp) {
                    int cnt = 1;
                    for (int j = i; j < N; j++) {
                        if (lcp[j] >= len) {
                            cnt++;
                        } else {
                            break;
                        }
                    }
                    ans.add(new int[]{len, cnt});
                }
            }

            return ans;
        }
    }


    private static class InputReader2 {
        private final int BUFFER_SIZE = 1 << 16;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader2() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private String nextLine() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
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
