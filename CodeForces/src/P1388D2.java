import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class P1388D2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        long[] a = new long[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = ir.nextLong();
        }
        for (int i = 0; i < n; i++) {
            b[i] = ir.nextInt() - 1;
        }

        ArrayList<ArrayList<Integer>> children = new ArrayList<>();
        boolean[] isNotLeaf = new boolean[n];
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            if (b[i] != -2) {
                isNotLeaf[b[i]] = true;
                children.get(b[i]).add(i);
            }
        }
        boolean[] visited = new boolean[n];
        StringBuilder ans = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            if (!visited[i] && !isNotLeaf[i]) {
                int root = findRoot(b, i);
                ArrayList<Integer> order = new ArrayList<>();
                ArrayList<Integer> addToEnd = new ArrayList<>();
                ArrayList<Integer> tree = getTree(children, visited, root);

                Collections.reverse(tree);
                for (int node : tree) {
                    if (a[node] > 0) {
                        order.add(node);
                        if (b[node] != -2) {
                            a[b[node]] += a[node];
                        }
                    } else {
                        addToEnd.add(node);
                    }
                }
                Collections.reverse(addToEnd);
                for (int node : order) {
                    ans.append(node + 1).append(" ");
                }
                for (int node : addToEnd) {
                    ans.append(node + 1).append(" ");
                }
            }
        }
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }

        pw.println(sum);
        pw.println(ans);
        pw.close();
    }

    private static int findRoot(int[] b, int at) {
        if (b[at] == -2) {
            return at;
        }
        return findRoot(b, b[at]);
    }

    private static ArrayList<Integer> getTree(ArrayList<ArrayList<Integer>> children, boolean[] visited, int at) {
        ArrayList<Integer> tree = new ArrayList<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(at);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            tree.add(cur);
            visited[cur] = true;

            for (int next : children.get(cur)) {
                queue.offer(next);
            }
        }
        return tree;
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
