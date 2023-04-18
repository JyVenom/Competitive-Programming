import java.io.IOException;

public class EditDistance3 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();

        char[] a = ir.nextLine();
        char[] b = ir.nextLine();

        int n = a.length;
        int m = b.length;
        int N = n + 1;
        int M = m + 1;
        int[] prev = new int[M];
        for (int i = 1; i < M; i++) {
            prev[i] = i;
        }
        int j, J;
        for (int i = 1, I = 0; i < N; i++, I++) {
            int[] cur = new int[M];
            cur[0] = i;
            for (j = 1, J = 0; j < M; j++, J++) {
                cur[j] = a[I] == b[J] ? prev[J] : (Math.min(prev[J], Math.min(prev[j], cur[J])) + 1);
            }
            prev = cur;
        }

        System.out.print(prev[m]);
    }

    private static class InputReader {
        private final byte[] buf = new byte[10002];
        private int curChar;

        public InputReader() throws IOException {
            curChar = 0;
            System.in.read(buf);
            System.in.close();
        }

        private char[] nextLine() {
            int c = buf[curChar++];
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = buf[curChar++];
            } while (c != '\n');
            return res.toString().toCharArray();
        }
    }
}
