import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class P1370D {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int k = ir.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = ir.nextInt();
        }

        int half = k / 2;
        int minInc = 0, minExc;
        PriorityQueue<Integer> pqI = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> pqE = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < half; i++) {
            int i2 = i * 2;
            pqI.add(arr[i2]);
            pqE.add(arr[i2]);
            if (arr[i2] > minInc) {
                minInc = i2;
            }
        }
        minExc = minInc;
        int tmp = 2 * half - 1;
        if (arr[tmp] < minExc) {
            pqI.add(arr[tmp]);
            pqI.poll();
            minInc = pqI.remove();
        }
        for (int i = tmp+1; i < n; i++) {
            if (minInc < minExc) {
                minExc = minInc;
                pqE.clear();
                pqE.addAll(pqI);
            }

            if (arr[i] < minExc) {
                pqE.add(arr[i]);
                pqE.poll();
                minInc = pqE.peek();
            }
        }
        int ans = Math.min(minInc, minExc);
        minInc = 0;
        pqI.clear();
        pqE.clear();
        for (int i = 0; i < half; i++) {
            int i2 = i * 2 + 1;
            pqI.add(arr[i2]);
            pqE.add(arr[i2]);
            if (arr[i2] > minInc) {
                minInc = i2;
            }
        }
        minExc = minInc;
        tmp = 2 * half;
        if (arr[tmp] < minExc) {
            pqI.add(arr[tmp]);
            pqI.poll();
            minInc = pqI.remove();
        }
        for (int i = tmp + 1; i < n; i++) {
            if (minInc < minExc) {
                minExc = minInc;
                pqE.clear();
                pqE.addAll(pqI);
            }

            if (arr[i] < minExc) {
                pqE.add(arr[i]);
                pqE.poll();
                minInc = pqE.peek();
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

        private String nextLine() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

//        private String nextLine() throws IOException {
//            byte[] buf = new byte[BUFFER_SIZE]; // line length
//            int cnt = 0, c;
//            while ((c = read()) != -1) {
//                if (c == '\n')
//                    break;
//                buf[cnt++] = (byte) c;
//            }
//            return new String(buf, 0, cnt);
//        }

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

        private long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        private double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
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
