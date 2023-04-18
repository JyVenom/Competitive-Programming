import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class P2A {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        HashMap<String, Integer> end = new HashMap<>();
        ArrayList<move> moves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String p = ir.nextLine();
            int d = ir.nextInt();

            if (!end.containsKey(p)) {
                end.put(p, 0);
            }
            end.put(p, end.get(p) + d);
            if (end.get(p) > 0) {
                moves.add(new move(p, end.get(p)));
            }
        }
        int max = 0;
        HashSet<String> all = new HashSet<>();
        for (String s : end.keySet()) {
            if (end.get(s) > max) {
                max = end.get(s);
                all.clear();
                all.add(s);
            } else if (end.get(s) == max) {
                all.add(s);
            }
        }
        for (move move : moves) {
            if (all.contains(move.p)) {
                if (move.v >= max) {
                    pw.println(move.p);
                    break;
                }
            }
        }
        pw.close();
    }

    private static class move {
        String p;
        int v;

        public move(String p, int v) {
            this.p = p;
            this.v = v;
        }
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

        private String nextLine() throws IOException {
            byte[] buf = new byte[BUFFER_SIZE]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == ' ')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
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
