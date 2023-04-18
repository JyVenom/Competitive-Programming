import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class RestaurantCustomers {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = r.nextInt();
        ArrayList<time> times = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            times.add(new time(r.nextInt(), i, true));
            times.add(new time(r.nextInt(), i, false));
        }

        times.sort(Comparator.comparingInt(o -> o.at));
        int n2 = 2 * n;
        HashSet<Integer> cur = new HashSet<>();
        int max = 0;
        for (int i = 0; i < n2; i++) {
            time next = times.get(i);

            if (next.start) {
                cur.add(next.who);
                if (cur.size() > max) {
                    max = cur.size();
                }
            } else {
                cur.remove(next.who);
            }
        }

        pw.println(max);
        pw.close();
    }

    private static class time {
        int at, who;
        boolean start;

        public time(int at, int who, boolean start) {
            this.at = at;
            this.who = who;
            this.start = start;
        }
    }

    private static class Reader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
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

        public void close() throws IOException {
            dis.close();
        }
    }
}
