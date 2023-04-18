import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P459C {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int k = ir.nextInt();
        int d = ir.nextInt();

        if (k >= n) {
            for (int i = 0; i < d; i++) {
                for (int j = 1; j <= n; j++) {
                    pw.print(j);
                    pw.print(" ");
                }
            }
        } else if (k == 1) {
            pw.println(-1);
        } else {
            int init = (n + k - 1) / k;
            int half = (init + 1) / 2;

            int min = (int) (Math.log(half) / Math.log(2));
            if (min > d) {
                pw.println(-1);
            }
            else {
                int tmp = d-min;
                for (int i = 0; i < tmp; i++) {
                    for (int j = 1; j <= n; j++) {
                        pw.print(1);
                        pw.print(" ");
                    }
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
