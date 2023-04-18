import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class P722D2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[] y = new int[n];
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            y[i] = ir.nextInt();
            set.add(y[i]);
        }

        int N = n - 1;
        sort(y);
        ArrayList<Integer> x = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            x.add(y[i]);
        }
        for (int i = N; i >= 0; i--) {
            int cur = x.get(i);
            int min = cur;
            boolean foundBetter = false;
            while (cur > 0) {
                if (!set.contains(cur)) {
                    if (cur < min) {
                        min = cur;
                        if (min < x.get(N)) {
                            foundBetter = true;
                            break;
                        }
                    }
                }
                cur /= 2;
            }
            x.set(i, min);
            sort(x);
            set.remove(y[i]);
            set.add(min);
            if (foundBetter) {
                i++;
            }
        }

        for (int i = 0; i < n; i++) {
            pw.print(x.get(i) + " ");
        }
        pw.close();
    }

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

    public static void sort(final ArrayList<Integer> array) {
        int l, min = 0xFFFFFFFF, max = 0;
        for (l = 0; l < array.size(); ++l) {
            int i = array.get(l);
            min &= i;
            max |= i;
            if ((i & 0x80000000) == 0) break;
        }
        for (int r = l + 1; r < array.size(); ++r) {
            int i = array.get(r);
            min &= i;
            max |= i;
            if ((i & 0x80000000) != 0) {
                array.set(r, array.get(l));
                array.set(l++, i);
            }
        }
        int use = min ^ max, bit = Integer.highestOneBit(use & 0x7FFFFFFF);
        if (bit == 0) return;
        sort(array, 0, l, use, bit);
        sort(array, l, array.size(), use, bit);
    }

    private static void sort(final ArrayList<Integer> array, final int left, final int right, final int use, int digit) {
        if (right - left <= 96) {
            for (int i = left + 1; i < right; ++i) {
                int tmp = array.get(i), tmp2, j;
                for (j = i; j > left && (tmp2 = array.get(j - 1)) > tmp; --j) array.set(j, tmp2);
                array.set(j, tmp);
            }
            return;
        }
        int l = left;
        while (l < right && (array.get(l) & digit) == 0) ++l;
        for (int r = l + 1; r < right; ++r) {
            int i = array.get(r);
            if ((i & digit) == 0) {
                array.set(r, array.get(l));
                array.set(l++, i);
            }
        }
        if ((digit = Integer.highestOneBit(use & digit - 1)) == 0) return;
        sort(array, left, l, use, digit);
        sort(array, l, right, use, digit);
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
