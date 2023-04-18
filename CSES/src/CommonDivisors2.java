import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class CommonDivisors2 {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = r.nextInt();
        if (n == 1) {
            pw.println(r.nextInt());
            pw.close();
            return;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        while (n-- > 0) {
            int x = r.nextInt();
            if (map.containsKey(x)) {
                map.replace(x, map.get(x) + 1);
            } else {
                map.put(x, 1);
            }

            if (x > max) {
                max = x;
            }
        }
        r.close();

        for (int a = max; a >= 2; a--) {
            int k = 0;
            for (int b = a; b <= max; b += a) {
                if (map.containsKey(b)) {
                    k += map.get(b);
                    if (k >= 2) {
                        pw.println(a);
                        pw.close();
                        return;
                    }
                }
            }
        }
        pw.println(1);

        pw.close();
    }

    private static class Reader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
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