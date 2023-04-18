import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1244C5 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        long N = ir.nextLong();
        long P = ir.nextLong();
        int W = ir.nextInt();
        int D = ir.nextInt();

        long y = 0;

        while (y < W && (P - D * y) % W != 0) ++y;
        if (y < W) {
            long x = (P - D * y) / W;
            long z = N - x - y;

            if (x >= 0 && z >= 0)
                pw.println(x + " " + y + " " + z);
            else
                pw.println(-1);
        } else
            pw.println(-1);
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
