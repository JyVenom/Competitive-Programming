import java.io.DataInputStream;
import java.io.IOException;

public class P2B9 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();

        int n = ir.nextInt();

        int[][] m2 = new int[n][n];
        int[][] m5 = new int[n][n];

        boolean[][] p2 = new boolean[n][n];
        boolean[][] p5 = new boolean[n][n];

        boolean hasZero = false;
        int zeroRow = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cur = ir.nextInt();

                if (cur == 0) {
                    hasZero = true;
                    zeroRow = i;
                }

                m2[i][j] = factorize(cur, 2);
                m5[i][j] = factorize(cur, 2);

                if (i == 0) {
                    if (j != 0) {
                        m2[0][j] += m2[0][j - 1];
                        m5[0][j] += m5[0][j - 1];
                    }
                } else {
                    if (j == 0) {
                        m2[i][0] += m2[i - 1][0];
                        m5[i][0] += m5[i - 1][0];

                        p2[i][0] = true;
                        p5[i][0] = true;
                    } else {
                        if (m2[i - 1][j] < m2[i][j - 1]) {
                            m2[i][j] += m2[i - 1][j];
                            p2[i][j] = true;
                        } else
                            m2[i][j] += m2[i][j - 1];

                        if (m5[i - 1][j] < m5[i][j - 1]) {
                            m5[i][j] += m5[i - 1][j];
                            p5[i][j] = true;
                        } else
                            m5[i][j] += m5[i][j - 1];
                    }
                }
            }
        }

        if (m2[n - 1][n - 1] < m5[n - 1][n - 1]) {
            if (hasZero && m2[n - 1][n - 1] > 1) {
                System.out.println(1);
                System.out.println(getPathWithZero(zeroRow, n));
            } else {
                System.out.println(m2[n - 1][n - 1]);
                System.out.println(getPath(p2));
            }
        } else {
            if (hasZero && m5[n - 1][n - 1] > 1) {
                System.out.println(1);
                System.out.println(getPathWithZero(zeroRow, n));
            } else {
                System.out.println(m5[n - 1][n - 1]);
                System.out.println(getPath(p5));
            }
        }
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

    private static String getPathWithZero(int zeroRow, int n) {
        return "D".repeat(Math.max(0, zeroRow)) +
                "R".repeat(Math.max(0, n - 1)) +
                "D".repeat(Math.max(0, n - 1 - zeroRow));
    }

    private static String getPath(boolean[][] path) {
        StringBuilder res = new StringBuilder();

        int i = path.length - 1, j = path.length - 1;
        while (i > 0 || j > 0) {
            if (path[i][j]) {
                res.append('D');
                i--;
            } else {
                res.append('R');
                j--;
            }
        }

        return res.reverse().toString();
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
