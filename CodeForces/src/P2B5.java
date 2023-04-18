import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P2B5 {
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
        int[][] prev2 = new int[n][2];
        int[][] prev5;
        boolean[][] dir2 = new boolean[n][n]; //true is right, false is down
        boolean[][] dir5 = new boolean[n][n];
        prev2[N][0] = findNum(2, matrix[N][N]);
        prev2[N][1] = findNum(5, matrix[N][N]);
        for (int i = N1; i >= 0; i--) {
            prev2[i][0] = prev2[i + 1][0] + findNum(2, matrix[N][i]);
            prev2[i][1] = prev2[i + 1][1] + findNum(5, matrix[N][i]);
            dir2[N][i] = true;
        }
        prev5 = prev2.clone();
        dir5[N] = dir2[N].clone();
        for (int i = N1; i >= 0; i--) {
            int[][] cur2 = new int[n][2];
            int[][] cur5 = new int[n][2];
            cur2[N][0] = prev2[N][0] + findNum(2, matrix[i][N]);
            cur2[N][1] = prev2[N][1] + findNum(5, matrix[i][N]);
            cur5[N] = cur2[N].clone();
            for (int j = N1; j >= 0; j--) {
                int tmp2 = findNum(2, matrix[i][j]);
                int tmp5 = findNum(5, matrix[i][j]);
                int right = cur2[j + 1][0] + tmp2;
                int down = prev2[j][0] + tmp2;
                if (right < down) {
                    cur2[j][0] = cur2[j + 1][0] + tmp2;
                    cur2[j][1] = cur2[j + 1][1] + tmp5;
                    dir2[i][j] = true;
                } else {
                    cur2[j][0] = prev2[j][0] + tmp2;
                    cur2[j][1] = prev2[j][1] + tmp5;
                }

                right = cur5[j + 1][1] + tmp5;
                down = prev5[j][1] + tmp5;
                if (right < down) {
                    cur5[j][0] = cur5[j + 1][0] + tmp2;
                    cur5[j][1] = cur5[j + 1][1] + tmp5;
                    dir5[i][j] = true;
                } else {
                    cur5[j][0] = prev5[j][0] + tmp2;
                    cur5[j][1] = prev5[j][1] + tmp5;
                }
            }
            prev2 = cur2;
            prev5 = cur5;
        }
        int ans2 = Math.min(prev2[0][0], prev2[0][1]);
        int ans5 = Math.min(prev5[0][0], prev5[0][1]);
        StringBuilder sb = new StringBuilder(2 * n);
        if (ans2 < ans5) {
            sb.append(ans2).append("\n");
            int row = 0;
            int col = 0;
            while (row < N || col < N) {
                sb.append(dir2[row][col] ? "R" : "D");
                if (dir2[row][col]) {
                    col++;
                } else {
                    row++;
                }
            }
        } else {
            sb.append(ans5).append("\n");
            int row = 0;
            int col = 0;
            while (row < N || col < N) {
                sb.append(dir5[row][col] ? "R" : "D");
                if (dir5[row][col]) {
                    col++;
                } else {
                    row++;
                }
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
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
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
