import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class ConvexHull2 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.readInt();
        ArrayList<Point> points = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            points.add(new Point(ir.readInt(), ir.readInt()));
        }
        ir.close();

        ArrayList<Point> hull = convexHull(points);
        StringBuilder sb = new StringBuilder();
        for (Point point : hull) {
            sb.append(point.x).append(" ").append(point.y).append("\n");
        }

        pw.println(hull.size());
        pw.print(sb);
        pw.close();
    }

    private static class Point implements Comparable<Point> {
        private int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(x, o.x);
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }

    private static ArrayList<Point> convexHull(ArrayList<Point> p) {
        p.sort(Point::compareTo);
        ArrayList<Point> h = new ArrayList<>();

        // lower hull
        for (Point pt : p) {
            while (h.size() >= 2 && ccw(h.get(h.size() - 2), h.get(h.size() - 1), pt)) {
                h.remove(h.size() - 1);
            }
            h.add(pt);
        }

        // upper hull
        int t = h.size() + 1;
        for (int i = p.size() - 1; i >= 0; i--) {
            Point pt = p.get(i);
            while (h.size() >= t && ccw(h.get(h.size() - 2), h.get(h.size() - 1), pt)) {
                h.remove(h.size() - 1);
            }
            h.add(pt);
        }

        h.remove(h.size() - 1);
        return h;
    }

    // ccw returns true if the three points make a counter-clockwise turn
    private static boolean ccw(Point a, Point b, Point c) {
        return ((b.x - a.x) * (c.y - a.y)) <= ((b.y - a.y) * (c.x - a.x));
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 24];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public void close() throws IOException {
            stream.close();
        }

        public int read() {
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

        public int readInt() {
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

        public boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}
