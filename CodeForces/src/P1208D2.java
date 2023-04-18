import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class P1208D2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = ir.nextInt();
        }

        ArrayDeque<Integer> rem = new ArrayDeque<>(n);
        for (int i = 1; i <= n; i++) {
            rem.addLast(i);
        }
        ArrayDeque<Integer> hold = new ArrayDeque<>(rem.size());
        int sum;
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int cur = s[i];
            if (cur == 0) {
                ans[i] = rem.removeFirst();
            } else {
                sum = 0;
                while (!rem.isEmpty()) {
                    hold.addLast(rem.removeFirst());
                    sum += hold.getLast();
                    if (sum == cur) {
                        ans[i] = rem.removeFirst();
                        break;
                    }
                }
                while (!hold.isEmpty()) {
                    rem.addFirst(hold.removeLast());
                }
            }
        }

        for (int an : ans) {
            pw.print(an + " ");
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
