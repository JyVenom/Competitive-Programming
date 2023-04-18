import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class P25D2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int a = ir.nextInt();
            int b = ir.nextInt();
            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        int N = n + 1;
        ArrayList<Integer> components = new ArrayList<>();
        HashSet<Edge> del = new HashSet<>();
        boolean[] visited = new boolean[N];
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                components.add(i);
                dfs(edges, del, visited, i, -1);
            }
        }
        ArrayList<Edge> del2 = new ArrayList<>(del);
        del2.add(0, new Edge(-1, -1));
        pw.println(components.size() - 1);
        for (int i = 1; i < components.size(); i++) {
            pw.println(del2.get(i).from + " " + del2.get(i).to + " " + components.get(i - 1) + " " + components.get(i));
        }

        pw.close();
    }

    private static void dfs(ArrayList<ArrayList<Integer>> edges, HashSet<Edge> del, boolean[] visited, int at, int prev) {
        visited[at] = true;

        for (int next : edges.get(at)) {
            if (next != prev && visited[next]) {
                if (at < next) {
                    del.add(new Edge(at, next));
                } else {
                    del.add(new Edge(next, at));
                }
            } else if (!visited[next]) {
                dfs(edges, del, visited, next, at);
            }
        }
    }

    private static class Edge {
        int from, to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            if (from != edge.from) return false;
            return to == edge.to;
        }

        @Override
        public int hashCode() {
            int result = from;
            result = 31 * result + to;
            return result;
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
