import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class P61E3 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = ir.nextInt();
        }

        HashMap<Integer, Integer> numVal = new HashMap<>();
        HashMap<Integer, Long> numChildren = new HashMap<>();
        HashMap<Integer, Long> prevAns = new HashMap<>();
        ArrayList<Integer> all = new ArrayList<>(n);
        long ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int loc;
            if (!numVal.containsKey(a[i])) {
                numVal.put(a[i], 1);
                numChildren.put(a[i], 0L);
                loc = binSearch(all, a[i]);
                all.add(loc, a[i]);
            } else {
                numVal.replace(a[i], numVal.get(a[i]) + 1);
                loc = binSearch(all, a[i]);
            }

            long tmp = 0L;
            for (int j = loc + 1; j < all.size(); j++) {
                numChildren.replace(all.get(i), numChildren.get(all.get(i)) + 1);
            }

            long tmp2 = (prevAns.get(a[i]) + 1000000000);
            ans += tmp2;
            prevAns.replace(a[i], tmp2);
            for (int j = loc - 1; j >= 0; j--) {
                int cur = all.get(j);

                ans += numChildren.get(cur);
                tmp += numVal.get(cur);
            }
            numChildren.replace(a[i], numChildren.get(a[i]) + tmp);
        }

        pw.println(ans);
        pw.close();
    }

    private static int binSearch(ArrayList<Integer> arr, int key) {
        int low = 0, high = arr.size() - 1;

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
