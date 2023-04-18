import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P1388D9 {
    static int N;
    static long[] A;
    static int[] B;

    public static void main(String[] args) {
        FastScanner sc = new FastScanner(System.in);
        N = sc.nextInt();
        A = sc.nextLongArray(N);
        B = sc.nextIntArray(N);

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (B[i] != -1) cnt++;
        }
        int[] S = new int[cnt];
        int[] V = new int[cnt];
        cnt = 0;
        for (int i = 0; i < N; i++) {
            if (B[i] != -1) {
                int b = B[i] - 1;
                int idx = cnt++;
                S[idx] = i;
                V[idx] = b;
            }
        }
        int[][] G = adjD(N, S, V);
        int[] topo = khan(N, G);

        var plus = new ArrayList<Integer>();
        var minus = new ArrayList<Integer>();
        long ans = 0;
        for (int i = 0; i < N; i++) {
            assert topo != null;
            int idx = topo[i];
            long a = A[idx];
            int b = B[idx];
            ans += a;
            if (b != -1) {
                if (a >= 0) {
                    A[b - 1] += a;
                    plus.add(idx + 1);
                } else {
                    minus.add(idx + 1);
                }
            } else {
                plus.add(idx + 1);
            }
        }
        Collections.reverse(minus);
        plus.addAll(minus);

        System.out.println(ans);
        writeSingleLine(plus);
    }

    static int[][] adjD(int n, int[] from, int[] to) {
        int[][] adj = new int[n][];
        int[] cnt = new int[n];
        for (int f : from) {
            cnt[f]++;
        }
        for (int i = 0; i < n; i++) {
            adj[i] = new int[cnt[i]];
        }
        for (int i = 0; i < from.length; i++) {
            adj[from[i]][--cnt[from[i]]] = to[i];
        }
        return adj;
    }

    static int[] khan(int V, int[][] G) {
        int[] deg = new int[V];
        for (int[] tos : G) {
            for (int to : tos) {
                deg[to]++;
            }
        }

        int[] q = new int[V];
        int a = 0, b = 0;
        for (int v = 0; v < V; v++) {
            if (deg[v] == 0) q[b++] = v;
        }

        int[] ret = new int[V];
        int idx = 0;
        while (a != b) {
            int v = q[a++];
            ret[idx++] = v;
            for (int to : G[v]) {
                deg[to]--;
                if (deg[to] == 0) {
                    q[b++] = to;
                }
            }
        }

        for (int v = 0; v < V; v++) {
            if (deg[v] != 0) return null;
        }
        return ret;
    }

    static void writeSingleLine(List<Integer> as) {
        var pw = new PrintWriter(System.out);
        for (var i = 0; i < as.size(); i++) {
            if (i != 0) pw.print(" ");
            pw.print(as.get(i));
        }
        pw.println();
        pw.flush();
    }

    static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] nextIntArray(int n) {
            var a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        long[] nextLongArray(int n) {
            var a = new long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }
    }
}
