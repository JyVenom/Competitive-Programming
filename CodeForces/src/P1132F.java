import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class P1132F {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[] s = ir.nextIntArr();

        int count = 0;
        ArrayDeque<Integer> order = new ArrayDeque<>(26);
        boolean[] on = new boolean[26];
        for (int i = 0; i < n; i++) {
            if (on[s[i]]) {
                while (order.getFirst() != s[i]) {
                    on[order.poll()] = false;
                }
            } else {
                count++;
                order.push(s[i]);
                on[s[i]] = true;
            }
        }

        pw.println(count);
        pw.close();
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

        private int[] nextIntArr() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            ArrayDeque<Integer> q = new ArrayDeque<>();
            do {
                q.add(c - 'a');
                c = read();
            } while (!isSpaceChar(c));
            int[] res = new int[q.size()];
            for (int i = 0; i < res.length; i++) {
                res[i] = q.removeFirst();
            }
            return res;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
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
