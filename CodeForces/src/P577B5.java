import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class P577B5 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        int[] as = new int[n];
        for (int i = 0; i < n; i++) {
            as[i] = ir.nextInt() % m;
            if (as[i] == 0) {
                pw.println("YES");
                pw.close();
                return;
            }
        }
        ir.close();

        if (n > m) {
            boolean[] all = new boolean[m];
            int[] psum = new int[n];
            psum[0] = as[0] % m;
            all[psum[0]] = true;
            for (int i = 1; i < n; i++) {
                psum[i] = (psum[i - 1] + as[i]) % m;
                if (all[psum[i]] || psum[i] == 0) {
                    pw.println("YES");
                    pw.close();
                    return;
                }
                all[psum[i]] = true;
            }
            pw.println("NO");
        } else {
            HashSet<Integer> prev = new HashSet<>();
            for (int i = 0; i < n; i++) {
                HashSet<Integer> cur = new HashSet<>(prev);
                cur.add(as[i]);
                for (int sum : prev) {
                    cur.add((sum + as[i]) % m);
                    if (cur.contains(0)) {
                        pw.println("YES");
                        pw.close();
                        return;
                    }
                }
                prev = cur;
            }
            pw.println("NO");
            pw.close();
        }
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

        private void close() throws IOException {
            dis.close();
        }
    }
}