import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class P577B {
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
            HashSet<Integer> all = new HashSet<>();
            int[] psum = new int[n];
            psum[0] = as[0] % m;
            all.add(psum[0]);
            for (int i = 1; i < n; i++) {
                psum[i] = (psum[i - 1] + as[i]) % m;
                if (all.contains(psum[i]) || psum[i] == 0) {
                    pw.println("YES");
                    pw.close();
                    return;
                }
                all.add(psum[i]);
            }
            pw.println("NO");
        } else {
            boolean[] prev = new boolean[m];
            for (int i = 0; i < n; i++) {
                boolean[] cur = prev.clone();
                for (int j = 1; j < m; j++) {
                    if (prev[j]) {
                        cur[(j + as[i]) % m] = true;
                        if (cur[0]) {
                            pw.println("YES");
                            pw.close();
                            return;
                        }
                    }
                }
                cur[as[i]] = true;
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