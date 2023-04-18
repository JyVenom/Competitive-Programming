import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class P1388D {
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
            b[i] = ir.nextInt();
        }

        ArrayList<ArrayList<Integer>> children = new ArrayList<>();
        boolean[] isNotLeaf = new boolean[n];
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            isNotLeaf[b[i]] = true;
            children.get(b[i]).add(i);
        }
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i] && !isNotLeaf[i]) {
                int root = findRoot(b, i);
                ArrayList<Integer> order = new ArrayList<>();
                ArrayList<int[]> addToEnd = new ArrayList<>();
                ArrayList<int[]> tree = getTree(children, root);

                Collections.reverse(tree);
                for (int[] node:tree){
                    if (a[node[0]] > 0) {
                        order.add(node[0]);
                    }
                    else {
                        addToEnd.add(node);
                    }
                }
            }
        }
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }

        pw.println(sum);
        pw.close();
    }

    private static int findRoot(int[] b, int at) {
        if (b[at] == -1) {
            return at;
        }
        return findRoot(b, b[at]);
    }

    private static void getTree(ArrayList<ArrayList<Integer>> children, ArrayList<int[]> tree, int lvl, int at) {
        tree.add(new int[]{at, lvl});

        for (int next : children.get(at)) {
            getTree(children, tree, lvl + 1, next);
        }
    }

    private static ArrayList<int[]> getTree(ArrayList<ArrayList<Integer>> children, int at) {
        ArrayList<int[]> tree = new ArrayList<>();
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{at, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            tree.add(cur);

            for (int next : children.get(at)) {
                queue.offer(new int[]{next, cur[1] + 1});
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
