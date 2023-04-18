import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AcowdemiaII {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int k = ir.nextInt();
        int n = ir.nextInt();
        String[] all = new String[n];
        for (int i = 0; i < n; i++) {
            all[i] = ir.nextToken();
        }

        String[] sorted = all.clone();
        Arrays.sort(sorted);
        HashMap<String, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            map.put(sorted[i], i);
        }
        HashMap<String, Integer> map2 = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            map2.put(all[i], i);
        }
        int[][] arr = new int[n][n];
        for (int i = 0; i < k; i++) {
            String[] order = new String[n];

            for (int j = 0; j < n; j++) {
                order[j] = ir.nextToken();
            }

            for (int j = 1, J = 0; j < n; j++, J++) {
                if (map.get(order[j]) < map.get(order[J])) {
                    for (int l = J; l >= 0; l--) {
                        arr[map2.get(order[j])][map2.get(order[l])] = 1;
                        arr[map2.get(order[l])][map2.get(order[j])] = -1;
                    }
                }
            }
            for (int j = 1, J = 0; j < n; j++, J++) {
                if (map.get(order[j]) < map.get(order[J])) {
                    for (int l = J; l >= 0; l--) {
                        arr[map2.get(order[j])][map2.get(order[l])] = 1;
                        arr[map2.get(order[l])][map2.get(order[j])] = -1;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    pw.print("B");
                    continue;
                }

                if (arr[i][j] == 1) {
                    pw.print("1");
                }
                else if (arr[i][j] == 0) {
                    pw.print("?");
                }
                else {
                    pw.print(0);
                }
            }
            pw.println();
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

        private String nextToken() throws IOException {
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

        private String nextLine() throws IOException {
            int c = read();
            while (isNewLineChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isNewLineChar(c));
            return res.toString();
        }

        private boolean isNewLineChar(int c) {
            return c == '\n' || c == '\r' || c == '\t' || c == -1;
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
