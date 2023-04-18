import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class practice_2_3 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        ir.nextInt();

        if (n == 5) {
            int a = 0, b = 1, c = 2, d = 3, e = 4;
            pw.println("? A B");
            pw.flush();
            if (ir.nextChar()) {
                a = 1;
                b = 0;
            }
            pw.println("? C D");
            pw.flush();
            if (ir.nextChar()) {
                c = 3;
                d = 2;
            }
            pw.println("? " + (char) (a + 'A') + " " + (char) (c + 'A'));
            pw.flush();
            if (ir.nextChar()) {
                int tmp = a;
                a = c;
                c = tmp;
                tmp = b;
                b = d;
                d = tmp;
            }
            pw.println("? " + (char) (e + 'A') + " " + (char) (c + 'A'));
            pw.flush();
            if (ir.nextChar()) {
                pw.println("? " + (char) (e + 'A') + " " + (char) (d + 'A'));
                pw.flush();
                if (ir.nextChar()) {
                    pw.println("? " + (char) (b + 'A') + " " + (char) (d + 'A'));
                    pw.flush();
                    if (ir.nextChar()) {
                        pw.println("? " + (char) (b + 'A') + " " + (char) (e + 'A'));
                        pw.flush();
                        if (ir.nextChar()) {
                            pw.println("! " + (char) (a + 'A') + (char) (c + 'A') + (char) (d + 'A') + (char) (e + 'A') + (char) (b + 'A'));
                        } else {
                            pw.println("! " + (char) (a + 'A') + (char) (c + 'A') + (char) (d + 'A') + (char) (b + 'A') + (char) (e + 'A'));
                        }
                    } else {
                        pw.println("? " + (char) (c + 'A') + " " + (char) (b + 'A'));
                        pw.flush();
                        if (ir.nextChar()) {
                            pw.println("! " + (char) (a + 'A') + (char) (b + 'A') + (char) (c + 'A') + (char) (d + 'A') + (char) (e + 'A'));
                        } else {
                            pw.println("! " + (char) (a + 'A') + (char) (c + 'A') + (char) (b + 'A') + (char) (d + 'A') + (char) (e + 'A'));
                        }
                    }
                } else {
                    pw.println("? " + (char) (b + 'A') + " " + (char) (e + 'A'));
                    pw.flush();
                    if (ir.nextChar()) {
                        pw.println("? " + (char) (b + 'A') + " " + (char) (d + 'A'));
                        pw.flush();
                        if (ir.nextChar()) {
                            pw.println("! " + (char) (a + 'A') + (char) (c + 'A') + (char) (e + 'A') + (char) (d + 'A') + (char) (b + 'A'));
                        } else {
                            pw.println("! " + (char) (a + 'A') + (char) (c + 'A') + (char) (e + 'A') + (char) (b + 'A') + (char) (d + 'A'));
                        }
                    } else {
                        pw.println("? " + (char) (c + 'A') + " " + (char) (b + 'A'));
                        pw.flush();
                        if (ir.nextChar()) {
                            pw.println("! " + (char) (a + 'A') + (char) (b + 'A') + (char) (c + 'A') + (char) (e + 'A') + (char) (d + 'A'));
                        } else {
                            pw.println("! " + (char) (a + 'A') + (char) (c + 'A') + (char) (b + 'A') + (char) (e + 'A') + (char) (d + 'A'));
                        }
                    }
                }
            } else {
                pw.println("? " + (char) (a + 'A') + " " + (char) (e + 'A'));
                pw.flush();
                if (ir.nextChar()) {
                    pw.println("? " + (char) (b + 'A') + " " + (char) (c + 'A'));
                    pw.flush();
                    if (ir.nextChar()) {
                        pw.println("? " + (char) (b + 'A') + " " + (char) (d + 'A'));
                        pw.flush();
                        if (ir.nextChar()) {
                            pw.println("! " + (char) (e + 'A') + (char) (a + 'A') + (char) (c + 'A') + (char) (d + 'A') + (char) (b + 'A'));
                        } else {
                            pw.println("! " + (char) (e + 'A') + (char) (a + 'A') + (char) (c + 'A') + (char) (b + 'A') + (char) (d + 'A'));
                        }
                    } else {
                        pw.println("! " + (char) (e + 'A') + (char) (a + 'A') + (char) (b + 'A') + (char) (c + 'A') + (char) (d + 'A'));
                    }
                } else {
                    pw.println("? " + (char) (b + 'A') + " " + (char) (c + 'A'));
                    pw.flush();
                    if (ir.nextChar()) {
                        pw.println("? " + (char) (b + 'A') + " " + (char) (d + 'A'));
                        pw.flush();
                        if (ir.nextChar()) {
                            pw.println("! " + (char) (a + 'A') + (char) (e + 'A') + (char) (c + 'A') + (char) (d + 'A') + (char) (b + 'A'));
                        } else {
                            pw.println("! " + (char) (a + 'A') + (char) (e + 'A') + (char) (c + 'A') + (char) (b + 'A') + (char) (d + 'A'));
                        }
                    } else {
                        pw.println("? " + (char) (e + 'A') + " " + (char) (b + 'A'));
                        pw.flush();
                        if (ir.nextChar()) {
                            pw.println("! " + (char) (a + 'A') + (char) (b + 'A') + (char) (e + 'A') + (char) (c + 'A') + (char) (d + 'A'));
                        } else {
                            pw.println("! " + (char) (a + 'A') + (char) (e + 'A') + (char) (b + 'A') + (char) (c + 'A') + (char) (d + 'A'));
                        }
                    }
                }
            }
        } else {
            ArrayList<Integer> order = new ArrayList<>();
            order.add(0);
            for (int i = 1; i < n; i++) {
                order.add(binSearch(ir, pw, order, i), i);
            }

            pw.print("! ");
            for (int e : order) {
                pw.print((char) (e + 'A'));
            }
        }

        pw.close();
    }

    private static int binSearch(InputReader2 ir, PrintWriter pw, ArrayList<Integer> arr, int key) throws IOException {
        int low = 0, high = arr.size() - 1;
        char key2 = (char) (key + 'A');

        while (low <= high) {
            int mid = (low + high) / 2;
            pw.println("? " + (char) (arr.get(mid) + 'A') + " " + key2);
            pw.flush();
            if (ir.nextChar()) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
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

        private boolean nextChar() throws IOException {
            byte c = read();
            while (c <= ' ')
                c = read();
            return c == '>';
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
