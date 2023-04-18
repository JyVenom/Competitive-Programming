import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class SocialDistancing5 {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader("socdist.in");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));

        int n = r.nextInt();
        int m = r.nextInt();
        long[][] grass = new long[m][2];
        for (int i = 0; i < m; i++) {
            grass[i][0] = r.nextLong();
            grass[i][1] = r.nextLong();
        }
        r.close();

        Arrays.sort(grass, Comparator.comparingLong(o -> o[0]));
        long ans = -1;
        long low = 0;
        long high = ((grass[m - 1][1] - grass[0][0] + 1) / n) + 1;
        int[] pos = new int[(int) (high + 2)];
        while (low <= high) {
            long mid = (low + high) / 2;
            if (pos[(int) mid] == 0) {
                if (possible(grass, mid, n)) {
                    pos[(int) mid] = 1;
                } else {
                    pos[(int) mid] = -1;
                }
            }
            long next = mid + 1;
            if (pos[(int) next] == 0) {
                if (possible(grass, next, n)) {
                    pos[(int) next] = 1;
                } else {
                    pos[(int) next] = -1;
                }
            }
            if (pos[(int) mid] == 1 && pos[(int) next] == -1) {
                ans = mid;
                break;
            } else if (pos[(int) next] == 1) {
                low = next;
            } else if (pos[(int) mid] == -1) {
                high = mid - 1;
            }
        }

        pw.println(ans);
        pw.close();
    }

    private static boolean possible(long[][] grass, long d, int n) {
        long count = 0;
        long min = 0;
        for (long[] cur : grass) {
            long diff;
            if (min < cur[0]) {
                min = cur[0];
                diff = cur[1] - cur[0] + d;
            } else if (min <= cur[1]) {
                diff = cur[1] - min + d;
            } else {
                continue;
            }
            long num = (diff / d);
            count += num;
            min += (num * d);
            if (count >= n) {
                return true;
            }
        }
        return false;
    }

    private static class Reader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
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

        private long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        private double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
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

        private void close() throws IOException {
            din.close();
        }
    }
}
