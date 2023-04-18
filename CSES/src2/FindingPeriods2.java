import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class FindingPeriods2 {
    private static char[] data;
    private static int[] fail;
    private static int buildLast;

    public static void main(String[] args) throws Exception {
        FastInput in = new FastInput();
        PrintWriter pw = new PrintWriter(System.out);

        char[] s = new char[1000000];
        int n = in.readString(s);
        int n2 = n + 2;
        data = new char[n2];
        fail = new int[n2];
        fail[0] = -1;
        buildLast = 0;
        for (int i = 0; i < n; i++) {
            build(s[i]);
        }
        int border = maxBorder(n - 1);
        IntegerArrayList list = new IntegerArrayList(n);
        while (border >= 1) {
            list.add(border);
            border = maxBorder(border - 1);
        }
        StringBuilder sb = new StringBuilder();
        for (int x : list.toArray()) {
            sb.append(n - x).append(' ');
        }
        sb.append(n);

        pw.println(sb);
        pw.close();
    }

    private static int maxBorder(int i) {
        return fail[i + 1];
    }

    private static int visit(char c, int trace) {
        while (trace >= 0 && data[trace + 1] != c) {
            trace = fail[trace];
        }
        return trace;
    }

    private static void build(char c) {
        buildLast++;
        fail[buildLast] = visit(c, fail[buildLast - 1]) + 1;
        data[buildLast] = c;
    }

    private static class IntegerArrayList implements Cloneable {
        private static final int[] EMPTY = new int[0];
        private int size;
        private int cap;
        private int[] data;

        public IntegerArrayList(int cap) {
            this.cap = cap;
            if (cap == 0) {
                data = EMPTY;
            } else {
                data = new int[cap];
            }
        }

        private void ensureSpace(int req) {
            if (req > cap) {
                while (cap < req) {
                    cap = Math.max(cap + 10, 2 * cap);
                }
                data = Arrays.copyOf(data, cap);
            }
        }

        private void add(int x) {
            ensureSpace(size + 1);
            data[size++] = x;
        }

        private int[] toArray() {
            return Arrays.copyOf(data, size);
        }
    }

    private static class FastInput {
        private final InputStream is;
        private final byte[] buf = new byte[1 << 24];
        private int bufLen;
        private int bufOffset;
        private int next;

        public FastInput() {
            is = System.in;
        }

        private int read() {
            while (bufLen == bufOffset) {
                bufOffset = 0;
                try {
                    bufLen = is.read(buf);
                } catch (IOException e) {
                    bufLen = -1;
                }
                if (bufLen == -1) {
                    return -1;
                }
            }
            return buf[bufOffset++];
        }

        private int readString(char[] data) {
            int offset = 0;
            while (next >= 0 && next <= 32) {
                next = read();
            }

            int originalOffset = offset;
            while (next > 32) {
                data[offset++] = (char) next;
                next = read();
            }

            return offset - originalOffset;
        }
    }
}