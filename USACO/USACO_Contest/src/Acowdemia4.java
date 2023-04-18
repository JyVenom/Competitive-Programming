import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Acowdemia4 {
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
        for (int i = 0; i < n; i++) {
            int tmp = Math.min(n, ir.nextInt());
            map.replace(tmp, map.get(tmp) + 1);
        }

        int[] sSum = new int[n + 1];
        sSum[n] = map.getOrDefault(n, 0);
        for (int i = n - 1, I = n; i >= 0; i--, I--) {
            sSum[i] = sSum[I] + map.getOrDefault(I, 0);
        }
        ArrayList<Integer> rem = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            rem.add(l);
        }
        int low = 0;
        int high = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPos(map, rem, sSum, mid)) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        pw.println(high);
        pw.close();
    }

    private static boolean isPos(HashMap<Integer, Integer> map, ArrayList<Integer> rem, int[] sSum, int h) {
        int sum = sSum[h];
        if (sum >= h) {
            return true;
        }
        int tmp = h - 1;
        int depth = 1;
        if (tmp > -1) {
            ArrayList<Integer> copy = new ArrayList<>(rem);
            while (tmp > -1) {
                if (copy.size() < depth) {
                    break;
                }

                int sz = copy.size() - 1;
                int num = Math.min(copy.get(sz), map.get(tmp));
                if (num > 0) {
                    sum += num;
                    for (int i = 0, I = sz; i < depth; i++, I--) {
                        copy.set(I, copy.get(I) - num);
                    }
                    while (sz >= 0 && copy.get(sz) == 0) {
                        copy.remove(sz);
                        sz--;
                    }
                }

                tmp--;
                depth++;
            }
        }
        return sum >= h;
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
