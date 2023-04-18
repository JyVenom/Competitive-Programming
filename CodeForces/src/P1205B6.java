import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class P1205B6 {
    static List<Long> a;
    static int ans;
    static ArrayList<ArrayList<Integer>> adj;

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long x = ir.nextLong();
            if (x != 0) {
                a.add(x);
            }
        }

        if (a.size() >= 121) {
            pw.println(3);
            pw.close();
            return;
        }

        adj = new ArrayList<>(a.size());
        for (int i = 0; i < a.size(); i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < a.size(); i++) {
            for (int j = i + 1; j < a.size(); j++) {
                if ((a.get(i) & a.get(j)) != 0) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }

        ans = Integer.MAX_VALUE;
        for (int i = 0; i < a.size(); i++) {
            bfs(i);
        }

        if (ans == Integer.MAX_VALUE) {
            pw.println(-1);
        } else {
            pw.println(ans);
        }
        pw.close();
    }

    static void bfs(int s) {
        boolean[] vis = new boolean[a.size()];
        int[] d = new int[a.size()];
        int[] p = new int[a.size()];
        Arrays.fill(p, -1);
        d[s] = 0;
        Queue<Integer> q = new ArrayDeque<>();

        q.add(s);
        vis[s] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj.get(u)) {
                if (!vis[v]) {
                    d[v] = d[u] + 1;
                    vis[v] = true;
                    p[v] = u;
                    q.add(v);
                } else if (v != p[u]) {
                    ans = Math.min(ans, d[u] + d[v] + 1);
                }
            }
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

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do
                ret = ret * 10 + c - '0';
            while ((c = read()) >= '0' && c <= '9');
            return ret;
        }

        private long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do
                ret = ret * 10 + c - '0';
            while ((c = read()) >= '0' && c <= '9');
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