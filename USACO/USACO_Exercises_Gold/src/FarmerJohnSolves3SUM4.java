import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class FarmerJohnSolves3SUM4 {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader("threesum.in");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("threesum.out")));

        int n = r.nextInt();
        int q = r.nextInt();
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = r.nextInt();
            nums[i][1] = i;
        }
        int[][] ranges = new int[q][2];
        for (int i = 0; i < q; i++) {
            ranges[i][0] = r.nextInt() - 1;
            ranges[i][1] = r.nextInt() - 1;
        }

        int[][] all = threeSum(nums);
        for (int i = 0; i < q; i++) {
            int[] range = ranges[i];

            int sum = 0;
            for (int j = range[0]; j < range[1]; j++) {
                for (int k = j + 2; k <= range[1]; k++) {
                    sum += all[j][k];
                }
            }
            pw.println(sum);
        }

        pw.close();
    }

    private static int[][] threeSum(int[][] nums) {
        Arrays.sort(nums, Comparator.comparingInt(o -> o[0]));
        int[][] result = new int[nums.length][nums.length];

        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {

                if (nums[i][0] + nums[j][0] + nums[k][0] > 0) {
                    k--;
                } else if (nums[i][0] + nums[j][0] + nums[k][0] < 0) {
                    j++;
                } else {
                    if (nums[j][0] == nums[k][0]) {
                        for (int l = k; l > j; l--) {
                            for (int m = j; m < l; m++) {
                                int min = Math.min(nums[i][1], Math.min(nums[m][1], nums[l][1]));
                                int max = Math.max(nums[i][1], Math.max(nums[m][1], nums[l][1]));
                                result[min][max]++;
                            }
                        }
                        break;
                    } else {
                        int J = j + 1;
                        int K = k - 1;
                        while (nums[J][0] == nums[j][0]) {
                            J++;
                        }
                        while (nums[K][0] == nums[k][0]) {
                            K--;
                        }
                        for (int l = j; l < J; l++) {
                            for (int m = K + 1; m <= k; m++) {
                                int min = Math.min(nums[i][1], Math.min(nums[l][1], nums[m][1]));
                                int max = Math.max(nums[i][1], Math.max(nums[l][1], nums[m][1]));
                                result[min][max]++;
                            }
                        }
                        if (J >= K) {
                            break;
                        } else {
                            j = J;
                            k = K;
                        }
                    }
                }
            }
        }

        return result;
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

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
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
    }
}
