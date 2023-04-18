import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class P61E7 {
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

        int[] all = new int[set.size()];
        {
            int tmp = 0;
            for (int val : set) {
                all[tmp++] = val;
            }
        }
        Arrays.sort(all);
        HashMap<Integer, Integer> map = new HashMap<>(all.length);
        for (int i = 0; i < all.length; i++) {
            map.put(all[i], i - 1);
        }
        HashMap<Integer, Integer> numVal = new HashMap<>();
        HashMap<Integer, Long> numChildren = new HashMap<>();
        for (Integer val : all) {
            numVal.put(val, 0);
            numChildren.put(val, 0L);
        }
        long ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            numVal.replace(a[i], numVal.get(a[i]) + 1);

            long tmp = 0L;
            for (int j = map.get(a[i]); j >= 0; j--) {
                int cur = all[j];

                ans += numChildren.get(cur);
                tmp += numVal.get(cur);
            }
            numChildren.replace(a[i], numChildren.get(a[i]) + tmp);
        }

        pw.println(ans);
        pw.close();
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
