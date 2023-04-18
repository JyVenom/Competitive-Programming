import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class P1349B {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        main:
        for (int i = 0; i < t; i++) {
            int n = ir.nextInt();
            int k = ir.nextInt();
            ArrayList<Integer> locs = new ArrayList<>();
            int less = 0;
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                int tmp = ir.nextInt();
                if (tmp < k) {
                    arr[j] = -1;
                    less++;
                } else if (tmp == k) {
                    locs.add(j);
                } else {
                    arr[j] = 1;
                }
            }

            int median = (n + 1) / 2;
            if (locs.size() == 0) {
                pw.println("no");
                continue;
            } else if (less < median && (less + locs.size()) >= median) {
                pw.println("yes");
                continue;
            }

            int N = n - 1;
            for (int j = 1; j < locs.size(); j++) {
                if (locs.get(j) - locs.get(j - 1) <= 2) {
                    pw.println("yes");
                    continue main;
                }
            }
            for (int loc : locs) {
                if (loc > 0) {
                    if (arr[loc - 1] == 1) {
                        pw.println("yes");
                        continue main;
                    }
                }
                if (loc < N) {
                    if (arr[loc + 1] == 1) {
                        pw.println("yes");
                        continue main;
                    }
                }
            }
            for (int j = 2; j < n; j++) {
                if (arr[j] != -1) {
                    if (arr[j - 1] != -1 || arr[j - 2] != -1) {
                        pw.println("yes");
                        continue main;
                    }
                }
            }
            if (arr[0] != -1 && arr[1] != -1) {
                pw.println("yes");
                continue;
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