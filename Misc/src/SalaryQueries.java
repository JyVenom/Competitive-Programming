import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class SalaryQueries {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int q = ir.nextInt();
        int[] vals = new int[n];
        ArrayList<Integer> vals2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vals[i] = ir.nextInt();
            vals2.add(vals[i]);
        }

        Collections.sort(vals2);
        for (int i = 0; i < q; i++) {
            int type = ir.nextChar();
            if (type == 63) { //count
                int a = ir.nextInt();
                int b = ir.nextInt();
                pw.println(binSearch3(vals2, b) - binSearch2(vals2, a));
            } else {
                int k = ir.nextInt() - 1;
                int x = ir.nextInt();
                vals2.remove(binSearch(vals2, vals[k]));
                vals2.add(binSearch(vals2, x), x);
                vals[k] = x;
            }
        }

        pw.close();
    }

    private static int binSearch(ArrayList<Integer> arr, int key) {
        int low = 0;
        int high = arr.size() - 1;
        int index = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) < key) {
                low = mid + 1;
            } else if (arr.get(mid) > key) {
                high = mid - 1;
            } else if (arr.get(mid) == key) {
                index = mid;
                break;
            }
        }
        if (index == Integer.MAX_VALUE) {
            return low;
        } else {
            return index;
        }
    }

    private static int binSearch2(ArrayList<Integer> arr, int key) {
        int low = 0;
        int high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cur = arr.get(mid);
            if (mid == 0) {
                if (cur < key) {
                    low = mid + 1;
                } else {
                    return 0;
                }
            }
            int prev = arr.get(mid - 1);
            if (cur < key) {
                low = mid + 1;
            } else if (prev >= key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return arr.size();
    }

    private static int binSearch3(ArrayList<Integer> arr, int key) {
        int low = 0;
        int high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cur = arr.get(mid);
            if (mid == 0) {
                if (cur <= key) {
                    low = mid + 1;
                } else {
                    return 0;
                }
            } else {
                int prev = arr.get(mid - 1);
                if (cur <= key) {
                    low = mid + 1;
                } else if (prev > key) {
                    high = mid - 1;
                } else {
                    return mid;
                }
            }
        }
        return arr.size();
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 24];
        private int curChar;
        private int numChars;

        public InputReader() throws FileNotFoundException {
            this.stream = new FileInputStream("test.txt");
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

        private int nextChar() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            return c;
        }

        private int nextInt() throws IOException {
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
