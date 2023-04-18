import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

public class SumOfTwoValues {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = r.nextInt();
        int x = r.nextInt();
        ArrayList<int[]> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(new int[]{r.nextInt(), i});
        }

        nums.sort(Comparator.comparingInt(o -> o[0]));
        int i = 0, j = n - 1;
        while (i < j) {
            int cur = nums.get(i)[0] + nums.get(j)[0];
            if (cur > x) {
                j--;
            } else if (cur < x) {
                i++;
            } else {
                pw.println((nums.get(i)[1] + 1) + " " + (nums.get(j)[1] + 1));
                break;
            }
        }
        if (i == j) {
            pw.println("IMPOSSIBLE");
        }

        pw.close();
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
