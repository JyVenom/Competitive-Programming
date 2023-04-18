import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class PRA7_9 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.readInt();
        int[] nums = new int[n];
        int count = 0;
        HashMap<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nums[i] = ir.readInt();
            if (!map2.containsKey(nums[i])) {
                map2.put(nums[i], count++);
            }
        }

        int q = ir.readInt();
        int a, b;
        HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();
        for (int i = 0; i < q; i++) {
            a = ir.readInt() - 1;
            b = ir.readInt() - 1;

            if (!map.containsKey(a)) {
                map.put(a, new ArrayList<>());
            }
            map.get(a).add(new int[]{b, i});
        }
        int[] ans = new int[q];
        int at, max, maxVal;
        boolean many;
        ArrayList<int[]> ends;
        int[] num = new int[map2.size()];
        for (int start : map.keySet()) {
            ends = map.get(start);

            ends.sort(Comparator.comparingInt(o -> o[0]));
            at = 0;
            Arrays.fill(num, 0);
            max = 0;
            maxVal = 0;
            many = false;
            for (int i = start; i < n; i++) {
                num[map2.get(nums[i])]++;
                if (num[map2.get(nums[i])] > max) {
                    max = num[map2.get(nums[i])];
                    maxVal = nums[i];
                    many = false;
                } else if (num[map2.get(nums[i])] == max) {
                    many = true;
                }

                if (i == ends.get(at)[0]) {
                    ans[ends.get(at)[1]] = many ? -1 : maxVal;
                    at++;
                    if (at == ends.size()) {
                        break;
                    }
                }
            }
        }

        for (int an : ans) {
            pw.println(an == -1 ? "many" : an);
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

        private int read() throws IOException {
            if (numChars == -1) {
                throw new IOException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new IOException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        private int readInt() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new IOException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}
