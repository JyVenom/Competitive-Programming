import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class P380C3 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        char[] s = ir.nextLine().toCharArray();

        int n = s.length;
        ArrayList<Integer> openLocs = new ArrayList<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int[] match = new int[n];
        int[] ans = new int[n];
        if (s[0] == '(') {
            stack.addLast(0);
        }
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1];
            if (s[i] == '(') {
                stack.addLast(i);
            } else {
                if (!stack.isEmpty()) {
                    int tmp = stack.removeLast();
                    match[tmp] = i;
                    openLocs.add(tmp);
                    ans[i]++;
                }
            }
        }
        Collections.sort(openLocs);
        int m = ir.nextInt();
        for (int j = 0; j < m; j++) {
            int l = ir.nextInt() - 1;
            int r = ir.nextInt() - 1;
            int count = 0;
            for (int i = binSearch(openLocs, l); i >= 0; i--) {
                if (match[openLocs.get(i)] <= r) {
                    count++;
                }
            }
            pw.println(2 * (ans[r] - count));
        }

        pw.close();
    }

    private static int binSearch(ArrayList<Integer> arr, int key) {
        int low = 0;
        int high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) < key) {
                low = mid + 1;
            } else if (arr.get(mid) > key) {
                high = mid - 1;
            } else if (arr.get(mid) == key) {
                return mid - 1;
            }
        }
        return high;
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
            StringBuilder res = new StringBuilder();
            while (c != '\n') {
                res.appendCodePoint(c);
                c = read();
            }
            return res.toString();
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
