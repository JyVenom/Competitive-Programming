import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class P7 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t-- > 0) {
            int n = ir.nextInt();
            ArrayList<Ship> ships = new ArrayList<>(n);
            while (n-- > 0) {
                ships.add(new Ship(ir.nextLine(), ir.nextLine(), ir.nextInt(), ir.nextInt()));
            }

            ships.sort((o1, o2) -> {
                if (o1.x < o2.x) {
                    return 1;
                } else if (o1.x == o2.x) {
                    return Integer.compare(o1.y, o2.y);
                } else {
                    return -1;
                }
            });

            int sz = ships.size() - 1;
            while (sz >= 0) {
                pw.println("Destroyed Ship: " + ships.get(sz).name + " xLoc: " + ships.get(sz).x);
                ships.remove(sz--);
                for (int i = 0; i <= sz; i++) {
                    if (ships.get(i).type.equals("A")) {
                        ships.get(i).x -= 10;
                    } else if (ships.get(i).type.equals("B")) {
                        ships.get(i).x -= 20;
                    } else {
                        ships.get(i).x -= 30;
                    }
                }
                ships.sort((o1, o2) -> {
                    if (o1.x < o2.x) {
                        return 1;
                    } else if (o1.x == o2.x) {
                        return Integer.compare(o1.y, o2.y);
                    } else {
                        return -1;
                    }
                });
            }
        }

        pw.close();
    }

    private static class Ship {
        String name, type;
        int x, y;

        public Ship(String name, String type, int x, int y) {
            this.name = name;
            this.type = type;
            this.x = x;
            this.y = y;
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
            return c == '_' || c == ':' || c == '\n' || c == '\r' || c == '\t' || c == -1;
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
