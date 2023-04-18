import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class NoTimeToPaint3 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int q = ir.nextInt();
        int[] fence = new int[n];
        for (int i = 0; i < n; i++) {
            fence[i] = ir.nextChar();
        }

        HashSet<Integer> helper = new HashSet<>();
        int[] fow = new int[n];
        helper.add(fence[0]);
        fow[0] = 1;
        for (int i = 1, I = 0; i < n; i++, I++) {
            fow[i] = fow[I];
            if (fence[i] > fence[I]) {
                fow[i]++;
            } else if (fence[i] < fence[I] && !helper.contains(fence[i])) {
                fow[i]++;
            }
            helper.add(fence[i]);
        }
        int N = n - 1;
        helper = new HashSet<>();
        int[] rev = new int[n];
        helper.add(fence[N]);
        rev[N] = 1;
        for (int i = N - 1, I = N; i >= 0; i--, I--) {
            rev[i] = rev[I];
            if (fence[i] > fence[I]) {
                rev[i]++;
            } else if (fence[i] < fence[I] && !helper.contains(fence[i])) {
                rev[i]++;
            }
            helper.add(fence[i]);
        }
        for (int i = 0; i < q; i++) {
            int a = ir.nextInt() - 1;
            int b = ir.nextInt() - 1;

            if (a == 0) {
                if (b == N) {
                    pw.println(0);
                } else {
                    pw.println(rev[b + 1]);
                }
            } else if (b == N) {
                pw.println(fow[a - 1]);
            } else {
                pw.println(fow[a - 1] + rev[b + 1]);
            }
        }

        pw.close();
    }

    private static class InputReader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader() {
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

        private int nextChar() throws IOException {
            return (read() - 'A');
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
