import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class P1354D7 {
    private static int[] a, k;

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int q = ir.nextInt();
        a = new int[n];
        k = new int[q];
        for (int i = 0; i < n; i++) {
            a[i] = ir.nextInt();
        }
        for (int i = 0; i < q; i++) {
            k[i] = ir.nextInt();
        }

        Arrays.sort(a);
        int ans = 0;
        if (count(n) != 0) {
            int lhs = 0;
            int rhs = n + 1;
            while (rhs - lhs > 1) {
                int mid = (lhs + rhs) / 2;
                if (count(mid) > 0)
                    rhs = mid;
                else
                    lhs = mid;
            }
            ans = rhs;
        }

        pw.println(ans);
        pw.close();
    }

    private static int count(int x) {
        int cnt = binSearch(a, x);
        for (int y : k) {
            if (y > 0 && y <= x)
                cnt++;
            if (y < 0 && -y <= cnt)
                cnt--;
        }
        return cnt;
    }

    private static int binSearch(int[] arr, int key) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] < key) {
                low = mid + 1;
            } else if (arr[mid] > key) {
                high = mid - 1;
            } else if (arr[mid] == key) {
                return mid + 1;
            }
        }
        return low;
    }


    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 16;
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