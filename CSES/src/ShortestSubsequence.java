import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashSet;

public class ShortestSubsequence {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();

        byte[] s = ir.nextCharArr();

        HashSet<Byte> all = new HashSet<>();
        all.add((byte) 'A');
        all.add((byte) 'C');
        all.add((byte) 'G');
        all.add((byte) 'T');
        HashSet<Byte> rem = new HashSet<>(all);
        StringBuilder sb = new StringBuilder(s.length / 4);
        for (byte b : s) {
            rem.remove(b);
            if (rem.size() == 0) {
                sb.append((char) b);
                rem.addAll(all);
            }
        }
        sb.append(Character.toChars(rem.iterator().next()));

        System.out.println(sb);
    }

    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 20;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader2() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private byte[] nextCharArr() throws IOException {
            byte c;
            ArrayDeque<Byte> q = new ArrayDeque<>();
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                q.addLast(c);
            }
            byte[] res = new byte[q.size()];
            for (int i = 0; i < res.length; i++) {
                res[i] = q.removeFirst();
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
