import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P722D6 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[] tree = new int[41 * n];
        tree[1] = tree[2] = -1;
        int free = 3;
        for (int i = 0; i < n; i++) {
            int cur = ir.nextInt();
            int cNode = 0;
            for (int j = 30 - Integer.numberOfLeadingZeros(cur); j >= 0; j--) {
                int bit = (cur >> j) & 1;
                int nNode = tree[cNode + 1 + bit];
                if (nNode < 0) {
                    tree[free + 1] = tree[free + 2] = -1;
                    tree[cNode + 1 + bit] = nNode = free;
                    free += 3;
                }
                cNode = nNode;
                ++tree[cNode];
            }
        }
        cur:
        for (int i = 0, cur = 1; i < n; cur++) {
            int cNode = 0;
            for (int j = 30 - Integer.numberOfLeadingZeros(cur); j >= 0; j--) {
                int bit = (cur >> j) & 1;
                int nNode = tree[cNode + 1 + bit];
                if (nNode < 0 || tree[nNode] == 0) {
                    cur |= (1 << j) - 1;
                    continue cur;
                }
                cNode = nNode;
            }
            cNode = 0;
            for (int j = 30 - Integer.numberOfLeadingZeros(cur); j >= 0; j--) {
                int bit = (cur >> j) & 1;
                cNode = tree[cNode + 1 + bit];
                --tree[cNode];
            }
            pw.print(cur);
            pw.print(' ');
            ++i;
        }

        pw.close();
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
