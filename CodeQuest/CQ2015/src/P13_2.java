import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class P13_2 {
    private static final Block base = new Block(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    private static Block[] blocks = null;
    private static boolean[] used = null;
    private static int currentHeight = 0;
    private static int maxHeight = 0;
    private static ArrayDeque<Block> tower = null;
    private static int numBlocks = 0;

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int T = ir.nextInt();
        while (T-- > 0) {
            currentHeight = 0;
            maxHeight = 0;

            int N = ir.nextInt();
            numBlocks = N;

            blocks = new Block[N];
            used = new boolean[N];

            tower = new ArrayDeque<>();
            tower.push(base);

            for (int i = 0; i < N; i++) {
                blocks[i] = new Block(ir.nextInt(), ir.nextInt(), ir.nextInt());
            }

            build();

            pw.println(maxHeight);
        }

        pw.close();
    }

    private static void build() {
        if (isWinPossible()) {
            boolean addedBlock = false;

            Block currentTop = tower.getFirst();

            for (int i = 0; i < numBlocks; i++) {
                if (!used[i]) {
                    for (int j = 0; j < 3; j++) {
                        if (blocks[i].willFitOnTopOf(currentTop)) {
                            addedBlock = true;

                            tower.push(blocks[i]);
                            currentHeight += blocks[i].h;
                            used[i] = true;

                            build();

                            tower.pop();
                            currentHeight -= blocks[i].h;
                            used[i] = false;
                        }

                        blocks[i].rotate();
                    }
                }
            }

            if (!addedBlock) {
                if (currentHeight > maxHeight) {
                    maxHeight = currentHeight;
                }
            }
        }
    }

    private static boolean isWinPossible() {
        int possibleHeight = currentHeight;

        for (int i = 0; i < numBlocks; i++) {
            if (!used[i]) {
                possibleHeight += blocks[i].getMaxDim();
            }
        }

        return (possibleHeight > maxHeight);
    }

    public static class Block {
        public int l, w, h;
        private int numRotations = 0;

        public Block(int s1, int s2, int s3) {

            if (s1 < s2) {
                if (s2 < s3) {
                    h = s1;
                    w = s2;
                    l = s3;
                } else {
                    if (s1 < s3) {
                        h = s1;
                        w = s3;
                    } else {
                        h = s3;
                        w = s1;
                    }
                    l = s2;
                }
            } else {
                if (s1 < s3) {
                    h = s2;
                    w = s1;
                    l = s3;
                } else {
                    if (s2 < s3) {
                        h = s2;
                        w = s3;
                    } else {
                        h = s3;
                        w = s2;
                    }
                    l = s1;
                }
            }
        }

        public void rotate() {
            int temp;

            switch (numRotations++) {
                case 0:
                    // first rotation: 3, 2, 1 --> 3, 1, 2
                    temp = w;
                    w = h;
                    h = temp;
                    break;
                case 1:
                    // second rotation: 3, 1, 2 --> 2, 1, 3
                    temp = l;
                    l = h;
                    h = temp;
                    break;
                case 2:
                    // third rotation: 2, 1, 3 --> 3, 2, 1
                    temp = l;
                    l = h;
                    h = w;
                    w = temp;
                default:
                    // set this here to prevent overflow from too much rotating!
                    numRotations = 0;
                    break;
            }
        }

        public boolean willFitOnTopOf(Block otherBlock) {
            if (otherBlock.l >= this.l) {
                return otherBlock.w >= this.w;
            }
            return false;
        }

        public int getMaxDim() {
            return Math.max(l, Math.max(w, h));
        }
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

        int nextInt() throws IOException {
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
