import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P2B {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = ir.nextInt();
            }
        }

        int N = n - 1;
        int N1 = N - 1;
        int[][] dp2 = new int[n][n];
        int[][] dp5 = new int[n][n];
        boolean[][] dir2 = new boolean[n][n]; //true is right, false is down
        boolean[][] dir5 = new boolean[n][n];
        dp2[N][N] = findNum(2, matrix[N][N]);
        dp5[N][N] = findNum(5, matrix[N][N]);
        for (int i = N1; i >= 0; i--) {
            dp2[N][i] = dp2[N][i + 1] + findNum(2, matrix[N][i]);
            dp5[N][i] = dp5[N][i + 1] + findNum(5, matrix[N][i]);
            dir2[N][i] = true;
            dir5[N][i] = true;
        }
        for (int i = N1; i >= 0; i--) {
            dp2[i][N] = findNum(2, matrix[i][N]);
            dp5[i][N] = findNum(5, matrix[i][N]);
            for (int j = N1; j >= 0; j--) {
                if (dp2[i + 1][j] < dp2[i][j + 1]) {
                    dp2[i][j] = dp2[i + 1][j];
                }
                else {
                    dp2[i][j] = dp2[i][j + 1];
                    dir2[i][j] = true;
                }
                if (dp5[i + 1][j] < dp5[i][j + 1]) {
                    dp5[i][j] = dp5[i + 1][j];
                }
                else {
                    dp5[i][j] = dp5[i][j + 1];
                    dir5[i][j] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder(2 * n);
        sb.append(Math.min(dp2[0][0], dp5[0][0])).append("\n");
        int row = 0;
        int col = 0;
        while (row < N || col < N) {
            sb.append(dir2[row][col] ? "R" : "D");
            if (dir2[row][col]);
        }


        pw.close();
    }

    private static int findNum(int p, int v) {
        int count = 0;

        while (v % p == 0) {
            count++;
            v /= p;
        }

        return count;
    }

    private static class InputReader2 {
        private final int BUFFER_SIZE = 1 << 24;
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
    }
}
