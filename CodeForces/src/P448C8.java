import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P448C8 {
    private static int n;

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        n = ir.nextInt();
        int[] as = new int[n];
        for (int i = 0; i < n; i++) {
            as[i] = ir.nextInt();
        }

        pw.println(count(as, 0, n, 0));
        pw.close();
    }

    private static long count(int[] as, int left, int right, int painted) {
        if (left >= right) {
            return 0;
        }

        int minPos = left;
        for (int i = left; i < right; i++) {
            if (as[i] < as[minPos]) {
                minPos = i;
            }
        }
        long a = right - left;
        if (as[minPos] > n) {
            return a;
        }
        long b = count(as, left, minPos, as[minPos]) + count(as, minPos + 1, right, as[minPos]) + (as[minPos] - painted);

        return Math.min(a, b);
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