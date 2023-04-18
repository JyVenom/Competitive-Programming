import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ForestQueries {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = r.nextInt();
        int q = r.nextInt();
        int N = n + 1;
        int[][] forest = new int[N][N];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < n; j++) {
                if (r.isTree()) {
                    forest[i][j + 1] = 1;
                }
            }
        }
        int[][] queries = new int[q][4];
        for (int i = 0; i < q; i++) {
            queries[i][0] = r.nextInt();
            queries[i][1] = r.nextInt();
            queries[i][2] = r.nextInt();
            queries[i][3] = r.nextInt();
        }
        r.close();

        int[][] psum = new int[N][N];
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                psum[i][j] = psum[i - 1][j] + psum[i][j - 1] - psum[i - 1][j - 1] + forest[i][j];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            sb.append(psum[queries[i][2]][queries[i][3]] - psum[queries[i][0] - 1][queries[i][3]] - psum[queries[i][2]][queries[i][1] - 1] + psum[queries[i][0] - 1][queries[i][1] - 1]).append("\n");
        }

        pw.println(sb);
        pw.close();
    }

    private static class Reader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
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

        private boolean isTree() throws IOException {
            byte c = read();
            while (c == '\n' || c == '\r')
                c = read();
            return c == '*';
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

        public void close() throws IOException {
            dis.close();
        }
    }
}
