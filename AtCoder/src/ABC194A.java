import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ABC194A {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int a = ir.nextInt();
        int b = ir.nextInt();
        int sum = a + b;
        if (sum >= 3) {
            if (sum >= 10) {
                if (sum >= 15) {
                    if (b >= 8) {
                        pw.println(1);
                        pw.close();
                        return;
                    }
                }
                if (b >= 3) {
                    pw.println(2);
                    pw.close();
                    return;
                }
            }
            pw.println(3);
            pw.close();
            return;
        }
        pw.println(4);
        pw.close();
    }

    private static class InputReader2 {
        private final int BUFFER_SIZE = 1 << 3;
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
