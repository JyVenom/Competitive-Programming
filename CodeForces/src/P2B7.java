import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P2B7 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = ir.nextInt();
            }
        }

        boolean zero = false;
        int c = -1;
        loop:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0) {
                    zero = true;
                    c = j;
                    break loop;
                }
            }
        }

        pw.println(solve(arr, n, zero, c));
        pw.close();
    }

    public static StringBuilder solve(int[][] arr, int n, boolean zero, int c) {
        int[][] two = new int[n][n];
        int[][] five = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                two[i][j] = factorize(arr[i][j], 2);
                five[i][j] = factorize(arr[i][j], 5);
            }
        }
        int[][] dp2 = new int[n][n];
        int[][] dp5 = new int[n][n];
        dp2[0][0] = two[0][0];
        dp5[0][0] = five[0][0];
        for (int i = 1; i < n; i++) {
            dp2[0][i] = dp2[0][i - 1] + two[0][i];
            dp5[0][i] = dp5[0][i - 1] + five[0][i];

            dp2[i][0] = dp2[i - 1][0] + two[i][0];
            dp5[i][0] = dp5[i - 1][0] + five[i][0];
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                dp2[i][j] = Math.min(dp2[i - 1][j], dp2[i][j - 1]) + two[i][j];
                dp5[i][j] = Math.min(dp5[i - 1][j], dp5[i][j - 1]) + five[i][j];
            }
        }
        int count;
        if (dp2[n - 1][n - 1] < dp5[n - 1][n - 1]) {
            int i, j;
            i = j = n - 1;
            count = dp2[i][j];
            while (i != 0 || j != 0) {
                if (i == 0) {
                    while (j != 0) {
                        j--;
                        ans.append('R');
                    }
                } else if (j == 0) {
                    while (i != 0) {
                        i--;
                        ans.append('D');
                    }
                } else {
                    if (dp2[i - 1][j] < dp2[i][j - 1]) {
                        ans.append('D');
                        i--;
                    } else {
                        ans.append('R');
                        j--;
                    }
                }
            }
        } else {
            int i, j;
            i = j = n - 1;
            count = dp5[i][j];
            while (i != 0 || j != 0) {
                if (i == 0) {
                    while (j != 0) {
                        j--;
                        ans.append('R');
                    }
                } else if (j == 0) {
                    while (i != 0) {
                        i--;
                        ans.append('D');
                    }
                } else {
                    if (dp5[i - 1][j] < dp5[i][j - 1]) {
                        ans.append('D');
                        i--;
                    } else {
                        ans.append('R');
                        j--;
                    }
                }
            }
        }
        if (zero && count > 1) {
            ans = new StringBuilder();
            ans.append("R".repeat(Math.max(0, c)));
            ans.append("D".repeat(n - 1));
            ans.append("R".repeat(Math.max(0, n - 1 - c)));
            StringBuilder sb = new StringBuilder();
            sb.append(1).append("\n").append(ans);
            return sb;
        }
        ans.reverse();
        StringBuilder sb = new StringBuilder();
        sb.append(count).append("\n").append(ans);
        return sb;
    }

    public static int factorize(int n, int prime) {
        if (n == 0) {
            return 0;
        }
        int count = 0;
        while (n % prime == 0) {
            n /= prime;
            count++;
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
