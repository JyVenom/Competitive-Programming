import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class P12_2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t-- > 0) {
            int n = ir.nextInt();
            while (n-- > 0) {
                ArrayDeque<Boolean> s = ir.nextLine();

                int numPBits = 0;
                int tmp = s.size();
                int tmp2 = 1;
                while (tmp > 0) {
                    tmp -= tmp2;
                    tmp += 1;
                    tmp2 *= 2;
                    numPBits++;
                }

                boolean[] ans = new boolean[s.size() + numPBits + 1];
                tmp2 = 1;
                tmp = 1;
                while (!s.isEmpty()) {
                    for (; tmp < ans.length; tmp++) {
                        if (tmp == tmp2) {
                            tmp2 *= 2;
                        } else {
                            ans[tmp++] = s.removeFirst();
                            break;
                        }
                    }
                }
                tmp2 = 1;
                while (tmp2 < ans.length) {
                    int start = tmp2;
                    int end = 2 * tmp2;
                    int count = 0;
                    while (end < ans.length) {
                        for (; start < end; start++) {
                            if (ans[start]) {
                                count++;
                            }
                        }

                        start = end + tmp2;
                        end = start + tmp2;
                    }
                    for (; start < ans.length; start++) {
                        if (ans[start]) {
                            count++;
                        }
                    }

                    ans[tmp2] = count % 2 == 1;
                    tmp2 *= 2;
                }

                for (int i = 1; i < ans.length; i++) {
                    pw.print(ans[i] ? "1" : "0");
                }
                pw.println();
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

        private ArrayDeque<Boolean> nextLine() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            ArrayDeque<Boolean> res = new ArrayDeque<>();
            do {
                res.add(c == '1');
                c = read();
            } while (!isSpaceChar(c));
            return res;
        }

        private boolean isSpaceChar(int c) {
            return c == '\n' || c == '\r' || c == '\t' || c == -1;
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
