import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class SpacedOut4 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int N = n - 1;
        int[][] data = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = ir.nextInt();
            }
        }

        int max = 0;
        if (n == 2) {
            max = Math.max(max, data[0][0] + data[0][1]);
            max = Math.max(max, data[0][0] + data[1][0]);
            max = Math.max(max, data[0][0] + data[1][1]);
            max = Math.max(max, data[1][0] + data[1][1]);
            max = Math.max(max, data[0][1] + data[1][1]);
            max = Math.max(max, data[1][0] + data[0][1]);
        }
        else if (n == 3) {
            max = Math.max(max, data[0][0] + data[0][1] + data[0][2] + data[2][0] + data[2][1] + data[2][2]);
            max = Math.max(max, data[0][0] + data[1][1] + data[0][2] + data[2][0] + data[2][2]);
            max = Math.max(max, data[0][0] + data[0][1] + data[1][2] + data[2][0] + data[2][1]);
            max = Math.max(max, data[1][0] + data[0][1] + data[0][2] + data[2][1] + data[2][2]);
            max = Math.max(max, data[1][0] + data[1][1] + data[0][2] + data[2][2]);
            max = Math.max(max, data[1][0] + data[0][1] + data[1][2] + data[2][1]);
            max = Math.max(max, data[0][0] + data[1][1] + data[1][2] + data[2][0]);
            max = Math.max(max, data[1][0] + data[1][1] + data[1][2]);


            max = Math.max(max, data[0][0] + data[1][0] + data[2][0] + data[0][2] + data[1][2] + data[2][2]);
            max = Math.max(max, data[0][1] + data[1][0] + data[2][0] + data[1][2] + data[2][2]);
            max = Math.max(max, data[0][0] + data[1][1] + data[2][0] + data[0][2] + data[2][2]);
            max = Math.max(max, data[0][0] + data[1][0] + data[2][1] + data[0][2] + data[1][2]);
            max = Math.max(max, data[0][1] + data[1][1] + data[2][0] + data[2][2]);
            max = Math.max(max, data[0][1] + data[1][0] + data[2][1] + data[1][2]);
            max = Math.max(max, data[0][0] + data[1][1] + data[2][1] + data[0][2]);
            max = Math.max(max, data[0][1] + data[1][1] + data[2][1]);
        }
        else if (n == 4) {

        }

        pw.println(max);
        pw.close();
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
