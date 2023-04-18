import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class BOMB1 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        String a = ir.nextLine();
        String b = ir.nextLine();
        String c = ir.nextLine();

        if (!a.equals("red") && !b.equals("red") && !c.equals("red")) {
            pw.println("Cut wire 2");
        } else if (c.equals("white")) {
            pw.println("Cut wire 3");
        } else if ((a.equals("blue") && b.equals("blue")) || (a.equals("blue") && c.equals("blue")) || (b.equals("blue") && c.equals("blue"))) {
            pw.println("Cut wire 1");
        } else {
            pw.println("Cut wire 3");
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
            return c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == '\f' || c == -1;
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
