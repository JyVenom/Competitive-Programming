import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ClockwiseFence2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        for (int i = 0; i < n; i++) {
            String s = ir.nextLine();

            int col = 0;
            int rightmost = 0;
            int rightmostLoc = 0;
            boolean cw = false;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == 'N') {
                    if (rightmostLoc == j - 1) {
                        cw = false;
                    }
                } else if (s.charAt(j) == 'E') {
                    col++;
                    if (col > rightmost) {
                        rightmost = col;
                        rightmostLoc = j;
                    }
                } else if (s.charAt(j) == 'S') {
                    if (rightmostLoc == j - 1) {
                        cw = true;
                    }
                } else if (s.charAt(j) == 'W') {
                    col--;
                }
            }

            pw.println(cw ? "CW" : "CCW");
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

        private String nextLine() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
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
