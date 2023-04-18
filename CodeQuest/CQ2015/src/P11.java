import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class P11 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        HashMap<Character, Integer> group = new HashMap<>(24);
        group.put('a', 8);
        group.put('b', 1);
        group.put('c', 2);
        group.put('d', 3);
        group.put('e', 8);
        group.put('f', 1);
        group.put('g', 2);
        group.put('h', 7);
        group.put('i', 8);
        group.put('j', 2);
        group.put('k', 2);
        group.put('l', 4);
        group.put('m', 5);
        group.put('n', 5);
        group.put('o', 8);
        group.put('p', 1);
        group.put('q', 2);
        group.put('r', 6);
        group.put('s', 2);
        group.put('t', 3);
        group.put('u', 8);
        group.put('v', 1);
        group.put('w', 7);
        group.put('x', 2);
        group.put('y', 8);
        group.put('z', 2);

        int t = ir.nextInt();
        while (t-- > 0) {
            int n = ir.nextInt();

            ArrayList<String> all = new ArrayList<>(n);
            HashMap<String, Integer> ans = new HashMap<>(n);
            while (n-- > 0) {
                char[] s = ir.nextLine().toLowerCase().toCharArray();

                int start = s.length;
                for (int i = 0; i < s.length; i++) {
                    if (group.get(s[i]) < 7) {
                        start = i;
                        break;
                    }
                }
                ArrayList<Character> s2 = new ArrayList<>(s.length);
                for (char c : s) {
                    s2.add(c);
                }
                for (int i = start + 1; i < s2.size(); i++) {
                    if (group.get(s2.get(i)).equals(group.get(s2.get(i - 1))) || group.get(s2.get(i)) == 7) {
                        s2.remove(i);
                        i--;
                    }
                }
                for (int i = 1; i < s2.size(); i++) {
                    if (group.get(s2.get(i)) == 7 || group.get(s2.get(i)) == 8) {
                        s2.remove(i);
                        i--;
                    }
                }
                for (int i = 1; i < s2.size(); i++) {
                    s2.set(i, (char) ('0' + group.get(s2.get(i))));
                }
                while (s2.size() < 4) {
                    s2.add('0');
                }
                int sz = s2.size();
                while (s2.size() > 4) {
                    s2.remove(--sz);
                }

                s2.set(0, Character.toUpperCase(s2.get(0)));
                StringBuilder sb = new StringBuilder(s2.size());
                for (Character character : s2) {
                    sb.append(character);
                }
                String res = sb.toString();

                if (!ans.containsKey(res)) {
                    ans.put(res, 0);
                    all.add(res);
                }
                ans.replace(res, ans.get(res) + 1);
            }

            Collections.sort(all);

            pw.println("OUTPUT");
            for (String s : all) {
                pw.println(s + " " + ans.get(s));
            }
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

        private String nextLine() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        private boolean isSpaceChar(int c) {
            return c == '\n' || c == '\r' || c == '\t' || c == -1;
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
