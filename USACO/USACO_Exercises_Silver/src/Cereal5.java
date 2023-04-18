import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Cereal5 {
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

        int[] ans = new int[n];
        int[] cows2 = new int[n];
        boolean[] taken = new boolean[m];
        ArrayList<LinkedList<int[]>> wait = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            wait.add(new LinkedList<>());
        }
        for (int i = 0; i < n; i++) {
            if (!taken[cows[i][0]]) {
                taken[cows[i][0]] = true;
                ans[0]++;
            }
            else if (!taken[cows[i][1]]) {
                taken[cows[i][1]] = true;
                cows2[i] = 1;
                wait.get(cows[i][0]).add(new int[]{i, 0});
                ans[0]++;
            }
            else {
                cows2[i] = -1;
                wait.get(cows[i][0]).add(new int[]{i, 0});
                wait.get(cows[i][1]).add(new int[]{i, 1});
            }
        }
        for (int i = 1; i < n; i++) {
            int I = i - 1;
            ans[i] = ans[I];
            if (cows2[I] != -1) {
                if (!fix(wait, taken, cows, cows2, cows[I][cows2[I]])) {
                    ans[i]--;
                }
            }
        }

        for (int an : ans) {
            pw.println(an);
        }
        pw.close();
    }

    private static boolean fix(ArrayList<LinkedList<int[]>> wait, boolean[] taken, int[][] cows, int[] cows2, int at) {
        if (wait.get(at).size() > 0) {
            int[] next = wait.get(at).poll();

            assert next != null;
            if (cows2[next[0]] == -1) {
                cows2[next[0]] = next[1];
                return true;
            }
            else if (cows2[next[0]] == 1) {
                cows2[next[0]] = 0;
                return fix(wait, taken, cows, cows2, cows[next[0]][1]);
            }
            else {
                return fix(wait, taken, cows, cows2, at);
            }
        }
        else {
            taken[cows[at][cows2[at]]] = false;
            return false;
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

        public long nextLong() throws IOException {
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

        public double nextDouble() throws IOException {
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

        public void close() throws IOException {
            din.close();
        }
    }
}
