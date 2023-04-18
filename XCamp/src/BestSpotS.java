import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BestSpotS {
    private static final int MAX = 1073741823;

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int P, F, C;
        P = ir.nextInt();
        F = ir.nextInt();
        C = ir.nextInt();

        int[] fs = new int[F];
        for (int i = 0; i < F; i++) {
            fs[i] = ir.nextInt();
            fs[i]--;
        }
        int[][] dist = new int[P][P];
        ArrayList<ArrayList<int[]>> edges = new ArrayList<>();
        for (int i = 0; i < P; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < P; i++) {
            for (int j = 0; j < P; j++) {
                dist[i][j] = MAX;
            }
        }
        for (int i = 0; i < P; i++) {
            dist[i][i] = 0;
        }
        for (int i = 0; i < C; i++) {
            int a, b, t;
            a = ir.nextInt();
            b = ir.nextInt();
            t = ir.nextInt();
            a--;
            b--;

            edges.get(a).add(new int[]{b, t});
            edges.get(b).add(new int[]{a, t});

            dist[a][b] = t;
            dist[b][a] = t;
        }

        for (int k = 0; k < P; k++) {
            for (int i = 0; i < P; i++) {
                for (int j = 0; j < P; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        double min = MAX;
        int idx = -1;
        for (int i = 0; i < P; i++) {
            double sum = 0;
            for (int j = 0; j < F; j++) {
                sum += dist[i][fs[j]];
            }
            sum /= F;
            if (sum < min) {
                min = sum;
                idx = i;
            }
        }

        pw.println(idx + 1);
        pw.close();
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
