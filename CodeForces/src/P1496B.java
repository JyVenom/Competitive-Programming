import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class P1496B {
    public static void main(String[] args) throws IOException {
        Reader s = new Reader();
        PrintWriter pw = new PrintWriter(System.out);

        int t = s.nextInt();
        for (int i = 0; i < t; i++) {
            int n = s.nextInt();
            int k = s.nextInt();
            long max = 0;
            long first = 0;
            HashSet<Long> set = new HashSet<>();
            for (int j = 0; j < n; j++) {
                long arr = s.nextInt();
                set.add(arr);
                if (arr > max) {
                    max = arr;
                }
                while (set.contains(first)) {
                    first++;
                }
            }

            if (k > 0) {
                if (first == (max + 1L)) {
                    long tot = set.size() + k;
                    pw.println(tot);
                } else {
                    long half = (first + max + 1) / 2;
                    set.add(half);
                    pw.println(set.size());
                }
            } else {
                pw.println(set.size());
            }
        }

        pw.close();
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
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
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
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
