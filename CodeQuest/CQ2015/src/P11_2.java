import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class P11_2 {
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

            ArrayList<ArrayList<Character>> all = new ArrayList<>(n);
            HashMap<ArrayList<Character>, Integer> ans = new HashMap<>(n);
            while (n-- > 0) {
                ArrayList<Character> s = ir.nextLine();

                int start = s.size();
                for (int i = 0; i < s.size(); i++) {
                    if (group.get(s.get(i)) < 7) {
                        start = i;
                        break;
                    }
                }
                for (int i = start + 1; i < s.size(); i++) {
                    if (group.get(s.get(i)).equals(group.get(s.get(i - 1))) || group.get(s.get(i)) == 7) {
                        s.remove(i);
                        i--;
                    }
                }
                for (int i = 1; i < s.size(); i++) {
                    if (group.get(s.get(i)) == 7 || group.get(s.get(i)) == 8) {
                        s.remove(i);
                        i--;
                    }
                }
                for (int i = 1; i < s.size(); i++) {
                    s.set(i, (char) ('0' + group.get(s.get(i))));
                }
                while (s.size() < 4) {
                    s.add('0');
                }
                int sz = s.size();
                while (s.size() > 4) {
                    s.remove(--sz);
                }

                s.set(0, Character.toUpperCase(s.get(0)));

                if (!ans.containsKey(s)) {
                    ans.put(s, 0);
                    all.add(s);
                }
                ans.replace(s, ans.get(s) + 1);
            }

            all.sort((o1, o2) -> {
                if (o1.size() < o2.size()) {
                    for (int i = 0; i < o1.size(); i++) {
                        if (o1.get(i) < o2.get(i)) {
                            return -1;
                        } else if (o2.get(i) < o1.get(i)) {
                            return 1;
                        }
                    }
                    return -1;
                } else if (o1.size() == o2.size()) {
                    for (int i = 0; i < o1.size(); i++) {
                        if (o1.get(i) < o2.get(i)) {
                            return -1;
                        } else if (o2.get(i) < o1.get(i)) {
                            return 1;
                        }
                    }
                    return 0;
                } else {
                    for (int i = 0; i < o2.size(); i++) {
                        if (o1.get(i) < o2.get(i)) {
                            return -1;
                        } else if (o2.get(i) < o1.get(i)) {
                            return 1;
                        }
                    }
                    return 1;
                }
            });

            pw.println("OUTPUT");
            for (ArrayList<Character> s : all) {
                pw.println(toString(s) + " " + ans.get(s));
            }
        }

        pw.close();
    }

    private static StringBuilder toString(ArrayList<Character> arr) {
        StringBuilder sb = new StringBuilder(arr.size());
        for (Character character : arr) {
            sb.append(character);
        }
        return sb;
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

        private ArrayList<Character> nextLine() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            ArrayList<Character> res = new ArrayList<>();
            do {
                res.add(Character.toLowerCase((char) c));
                c = read();
            } while (!isSpaceChar(c));
            return res;
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
