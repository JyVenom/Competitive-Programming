import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class P1366D4 {
    static int[] lpf = enumLowestPrimeFactors(10000000);

    public static void main(String[] args) throws Exception {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[] ra = new int[n];
        int[] rb = new int[n];
        Arrays.fill(ra, -1);
        Arrays.fill(rb, -1);
        for (int i = 0; i < n; i++) {
            int x = ir.nextInt();
            int p = lpf[x];
            int tmp = x;
            while (tmp % p == 0) {
                tmp /= p;
            }
            if (tmp > 1) {
                ra[i] = p;
                rb[i] = tmp;
                assert gcd(ra[i] + rb[i], x) == 1;
            }
        }
        for (int v : ra) pw.print(v + " ");
        pw.println();
        for (int v : rb) pw.print(v + " ");
        pw.close();
    }

    public static int gcd(int a, int b) {
        while (b > 0) {
            int c = a;
            a = b;
            b = c % b;
        }
        return a;
    }

    public static int[] enumLowestPrimeFactors(int n) {
        int tot = 0;
        int[] lpf = new int[n + 1];
        int u = n + 32;
        double lu = Math.log(u);
        int[] primes = new int[(int) (u / lu + u / lu / lu * 1.5)];
        for (int i = 2; i <= n; i++)
            lpf[i] = i;
        for (int p = 2; p <= n; p++) {
            if (lpf[p] == p)
                primes[tot++] = p;
            int tmp;
            for (int i = 0; i < tot && primes[i] <= lpf[p] && (tmp = primes[i] * p) <= n; i++) {
                lpf[tmp] = primes[i];
            }
        }
        return lpf;
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
    }
}