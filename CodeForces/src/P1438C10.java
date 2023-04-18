import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1438C10 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t-- > 0) {
            int n = ir.nextInt();
            int m = ir.nextInt();

            int[][] c = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    c[i][j] = ir.nextInt();
                }
            }

            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    for (int j = 0; j < m; j++) {
                        if (j % 2 == 0) {
                            if (c[i][j] % 2 == 1) {
                                c[i][j]++;
                            }
                        } else {
                            if (c[i][j] % 2 == 0) {
                                c[i][j]++;
                            }
                        }
                    }
                } else {
                    for (int j = 0; j < m; j++) {
                        if (j % 2 == 1) {
                            if (c[i][j] % 2 == 1) {
                                c[i][j]++;
                            }
                        } else {
                            if (c[i][j] % 2 == 0) {
                                c[i][j]++;
                            }
                        }

                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    pw.print(c[i][j] + " ");
                }
                pw.println();
            }
        }

        pw.close();
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
