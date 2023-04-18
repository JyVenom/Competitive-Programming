import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

public class ConvexHull3 {
    // Small epsilon used for double value comparison.
    private static final double EPS = 1e-5;

    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.readInt();
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point2D.Double(ir.readInt(), ir.readInt());
        }
        ir.close();

        Point2D[] hull = convexHull(points);
        StringBuilder sb = new StringBuilder();
        for (Point2D point : hull) {
            sb.append((int) point.getX()).append(" ").append((int) point.getY()).append("\n");
        }

        pw.println(hull.length);
        pw.print(sb);
        pw.close();
    }

    // Use the monotone chains algorithm to find the
    // convex hull of a set of points in O(nlogn) time.
    public static Point2D[] convexHull(Point2D[] pts) {
        int n = pts.length, k = 0;
        if (n <= 1) return pts;

        Point2D[] hull = new Point2D[2 * n];
        Arrays.sort(pts, new PointComparator());

        // Build upper chain.
        for (Point2D pt : pts) {
            while (k >= 2 && orientation(hull[k - 2], hull[k - 1], pt) <= 0) k--;
            hull[k++] = pt;
        }

        int lastUpperChainIndex = k;

        // Build lower chain.
        for (int i = n - 2; i >= 0; i--) {
            while (k > lastUpperChainIndex && orientation(hull[k - 2], hull[k - 1], pts[i]) <= 0) k--;
            hull[k++] = pts[i];
        }

        // Conserve only unique points.
        int index = 1;
        Point2D lastPt = hull[0];
        for (int i = 1; i < k - 1; i++) {
            if (!hull[i].equals(lastPt)) {
                hull[index++] = lastPt = hull[i];
            }
        }

        return Arrays.copyOfRange(hull, 0, index);
    }

    // To find orientation of point 'c' relative to the line segment (a, b).
    // Imagine yourself standing at point 'a' looking out towards point 'b'.
    // Returns  0 if all three points are collinear.
    // Returns -1 if 'c' is clockwise to segment (a, b), i.e right of line formed by the segment.
    // Returns +1 if 'c' is counter clockwise to segment (a, b), i.e left of line
    // formed by the segment.
    private static int orientation(Point2D a, Point2D b, Point2D c) {
        double value =
                (b.getY() - a.getY()) * (c.getX() - b.getX())
                        - (b.getX() - a.getX()) * (c.getY() - b.getY());
        if (Math.abs(value) < EPS) return 1;
        return (value > 0) ? -1 : 1;
    }

    // Sorts points by first x coordinate and then y coordinate.
    private static class PointComparator implements Comparator<Point2D> {
        public int compare(Point2D p1, Point2D p2) {
            if (Math.abs(p1.getX() - p2.getX()) < EPS) {
                if (Math.abs(p1.getY() - p2.getY()) < EPS) return 0;
                else if (p1.getY() > p2.getY()) return 1;
            } else if (p1.getX() > p2.getX()) return 1;
            return -1;
        }
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
