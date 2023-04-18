import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class P1430E3 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        char[] s = ir.nextCharArr(n);
        ArrayList<ArrayDeque<Integer>> next = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            next.add(new ArrayDeque<>());
        }
        for (int i = 0; i < n; i++) {
            next.get(s[i] - 'a').addLast(i);
        }
        ArrayList<Integer> removed = new ArrayList<>(n);
        long count = 0;
        int at = 0, low = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (low < removed.size() && removed.get(low) == at) {
                low++;
                at++;
            }

            if (s[at] == s[i]) {
                at++;
                next.get(s[i] - 'a').removeFirst();
            } else {
                int tmp = next.get(s[i] - 'a').removeFirst();
                int tmp2 = binSearch(removed, tmp, low);
                count += tmp - at - tmp2 + low;
                removed.add(tmp2, tmp);
            }
        }

        pw.println(count);
        pw.close();
    }

    private static int binSearch(ArrayList<Integer> arr, int key, int l) {
        int low = l, high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) < key) {
                low = mid + 1;
            } else if (arr.get(mid) > key) {
                high = mid - 1;
            } else if (arr.get(mid) == key) {
                return mid;
            }
        }
        return low;
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
