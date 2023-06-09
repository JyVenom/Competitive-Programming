import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class TheMooParticle6 {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader("moop.in");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));

        int n = r.nextInt();
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            data[i][0] = r.nextInt();
            data[i][1] = r.nextInt();
        }
        r.close();

        Arrays.sort(data, Comparator.comparingInt(o -> o[1]));
        Arrays.sort(data, Comparator.comparingInt(o -> o[0]));
        int[] minL = new int[n];
        int[] maxR = new int[n];
        minL[0] = data[0][1];
        for (int i = 1; i < n; i++) {
            minL[i] = Math.min(minL[i - 1], data[i][1]);
        }
        int N = n - 1;
        maxR[n - 1] = data[N][1];
        for (int i = N - 1; i >= 0; i--) {
            maxR[i] = Math.max(maxR[i + 1], data[i][1]);
        }
        int ans = 1;
        for (int i = 0; i < N; i++) {
            if (minL[i] > maxR[i + 1]) {
                ans++;
            }
        }

        pw.println(ans);
        pw.close();
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
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

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            din.close();
        }
    }
}
