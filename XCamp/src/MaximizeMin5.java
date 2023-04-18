import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class MaximizeMin5 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = ir.nextInt();
        }

        Arrays.sort(x);
        int lhs = 0, rhs = x[n - 1] - x[0];
        while (lhs <= rhs) {
            int mid = (lhs + rhs) / 2;

            if (isPos(x, mid, m)) {
                lhs = mid + 1;
            } else
                rhs = mid - 1;
        }

        pw.println(rhs);
        pw.close();
    }

    private static boolean isPos(int[] arr, int dist, int m) {
        int count = 1;
        int at = arr[0] + dist;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= at) {
                count++;
                if (count == m) {
                    return true;
                }
                at = arr[i] + dist;
            }
        }
        return false;
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
