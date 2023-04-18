import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class P999E3 {
    private static ArrayList<ArrayList<Integer>> list;
    private static boolean[] visited;
    private static final ArrayDeque<Integer> stack = new ArrayDeque<>();

    private static void dfs(int u, boolean p) {
        if (p) {
            if (visited[u])
                return;
            visited[u] = true;
        } else {
            if (!visited[u])
                return;
            visited[u] = false;
        }
        for (Integer v : list.get(u))
            dfs(v, p);
        if (p)
            stack.push(u);
    }

    public static void main(String[] args) throws IOException {
        InputReader2 scan = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int start = scan.nextInt();
        list = new ArrayList<>(n + 1);
        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++)
            list.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int u = scan.nextInt();
            int v = scan.nextInt();
            list.get(u).add(v);
        }
        for (int i = 1; i <= n; i++) {
            dfs(i, true);
        }
        dfs(start, false);
        int count = 0;
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (visited[u]) {
                dfs(u, false);
                count++;
            }
        }
        pw.println(count);
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
}