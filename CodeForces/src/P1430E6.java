import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class P1430E6 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        char[] arr = ir.nextCharArr(n);

        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            a.add(new ArrayList<>());
        }
        int N = n - 1;
        for (int i = N, j = 1; i >= 0; i--, j++) {
            a.get(arr[i] - 'a').add(j);
        }
        int[] brr = new int[n];
        int count = 0;
        int[] counter = new int[26];
        for (int i = 0; i < n; i++) {
            int tmp = arr[i] - 'a';
            brr[count] = a.get(tmp).get(counter[tmp]);
            counter[tmp]++;
            count++;
        }
        BIT bit = new BIT(n);
        long inv = 0;
        for (int i = N; i >= 0; i--) {
            inv += bit.read(brr[i] - 1);
            bit.update(brr[i] - 1);
        }

        pw.println(inv);
        pw.close();
    }

    static class BIT {
        int n;
        int[] tree;

        public BIT(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        int read(int i) {
            i++;
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }

        void update(int i) {
            i++;
            while (i <= n) {
                tree[i] += 1;
                i += i & -i;
            }
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

        private char[] nextCharArr(int n) throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            char[] res = new char[n];
            int tmp = 0;
            while (tmp < n) {
                res[tmp++] = (char) c;
                c = read();
            }
            return res;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
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