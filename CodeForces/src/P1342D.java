import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class P1342D {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int k = ir.nextInt();
        ArrayList<Integer> rem = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            rem.add(ir.nextInt());
        }
        int[] cs = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            cs[i] = ir.nextInt();
        }

        Collections.sort(rem);
        int ans = 0;
        StringBuilder sb = new StringBuilder(2 * n);
        while (!rem.isEmpty()) {
            ans++;
            int count = 0;
            StringBuilder tmp = new StringBuilder(n);
            for (int i = rem.size() - 1; i >= 0; i--) {
                if (count < cs[rem.get(i)]) {
                    count++;
                    tmp.append(" ").append(rem.get(i));
                    rem.remove(i);
                }
            }
            sb.append(count).append(tmp).append("\n");
        }

        pw.println(ans);
        pw.println(sb);
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
