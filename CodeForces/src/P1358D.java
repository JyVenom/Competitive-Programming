import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1358D {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        long x = ir.nextLong();
        int[] days = new int[n];
        for (int i = 0; i < n; i++) {
            days[i] = ir.nextInt();
        }

        long max = 0;
        long sum = 0;
        long rem = x;
        int month = 0, day = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= days[i]; j++) {
                sum += j;
                rem--;
                if (rem == 0) {
                    rem = 1;
                    if (sum > max) {
                        max = sum;
                    }
                    if (day > days[month]) {
                        day = 1;
                        month++;
                    }
                    sum -= day;
                    day++;
                }
            }
        }
        loop:
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= days[i]; j++) {
                sum += j;
                rem--;
                if (rem == 0) {
                    rem = 1;
                    if (sum > max) {
                        max = sum;
                    }
                    if (day > days[month]) {
                        day = 1;
                        month++;
                        if (month == n) {
                            break loop;
                        }
                    }
                    sum -= day;
                    day++;
                }
            }
        }

        pw.println(max);
        pw.close();
    }

    private static class InputReader2 {
        private final int BUFFER_SIZE = 1 << 24;
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

        private long nextLong() throws IOException {
            long ret = 0;
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
