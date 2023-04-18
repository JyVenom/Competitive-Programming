import java.io.IOException;
import java.io.PrintWriter;

public class Repetitions2 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);

        String s = ir.readString();
        int n = s.length();
        int start = 0;
        int type = s.charAt(0);
        int max = -1;
        for (int i = 1; i < n; i++) {
            int cur = s.charAt(i);
            if (cur != type) {
                max = Math.max(max, i - start);
                start = i;
                type = cur;
            }
        }
        if (max == -1) {
            max = n;
        }

        pw.println(max);
        pw.close();
    }

    private static class InputReader {
        private final byte[] buf = new byte[1000002];
        private int curChar;

        public InputReader() throws IOException {
            System.in.read(buf);
            curChar = 0;
        }

        public byte read() {
            return buf[curChar++];
        }

        public String readString() {
            byte c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!(c == '\n'));
            res.append("X");
            return res.toString();
        }
    }
}
