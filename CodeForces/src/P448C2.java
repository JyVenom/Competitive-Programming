import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P448C2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[] as = new int[n];
        for (int i = 0; i < n; i++) {
            as[i] = ir.nextInt();
        }

        pw.println(findBest(as, 0, n - 1, 0));
        pw.close();
    }

    private static int findBest(int[] as, int l, int r, int ofs) {
        if (l == r) {
            return 1;
        }

        int min = r - l + 1;

        int shortest = 1000000000;
        for (int i = l; i <= r; i++) {
            if (as[i] < shortest) {
                shortest = as[i];
            }
        }

        int sum = shortest - ofs;
        int start = -1;
        if (as[l] != shortest) {
            if (as[l + 1] == shortest) {
                sum += findBest(as, l, l, shortest);
            } else {
                start = l;
            }
        }
        for (int i = l + 1; i < r; i++) {
            if (as[i] != shortest) {
                if (as[i - 1] == shortest) {
                    start = i;
                }
                if (as[i + 1] == shortest) {
                    sum += findBest(as, start, i, shortest);
                }
            }
        }
        if (as[r] != shortest) {
            if (start == -1) {
                start = r;
            }
            sum += findBest(as, start, r, shortest);
        }

        return Math.min(sum, min);
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
