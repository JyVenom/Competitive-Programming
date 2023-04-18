import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class MinimizingCoins {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = r.nextInt();
        int x = r.nextInt();
        HashSet<Integer> temp = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int c = r.nextInt();
            if (c <= x) {
                temp.add(c);
            }
        }
        r.close();

        ArrayList<Integer> temp2 = new ArrayList<>(temp);
        Collections.sort(temp2);
        int[] coins = new int[temp2.size()];
        for (int i = 0; i < temp2.size(); i++) {
            coins[i] = temp2.get(i);
        }

        pw.println(minNum(coins, x));
        pw.close();
    }

    private static int minNum(int[] coins, int sum) {
        int sum1 = sum + 1;
        int[] prev = new int[sum1];
        int MAX = Integer.MAX_VALUE / 2;
        Arrays.fill(prev, MAX);
        prev[0] = 0;
        for (int i = 1; i <= coins.length; i++) {
            int I = i - 1;
            int[] cur = new int[sum1];
            System.arraycopy(prev, 0, cur, 0, coins[I]);
            for (int j = coins[I]; j <= sum; j++) {
                cur[j] = Math.min(prev[j], cur[j - coins[I]] + 1);
            }
            prev = cur;
        }
        return prev[sum] != MAX ? prev[sum] : -1;
    }

    private static class Reader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
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

        private void close() throws IOException {
            dis.close();
        }
    }
}
