import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ABC194B2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[] as = new int[n];
        int[] bs = new int[n];
        for (int i = 0; i < n; i++) {
            as[i] = ir.nextInt();
            bs[i] = ir.nextInt();
        }

        int min = 200000;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, as[i] + bs[i]);
            for (int j = i + 1; j < n; j++) {
                min = Math.min(min, Math.max(as[i], bs[j]));
                min = Math.min(min, Math.max(as[j], bs[i]));
            }
        }

        pw.println(min);
        pw.close();
    }

    private static class InputReader2 {
        private final int BUFFER_SIZE = 1 << 5;
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

