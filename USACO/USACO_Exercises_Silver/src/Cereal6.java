import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Cereal6 {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader("cereal.in");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));

        int n = r.nextInt();
        int m = r.nextInt();
        int[][] cows = new int[n][2];
        for (int i = 0; i < n; i++) {
            cows[i][0] = r.nextInt() - 1;
            cows[i][1] = r.nextInt() - 1;
        }
        r.close();

        ArrayList<Integer> ans = new ArrayList<>();
        int[] by = new int[m];
        int[] choice = new int[m];
        Arrays.fill(by, -1);
        int N = n - 1;
        by[cows[N][0]] = N;
        choice[cows[N][0]] = 1;
        ans.add(1);
        int count = 1;
        for (int i = N - 1; i >= 0; i--) {
            int[] next = cows[i];
            if (by[next[0]] == -1) {
                by[next[0]] = i;
                choice[next[0]] = 1;
                count++;
            } else {
                int hold = by[next[0]];
                int temp = choice[next[0]];
                by[next[0]] = i;
                choice[next[0]] = 1;
                boolean first = temp == 1;
                int second = cows[hold][1];
                if (first && by[second] == -1) {
                    by[second] = hold;
                    choice[second] = 2;
                    count++;
                } else if (first && by[second] > hold) {
                    count = fix(cows, by, choice, hold, count);
                }
            }

            ans.add(count);
        }

        for (int i = ans.size() - 1; i >= 0; i--) {
            pw.println(ans.get(i));
        }
        pw.close();
    }

    private static int fix(int[][] cows, int[] by, int[] choice, int hold, int count) {
        int second = cows[hold][1];
        int next = by[second];
        if (next != -1) {
            if (next > hold) {
                if (choice[second] == 1) {
                    by[second] = hold;
                    choice[second] = 2;
                    count = fix(cows, by, choice, next, count);
                } else {
                    by[second] = hold;
                    choice[second] = 2;
                }
            }
        } else {
            by[second] = hold;
            choice[second] = 2;
            count++;
        }

        return count;
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

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
