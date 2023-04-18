import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class TimeIsMooney4 {
    private static int[] best;
    private static int max = 0;
    private static int c;

    public static void main(String[] args) throws IOException {
        Reader r = new Reader("time.in");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("time.out")));

        int n = r.nextInt();
        int m = r.nextInt();
        c = r.nextInt();
        int[] earn = new int[n];
        for (int i = 0; i < n; i++) {
            earn[i] = r.nextInt();
            if (earn[i] > max) {
                max = earn[i];
            }
        }
        ArrayList<HashSet<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new HashSet<>());
        }
        for (int i = 0; i < m; i++) {
            edges.get(r.nextInt() - 1).add(r.nextInt() - 1);
        }
        r.close();

        best = new int[n];
        Arrays.fill(best, Integer.MIN_VALUE);
        bfs(edges, earn);

        pw.println(Math.max(0, best[0]));
        pw.close();
    }

    private static void bfs(ArrayList<HashSet<Integer>> edges, int[] earn) {
        LinkedList<state> queue = new LinkedList<>();
        queue.offer(new state(0, 0, 0));
        
        while (!queue.isEmpty()) {
            state cur = queue.poll();

            if (best[cur.at] > cur.tot) {
                continue;
            }

            if (cur.tot > best[cur.at]) {
                best[cur.at] = cur.tot;
            }

            for (int next : edges.get(cur.at)) {
                queue.offer(new state(next, cur.cost + 1, cur.earned + earn[next]));
            }
//            queue.sort((o1, o2) -> o2.tot - o1.tot);
        }
    }
    
    private static class state {
        int at;
        int cost, earned, tot;

        public state(int at, int cost, int earned) {
            this.at = at;
            this.cost = cost;
            this.earned = earned;

            tot = earned - (cost * cost * c);
        }
    }

    private static class Reader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
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
