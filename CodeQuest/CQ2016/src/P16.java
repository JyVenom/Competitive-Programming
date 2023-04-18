import java.io.*;

public class P16 {
    private static int ans;

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        InputReader2 ir = new InputReader2("tmp.txt");
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t-- > 0) {
            int l = ir.nextInt();
            int n = ir.nextInt();

            int n2 = n + 2;
            Star[] stars = new Star[n2];
            for (int i = 1; i <= n; i++) {
                stars[i] = new Star(getType(ir.nextChar()), ir.nextInt(), ir.nextInt(), ir.nextInt());
            }

            ans = 3 * (l - 1);
            if (ans <= 20) {
                pw.println(ans);
            } else {
                stars[0] = new Star(10, 0, 0, 0);
                stars[0].visited = true;
                int N = n - 1;
                int n1 = n + 1;
                stars[n1] = new Star(10, N, N, N);
                dfs(stars, stars[0], 0, 20, n1);
                pw.println(ans);
            }
        }

        pw.println((System.nanoTime() - start) / 1e9);
        pw.close();
    }

    private static void dfs(Star[] stars, Star at, int dist, int energy, int N) {
        if (at == stars[N]) {
            ans = dist;
            return;
        }

        if (at.visited || dist >= ans || dist + getDist(at, stars[N]) >= ans) {
            return;
        }

        for (Star next : stars) {
            if (next != at) {
                int tmp = getDist(at, next);
                if (tmp <= energy) {
                    next.visited = true;
                    energy -= tmp;
                    energy += next.e;
                    if (energy > 20) {
                        energy = 20;
                    }
                    dfs(stars, next, dist + getDist(at, next), energy, N);
                    next.visited = false;
                }
            }
        }
    }

    private static int getDist(Star a, Star b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y) + Math.abs(a.z - b.z);
    }

    private static int getType(char c) {
        if (c == 'M') {
            return 3;
        } else if (c == 'K') {
            return 4;
        } else if (c == 'G') {
            return 5;
        } else if (c == 'F') {
            return 6;
        } else if (c == 'A') {
            return 7;
        } else if (c == 'B') {
            return 8;
        } else {
            return 9;
        }
    }

    private static class Star {
        int e, x, y, z;
        boolean visited;

        public Star(int e, int x, int y, int z) {
            this.e = e;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader2(String file_name) throws FileNotFoundException {
            dis = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c < '0')
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return ret;
        }

        private char nextChar() throws IOException {
            byte c = read();
            while (c < 'A')
                c = read();
            return (char) c;
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
