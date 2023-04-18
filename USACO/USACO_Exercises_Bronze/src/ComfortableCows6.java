import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ComfortableCows6 {
    private static final int[] dirRow = new int[]{-1, 0, 1, 0};
    private static final int[] dirCol = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int N = n + 1;
        int[][] num = new int[2001][2001];
        int count = 0;
        for (int i = 0; i < n; i++) {
            int col = ir.nextInt() + 1000;
            int row = ir.nextInt() + 1000;

            if (num[row][col] == 3) {
                count++;
            }
            for (int j = 0; j < 4; j++) {
                int newRow = row + dirRow[j];
                int newCol = col + dirCol[j];
                num[newRow][newCol]++;
                if (num[newRow][newCol] == 4) {
                    count--;
                } else if (num[newRow][newCol] == 3) {
                    count++;
                }
            }
            pw.println(count);
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
