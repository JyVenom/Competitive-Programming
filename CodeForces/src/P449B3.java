import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P449B3 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        int k = ir.nextInt();
        KShortestPaths d = new KShortestPaths(n);
        for (int i = 0; i < m; i++) {
            d.addEdge(ir.nextInt() - 1, ir.nextInt() - 1, ir.nextInt());
        }
        ArrayList<Integer> queries = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int s = ir.nextInt() - 1;
            int y = ir.nextInt();
            d.addEdge(0, s, y);
            queries.add(s);
        }

        ArrayList<ArrayList<Long>> dist = d.dijkstra(0);
        int count = 0;
        for (int i = 0; i < k; i++) {
            int s = queries.get(i);
            if (dist.get(s).get(0) < dist.get(s).get(1)) {
                count++;
            }
        }

        pw.println(count);
        pw.close();
    }

    private static class KShortestPaths {
        private final int n;
        private final ArrayList<ArrayList<Edge>> graph;

        public KShortestPaths(int n) {
            this.n = n;
            graph = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
        }

        public void addEdge(int from, int to, long cost) {
            graph.get(from).add(new Edge(to, cost));
            graph.get(to).add(new Edge(from, cost));
        }

        public ArrayList<ArrayList<Long>> dijkstra(int start) {
            ArrayList<ArrayList<Long>> ans = new ArrayList<>();
            int[] count = new int[n];
            PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.cost));
            pq.add(new State(start, 0L));

            for (int i = 0; i < n; i++) {
                ans.add(new ArrayList<>());
            }

            while (!pq.isEmpty()) {
                State cur = pq.poll();
                int nodeId = cur.at;
                long minValue = cur.cost;

                ans.get(nodeId).add(minValue);

                count[nodeId]++;
                if (count[nodeId] <= 2) {
                    for (Edge edge : graph.get(nodeId)) {
                        pq.add(new State(edge.to, minValue + edge.cost));
                    }
                }
            }

            return ans;
        }

        private static class State {
            int at;
            long cost;

            public State(int at, long cost) {
                this.at = at;
                this.cost = cost;
            }
        }

        public static class Edge {
            int to;
            long cost;

            public Edge(int to, long cost) {
                this.to = to;
                this.cost = cost;
            }
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
