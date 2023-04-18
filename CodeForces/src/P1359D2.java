import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1359D2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = ir.nextInt();
        }

        int[] psum = new int[n];
        psum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            psum[i] = psum[i - 1] + arr[i];
        }
        int max = 0, max2 = -31, max2Ind = -1, min = 0, minInd = -1, ans = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > max2) {
                max2 = arr[i];
                max2Ind = i;
            }
            if (psum[i] <= min) {
                min = psum[i];
                minInd = i;
            }
            if (max2Ind <= minInd) {
                max2 = -31;
                for (int j = minInd; j <= i; j++) {
                    if (arr[j] > max2) {
                        max2 = arr[j];
                        max2Ind = j;
                    }
                }
            }
            int tmp = psum[i] - min;
            if (tmp > max) {
                max = tmp;
            }
            if (minInd < i) {
                ans = Math.max(ans, max - max2);
            }
        }

        pw.println(ans);
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
