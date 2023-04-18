import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class P1363E {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        node[] nodes = new node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new node(ir.nextInt(), ir.nextInt(), ir.nextInt());
        }
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int u = ir.nextInt() - 1;
            int v = ir.nextInt() - 1;

            edges.get(u).add(v);
            edges.get(v).add(u);
        }

        HashMap<Integer, Integer> have = new HashMap<>();
        HashMap<Integer, Integer> need = new HashMap<>();
        for (node cur : nodes) {
            if (!have.containsKey(cur.digit)) {
                have.put(cur.digit, 1);
            } else {
                have.replace(cur.digit, have.get(cur.digit) + 1);
            }

            if (!need.containsKey(cur.target)) {
                need.put(cur.target, 1);
            } else {
                need.replace(cur.target, need.get(cur.target) + 1);
            }
        }
        for (int key : need.keySet()) {
            if (!have.containsKey(key) || !have.get(key).equals(need.get(key))) {
                pw.println(-1);
                pw.close();
                return;
            }
        }
        dfs(edges, nodes, 0);
        for (int i = 0; i < n; i++) {
            if (nodes[i].digit != nodes[i].target) {
                add(nodes[i], nodes[i]);
            }
        }
        Arrays.sort(nodes, Comparator.comparingInt(o -> o.cost));
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (nodes[i].arr.size() > 0) {
                HashMap<Integer, ArrayList<node>> map = new HashMap<>();

                for (node cur : nodes[i].arr) {
                    if (!map.containsKey(cur.digit)) {
                        map.put(cur.digit, new ArrayList<>());
                    }
                    map.get(cur.digit).add(cur);
                }
                long count = 0L;
                boolean changed;
                do {
                    changed = false;
                    ArrayList<node> remove = new ArrayList<>();
                    for (node cur : nodes[i].arr) {
                        if (map.containsKey(cur.target)) {
                            int idx = 0;
                            for (; idx < map.get(cur.target).size(); idx++) {
                                if (map.get(cur.target).get(idx).target == cur.digit) {
                                    break;
                                }
                            }
                            if (idx == map.get(cur.target).size())idx--;

                            node tmp = map.get(cur.target).get(idx);

                            int orig = cur.digit;
                            cur.digit = tmp.digit;
                            tmp.digit = orig;
                            remove.add(cur);
                            if (tmp.digit != tmp.target) {
                                if (!map.containsKey(tmp.digit)) {
                                    map.put(tmp.digit, new ArrayList<>());
                                }
                                map.get(tmp.digit).add(cur);
                            }
                            else {
                                remove.add(tmp);
                            }
                            changed = true;
                            count++;
                        }
                    }
//                    remove.forEach(nodes[i].arr::remove);
                    for (node cur : remove) {
                        remove(cur, cur);
                    }
                } while (changed);
                ans += count * nodes[i].cost;
            }
        }

        pw.println(ans);
        pw.close();
    }

    private static void dfs(ArrayList<ArrayList<Integer>> edges, node[] nodes, int at) {
        for (int next : edges.get(at)) {
            if (nodes[next] == nodes[at].parent) continue;
            nodes[next].parent = nodes[at];

            dfs(edges, nodes, next);
        }
    }

    private static void add(node at, node n) {
        at.arr.add(n);

        if (at.parent != null) {
            add(at.parent, n);
        }
    }

    private static void remove(node at, node n) {
        at.arr.remove(n);

        if (at.parent != null) {
            remove(at.parent, n);
        }
    }

    private static class node {
        int cost, digit, target;
        node parent;
        HashSet<node> arr = new HashSet<>();

        public node(int cost, int digit, int target) {
            this.cost = cost;
            this.digit = digit;
            this.target = target;
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

        public InputReader2(String file_name) throws IOException {
            dis = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private String nextLine() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == '\f' || c == -1;
        }

//        private String nextLine() throws IOException {
//            byte[] buf = new byte[BUFFER_SIZE]; // line length
//            int cnt = 0, c;
//            while ((c = read()) != -1) {
//                if (c == '\n')
//                    break;
//                buf[cnt++] = (byte) c;
//            }
//            return new String(buf, 0, cnt);
//        }

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
            bytesRead = dis.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        private void close() throws IOException {
            dis.close();
        }
    }
}
