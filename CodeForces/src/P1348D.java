import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1348D {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        double lg2 = Math.log(2.0);
        for (int i = 0; i < t; i++) {
            int n = ir.nextInt();

            int num = (int) Math.ceil(Math.log1p(n) / lg2);
            int num1 = num - 1;
            pw.println(num1);
            int max = (1 << num) - 1;
            int rem = max - n;
            int[] rmv = new int[num1];
            while (rem > 0) {
                int tmp = (int) Math.floor(Math.log1p(rem) / lg2);
                int val = (1 << tmp) - 1;
                int ind = num1 - tmp;
                rmv[ind] = rem / val;
                rem -= val * rmv[ind];
            }
            int cur = 1;
            for (int j = 0; j < num1; j++) {
                int amt = cur - rmv[j];
                pw.print(amt + " ");
                cur += amt;
            }
            pw.println();
        }
        pw.close();
    }

    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 14;
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
