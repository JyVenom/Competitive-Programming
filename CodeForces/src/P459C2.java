import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class P459C2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int k = ir.nextInt();
        int d = ir.nextInt();

        if (k >= n) {
            StringBuilder sb = new StringBuilder(n * n);
            for (int i = 1; i <= n; i++) {
                sb.append(i).append(" ");
            }
            for (int i = 0; i < d; i++) {
                pw.println(sb);
            }
        } else if (k == 1) {
            pw.println(-1);
        } else {
            int init = (n + k - 1) / k;
            int min = (int) Math.ceil(Math.log(init) / Math.log(2));
            if (min > d) {
                pw.println(-1);
            } else {
                int dif = d - min;
                StringBuilder sb = new StringBuilder(n * n);
                for (int i = 1; i <= n; i++) {
                    sb.append(i).append(" ");
                }
                for (int i = 0; i < dif; i++) {
                    pw.println(sb);
                }

                int half = (int) Math.pow(2, init - 1);
                int[][] busses = new int[k][init];
                for (int i = 0; i < n; i++) {
                    busses[i / k][i % k] = i + 1;
                }

                ArrayDeque<Integer> carry = new ArrayDeque<>(half);
                int start, end, add;
                while (half > 0) {
                    add = half * 2;

                    start = half;
                    while (start < n) {
                        end = start + half;
                        if (n < end) {
                            end = n;
                        }
                        for (int i = start; i < end; i++) {
                            carry.addLast(busses[0][i]);
                        }
                        start += add;
                    }

                    for (int i = 1; i < n; i++) {
                        start = half;
                        ArrayDeque<Integer> tmp = new ArrayDeque<>(half);
                        while (start < n) {
                            end = start + half;
                            if (n < end) {
                                end = n;
                            }
                            for (int j = start; j < end; j++) {
                                tmp.addLast(busses[i][j]);
                                busses[i][j] = carry.removeFirst();
                            }
                            start += add;
                        }
                        carry = tmp;
                    }

                    start = half;
                    while (start < n) {
                        end = start + half;
                        if (n < end) {
                            end = n;
                        }
                        for (int i = start; i < end; i++) {
                            busses[0][i] = carry.removeFirst();
                        }
                        start += add;
                    }

                    half /= 2;
                }
            }
        }

        pw.close();
    }

    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 16;
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
