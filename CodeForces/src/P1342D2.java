import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class P1342D2 {
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
        ArrayList<ArrayList<Integer>> all = new ArrayList<>();
        for (int i = rem.size() - 1; i >= 0; i--) {
            boolean tmp = false;
            for (ArrayList<Integer> integers : all) {
                if (integers.size() < cs[rem.get(i)]) {
                    integers.add(rem.get(i));
                    tmp = true;
                    break;
                }
            }
            if (!tmp) {
                int sz = all.size();
                all.add(new ArrayList<>());
                all.get(sz).add(rem.get(i));
            }
        }

        pw.println(all.size());
        for (ArrayList<Integer> integers : all) {
            pw.print(integers.size() + " ");
            for (Integer integer : integers) {
                pw.print(integer + " ");
            }
            pw.println();
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
