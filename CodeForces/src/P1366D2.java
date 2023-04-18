import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1366D2 {
    static int[] spf = new int[10000001];

    public static void main(String[] args) throws IOException {
        InputReader2 r = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = r.nextInt();

        StringBuilder res1 = new StringBuilder();
        StringBuilder res2 = new StringBuilder();

        modifiedSieve();

        for (int i = 0; i < n; i++) {
            int ele = r.nextInt();
            int d1 = -1, d2 = -1;
            if (spf[ele] != ele) {
                d1 = spf[ele];
                while (ele % d1 == 0) {
                    ele /= d1;
                }
                if (ele == 1) {
                    d1 = -1;
                } else {
                    d2 = ele;
                }
            }
            res1.append(d1).append(" ");
            res2.append(d2).append(" ");
        }

        pw.println(res1);
        pw.println(res2);
        pw.close();
    }

    private static void modifiedSieve() {
        spf[1] = 1;
        for (int i = 2; i < 10000001; i++) {
            if ((i & 1) == 0 && i > 2) {
                spf[i] = 2;
            } else {
                spf[i] = i;
            }
        }
        for (int i = 3; i * i < 10000001; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j < 10000001; j += i)
                    if (spf[j] == j)
                        spf[j] = i;
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
