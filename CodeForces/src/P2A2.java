import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class P2A2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, ArrayList<int[]>> map2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String p = ir.nextLine();

            if (!map.containsKey(p)) {
                map.put(p, 0);
                map2.put(p, new ArrayList<>());
            }
            map.replace(p, map.get(p) + ir.nextInt());
            map2.get(p).add(new int[]{map.get(p), i});
        }
        ArrayList<helper> all = new ArrayList<>();
        for (String s : map.keySet()) {
            all.add(new helper(s, map.get(s)));
        }
        all.sort((o1, o2) -> o2.v - o1.v);
        ArrayList<String> all2 = new ArrayList<>();
        int max = all.get(0).v;
        all2.add(all.get(0).p);
        for (int i = 1; i < all.size(); i++) {
            if (all.get(i).v == max) {
                all2.add(all.get(i).p);
            } else {
                break;
            }
        }
        if (all2.size() == 1) {
            pw.println(all.get(0).p);
        } else {
            int low = Integer.MAX_VALUE;
            String ans = "";
            for (String s : all2) {
                ArrayList<int[]> temp = map2.get(s);
                for (int[] ints : temp) {
                    if (ints[0] >= max) {
                        if (ints[1] < low) {
                            low = ints[1];
                            ans = s;
                        }
                        break;
                    }
                }
            }
            pw.println(ans);
        }
        pw.close();
    }

    private static class helper {
        String p;
        int v;

        public helper(String p, int v) {
            this.p = p;
            this.v = v;
        }
    }

    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 24;
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
