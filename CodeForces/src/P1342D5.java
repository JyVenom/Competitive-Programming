import java.io.PrintWriter;

public class P1342D5 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = fs.nextInt(), k = fs.nextInt();
        int[] m = new int[n], c = new int[k];
        for (int i = 0; i < n; ++i) m[i] = fs.nextInt();
        for (int i = 0; i < k; ++i) c[i] = fs.nextInt();
        Arrays.sort(m);
        int[] msum = new int[k + 1];
        for (int i : m) ++msum[i];
        for (int i = k; i > 0; --i) msum[i - 1] += msum[i];
        {
            int min = 0, max = n, mid, halfDiff;
            cont:
            while ((halfDiff = max - min >> 1) != 0) {
                mid = min + halfDiff;
                for (int i = 0; i < k; ++i) {
                    if (msum[i + 1] > (long) c[i] * mid) {
                        min = mid;
                        continue cont;
                    }
                }
                max = mid;
            }
            out.println(max);
            for (int i = 0; i < max; ++i) {
                int len = (max + n - i - 1) / max;
                out.print(len);
                for (int j = i; j < n; j += max) {
                    out.print(" " + m[j]);
                }
                out.println();
            }
        }
        out.close();
    }

    static class FastScanner {
        private final java.io.InputStream in = System.in;
        private final byte[] buffer = new byte[1024];
        private int ptr = 0;
        private int buflen = 0;

        private static boolean isPrintableChar(byte c) {
            return 32 < c || c < 0;
        }

        private static boolean isNumber(int c) {
            return '0' <= c && c <= '9';
        }

        private boolean hasNextByte() {
            if (ptr < buflen) return true;
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return buflen > 0;
        }

        private byte readByte() {
            return hasNextByte() ? buffer[ptr++] : -1;
        }

        public boolean hasNext() {
            while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
            return hasNextByte();
        }

        public final int nextInt() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            int n = 0;
            try {
                byte b = readByte();
                if (b == '-') {
                    while (isNumber(b = readByte())) n = n * 10 + '0' - b;
                    return n;
                } else if (!isNumber(b)) throw new NumberFormatException();
                do n = n * 10 + b - '0'; while (isNumber(b = readByte()));
            } catch (java.util.NoSuchElementException ignored) {
            }
            return n;
        }
    }

    public static class Arrays {
        public static void sort(final int[] array) {
            int l, min = 0xFFFFFFFF, max = 0;
            for (l = 0; l < array.length; ++l) {
                int i = array[l];
                min &= i;
                max |= i;
                if ((i & 0x80000000) == 0) break;
            }
            for (int r = l + 1; r < array.length; ++r) {
                int i = array[r];
                min &= i;
                max |= i;
                if ((i & 0x80000000) != 0) {
                    array[r] = array[l];
                    array[l++] = i;
                }
            }
            int use = min ^ max, bit = Integer.highestOneBit(use & 0x7FFFFFFF);
            if (bit == 0) return;
            sort(array, 0, l, use, bit);
            sort(array, l, array.length, use, bit);
        }

        private static void sort(final int[] array, final int left, final int right, final int use, int digit) {
            if (right - left <= 96) {
                for (int i = left + 1; i < right; ++i) {
                    int tmp = array[i], tmp2, j;
                    for (j = i; j > left && (tmp2 = array[j - 1]) > tmp; --j) array[j] = tmp2;
                    array[j] = tmp;
                }
                return;
            }
            int l = left;
            while (l < right && (array[l] & digit) == 0) ++l;
            for (int r = l + 1; r < right; ++r) {
                int i = array[r];
                if ((i & digit) == 0) {
                    array[r] = array[l];
                    array[l++] = i;
                }
            }
            if ((digit = Integer.highestOneBit(use & digit - 1)) == 0) return;
            sort(array, left, l, use, digit);
            sort(array, l, right, use, digit);
        }
    }
}
