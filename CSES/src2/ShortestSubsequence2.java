import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayDeque;

public class ShortestSubsequence2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();

        char[] origSeq = ir.nextCharArr();
        int n = origSeq.length, k = 0;
        boolean a, c, g, t;
        char x;
        a = c = g = t = false;
        for (int i = 0; i < n; i++) {
            x = origSeq[i];
            if (x == 'A')
                a = true;
            else if (x == 'C')
                c = true;
            else if (x == 'G')
                g = true;
            else
                t = true;
            if (a && c && g && t) {
                origSeq[k++] = x;
                a = c = g = t = false;
            }
        }
        if (!a)
            origSeq[k++] = 'A';
        else if (!c)
            origSeq[k++] = 'C';
        else if (!g)
            origSeq[k++] = 'G';
        else
            origSeq[k++] = 'T';
        System.out.println(new String(origSeq, 0, k));
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

        private char[] nextCharArr() throws IOException {
            int c;
            ArrayDeque<Character> q = new ArrayDeque<>();
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                q.addLast((char) c);
            }
            char[] res = new char[q.size()];
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
