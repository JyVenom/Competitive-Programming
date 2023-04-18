import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1349B2 {
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
                    arr[j] = 0;
                } else if (arr[j] > k) {
                    arr[j] = 2;
                } else {
                    arr[j] = 1;
                }
            }

            boolean flag = false;
            for (int j = 0; j < n; j++) {
                if (arr[j] == 1) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                pw.println("no");
                continue;
            }
            if (n == 1) {
                pw.println("yes");
                continue;
            }
            for (int j = 0; j < n; j++) {
                for (int l = j + 1; l < n && l - j <= 2; l++) {
                    if (arr[j] != 0 && arr[l] != 0) {
                        pw.println("yes");
                        continue main;
                    }
                }
            }
            pw.println("no");
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