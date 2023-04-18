import java.io.*;
import java.util.ArrayList;

public class MilkPumping3 {
    private static double max = 0;

    public static void main(String[] args) throws IOException {
        Reader r = new Reader("pump.in");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pump.out")));

        int n = r.nextInt();
        int m = r.nextInt();
        ArrayList<ArrayList<int[]>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int a = r.nextInt() - 1;
            int b = r.nextInt() - 1;
            int c = r.nextInt();
            int f = r.nextInt();

            edges.get(a).add(new int[]{b, c, f});
            edges.get(b).add(new int[]{a, c, f});
        }

        boolean[] visited = new boolean[n];
        dfs(edges, visited, Integer.MAX_VALUE, 0, 0, n - 1);

        pw.println((int) (max * 1000000));
        pw.close();
    }

    private static void dfs(ArrayList<ArrayList<int[]>> edges, boolean[] visited, double curFlow, double curCost, int at, int n) {
        visited[at] = true;

        if (at == n) {
            double cur = curFlow / curCost;
            if (cur > max) {
                max = cur;
            }
            visited[at] = false;
            return;
        }

        for (int[] next : edges.get(at)) {
            if (!visited[next[0]]) {
                dfs(edges, visited, Math.min(curFlow, next[2]), curCost + next[1], next[0], n);
            }
        }

        visited[at] = false;
    }
    
//    private static class state {
//        private static double flow, cost;
//
//        private state (double f, double c) {
//            flow = f;
//            cost = c;
//        }
//
//        private static void addFlow (double amt){
//            flow += amt;
//        }
//
//        private static void addCost(double amt) {
//            cost += amt;
//        }
//    }

    private static class Reader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        private Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
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

        private long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        private double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        private void close() throws IOException {
            din.close();
        }
    }
}
