import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;

public class PRA7_5 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.readInt();
        HashSet<Integer> map = new HashSet<>();
        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = ir.readInt();
            map.add(nums[i]);
        }

        HashMap<Integer, Integer> map3 = new HashMap<>();
        int temp = 0;
        for (int key : map) {
            map3.put(key, temp++);
        }
        HashMap<Integer, int[]> map2 = new HashMap<>();
        map2.put(0, new int[map.size()]);
        for (int i = 1; i <= n; i++) {
            map2.put(i, map2.get(i - 1).clone());
            map2.get(i)[map3.get(nums[i])]++;
        }
        int q = ir.readInt();
        for (int i = 0; i < q; i++) {
            int a = ir.readInt();
            int b = ir.readInt();
            int mode = 0;
            int modeVal = 0;
            boolean many = false;
            for (int key : map) {
                int num = map2.get(b)[map3.get(key)] - map2.get(a - 1)[map3.get(key)];
                if (num > mode) {
                    mode = num;
                    modeVal = key;
                    many = false;
                } else if (num == mode) {
                    many = true;
                }
            }
            pw.println(many ? "many" : modeVal);
        }
        
        pw.close();
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 24];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        private int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n';
        }
    }
}
