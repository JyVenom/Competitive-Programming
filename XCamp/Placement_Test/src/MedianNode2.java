import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MedianNode2 {
    static Map<Integer, ArrayList<Integer>> tree;
    static ArrayList<Integer> path;
    static int maxHeight, maxHeightNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        tree = new HashMap<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            addedge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        }

        FindCentre();

        pw.close();
    }

    static boolean getDiameterPath(int vertex, int targetVertex, int parent, ArrayList<Integer> path) {
        if (vertex == targetVertex) {
            path.add(vertex);
            return true;
        }

        for (Integer i : tree.get(vertex)) {
            if (i == parent)
                continue;

            if (getDiameterPath(i, targetVertex, vertex, path)) {
                path.add(vertex);
                return true;
            }
        }
        return false;
    }

    static void farthestNode(int vertex, int parent, int height) {
        if (height > maxHeight) {
            maxHeight = height;
            maxHeightNode = vertex;
        }

        if (tree.get(vertex) != null)
            for (Integer i : tree.get(vertex)) {
                if (i == parent)
                    continue;

                farthestNode(i, vertex, height + 1);
            }
    }

    static void addedge(int a, int b) {
        if (!tree.containsKey(a))
            tree.put(a, new ArrayList<>());

        tree.get(a).add(b);

        if (!tree.containsKey(b))
            tree.put(b, new ArrayList<>());

        tree.get(b).add(a);
    }

    private static void FindCentre() {
        maxHeight = -1;
        maxHeightNode = -1;

        farthestNode(0, -1, 0);
        int leaf1 = maxHeightNode;
        maxHeight = -1;
        farthestNode(maxHeightNode, -1, 0);
        int leaf2 = maxHeightNode;
        path = new ArrayList<>();
        getDiameterPath(leaf1, leaf2, -1, path);
        int pathSize = path.size();
        if (pathSize % 2 == 1) {
            System.out.println(1 + path.get(pathSize / 2));
        } else {
            int a = path.get(pathSize / 2) + 1;
            int b = path.get((pathSize - 1) / 2) + 1;
            if (a < b) {
                System.out.println(a + " " + b);
            } else {
                System.out.println(b + " " + a);
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
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
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
