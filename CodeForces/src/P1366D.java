import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class P1366D {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[] minDiv = new int[10000002];
        for (int i = 0; i < minDiv.length; i++) {
            minDiv[i] = i;
        }
        for (int i = 2; i < minDiv.length; i++) {
            if (minDiv[i] != i) {
                continue;
            }
            for (int j = i; j < minDiv.length; j += i) {
                minDiv[j] = Math.min(minDiv[j], i);
            }
        }
        int[] d1 = new int[n];
        int[] d2 = new int[n];
        for (int i = 0; i < n; i++) {
            int id = ir.nextInt();

            ArrayList<Integer> list = getPrimeDivisors(minDiv, id);
            if (list.size() < 2) {
                d1[i] = -1;
                d2[i] = -1;
            } else {
                d1[i] = list.get(list.size() - 1);
                list.remove(list.size() - 1);
                d2[i] = list.get(0);
                for (int j = 1; j < list.size(); j++) {
                    d2[i] *= list.get(i);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            pw.print(d1[i] + " ");
        }
        pw.println();
        for (int i = 0; i < n; i++) {
            pw.print(d2[i] + " ");
        }
        pw.close();
    }

    private static ArrayList<Integer> getPrimeDivisors(int[] minDiv, int v) {
        ArrayList<Integer> ans = new ArrayList<>();
        int curVal = v;

        while (curVal != 1) {
            if (ans.isEmpty() || ans.get(ans.size() - 1) != minDiv[curVal]) {
                ans.add(minDiv[curVal]);
            }
            curVal /= minDiv[curVal];
        }
        return ans;
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

        public InputReader2(String file_name) throws IOException {
            dis = new DataInputStream(new FileInputStream(file_name));
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
            return c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == '\f' || c == -1;
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
