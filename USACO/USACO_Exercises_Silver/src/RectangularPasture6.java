import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class RectangularPasture6 {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = r.nextInt();
        int[][] cows = new int[n][2];
        for (int i = 0; i < n; i++) {
            cows[i][0] = r.nextInt();
            cows[i][1] = r.nextInt();
        }

        Arrays.sort(cows, Comparator.comparingInt(o -> o[0]));
        int count = n + 1;
        for (int i = 0; i < 1; i++) {
            ArrayList<Integer> all = new ArrayList<>();
            ArrayList<Integer> all2 = new ArrayList<>();
            all.add(cows[i][1]);
            all2.add(cows[i][1]);
            int above1 = 1;
            int below1 = 1;
            for (int j = i + 1; j < n; j++) {
                all.add(cows[j][1]);
                all2.add(cows[j][1]);
                Collections.sort(all);

                int above = all.size() - binSearch(all, cows[j][1], all.size() - 1);
                int below = binSearch(all, cows[j][1], all.size() - 1) + 1;

                if (cows[j][1] > cows[i][1]) {
                    above1++;
                    count += below1 * above;
                }
                else {
                    below1++;
                    count += below * above1;
                }

                ArrayList<Integer> all3 = new ArrayList<>(all2);
                for (int k = i + 1; k < j; k++) {
                    all3.remove(0);
                    ArrayList<Integer> all4 = new ArrayList<>(all3);
                    Collections.sort(all4);
                    if (cows[k - 1][1] > cows[j][1]) {
                        above--;
                    }
                    else {
                        below--;
                    }
                    if (cows[j][1] > cows[k][1]) {
                        int below2 = binSearch(all4, cows[k][1], all4.size() - 1) + 1;
                        count += below2 * above;
                    }
                    else {
                        int above2 = all4.size() - binSearch(all4, cows[k][1], all4.size() - 1);
                        count += above2 * below;
                    }
                }
            }
        }

        pw.println(count);
        pw.close();
    }

    private static int binSearch(ArrayList<Integer> arr, int key, int high) {
        int low = 0;
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
            return (-1 * low) - 1;
        }
        else {
            return index;
        }
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            din.close();
        }
    }
}
