import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P2B2 {
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
        int[][][] num = new int[n][n][2];
        int[][] dp = new int[n][n];
        boolean[][] dir = new boolean[n][n]; //true is right, false is down
        num[N][N][0] = findNum(2, matrix[N][N]);
        num[N][N][1] = findNum(5, matrix[N][N]);
        dp[N][N] = Math.min(num[N][N][0], num[N][N][1]);
        for (int i = N1; i >= 0; i--) {
            num[N][i][0] = num[N][i + 1][0] + findNum(2, matrix[N][i]);
            num[N][i][1] = num[N][i + 1][1] + findNum(5, matrix[N][i]);
            dp[N][i] = Math.min(num[N][i][0], num[N][i][1]);
            dir[N][i] = true;
        }
        for (int i = N1; i >= 0; i--) {
            num[i][N][0] = findNum(2, matrix[i][N]);
            num[i][N][1] = findNum(5, matrix[i][N]);
            dp[i][N] = Math.min(num[i][N][0], num[i][N][1]);
            for (int j = N1; j >= 0; j--) {
                if (dp[i + 1][j] < dp[i][j + 1]) {
                    num[i][j][0] = num[i + 1][j][0] + findNum(2, matrix[i][j]);
                    num[i][j][1] = num[i + 1][j][1] + findNum(5, matrix[i][j]);
                    dp[i][j] = Math.min(num[i][j][0], num[i][j][1]);
                } else {
                    num[i][j][0] = num[i][j + 1][0] + findNum(2, matrix[i][j]);
                    num[i][j][1] = num[i][j + 1][1] + findNum(5, matrix[i][j]);
                    dp[i][j] = Math.min(num[i][j][0], num[i][j][1]);
                    dir[i][j] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder(2 * n);
        sb.append(dp[0][0]).append("\n");
        int row = 0;
        int col = 0;
        while (row < N || col < N) {
            sb.append(dir[row][col] ? "R" : "D");
            if (dir[row][col]) {
                col++;
            } else {
                row++;
            }
        }

        pw.println(sb);
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
