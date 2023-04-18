import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class StuckInARut3 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = ir.nextInt();
        cow[] cows = new cow[n];
        for (int i = 0; i < n; i++)
            cows[i] = new cow(ir.nextChar() == 78, ir.nextInt(), ir.nextInt(), i);

        ArrayList<cow> curEast = new ArrayList<>();
        Arrays.sort(cows, Comparator.comparingInt(o -> o.x));
        int[] stopped = new int[n];
        for (int i = 0; i < n; i++) {
            if (cows[i].d) {
                for (int j = binSearch(curEast, cows[i].y); j < curEast.size(); j++) {
                    int x = cows[i].x - curEast.get(j).x;
                    int y = curEast.get(j).y - cows[i].y;
                    if (x < y) {
                        stopped[curEast.get(j).id] += (stopped[cows[i].id] + 1);
                        break;
                    } else if (y < x) {
                        stopped[cows[i].id] += (stopped[curEast.get(j).id] + 1);
                        curEast.remove(j);
                        j--;
                    }
                }
            } else {
                curEast.add(cows[i]);
                curEast.sort(Comparator.comparingInt(o -> o.y));
            }
        }

        for (int num : stopped)
            pw.println(num);
        pw.close();
    }

    private static int binSearch(ArrayList<cow> arr, int key) {
        int low = 0;
        int high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid).y < key)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }

    private static class cow {
        int x, y, id;
        boolean d;

        public cow(boolean d, int x, int y, int id) {
            this.d = d;
            this.x = x;
            this.y = y;
            this.id = id;
        }
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 24];
        private int curChar;
        private int numChars;

        public InputReader() {
            this.stream = System.in;
        }

        private int read() throws IOException {
            if (numChars == -1)
                throw new IOException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new IOException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        private int nextInt() throws IOException {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new IOException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res;
        }

        public int nextChar() throws IOException {
            return read();
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == -1;
        }
    }
}
