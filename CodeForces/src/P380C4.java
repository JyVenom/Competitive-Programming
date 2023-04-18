import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;

public class P380C4 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        // get sequence
        char[] s = ir.nextLine().toCharArray();

        // preprocess by finding matching parenthesis
        int n = s.length;
        int[] corrOpen = new int[n];
        int[] corrClose = new int[n];
        int szCorr = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (s[i] == '(') {
                stack.addLast(i);
            } else {
                if (!stack.isEmpty()) {
                    corrOpen[szCorr] = stack.removeLast();
                    corrClose[szCorr] = i;
                    szCorr++;
                }
            }
        }

        // get queries
        int m = ir.nextInt();
        Query[] queries = new Query[m];
        for (int i = 0; i < m; i++) {
            queries[i] = new Query(ir.nextInt() - 1, ir.nextInt() - 1, i);
        }

        // find ans
        Arrays.sort(queries);
        int[] ans = new int[m];
        int[] fen = new int[n];
        for (int i = 0, j = 0; i < m; i++) {
            Query cur = queries[i];
            while (j < szCorr && corrClose[j] <= cur.r) {
                add(fen, corrOpen[j++]);
            }
            ans[cur.ind] = get(fen, cur.r) - get(fen, cur.l - 1);
        }

        // print ans
        for (int i = 0; i < m; i++) {
            pw.println(ans[i] << 1);
        }
        pw.close();
    }

    private static void add(int[] f, int pos) {
        for (int i = pos; i < f.length; i |= i + 1) {
            f[i]++;
        }
    }

    private static int get(int[] f, int pos) {
        int ret = 0;
        for (int i = pos; i >= 0; i = (i & (i + 1)) - 1)
            ret += f[i];
        return ret;
    }


    private static class Query implements Comparable<Query> {
        int l, r, ind;

        public Query(int l, int r, int ind) {
            this.l = l;
            this.r = r;
            this.ind = ind;
        }

        @Override
        public int compareTo(Query o) {
            return Integer.compare(r, o.r);
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
            StringBuilder res = new StringBuilder();
            while (c != '\n') {
                res.appendCodePoint(c);
                c = read();
            }
            return res.toString();
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
