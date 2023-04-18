import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class P999E2 {
    private static int cnt;
    private static int cur;
    private static ArrayDeque<Vertex> stack;
    private static Vertex[] vertices;

    public static void main(String[] args) throws IOException {
        InputReader2 in = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt() - 1;

        stack = new ArrayDeque<>();

        vertices = new Vertex[n + n];
        for (int i = 0; i < n + n; ++i) {
            vertices[i] = new Vertex();
            vertices[i].adj = new ArrayList<>();
        }

        for (int i = 0; i < m; ++i) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vertices[u].adj.add(vertices[v]);
        }

        cur = n;
        for (int i = 0; i < n; ++i) {
            if (vertices[i].dead == 0) vertices[i].dfs();

        }

        for (int i = n; i < cur; ++i) {
            for (Vertex next : vertices[i].adj) {
                Vertex now = next.at;
                if (now == vertices[i]) continue;
                now.indegree++;
            }
        }

        int answer = 0;
        for (int i = n; i < cur; ++i) {
            if (vertices[i].indegree == 0) ++answer;
        }

        if (vertices[s].at.indegree == 0) --answer;

        pw.println(answer);
        pw.close();
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

    static class Vertex {
        ArrayList<Vertex> adj;
        int indegree;
        int low;
        int num;
        int dead;
        Vertex at;

        void dfs() {
            low = num = ++cnt;
            stack.push(this);
            for (Vertex next : adj) {
                if (next.dead == 1) continue;
                if (next.num > 0) {
                    low = Math.min(low, next.num);
                } else {
                    next.dfs();
                    low = Math.min(low, next.low);
                }
            }

            if (low >= num) {

                while (true) {
                    Vertex now = stack.pop();
                    now.at = vertices[cur];
                    now.dead = 1;
                    now.at.adj.addAll(now.adj);
                    if (now == this) break;
                }
                cur++;
            }
        }

    }
}
