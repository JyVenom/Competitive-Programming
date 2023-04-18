import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class DoYouKnowYourABCs4 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t-- > 0) {
            int n = ir.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = ir.nextInt();
            }

            int ans = 0;
            HashSet<Integer> exp = new HashSet<>();
            for (int x : arr) {
                exp.add(x);
                for (int y : arr) {
                    if (x < y) {
                        exp.add(y - x);
                    }
                }
            }

            for (int a : exp) {
                for (int b : exp) {
                    for (int c : exp) {
                        if (a <= b && b <= c) {
                            List<Integer> allNumbers = Arrays.asList(a, b, c, a + b, b + c, c + a, a + b + c);
                            boolean works = true;
                            for (int x : arr) {
                                if (!allNumbers.contains(x)) {
                                    works = false;
                                    break;
                                }
                            }
                            if (works) {
                                ans++;
                            }
                        }
                    }
                }
            }
            pw.println(ans);
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
