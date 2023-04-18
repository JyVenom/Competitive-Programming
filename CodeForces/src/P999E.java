import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

public class P999E {
    private static final HashSet<Integer> rmvHeads = new HashSet<>();

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        int s = ir.nextInt();
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            edges.get(ir.nextInt() - 1).add(ir.nextInt() - 1);
        }

        boolean[] visited = new boolean[n];
        dfs(edges, visited, s - 1);
        ArrayDeque<Integer> rem = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                rem.add(i);
            }
        }
        HashSet<Integer> heads = new HashSet<>();
        while (!rem.isEmpty()) {
            int cur = rem.poll();
            if (!visited[cur]) {
                rmvHeads.clear();
                dfs2(edges, heads, new boolean[n], visited, cur);
                heads.add(cur);
                heads.removeAll(rmvHeads);
            }
        }

        pw.println(heads.size());
        pw.close();
    }

    private static void dfs(ArrayList<ArrayList<Integer>> edges, boolean[] visited, int at) {
        visited[at] = true;
        for (int next : edges.get(at)) {
            if (!visited[next]) {
                dfs(edges, visited, next);
            }
        }
    }

    private static void dfs2(ArrayList<ArrayList<Integer>> edges, HashSet<Integer> heads, boolean[] visited, boolean[] visited2, int at) {
        visited[at] = true;
        visited2[at] = true;
        for (int next : edges.get(at)) {
            if (heads.contains(next)) {
                rmvHeads.add(next);
            }
            if (!visited[next]) {
                dfs2(edges, heads, visited, visited2, next);
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
