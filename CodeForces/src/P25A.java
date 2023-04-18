import java.io.DataInputStream;
import java.io.IOException;

public class P25A {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();

        int n = ir.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = ir.nextInt();
        }

        int even = 0;
        for (int i = 0; i < 3; i++) {
            if (arr[i] % 2 == 0) {
                even++;
            }
        }
        if (even >= 2) {
            for (int i = 0; i < n; i++) {
                if (arr[i] % 2 == 1) {
                    System.out.println(i + 1);
                    return;
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (arr[i] % 2 == 0) {
                    System.out.println(i + 1);
                    return;
                }
            }
        }
    }

    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 2;
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
