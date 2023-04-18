import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1360G2 {
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
                int shift = 0;
                int sum = 0;
                do {
                    sum += n;
                    shift++;
                } while (sum % m != 0);

                int cur = 0;
                StringBuilder sb = new StringBuilder(n * m);
                for (int j = 0; j < n; j++) {
                    boolean[] tmp = new boolean[m];
                    int count = 0;
                    int tmp2 = cur;
                    while (count++ < a) {
                        tmp[tmp2] = true;
                        tmp2 = (tmp2 + 1) % m;
                    }
                    for (boolean bool : tmp) {
                        sb.append(bool ? "1" : "0");
                    }
                    sb.append("\n");
                    cur = (cur + shift) % m;
                }
                pw.println("YES");
                pw.print(sb);
            }
        }

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