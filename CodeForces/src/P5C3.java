import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

public class P5C3 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        boolean[] arr = ir.nextBoolArr();

        int max = -1;
        int num = 1;
        HashMap<Integer, HashSet<Integer>> pos = new HashMap<>();
        for (int i = arr.length - 2; i >= 0; i--) {
            int I = i + 1;
            if (arr[i] && !arr[I]) {
                add(pos, i, I);
                if (max < 2) {
                    max = 1;
                    num = 1;
                } else if (max == 2) {
                    num++;
                }
            }
            for (int j = i + 3; j < arr.length; j += 2) {
                if (contains(pos, I, j - 1) && arr[i] && !arr[j]) {
                    add(pos, i, j);
                    int dif = j - i;
                    if (dif > max) {
                        max = dif;
                        num = 1;
                    } else if (max == dif) {
                        num++;
                    }
                } else {
                    for (int k = I; k < j; k += 2) {
                        if (contains(pos, i, k) && contains(pos, k + 1, j)) {
                            add(pos, i, j);
                            int dif = j - i;
                            if (dif > max) {
                                max = dif;
                                num = 1;
                            } else if (max == dif) {
                                num++;
                            }
                            break;
                        }
                    }
                }
            }
        }

        pw.println(++max + " " + num);
        pw.close();
    }

    private static void add(HashMap<Integer, HashSet<Integer>> map, int i, int j) {
        if (!map.containsKey(i)) {
            map.put(i, new HashSet<>());
        }
        map.get(i).add(j);
    }

    private static boolean contains(HashMap<Integer, HashSet<Integer>> map, int i, int j) {
        if (!map.containsKey(i)) {
            return false;
        }
        return map.get(i).contains(j);
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

        private boolean[] nextBoolArr() throws IOException {
            byte c;
            ArrayDeque<Byte> q = new ArrayDeque<>();
            while ((c = read()) == '(' || c == ')') {
                q.addLast(c);
            }
            boolean[] res = new boolean[q.size()];
            for (int i = 0; i < res.length; i++) {
                res[i] = q.removeFirst() == '(';
            }
            return res;
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
