import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class YearOfTheCow2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int k = ir.nextInt();
        int N = n + 1;
        int[] arr = new int[N];
        for (int i = 0; i < n; i++) {
            arr[i] = ir.nextInt();
        }

        arr[n] = 0;
        Arrays.sort(arr);
        int[] dif = new int[N];
        dif[1] = arr[1] / 12 * 12;
        for (int i = 2; i < N; i++) {
            dif[i] = (arr[i] / 12) * 12 - (arr[i - 1] / 12 + 1) * 12;
        }
        Arrays.sort(dif);
        int cost = ((arr[n] / 12) + 1) * 12;
        int at = n;
        while (--k > 0) {
            cost -= dif[at--];
        }

        pw.println(cost);
        pw.close();
    }

    private static class InputReader2 {
        private final int BUFFER_SIZE = 1 << 16;
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
