import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

public class P337D {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        int d = ir.nextInt();
        int[] affected = new int[m];
        for (int i = 0; i < m; i++) {
            affected[i] = ir.nextInt() - 1;
        }
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int a = ir.nextInt() - 1;
            int b = ir.nextInt() - 1;

            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        HashSet<Integer> pos = bfs(edges, affected[0], d);
        for (int i = 1; i < m; i++) {
            HashSet<Integer> tmp = bfs(edges, affected[i], d);
            HashSet<Integer> tmp2 = new HashSet<>(pos);
            for (int v : pos) {
                if (!tmp.contains(v)) {
                    tmp2.remove(v);
                }
            }
            pos = tmp2;
        }

        pw.println(pos.size());
        pw.close();
    }

    private static HashSet<Integer> bfs(ArrayList<ArrayList<Integer>> edges, int start, int d) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{start, 0});
        boolean[] visited = new boolean[edges.size()];
        HashSet<Integer> res = new HashSet<>();

        while (!queue.isEmpty()) {
            int[] cur = queue.pollFirst();

            if (visited[cur[0]]) {
                continue;
            }

            visited[cur[0]] = true;
            res.add(cur[0]);

            if (cur[1] < d) {
                int newDist = cur[1] + 1;
                for (int next : edges.get(cur[0])) {
                    queue.offer(new int[]{next, newDist});
                }
            }
        }

        return res;
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
