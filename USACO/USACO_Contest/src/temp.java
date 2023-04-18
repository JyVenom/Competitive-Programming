import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class temp {
    private static int[][] times;
    private static int time = 0;

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        int k = ir.nextInt();
        times = new int[n][2];
        String s = ir.nextLine();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = ir.nextInt();
        }
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            int a = ir.nextInt() - 1;
            int b = ir.nextInt() - 1;

            edges.get(a).add(b);
            edges.get(b).add(a);
        }
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] tmp = ir.nextLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = ir.nextInt();
                map[i][j] = tmp[j] - 'A';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

            }
        }
        int[] psum = new int[n];
        int[] ssum = new int[n];
        psum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            psum[i] = psum[i - 1] + arr[i];
        }
        int N = n - 1;
        ssum[N] = arr[N];
        for (int i = N - 1; i >= 0; i--) {
            ssum[i] = ssum[i + 1] + arr[i];
        }


        pw.println();
        pw.close();
    }

    private static void bfs(ArrayList<ArrayList<Integer>> edges) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        boolean[] visited = new boolean[edges.size()];

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            visited[cur] = true;

            for (int next : edges.get(cur)) {
                if (!visited[next]) {
                    queue.offer(next);
                }
            }
        }
    }

    private static void dfs(ArrayList<ArrayList<Integer>> edges, int at) {
        times[at][0] = time++;

        for (int next : edges.get(at)) {
            dfs(edges, next);
        }

        times[at][1] = time;
    }

    private static int binSearch(ArrayList<Integer> arr, int key) {
        int low = 0;
        int high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) < key) {
                low = mid + 1;
            } else if (arr.get(mid) > key) {
                high = mid - 1;
            } else if (arr.get(mid) == key) {
                return mid;
            }
        }
        return (-1 * low) - 1;
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
