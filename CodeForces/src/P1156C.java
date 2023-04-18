import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class P1156C {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int z = ir.nextInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(ir.nextInt());
        }

        Arrays.sort(points, Comparator.comparingInt(o -> o.val));
        int lhs = 0;
        int rhs = 1;
        int count = 0;
        while (rhs < n) {
            if (points[lhs].matched) {
                lhs++;
            } else if (points[rhs].matched) {
                rhs++;
            } else {
                if (points[rhs].val - points[lhs].val >= z) {
                    points[lhs].matched = true;
                    points[rhs].matched = true;
                    count++;
                    lhs++;
                }
                rhs++;
            }
        }

        pw.println(count);
        pw.close();
    }

    private static class Point {
        int val;
        boolean matched = false;

        public Point(int val) {
            this.val = val;
        }
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
