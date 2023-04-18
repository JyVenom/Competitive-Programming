import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1244C {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);

        long n = ir.nextLong();
        long p = ir.nextLong();
        int w = ir.nextInt();
        int d = ir.nextInt();

        if (w * n < p) {
            pw.println(-1);
            pw.close();
            return;
        }
        long wins = (long) Math.ceil((double) p / (double) w);
        long ties = 0L;
        long losses = n - wins;
        int dif = w - d;
        long cur = wins * w;
        while (cur != p) {
            if (cur > p) {
                cur -= dif;
                wins--;
                ties++;
                if (wins < 0) {
                    pw.println(-1);
                    pw.close();
                    return;
                }
            } else {
                losses--;
                ties++;
                cur += d;
                if (losses < 0) {
                    pw.println(-1);
                    pw.close();
                    return;
                }
            }
        }

        pw.println(wins + " " + ties + " " + losses);
        pw.close();
    }

    private static class InputReader {
        private final int BUFFER_SIZE = 1 << 16;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return ret;
        }

        private long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
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
