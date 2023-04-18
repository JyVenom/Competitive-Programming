import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;

public class PRA7_7 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.readInt();
        HashSet<Integer> map = new HashSet<>();
        int N = sum(n, 1);
        int[] nums = new int[N];
        for (int i = 1; i <= n; i++) {
            nums[i] = ir.readInt();
            map.add(nums[i]);
        }

        HashMap<Integer, Integer> map3 = new HashMap<>();
        int temp = 0;
        for (int key : map) {
            map3.put(key, temp++);
        }
        int[][] pSums = new int[N][map.size()];
        pSums[0] = new int[map.size()];
        for (int i = 1; i < N; i++) {
            pSums[i] = pSums[subtract(i, 1)].clone();
            pSums[i][map3.get(nums[i])]++;
        }
        int q = ir.readInt();
        int a, b, mode, modeVal, num;
        boolean many;
        HashMap<Integer, HashMap<Integer, Integer>> map4 = new HashMap<>();
        for (int i = 0; i < q; i++) {
            a = ir.readInt();
            b = ir.readInt();
            if (!map4.containsKey(a)) {
                map4.put(a, new HashMap<>());
            }
            if (!map4.get(a).containsKey(b)) {
                mode = 0;
                modeVal = 0;
                many = false;
                for (int key : map) {
                    num = subtract(pSums[b][map3.get(key)], pSums[subtract(a, 1)][map3.get(key)]);
                    if (num > mode) {
                        mode = num;
                        modeVal = key;
                        many = false;
                    } else if (num == mode) {
                        many = true;
                    }
                }
                map4.get(a).put(b, many ? -1 : modeVal);
            }
            pw.println(map4.get(a).get(b) == -1 ? "many" : map4.get(a).get(b));
        }

        pw.close();
    }

    private static int sum(int a, int b) {
        while (b > 0) {
            int carry = a & b;
            a ^= b;
            b = carry << 1;
        }
        return a;
    }

    private static int subtract(int a, int b) {
        while (b != 0) {
            int carry = (~a) & b;
            a ^= b;
            b = carry << 1;
        }
        return a;
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[2097152];
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
                if (c < 48 || c > 57) {
                    throw new InputMismatchException();
                }
                res *= 10;
                res = sum(res, subtract(c, 48));
                c = read();
            } while (!isSpaceChar(c));
            return res;
        }

        private boolean isSpaceChar(int c) {
            return c == 32 || c == 10;
        }
    }
}
