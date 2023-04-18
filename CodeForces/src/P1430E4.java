import java.io.*;
import java.util.ArrayList;

public class P1430E4 {

    public static void main(String[] Args) throws Exception {
        InputReader2 scan = new InputReader2();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int t = 1;
        while (t-- > 0) {
            int n = scan.nextInt();
            char[] s = scan.nextCharArr(n);
            ArrayList<ArrayList<Integer>> indices = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                indices.add(new ArrayList<>());
            }
            for (int i = 0; i < n; i++) {
                int ch = s[i] - 'a';
                indices.get(ch).add(i);
            }
            fenwickTree ft = new fenwickTree(n);
            long ans = 0;
            for (int i = 0; i < n; i++) {
                int req = s[i] - 'a';
                int size = indices.get(req).size();
                int use = indices.get(req).get(size - 1);
                indices.get(req).remove(size - 1);
                int removed = ft.query(n - 1) - ft.query(use);
                ans += n - 1 - use;
                ans += removed;
                ft.update(use);
            }
            out.println(ans);
        }
        out.flush();
        out.close();
    }

    static class fenwickTree {
        int[] arr;

        fenwickTree(int n) {
            arr = new int[n + 1];
        }

        void update(int i) {
            int in = i + 1;
            while (in < arr.length) {
                arr[in] += -1;
                in += (in & (-in));
            }
        }

        int query(int i) {
            int sum = 0;
            int in = i + 1;
            while (in > 0) {
                sum += arr[in];
                in = (in & (in - 1));
            }
            return sum;
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