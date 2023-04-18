import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class practice_2_2 {
    private static final InputReader2 ir = new InputReader2();
    private static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {

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
            int[] order = new int[n];
            for (int i = 0; i < n; i++) {
                order[i] = i;
            }
            sort(order, 0, n - 1);

            pw.print("! ");
            for (int e : order) {
                pw.print((char) (e + 'A'));
            }
        }

        pw.close();
    }

    private static void merge(int[] arr, int l, int m, int r) throws IOException {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        if (n1 >= 0) System.arraycopy(arr, l, L, 0, n1);
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            pw.println("? " + (char) (R[j] + 'A') + " " + (char) (L[i] + 'A'));
            pw.flush();
            if (ir.nextChar()) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private static void sort(int[] arr, int l, int r) throws IOException {
        if (l < r) {
            int m = (l + r) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
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
