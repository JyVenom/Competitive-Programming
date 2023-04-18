import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class DiceCombinations {
    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = fr.nextInt();
        fr.close();

        int mod = 1000000007;
        int N = n + 1;
        long[] dp = new long[N];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 6 && i - j >= 0; j++) {
                dp[i] += dp[i - j];
                dp[i] %= mod;
            }
        }

        pw.println(dp[n]);
        pw.close();
    }

    private static class FastReader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
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
