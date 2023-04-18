import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class MilkVisits32 {
    private static int time = 0;

    public static void main(String[] args) throws IOException {
//        InputReader1 ir = new InputReader1(new FileInputStream("milkvisits.in"));
//        InputReader2 ir = new InputReader2("milkvisits.in");
//        InputReader3 ir = new InputReader3("milkvisits.in");
        InputReader4 ir = new InputReader4(new FileInputStream("milkvisits.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));

        int n = ir.nextInt();
        int m = ir.nextInt();
        int[] type = new int[n];
        for (int i = 0; i < n; i++) {
            type[i] = ir.nextInt() - 1;
        }
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        int N = n - 1;
        for (int i = 0; i < N; i++) {
            int a = ir.nextInt() - 1;
            int b = ir.nextInt() - 1;

            edges.get(a).add(b);
            edges.get(b).add(a);
        }
        HashMap<Integer, HashMap<Integer, ArrayList<int[]>>> friends = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int a = ir.nextInt() - 1;
            int b = ir.nextInt() - 1;
            int c = ir.nextInt() - 1;

            if (!friends.containsKey(a)) {
                friends.put(a, new HashMap<>());
            }
            if (!friends.get(a).containsKey(b)) {
                friends.get(a).put(b, new ArrayList<>());
            }
            friends.get(a).get(b).add(new int[]{c, i});

            if (b != a) {
                if (!friends.containsKey(b)) {
                    friends.put(b, new HashMap<>());
                }
                if (!friends.get(b).containsKey(a)) {
                    friends.get(b).put(a, new ArrayList<>());
                }
                friends.get(b).get(a).add(new int[]{c, i});
            }
        }

        int[][] times = new int[n][2];
        dfs2(edges, times, 0, -1);
        boolean[] ans = new boolean[m];
        ArrayList<Integer> path = new ArrayList<>();
        int[][] lasts = new int[n][2];
        dfs(friends, edges, path, lasts, times, type, ans, 0, -1);
        StringBuilder sb = new StringBuilder(m);
        for (boolean an : ans) {
            sb.append(an ? 1 : 0);
        }

        pw.println(sb);
        pw.close();
    }


    private static void dfs(HashMap<Integer, HashMap<Integer, ArrayList<int[]>>> friends, ArrayList<ArrayList<Integer>> edges, ArrayList<Integer> path, int[][] last, int[][] times, int[] type, boolean[] ans, int cur, int parent) {
        int temp = path.size();
        path.add(cur);
        int[] prev = last[type[cur]].clone();
        last[type[cur]][0] = cur;
        last[type[cur]][1] = path.size();


        if (friends.containsKey(cur)) {
            for (int b : friends.get(cur).keySet()) {
                for (int[] pair : friends.get(cur).get(b)) {
                    int c = pair[0];
                    int d = pair[1];

                    if (ans[d]) {
                        continue;
                    }

                    if (cur == b) {
                        if (type[b] == c) {
                            ans[d] = true;
                        }
                    } else {
                        if (!(last[c][1] == 0)) {
                            int y = last[c][0];
                            if (isAnc(times, y, b)) {
                                if (y != cur) {
                                    int Y = path.get(last[c][1]);
                                    if (notAnc(times, Y, b)) {
                                        ans[d] = true;
                                    }
                                } else {
                                    ans[d] = true;
                                }
                            } else {
                                ans[d] = true;
                            }
                        }
                    }
                }
            }
        }

        for (int next : edges.get(cur)) {
            if (next != parent) {
                dfs(friends, edges, path, last, times, type, ans, next, cur);
            }
        }

        path.remove(temp);
        last[type[cur]] = prev.clone();
    }

    private static void dfs2(ArrayList<ArrayList<Integer>> edges, int[][] times, int cur, int parent) {
        times[cur][0] = time++;

        for (int next : edges.get(cur)) {
            if (next != parent) {
                dfs2(edges, times, next, cur);
            }
        }

        times[cur][1] = time - 1;
    }

    private static boolean isAnc(int[][] times, int a, int b) {
        return times[a][0] < times[b][0] && times[a][1] >= times[b][1];
    }

    private static boolean notAnc(int[][] times, int a, int b) {
//        return isAnc(times, b, a) || times[a][0] > times[b][0] || times[a][1] < times[b][1] || times[a][0] >= times[b][1] || times[a][1] <= times[b][0];
        return isAnc(times, b, a) || times[a][1] < times[b][0] || times[a][0] > times[b][1];
    }


    private static class InputReader1 {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 24];
        private int curChar;
        private int numChars;

        public InputReader1(InputStream stream) {
            this.stream = stream;
        }

        private int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        private int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == -1;
        }
    }

    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader2(String file_name) throws IOException {
            dis = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
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

    private static class InputReader3 {
        private final int BUFFER_SIZE = 1 << 24;
        private final FileInputStream is;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader3(String file_name) throws IOException {
            is = new FileInputStream(file_name);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = is.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }

    private static class InputReader4 {
        /**
         * The default size of the InputReader's buffer is 2<sup>16</sup>.
         */
        private static final int DEFAULT_BUFFER_SIZE = 1 << 24;

        /**
         * The default stream for the InputReader is standard input.
         */
        private static final InputStream DEFAULT_STREAM = System.in;

        // End Of File (EOF) character
        private static final byte EOF = -1;
        // New line character: '\n'
        private static final byte NEW_LINE = 10;
        // Space character: ' '
        private static final byte SPACE = 32;
        // Dash character: '-'
        private static final byte DASH = 45;
        // Dot character: '.'
        private static final byte DOT = 46;
        // Primitive double lookup table used for optimizations.
        private static final double[][] doubles = {
                {0.0d, 0.00d, 0.000d, 0.0000d, 0.00000d, 0.000000d, 0.0000000d, 0.00000000d, 0.000000000d, 0.0000000000d, 0.00000000000d, 0.000000000000d, 0.0000000000000d, 0.00000000000000d, 0.000000000000000d, 0.0000000000000000d, 0.00000000000000000d, 0.000000000000000000d, 0.0000000000000000000d, 0.00000000000000000000d, 0.000000000000000000000d},
                {0.1d, 0.01d, 0.001d, 0.0001d, 0.00001d, 0.000001d, 0.0000001d, 0.00000001d, 0.000000001d, 0.0000000001d, 0.00000000001d, 0.000000000001d, 0.0000000000001d, 0.00000000000001d, 0.000000000000001d, 0.0000000000000001d, 0.00000000000000001d, 0.000000000000000001d, 0.0000000000000000001d, 0.00000000000000000001d, 0.000000000000000000001d},
                {0.2d, 0.02d, 0.002d, 0.0002d, 0.00002d, 0.000002d, 0.0000002d, 0.00000002d, 0.000000002d, 0.0000000002d, 0.00000000002d, 0.000000000002d, 0.0000000000002d, 0.00000000000002d, 0.000000000000002d, 0.0000000000000002d, 0.00000000000000002d, 0.000000000000000002d, 0.0000000000000000002d, 0.00000000000000000002d, 0.000000000000000000002d},
                {0.3d, 0.03d, 0.003d, 0.0003d, 0.00003d, 0.000003d, 0.0000003d, 0.00000003d, 0.000000003d, 0.0000000003d, 0.00000000003d, 0.000000000003d, 0.0000000000003d, 0.00000000000003d, 0.000000000000003d, 0.0000000000000003d, 0.00000000000000003d, 0.000000000000000003d, 0.0000000000000000003d, 0.00000000000000000003d, 0.000000000000000000003d},
                {0.4d, 0.04d, 0.004d, 0.0004d, 0.00004d, 0.000004d, 0.0000004d, 0.00000004d, 0.000000004d, 0.0000000004d, 0.00000000004d, 0.000000000004d, 0.0000000000004d, 0.00000000000004d, 0.000000000000004d, 0.0000000000000004d, 0.00000000000000004d, 0.000000000000000004d, 0.0000000000000000004d, 0.00000000000000000004d, 0.000000000000000000004d},
                {0.5d, 0.05d, 0.005d, 0.0005d, 0.00005d, 0.000005d, 0.0000005d, 0.00000005d, 0.000000005d, 0.0000000005d, 0.00000000005d, 0.000000000005d, 0.0000000000005d, 0.00000000000005d, 0.000000000000005d, 0.0000000000000005d, 0.00000000000000005d, 0.000000000000000005d, 0.0000000000000000005d, 0.00000000000000000005d, 0.000000000000000000005d},
                {0.6d, 0.06d, 0.006d, 0.0006d, 0.00006d, 0.000006d, 0.0000006d, 0.00000006d, 0.000000006d, 0.0000000006d, 0.00000000006d, 0.000000000006d, 0.0000000000006d, 0.00000000000006d, 0.000000000000006d, 0.0000000000000006d, 0.00000000000000006d, 0.000000000000000006d, 0.0000000000000000006d, 0.00000000000000000006d, 0.000000000000000000006d},
                {0.7d, 0.07d, 0.007d, 0.0007d, 0.00007d, 0.000007d, 0.0000007d, 0.00000007d, 0.000000007d, 0.0000000007d, 0.00000000007d, 0.000000000007d, 0.0000000000007d, 0.00000000000007d, 0.000000000000007d, 0.0000000000000007d, 0.00000000000000007d, 0.000000000000000007d, 0.0000000000000000007d, 0.00000000000000000007d, 0.000000000000000000007d},
                {0.8d, 0.08d, 0.008d, 0.0008d, 0.00008d, 0.000008d, 0.0000008d, 0.00000008d, 0.000000008d, 0.0000000008d, 0.00000000008d, 0.000000000008d, 0.0000000000008d, 0.00000000000008d, 0.000000000000008d, 0.0000000000000008d, 0.00000000000000008d, 0.000000000000000008d, 0.0000000000000000008d, 0.00000000000000000008d, 0.000000000000000000008d},
                {0.9d, 0.09d, 0.009d, 0.0009d, 0.00009d, 0.000009d, 0.0000009d, 0.00000009d, 0.000000009d, 0.0000000009d, 0.00000000009d, 0.000000000009d, 0.0000000000009d, 0.00000000000009d, 0.000000000000009d, 0.0000000000000009d, 0.00000000000000009d, 0.000000000000000009d, 0.0000000000000000009d, 0.00000000000000000009d, 0.000000000000000000009d}
        };
        // Primitive data type lookup tables used for optimizations
        private static final int[] ints = new int[58];

        static {
            int value = 0;
            for (int i = 48; i < 58; i++) ints[i] = value++;
        }

        // Variables associated with the byte buffer.
        private final byte[] buf;
        private final InputStream stream;
        private int bufIndex;
        private int numBytesRead;

        /**
         * Create an InputReader that reads from standard input.
         *
         * @param stream Takes an InputStream as a parameter to read from.
         */
        public InputReader4(InputStream stream) {
            this(stream, DEFAULT_BUFFER_SIZE);
        }

        /**
         * Create an InputReader that reads from standard input.
         *
         * @param stream     Takes an {@link InputStream#InputStream() InputStream} as a parameter to read from.
         * @param bufferSize The size of the buffer to use.
         */
        public InputReader4(InputStream stream, int bufferSize) {
            if (stream == null || bufferSize <= 0)
                throw new IllegalArgumentException();
            buf = new byte[bufferSize];
            // A reusable character buffer when reading string data.
            this.stream = stream;
        }

        /**
         * Read values from the input stream until you reach a character with a
         * higher ASCII value than 'token'.
         *
         * @return Returns 0 if a value greater than the token was reached or -1 if
         * the end of the stream was reached.
         * @throws IOException Throws exception at end of stream.
         */
        private int readJunk() throws IOException {

            if (numBytesRead == EOF) return EOF;

            // Seek to the first valid position index
            do {

                while (bufIndex < numBytesRead) {
                    if (buf[bufIndex] > 44) return 0;
                    bufIndex++;
                }

                // reload buffer
                numBytesRead = stream.read(buf);
                if (numBytesRead == EOF) return EOF;
                bufIndex = 0;

            } while (true);

        }

        /**
         * Reads a 32 bit signed integer from input stream.
         *
         * @return The next integer value in the stream.
         * @throws IOException Throws exception at end of stream.
         */
        public int nextInt() throws IOException {

            if (readJunk() == EOF) throw new IOException();
            int sgn = 1, res = 0;

            // 'c' is used to refer to the current character in the stream
            int c = buf[bufIndex];
            if (c == DASH) {
                sgn = -1;
                bufIndex++;
            }

            do {

                while (bufIndex < numBytesRead) {
                    if (buf[bufIndex] > SPACE) {
                        res = (res << 3) + (res << 1);
                        res += ints[buf[bufIndex++]];
                    } else {
                        bufIndex++;
                        return res * sgn;
                    }
                }

                // Reload buffer
                numBytesRead = stream.read(buf);
                if (numBytesRead == EOF) return res * sgn;
                bufIndex = 0;

            } while (true);

        }
    }
}
