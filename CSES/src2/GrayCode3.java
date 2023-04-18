import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GrayCode3 {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = r.nextInt();
        r.close();

        ArrayList<Integer> all = grayCode(n);
        StringBuilder sb = new StringBuilder();
        for (int cur : all) {
            sb.append(zeroPad(n, Integer.toBinaryString(cur))).append("\n");
        }

        pw.print(sb);
        pw.close();
    }

    private static String zeroPad(int n, String s) {
        return "0".repeat(n - s.length()) + s;
    }

    private static ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(0);
        ans.add(1);

        for (int i = 1; ans.size() < Math.pow(2, n); ++i) {
            if ((i & 1) != 0) {
                ans.add(ans.get(i) * 2 + 1);
                ans.add(ans.get(i) * 2);
            } else {
                ans.add(ans.get(i) * 2);
                ans.add(ans.get(i) * 2 + 1);
            }
        }

        return ans;
    }


    private static class Reader {
        final private int BUFFER_SIZE = 1 << 3;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private int nextInt() throws IOException {
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

        private void close() throws IOException {
            dis.close();
        }
    }
}
