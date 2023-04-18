import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1496B2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        for (int i = 0; i < t; i++) {
            int n = ir.nextInt();
            int k = ir.nextInt();
            long max = 0;
            int min = 1000000000;
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = ir.nextInt();
                if (arr[j] > max) {
                    max = arr[j];
                }
                if (arr[j] < min) {
                    min = arr[j];
                }
            }
            long mex;
            if (min != 0) mex = 0;
            else {
                mex = -1;
                boolean[] was = new boolean[n + 1];
                for (int j = 0; j < n; j++) {
                    was[Math.min(n, arr[j] - min)] = true;
                }
                for (int j = 0; j < n; j++) {
                    if (!was[j]) {
                        mex = j + min;
                        break;
                    }
                }
            }
            if (mex == -1 || k == 0) {
                pw.println(n + k);
            } else {
                long half = (mex + max + 1) / 2;
                boolean ans = false;
                for (int j = 0; j < n; j++) {
                    if (arr[j] == half) {
                        ans = true;
                        break;
                    }
                }
                if (ans)
                    pw.println(n);
                else
                    pw.println(n + 1);
            }
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
