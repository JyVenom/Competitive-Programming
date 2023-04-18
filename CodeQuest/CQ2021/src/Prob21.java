import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Prob21 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t-- > 0) {
            int l = ir.nextInt();
            int s = ir.nextInt();

            loc[] landmarks = new loc[l];
            for (int i = 0; i < l; i++) {
                landmarks[i] = new loc(ir.nextDouble(), ir.nextDouble());
            }

            loc[] stops = new loc[s];
            for (int i = 0; i < s; i++) {
                stops[i] = new loc(ir.nextDouble(), ir.nextDouble());
            }

            sort(stops);

            int[] cur = new int[l];
            loc[] prev;
            do {
                prev = stops.clone();

                for (int i = 0; i < l; i++) {
                    double min = Double.POSITIVE_INFINITY;
                    int minInd = 0;
                    for (int j = 0; j < s; j++) {
                        double dist = findDist(landmarks[i].x, landmarks[i].y, stops[j].x, stops[j].y);

                        if (dist < min) {
                            min = dist;
                            minInd = j;
                        }
                    }
                    cur[i] = minInd;
                }

                double[] sumX = new double[s];
                double[] sumY = new double[s];
                double[] countX = new double[s];
                double[] countY = new double[s];

                for (int i = 0; i < l; i++) {
                    sumX[cur[i]] += landmarks[i].x;
                    sumY[cur[i]] += landmarks[i].y;
                    countX[cur[i]]++;
                    countY[cur[i]]++;
                }

                for (int i = 0; i < s; i++) {
                    sumX[i] /= countX[i];
                    sumY[i] /= countY[i];
                }

                for (int i = 0; i < s; i++) {
                    stops[i] = new loc(sumX[i], sumY[i]);
                }

                sort(stops);
            }
            while (isDif(prev, stops));

            for (int i = 0; i < s; i++) {
                if (stops[i].x < 0) {
                    stops[i].x = -1.0 * Math.round(-stops[i].x * 10) / 10.0;
                } else {
                    stops[i].x = Math.round(stops[i].x * 10) / 10.0;
                }

                if (landmarks[i].y < 0) {
                    stops[i].y = -1.0 * Math.round(-stops[i].y * 10) / 10.0;
                } else {
                    stops[i].y = Math.round(stops[i].y * 10) / 10.0;
                }

                pw.println(stops[i].x + " " + stops[i].y);
            }
        }

        pw.close();
    }

    private static void sort(loc[] arr) {
        Arrays.sort(arr, (o1, o2) -> {
            if (o1.x < o2.x) {
                return -1;
            } else if (o1.x == o2.x) {
                return Double.compare(o1.y, o2.y);
            } else {
                return 1;
            }
        });
    }

    private static double findDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private static boolean isDif(loc[] prev, loc[] cur) {
        for (int i = 0; i < prev.length; i++) {
            if (prev[i].x != cur[i].x || prev[i].y != cur[i].y) {
                return true;
            }
        }
        return false;
    }

    private static class loc {
        double x, y;

        public loc(double x, double y) {
            this.x = x;
            this.y = y;
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

        private double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
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
}
