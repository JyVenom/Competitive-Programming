import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class D {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int N = ir.nextInt();
        long M = ir.nextLong();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = ir.nextInt();
        }

        ArrayList<Integer> pos = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (arr[i] == 0) {
                pos.add(i);
            }
        }
        long MOD = 1000000007;
        long ans = 1;
        long M1 = M - 1;
        long M2 = M - 2;
        if (pos.size() == N) {
            for (int i = 1; i < N; i++) {
                ans *= M1;
                ans = ans % MOD;
            }
            ans *= M;
            ans = ans % MOD;
            pw.println(ans);
            pw.close();
            return;
        }
        int size;
        if (pos.get(pos.size() - 1) == N - 1) {
            size = 1;
            int i = pos.size() - 2;
            for (; i >= 0; i--) {
                if (pos.get(i) == pos.get(i + 1) - 1) {
                    size++;
                } else {
                    break;
                }
            }
            pos.subList(i + 1, pos.size()).clear();

            for (int j = 0; j < size; j++) {
                ans *= M1;
                ans = ans % MOD;
            }
        }
        size = 0;
        if (pos.get(0) == 0) {
            size = 1;
            for (int i = 1; i < pos.size(); i++) {
                if (pos.get(i) == pos.get(i - 1) + 1) {
                    size++;
                } else {
                    break;
                }
            }

            for (int i = 0; i < size; i++) {
                ans *= M1;
                ans = ans % MOD;
            }
        }
        for (int i = size; i < pos.size(); i++) {
            size = 1;
            int j = i + 1;
            for (; j < pos.size(); j++) {
                if (pos.get(j) == pos.get(j - 1) + 1) {
                    size++;
                } else {
                    break;
                }
            }
            if (size == 1) {
                if (arr[pos.get(i) - 1] == arr[pos.get(i) + 1]) {
                    ans *= M1;
                } else {
                    ans *= M2;
                }
            } else if (size == 2) {
                if (arr[pos.get(i) - 1] == arr[pos.get(j - 1) + 1]) {
                    ans *= M1;
                    ans = ans % MOD;
                    ans *= M2;
                } else {
                    ans *= M2;
                    ans = ans % MOD;
                    ans *= M2;
                    ans = ans % MOD;
                    ans += M1;
                }
            } else {
                int[] copy = arr.clone();
                ans *= ans(copy, i, j - 1, M1, M2, MOD);
                for (int k = 1; k < size; k++) {
                    ans *= M1;
                    ans = ans % MOD;
                }
                ans *= M2;
            }
            ans = ans % MOD;
            i = j - 1;
        }

        pw.println(ans);
        pw.close();
    }

    private static int ans(int[] arr, int l, int r, long M1, long M2, long MOD) {
        int sz = r - l + 1;
        if (sz == 1) {
            if (arr[l - 1] == arr[l + 1]) {
                return (int) M1;
            } else {
                return (int) M2;
            }
        } else if (sz == 2) {
            if (arr[l - 1] == arr[r + 1]) {
                long ans = 1;
                ans *= M1;
                ans = ans % MOD;
                ans *= M2;
                ans = ans % MOD;
                return (int) ans;
            } else {
                long ans = 1;
                ans *= M2;
                ans = ans % MOD;
                ans *= M2;
                ans = ans % MOD;
                ans += M1;
                ans = ans % MOD;
                return (int) ans;
            }
        } else {
            long ans = 1;
            for (int k = 1; k < sz; k++) {
                ans *= M1;
                ans = ans % MOD;
            }
            ans *= M2;
            ans = ans % MOD;
            arr[r - 1] = arr[r + 1];
            return (int) ((ans + ans(arr, l, r - 2, M1, M2, MOD)) % MOD);
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
