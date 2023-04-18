import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1244C4 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        long n = ir.nextLong();
        long p = ir.nextLong();
        int w = ir.nextInt();
        int d = ir.nextInt();

        if (w * n < p) {
            pw.println(-1);
            pw.close();
            return;
        } else if (p % w == 0) {
            pw.println(p / w + " 0 " + (n - (p / w)));
            pw.close();
            return;
        }
        long wins = (p + w) / w;
        long ties = 0L;
        long losses = n - wins;
        int dif = w - d;
        long cur = wins * w;
        long tmp = cur - p;
        long num = (tmp + dif - 1) / dif;
        wins -= num;
        if (wins < 0) {
            pw.println(-1);
            pw.close();
            return;
        }
        ties += num;
        cur -= num * dif;
        if (cur < p && d == dif) {
            pw.println(-1);
            pw.close();
            return;
        }
        while (cur != p) {
            if (cur > p) {
                tmp = cur - p;
                num = (tmp + dif) / dif;
                wins -= num;
                if (wins < 0) {
                    pw.println(-1);
                    pw.close();
                    return;
                }
                ties += num;
                cur -= num * dif;
            } else {
                tmp = p - cur;
                num = (tmp + d) / d;
                losses -= num;
                if (losses < 0) {
                    pw.println(-1);
                    pw.close();
                    return;
                }
                ties += num;
                cur += num * d;
            }
        }

        pw.println(wins + " " + ties + " " + losses);
        pw.close();
    }

    private static class InputReader2 {
        private final int BUFFER_SIZE = 64;
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
    }
}
