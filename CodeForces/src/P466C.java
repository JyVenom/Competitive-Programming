import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class P466C {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = ir.nextInt();
        }
        ir.close();

        if (n < 3) {
            pw.println(0);
            pw.close();
            return;
        }
        long[] fwd = new long[n];
        ArrayList<Integer> fwd2 = new ArrayList<>();
        fwd[0] = arr[0];
        for (int i = 1; i < n; i++) {
            fwd[i] = fwd[i - 1] + arr[i];
        }
        int N = n - 1;
        if (fwd[N] % 3 != 0) {
            pw.println(0);
            pw.close();
            return;
        }
        long tar = fwd[N] / 3;
        for (int i = 0; i < n; i++) {
            if (fwd[i] == tar) {
                fwd2.add(i);
            }
        }
        long[] rev = new long[n];
        ArrayList<Integer> rev2 = new ArrayList<>();
        rev[N] = arr[N];
        if (rev[N] == tar) {
            rev2.add(N);
        }
        for (int i = n - 2; i >= 0; i--) {
            rev[i] = rev[i + 1] + arr[i];
            if (rev[i] == tar) {
                rev2.add(i);
            }
        }
        long count = 0;
        int prev = 0;
        int prevInd = 0;
        for (int i = fwd2.size() - 1; i >= 0; i--) {
            int end = fwd2.get(i) + 1;
            for (; prevInd < rev2.size(); prevInd++) {
                if (rev2.get(prevInd) > end) {
                    prev++;
                } else {
                    break;
                }
            }
            count += prev;
        }

        pw.println(count);
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

        private void close() throws IOException {
            dis.close();
        }
    }
}
