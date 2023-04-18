import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class FibonacciNumbers2 {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        PrintWriter pw = new PrintWriter(System.out);

        long n = r.nextLong();
        r.close();

        pw.println(Math.round(binPow(1.618033988749895, n) / 2.23606797749979));
        pw.close();
    }

    private static double binPow(double x, long n) {
        assert (n >= 0);
        x %= 1000000007; //note: m*m must be less than 2^63 to avoid ll overflow
        double res = 1;
        while (n > 0) {
            if (n % 2 == 1) //if n is odd
                res = res * x % 1000000007;
            x = x * x % 1000000007;
            n /= 2; //divide by two
        }
        return res;
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
