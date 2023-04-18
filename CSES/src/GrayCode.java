import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GrayCode {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = r.nextInt();
        r.close();

        ArrayList<String> all = new ArrayList<>();
        all.add("0");
        all.add("1");
        ArrayList<String> temp = new ArrayList<>();
        int size;
        for (int i = 1; i < n; i++) {
            temp.clear();
            size = all.size();
            for (int j = 0; j < size; j++) {
                temp.add(all.get(i) + "0");
                temp.add(all.get(i) + "1");
            }
            all = new ArrayList<>(temp);
        }
        StringBuilder sb = new StringBuilder();
        for (String cur : all) {
            sb.append(cur).append("\n");
        }

        pw.println(sb);
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
