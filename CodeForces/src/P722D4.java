import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.PriorityQueue;

public class P722D4 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        HashSet<Integer> set = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int y = ir.nextInt();
            set.add(y);
            pq.add(y);
        }

        ArrayDeque<Integer> dq = new ArrayDeque<>(n);
        loop:
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            set.remove(cur);
            int min = cur;
            while (cur > 0) {
                if (!set.contains(cur)) {
                    if (cur < min) {
                        min = cur;
                        set.add(min);
                        continue loop;
                    }
                }
                cur /= 2;
            }
            dq.addLast(min);
        }

        for (int i = 0; i < n; i++) {
            pw.print(dq.removeLast() + " ");
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
