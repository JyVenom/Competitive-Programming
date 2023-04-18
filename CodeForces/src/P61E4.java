import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class P61E4 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[] a = new int[n];
        HashSet<Integer> set = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            a[i] = ir.nextInt();
            set.add(a[i]);
        }

        ArrayList<Integer> all = new ArrayList<>(set);
        Collections.sort(all);
        HashMap<Integer, Integer> numVal = new HashMap<>();
        HashMap<Integer, Long> numChildren = new HashMap<>();
        HashMap<Integer, Long> sumBelow = new HashMap<>();
        HashMap<Integer, Long> curAns = new HashMap<>();
        HashMap<Integer, Long> prevAns = new HashMap<>();
        for (Integer val : all) {
            numVal.put(val, 0);
            numChildren.put(val, 0L);
            sumBelow.put(val, 0L);
            curAns.put(val, 0L);
        }
        long ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int loc = binSearch(all, a[i]);;
            numVal.replace(a[i], numVal.get(a[i]) + 1);

            long tmp = 0L;
            int count = 0;
            for (int j = loc + 1; j < all.size(); j++) {
                count++;
                numChildren.replace(all.get(j), numChildren.get(all.get(j)) + 1);
                sumBelow.replace(all.get(j), sumBelow.get(all.get(j))+1);
                curAns.replace(all.get(j), curAns.get(all.get(j)) + count);
            }

            ans += curAns.get(a[i]);



//            long tmp2 = (prevAns.get(a[i]) + 1000000000);
//            ans += tmp2;
//            prevAns.replace(a[i], tmp2);
//            for (int j = loc - 1; j >= 0; j--) {
//                int cur = all.get(j);
//
//                ans += numChildren.get(cur);
//                tmp += numVal.get(cur);
//            }
//            numChildren.replace(a[i], numChildren.get(a[i]) + tmp);
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
