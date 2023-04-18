import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class P25D4 {
    private static int[] id, height;

    public static void main(String[] args) throws Exception {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int N = n + 1;
        id = new int[N];
        height = new int[N];
        for (int i = 1; i <= n; i++)
            id[i] = i;

        ArrayList<Pair> extra = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int x = ir.nextInt();
            int y = ir.nextInt();
            if (root(x) == root(y)) {
                extra.add(new Pair(x, y, 0));
                continue;
            }
            union(x, y);
        }
        int r = 0;
        for (int i = 1; i <= n; i++) {
            r = root(i);
        }
        ArrayList<Pair> ans = new ArrayList<>();
        for (int i = 1, j = 0; i <= n; i++) {
            int rr = root(i);
            if (rr != root(r)) {
                ans.add(new Pair(extra.get(j).x, extra.get(j++).y, rr));
                union(root(r), rr);
            }
        }

        pw.println(ans.size());
        for (Pair an : ans) pw.println(an.x + " " + an.y + " " + r + " " + an.i);
        pw.close();
    }

    private static void union(int a, int b) {
        int roota = root(a);
        int rootb = root(b);
        if (height[roota] < height[rootb]) {
            id[rootb] = roota;
            height[roota] += height[rootb];
        } else {
            id[roota] = rootb;
            height[rootb] += height[roota];
        }
    }

    private static int root(int i) {
        while (id[i] != i)
            i = id[i];
        return i;
    }

    private static class Pair implements Comparable<Pair> {
        int x, y, k, i;

        Pair(int x, int y, int i) {
            this.x = x;
            this.y = y;
            this.i = i;
        }

        public int compareTo(Pair o) {
            return this.x - o.x;
        }

        public boolean equals(Object o) {
            if (o instanceof Pair) {
                Pair p = (Pair) o;
                return p.x == x && p.y == y && p.k == k;
            }
            return false;
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

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
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
