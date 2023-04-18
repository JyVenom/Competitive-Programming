import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class P1388D4 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextIntNonNeg();
        long[] a = new long[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = ir.nextLong();
        }
        for (int i = 0; i < n; i++) {
            b[i] = ir.nextInt() - 1;
        }

        ArrayList<ArrayList<Integer>> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            if (b[i] != -2) {
                children.get(b[i]).add(i);
            }
        }
        boolean[] visited = new boolean[n];
        long sum = 0;
        StringBuilder ans = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int root = findRoot(b, i);
                ArrayDeque<Integer> order = new ArrayDeque<>();
                ArrayDeque<Integer> addToEnd = new ArrayDeque<>();
                ArrayDeque<Integer> tree = getTree(children, visited, root);

                tree.pollLast();
                while (!tree.isEmpty()) {
                    int node = tree.poll();
                    sum += a[node];
                    if (a[node] > 0) {
                        order.add(node);
                        a[b[node]] += a[node];
                    } else {
                        addToEnd.add(node);
                    }
                }
                sum += a[root];
                if (a[root] > 0) {
                    order.add(root);
                } else {
                    addToEnd.add(root);
                }
                while (!order.isEmpty()) {
                    int node = order.poll();
                    ans.append(node + 1).append(" ");
                }
                while (!addToEnd.isEmpty()) {
                    int node = addToEnd.pollLast();
                    ans.append(node + 1).append(" ");
                }
            }
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

    private static ArrayDeque<Integer> getTree(ArrayList<ArrayList<Integer>> children, boolean[] visited, int at) {
        ArrayDeque<Integer> tree = new ArrayDeque<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(at);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            tree.addFirst(cur);
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

        private int nextIntNonNeg() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
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
