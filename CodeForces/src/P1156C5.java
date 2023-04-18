import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1156C5 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int z = ir.nextInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(ir.nextInt());
        }

        radixSort(points, n);
        int lhs = 0;
        int rhs = 1;
        int count = 0;
        while (rhs < n && points[rhs].val - points[0].val < z) {
            rhs++;
        }
        int half = n / 2;
        if (half > rhs) {
            rhs = half;
        }
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

    private static void radixSort(Point[] f, int n) {
        Point[] to = new Point[n];

        int[] b = new int[65537];
        for (int i = 0; i < n; i++) b[1 + (f[i].val & 0xffff)]++;
        for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
        for (int i = 0; i < n; i++) to[b[f[i].val & 0xffff]++] = f[i];
        Point[] d = f;
        f = to;
        to = d;

        b = new int[65537];
        for (int i = 0; i < n; i++) b[1 + (f[i].val >>> 16)]++;
        for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
        for (int i = 0; i < n; i++) to[b[f[i].val >>> 16]++] = f[i];
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
