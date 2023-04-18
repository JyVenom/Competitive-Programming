import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class P1370D2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int k = ir.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = ir.nextInt();
        }

        if (k == 2) {
            int min = 1000000000;
            for (int i = 0; i < n; i++) {
                min = Math.min(min, arr[i]);
            }
            pw.println(min);
            pw.close();
            return;
        }
        int ans = 1000000000;
        if (k % 2 == 1) {
            int half = k / 2;
            int minInc = 0, minExc;
            PriorityQueue<Integer> pqI = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> pqE = new PriorityQueue<>(Collections.reverseOrder());
            for (int i = 1; i <= half; i++) {
                int i2 = i * 2;
                pqE.add(arr[i2]);
                if (i < half - 1) {
                    pqI.add(arr[i2]);
                }
                if (arr[i2] > minInc) {
                    minInc = arr[i2];
                }
            }
            minExc = minInc;
            int tmp = 2 * half + 1;
            if (tmp < n) {
                if (arr[tmp] < arr[tmp - 1]) {
                    pqI.add(arr[tmp]);
                    minInc = pqI.peek();
                } else {
                    pqI.add(arr[tmp - 1]);
                    minInc = pqI.peek();
                }
                for (int i = tmp + 1; i < n; i++) {
                    if (minInc < minExc) {
                        minExc = minInc;
                        pqE.clear();
                        pqE.addAll(pqI);
                    }

                    if (arr[i] < minExc) {
                        pqI.clear();
                        pqI.addAll(pqE);
                        pqI.add(arr[i]);
                        pqI.poll();
                        minInc = pqI.peek();
                    }
                }
            }
            ans = Math.max(arr[0], Math.min(minInc, minExc));
        } else {
            if (k == n) {
                int even = 0;
                for (int i = 0; i < n; i += 2) {
                    even = Math.max(even, arr[i]);
                }
                int odd = 0;
                for (int i = 1; i < n; i += 2) {
                    odd = Math.max(odd, arr[i]);
                }
                pw.println(Math.min(even, odd));
                pw.close();
                return;
            }
        }
        int half = k / 2;
        int minInc = 0, minExc;
        PriorityQueue<Integer> pqI = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> pqE = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 1; i <= half; i++) {
            int i2 = i * 2;
            pqE.add(arr[i2]);
            if (i < half - 1) {
                pqI.add(arr[i2]);
            }
            if (arr[i2] > minInc) {
                minInc = arr[i2];
            }
        }
        minExc = minInc;
        int tmp = 2 * half + 1;
        if (tmp < n) {
            if (arr[tmp] < arr[tmp - 1]) {
                pqI.add(arr[tmp]);
                minInc = pqI.peek();
            } else {
                pqI.add(arr[tmp - 1]);
                minInc = pqI.peek();
            }
            for (int i = tmp + 1; i < n; i++) {
                if (minInc < minExc) {
                    minExc = minInc;
                    pqE.clear();
                    pqE.addAll(pqI);
                }

                if (arr[i] < minExc) {
                    pqI.clear();
                    pqI.addAll(pqE);
                    pqI.add(arr[i]);
                    pqI.poll();
                    minInc = pqI.peek();
                }
            }
        }
        ans = Math.min(ans, Math.min(minInc, minExc));
        minInc = 0;
        pqI.clear();
        pqE.clear();
        for (int i = 0; i < half; i++) {
            int i2 = i * 2 + 1;
            pqE.add(arr[i2]);
            if (i < half - 1) {
                pqI.add(arr[i2]);
            }
            if (arr[i2] > minInc) {
                minInc = arr[i2];
            }
        }
        minExc = minInc;
        tmp = 2 * half;
        if (arr[tmp] < arr[tmp - 1]) {
            pqI.add(arr[tmp]);
            minInc = pqI.peek();
        } else {
            pqI.add(arr[tmp - 1]);
            minInc = pqI.peek();
        }
        for (int i = tmp + 1; i < n; i++) {
            if (minInc < minExc) {
                minExc = minInc;
                pqE.clear();
                pqE.addAll(pqI);
            }

            if (arr[i] < minExc) {
                pqI.clear();
                pqI.addAll(pqE);
                pqI.add(arr[i]);
                pqI.poll();
                minInc = pqI.peek();
            }
        }
        ans = Math.min(ans, Math.min(minInc, minExc));

        pw.println(ans);
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
    }
}
