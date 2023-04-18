import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P8 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t-- > 0) {
            int n = ir.nextInt();
            while (n-- > 0) {
                String name = ir.nextLine();
                int Ax = ir.nextInt();
                int Ay = ir.nextInt();
                int Sx = ir.nextInt();
                int Sy = ir.nextInt();
                int Ex = ir.nextInt();
                int Ey = ir.nextInt();

                int x1 = Ax - Sx;
                int y1 = Ay - Sy;
                double S1 = (double) y1 / (double) x1;
                int x2 = Ax - Ex;
                int y2 = Ay - Ey;
                double S2 = (double) y2 / (double) x2;
                pw.print(name + ", ");
                if (-1.6 <= S1 && S1 <= -0.8 && -1.6 <= S2 && S2 <= -0.8) {
                    pw.println("Clear To Land!");
                } else {
                    pw.println("Abort Landing!");
                }
            }
        }

        pw.close();
    }

    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader2() {
            dis = new DataInputStream(System.in);
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
            return c == '\n' || c == '\r' || c == '\t' || c == -1;
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
