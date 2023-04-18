import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class SpacedOut2 {
    private static boolean[][] used;
    private static int n;
    private static int N;
    private static int[] dirX = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
    private static int[] dirY = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);

        n = ir.nextInt();
        N = n - 1;
        int[][] data = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = ir.nextInt();
            }
        }

        used = new boolean[n][n];

        int max = 0;
        int[] maxInd = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (data[i][j] > max) {
                    max = data[i][j];
                    maxInd[0] = i;
                    maxInd[1] = j;
                }
            }
        }
        for (int i = 0; i < 8; i++) {

        }
        StringBuilder sb = new StringBuilder();


        pw.println();
        pw.close();
    }

    private static boolean pos(int[] loc) {
        if (loc[0] > 0) {
            if (loc[1] > 0) {
                int count = 0;
                if (used[loc[0] - 1][loc[1] - 1]) {
                    count++;
                }
                if (used[loc[0] - 1][loc[1]]) {
                    count++;
                }
                if (used[loc[0]][loc[1] - 1]) {
                    count++;
                }
                if (count > 1) {
                    return false;
                }
            }
            if (loc[1] < N) {
                int count = 0;
                if (used[loc[0] - 1][loc[1] + 1]) {
                    count++;
                }
                if (used[loc[0] - 1][loc[1]]) {
                    count++;
                }
                if (used[loc[0]][loc[1] + 1]) {
                    count++;
                }
                if (count > 1) {
                    return false;
                }
            }
        }
        if (loc[0] < N) {
            if (loc[1] > 0) {
                int count = 0;
                if (used[loc[0] + 1][loc[1] - 1]) {
                    count++;
                }
                if (used[loc[0] + 1][loc[1]]) {
                    count++;
                }
                if (used[loc[0]][loc[1] - 1]) {
                    count++;
                }
                if (count > 1) {
                    return false;
                }
            }
            if (loc[1] < N) {
                int count = 0;
                if (used[loc[0] + 1][loc[1] + 1]) {
                    count++;
                }
                if (used[loc[0] + 1][loc[1]]) {
                    count++;
                }
                if (used[loc[0]][loc[1] + 1]) {
                    count++;
                }
                return count <= 1;
            }
        }
        return true;
    }

    private static class InputReader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private String nextLine() throws IOException {
            byte[] buf = new byte[BUFFER_SIZE]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
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
