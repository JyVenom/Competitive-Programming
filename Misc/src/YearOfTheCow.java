import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class YearOfTheCow {
    private static int[][] times;
    private static int time = 0;

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int k = ir.nextInt();
        int N = n + 1;
        int[] arr = new int[N];
        for (int i = 0; i < n; i++) {
            arr[i] = ir.nextInt();
        }

        arr[n] = 0;
        Arrays.sort(arr);
        int[][] dif = new int[N][2];
        for (int i = 1; i < N; i++) {
            dif[i][0] = arr[i] - arr[i - 1];
            dif[i][1] = i;
        }
        Arrays.sort(dif, (o1, o2) -> o2[0] - o1[0]);
        int cost = ((arr[n] / 12) + 1) * 12;
        k--;
        for (int i = 0; i < k; i++) {
            cost -= (dif[i][0] / 12) * 12;
        }

        pw.println(cost);
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
