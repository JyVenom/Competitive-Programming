import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1349B3 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        main:
        for (int i = 0; i < t; i++) {
            int n = ir.nextInt();
            int k = ir.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = ir.nextInt();
                if (arr[j] < k) {
                    arr[j] = -1;
                } else if (arr[j] > k) {
                    arr[j] = 1;
                } else {
                    arr[j] = 0;
                }
            }

            int cnt = 0, less = 0;
            for (int j = 0; j < n; j++) {
                if (arr[j] == 0) {
                    cnt++;
                } else if (arr[j] == -1) {
                    less++;
                }
            }
            if (cnt == 0) {
                pw.println("no");
                continue;
            }
            int mdn = (n + 1) / 2;
            if (n == 1 || less < mdn && (less + cnt) >= mdn) {
                pw.println("yes");
                continue;
            }
            for (int j = 2; j < n; j++) {
                if (arr[j] != -1) {
                    if (arr[j - 1] != -1 || arr[j - 2] != -1) {
                        pw.println("yes");
                        continue main;
                    }
                }
            }
            pw.println(arr[0] != -1 && arr[1] != -1 ? "yes" : "no");
        }

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