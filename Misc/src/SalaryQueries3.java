import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

public class SalaryQueries3 {
    private static final Random rand = new Random();

    public static void main(String[] args) throws Exception {
        Reader br = new Reader();

        int n = br.nextInt(), q = br.nextInt(), cnt = 0;
        int[] salary = new int[n];
        int[][] query = new int[q][3], compress = new int[n + (q << 1)][2];
        // Read the input and query and do the index compression
        for (int i = 0; i < n; ++i) {
            compress[i][0] = salary[i] = br.nextInt();
            compress[i][1] = i;
        }
        for (int i = 0, j = n; i < q; ++i, j += 2) {
            query[i][0] = '!' == (char) br.read() ? 1 : 2;
            query[i][1] = br.nextInt();
            query[i][2] = br.nextInt();
            compress[j][0] = query[i][1];
            compress[j][1] = j;
            compress[j + 1][0] = query[i][2];
            compress[j + 1][1] = j + 1;
        }

        qsort(compress, 0, compress.length - 1);
        for (int i = 0, j = Integer.MIN_VALUE; i < compress.length; ++i) {
            if (compress[i][0] != j) cnt++;
            if (compress[i][1] < n) salary[compress[i][1]] = cnt;
            else {
                int p = (compress[i][1] - n) >> 1, r = 1 == ((compress[i][1] - n) & 1) ? 2 : 1;
                if (1 == query[p][0] && 2 == r) query[p][2] = cnt;
                else if (2 == query[p][0]) query[p][1 == ((compress[i][1] - n) & 1) ? 2 : 1] = cnt;
            }
            j = compress[i][0];
        }
        int[] bit = new int[cnt + 1];
        for (int i : salary) add(bit, i, 1);
        final StringBuilder sb = new StringBuilder(2 * q);
        for (int[] cmd : query) {
            if (1 == cmd[0]) {
                add(bit, salary[cmd[1] - 1], -1);
                add(bit, cmd[2], 1);
                salary[cmd[1] - 1] = cmd[2];
            } else {
                sb.append(get(bit, cmd[2]) - get(bit, cmd[1] - 1)).append('\n');
            }
        }

        System.out.print(sb);
    }

    private static void qsort(int[][] nums, int i, int j) {
        int pivot = i + rand.nextInt(j - i + 1), small = i - 1;
        swap(nums, pivot, j);
        for (int p = i; p < j; ++p) {
            if (nums[p][0] <= nums[j][0]) swap(nums, ++small, p);
        }
        swap(nums, ++small, j);
        if (i < small - 1) qsort(nums, i, small - 1);
        if (small + 1 < j) qsort(nums, small + 1, j);
    }

    private static void swap(int[][] nums, int i, int j) {
        int a = nums[i][0], b = nums[i][1];
        nums[i][0] = nums[j][0];
        nums[i][1] = nums[j][1];
        nums[j][0] = a;
        nums[j][1] = b;
    }

    private static int get(int[] bit, int i) {
        int res = 0;
        while (0 < i) {
            res += bit[i];
            i -= i & -i;
        }
        return res;
    }

    private static void add(int[] bit, int i, int val) {
        while (i < bit.length) {
            bit[i] += val;
            i += i & -i;
        }
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            din.close();
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return ret;
        }
    }
}