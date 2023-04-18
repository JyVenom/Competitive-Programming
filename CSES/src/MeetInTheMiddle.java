import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class MeetInTheMiddle {
    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = fr.nextInt();
        int x = fr.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = fr.nextInt();
        }
        fr.close();

        int lhs = 0;
        int rhs = 0;
        int sum = nums[0];
        int count = 0;
        int N = n - 1;
        while (rhs < N) {
            if (sum == x) {
                count++;
                rhs++;
                sum += nums[rhs];
            } else if (sum < x) {
                rhs++;
                sum += nums[rhs];
            } else {
                sum -= nums[lhs];
                lhs++;
            }
        }
        while (sum > x) {
            sum -= nums[lhs];
            lhs++;
        }
        if (sum == x) {
            count++;
        }

        pw.println(count);
        pw.close();
    }

    private static class FastReader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
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

        private void close() throws IOException {
            dis.close();
        }
    }
}
