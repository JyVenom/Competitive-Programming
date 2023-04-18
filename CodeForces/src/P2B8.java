import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P2B8 {
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
        int[] prev2 = new int[n];
        int[] prev5 = new int[n];
        prev2[0] = factorize(arr[0][0], 2);
        prev5[0] = factorize(arr[0][0], 5);
        boolean[][] dir2 = new boolean[n][n]; //t = come from left right
        boolean[][] dir5 = new boolean[n][n];
        for (int i = 1; i < n; i++) {
            prev2[i] = prev2[i - 1] + factorize(arr[0][i], 2);
            prev5[i] = prev5[i - 1] + factorize(arr[0][i], 5);

            dir2[0][i] = true;
            dir5[0][i] = true;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 1; i < n; i++) {
            int[] cur2 = new int[n];
            int[] cur5 = new int[n];
            cur2[0] = prev2[0] + factorize(arr[i][0], 2);
            cur5[0] = prev5[0] + factorize(arr[i][0], 5);
            for (int j = 1; j < n; j++) {
                if (prev2[j] < cur2[j - 1]) {
                    cur2[j] = prev2[j] + factorize(arr[i][j], 2);
                } else {
                    cur2[j] = cur2[j - 1] + factorize(arr[i][j], 2);
                    dir2[i][j] = true;
                }


                if (prev5[j] < cur5[j - 1]) {
                    cur5[j] = prev5[j] + factorize(arr[i][j], 5);

                } else {
                    cur5[j] = cur5[j - 1] + factorize(arr[i][j], 5);
                    dir5[i][j] = true;
                }
            }
            prev2 = cur2;
            prev5 = cur5;
        }
        int count;
        if (prev2[n - 1] < prev5[n - 1]) {
            int i, j;
            i = j = n - 1;
            count = prev2[j];
            while (i != 0 || j != 0) {
                ans.append(dir2[i][j] ? "R" : "D");
                if (dir2[i][j]) {
                    j--;
                } else {
                    i--;
                }
            }
        } else {
            int i, j;
            i = j = n - 1;
            count = prev5[j];
            while (i != 0 || j != 0) {
                ans.append(dir5[i][j] ? "R" : "D");
                if (dir5[i][j]) {
                    j--;
                } else {
                    i--;
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
