import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ComfortableCows11 {
    static final boolean[][] cows = new boolean[2001][2001];
    static final int[][] adj = new int[2001][2001];
    private static final int[] dirRow = new int[]{-1, 0, 1, 0};
    private static final int[] dirCol = new int[]{0, 1, 0, -1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();

        for (int j = 0; j < n; j++) {
            int x = ir.nextInt() + 500;
            int y = ir.nextInt() + 500;
            answer--;
            add(x, y);
            pw.println(answer);
        }

        pw.close();
    }

    private static void add(int x, int y) {
        if (!cows[x][y]) {
            cows[x][y] = true;
            answer++;
            if (adj[x][y] == 3) {
                for (int i = 0; i < 4; i++) {
                    add(x + dirRow[i], y + dirCol[i]);
                }
            }
            for (int i = 0; i < 4; i++) {
                int u = x + dirRow[i];
                int v = y + dirCol[i];
                adj[u][v]++;
                if (cows[u][v] && adj[u][v] == 3) {
                    for (int j = 0; j < 4; j++) {
                        add(u + dirRow[j], v + dirCol[j]);
                    }
                }
            }
        }
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
            do
                ret = ret * 10 + c - '0';
            while ((c = read()) >= '0' && c <= '9');
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
