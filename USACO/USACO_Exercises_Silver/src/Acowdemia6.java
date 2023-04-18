import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Acowdemia6 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int k = ir.nextInt();
        int l = ir.nextInt();
        HashMap<Integer, Integer> map = new HashMap<>(n + 1);
        for (int i = 0; i <= n; i++) {
            map.put(i, 0);
        }
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = Math.min(n, ir.nextInt());
            map.replace(data[i], map.get(data[i]) + 1);
        }

        int low = 0, high = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (pos(map, k, l, mid)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        pw.println(high);
        pw.close();
    }

    private static boolean pos(HashMap<Integer, Integer> map, int k, int l, int h) {
        int at = h - 1;
        for (int i = 0; i < k; i++) {
            int rem = l;
            while (rem > 0) {
                if (map.get(at) > rem) {
                    map.replace(at, map.get(at) - rem);
                    rem = 0;
                } else {
                    rem -= map.get(at);
                    map.replace(at, 0);
                    at--;
                }
            }
        }
        int sum = 0;
        for (int i = map.size() - 1; i >= h; i++) {
            sum += map.get(i);
        }
        return sum >= h;
    }

    private static int binSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] < key) {
                low = mid + 1;
            } else if (arr[mid] >= key) {
                high = mid - 1;
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

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do
                ret = ret * 10 + c - '0';
            while ((c = read()) >= '0' && c <= '9');
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
