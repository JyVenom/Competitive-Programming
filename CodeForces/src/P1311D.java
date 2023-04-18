import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1311D {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t-- > 0) {
            int a = ir.nextInt();
            int b = ir.nextInt();
            int c = ir.nextInt();

            int min = Integer.MAX_VALUE;
            int a2 = a * 2;
            int b2 = b * 2;
            int[] ans = new int[3];
            for (int A = 1; A < a; A++) {
                for (int B = A; B < b2; B += A) {
                    int C = (c / B) * B;
                    int C2 = C + B;
                    if (B <= C) {
                        int tmp = a - A + Math.abs(b - B) + c - C;
                        if (tmp < min) {
                            min = tmp;
                            ans[0] = A;
                            ans[1] = B;
                            ans[2] = C;
                        }
                    }
                    if (B <= C2) {
                        int tmp = a - A + Math.abs(b - B) + C2 - c;
                        if (tmp < min) {
                            min = tmp;
                            ans[0] = A;
                            ans[1] = B;
                            ans[2] = C2;
                        }
                    }
                }
            }
            for (int A = a; A < a2; A++) {
                for (int B = A; B < b2; B += A) {
                    int C = (c / B) * B;
                    int C2 = C + B;
                    if (B <= C) {
                        int tmp = A - a + Math.abs(b - B) + c - C;
                        if (tmp < min) {
                            min = tmp;
                            ans[0] = A;
                            ans[1] = B;
                            ans[2] = C;
                        }
                    }
                    if (B <= C2) {
                        int tmp = A - a + Math.abs(b - B) + C2 - c;
                        if (tmp < min) {
                            min = tmp;
                            ans[0] = A;
                            ans[1] = B;
                            ans[2] = C2;
                        }
                    }
                }
            }

            pw.println(min);
            pw.println(ans[0] + " " + ans[1] + " " + ans[2]);
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
