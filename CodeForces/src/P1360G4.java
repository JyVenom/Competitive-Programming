import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1360G4 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        for (int i = 0; i < t; i++) {
            int n = ir.nextInt();
            int m = ir.nextInt();
            int a = ir.nextInt();
            int b = ir.nextInt();
            if (a * n != b * m) {
                pw.println("NO");
            } else {
                int shift = m / gcd(n, m);

                int cur = 0;
                StringBuilder sb = new StringBuilder(n * m);
                for (int j = 0; j < n; j++) {
                    boolean[] tmp = new boolean[m];
                    for (int k = 0; k < a; k++) {
                        tmp[(cur + k) % m] = true;
                    }
                    for (boolean bool : tmp) {
                        sb.append(bool ? "1" : "0");
                    }
                    sb.append("\n");
                    cur += shift;
                    cur %= m;
                }
                pw.println("YES");
                pw.print(sb);
            }
        }

        pw.close();
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
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
    }
}