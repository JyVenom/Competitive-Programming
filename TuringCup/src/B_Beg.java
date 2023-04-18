import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class B_Beg {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        int[][] check = new int[n][2];
        for (int i = 0; i < n; i++) {
            check[i][0] = ir.nextInt();
            check[i][1] = ir.nextInt();
        }
        int[][] wall = new int[n][4];
        for (int i = 0; i < m; i++) {
            wall[i][0] = ir.nextInt();
        }



        int sz = 2 * n + 1;
        char[][] arr = new char[sz][sz];
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                arr[i][j] = ' ';
            }
        }
        for (int i = 0; i < sz; i++) {
            arr[i][i] = '+';
            arr[i][sz - i - 1] = '+';
        }
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                pw.print(arr[i][j]);
            }
            pw.println();
        }
        pw.println();

        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                arr[i][j] = ' ';
            }
        }
        for (int i = 0; i < n; i++) {
            arr[i][i] = '+';
            arr[i][sz - i - 1] = '+';
        }
        for (int i = n; i < sz; i++) {
            arr[i][n] = '+';
        }
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                pw.print(arr[i][j]);
            }
            pw.println();
        }
        pw.println();

        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                arr[i][j] = ' ';
            }
        }
        for (int i = 0; i < sz; i++) {
            arr[i][0] = '+';
            arr[i][sz - 1] = '+';
            arr[0][i] = '+';
            arr[sz - 1][i] = '+';
        }
        arr[0][sz-1]=' ';
        arr[sz-1][sz-1]=' ';
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                pw.print(arr[i][j]);
            }
            pw.println();
        }

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
