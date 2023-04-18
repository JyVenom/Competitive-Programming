import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class RectangularPasture7 {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = r.nextInt();
        int[][] cows = new int[n][2];
        for (int i = 0; i < n; i++) {
            cows[i][0] = r.nextInt();
            cows[i][1] = r.nextInt();
        }
        r.close();

        Arrays.sort(cows, Comparator.comparingInt(o -> o[0]));
        int[][] aboveF = new int[n][n];
        int[][] belowF = new int[n][n];
        for (int i = 0; i < n; i++) {
            int above = 1;
            int below = 1;
            for (int j = i + 1; j < n; j++) {
                if (cows[j][1] > cows[i][1]) {
                    above++;
                } else if (cows[j][1] == cows[i][1]) {
                    above++;
                    below++;
                } else {
                    below++;
                }

                aboveF[i][j] = above;
                belowF[i][j] = below;
            }
        }
        int N = n - 1;
        int[][] aboveR = new int[n][n];
        int[][] belowR = new int[n][n];
        for (int i = N; i > 0; i--) {
            int above = 1;
            int below = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (cows[j][1] > cows[i][1]) {
                    above++;
                } else if (cows[j][1] == cows[i][1]) {
                    above++;
                    below++;
                } else {
                    below++;
                }

                aboveR[j][i] = above;
                belowR[j][i] = below;
            }
        }
        long count = n + 1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (cows[j][1] > cows[i][1]) {
                    count += (long) aboveR[i][j] * belowF[i][j];
                } else {
                    count += (long) aboveF[i][j] * belowR[i][j];
                }
            }
        }

        pw.println(count);
        pw.close();
    }

    private static class Reader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
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
