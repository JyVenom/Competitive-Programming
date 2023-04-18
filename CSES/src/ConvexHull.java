import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class ConvexHull {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.readInt();
        ArrayList<Point> points = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            points.add(new Point(ir.readInt(), ir.readInt()));
        }
        ir.close();

        Stack<Point> hull = getConvexHull(points);
        StringBuilder sb = new StringBuilder();
        for (Point point : hull) {
            sb.append(point.x).append(" ").append(point.y).append("\n");
        }

        pw.println(hull.size());
        pw.print(sb);
        pw.close();
    }

    /**
     * Returns true iff all points in <code>points</code> are collinear.
     *
     * @param points the list of points.
     * @return true iff all points in <code>points</code> are collinear.
     */
    protected static boolean areAllCollinear(ArrayList<Point> points) {
        if (points.size() < 2) {
            return true;
        }

        Point a = points.get(0);
        Point b = points.get(1);

        for (int i = 2; i < points.size(); i++) {
            Point c = points.get(i);

            if (getTurn(a, b, c) != Turn.COLLINEAR) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the convex hull of the points created from the list
     * <code>points</code>. Note that the first and last point in the
     * returned <code>List&lt;java.awt.Point&gt;</code> are the same
     * point.
     *
     * @param points the list of points.
     * @return the convex hull of the points created from the list
     * <code>points</code>.
     * @throws IllegalArgumentException if all points are collinear or if there
     *                                  are less than 3 unique points present.
     */
    public static Stack<Point> getConvexHull(ArrayList<Point> points) throws IllegalArgumentException {
        ArrayList<Point> sorted = new ArrayList<>(getSortedPointSet(points));

        if (areAllCollinear(sorted)) {
            throw new IllegalArgumentException("cannot create a convex hull from collinear points");
        }

        Stack<Point> stack = new Stack<>();
        stack.push(sorted.get(0));
        stack.push(sorted.get(1));

        for (int i = 2; i < sorted.size(); i++) {
            Point head = sorted.get(i);
            Point middle = stack.pop();
            Point tail = stack.peek();

            Turn turn = getTurn(tail, middle, head);

            if (turn == Turn.CLOCKWISE) {
                i--;
            } else {
                stack.push(middle);
                stack.push(head);
            }
        }

        return stack;
    }

    /**
     * Returns the points with the lowest y coordinate. In case more than 1 such
     * point exists, the one with the lowest x coordinate is returned.
     *
     * @param points the list of points to return the lowest point from.
     * @return the points with the lowest y coordinate. In case more than
     * 1 such point exists, the one with the lowest x coordinate
     * is returned.
     */
    protected static Point getLowestPoint(ArrayList<Point> points) {
        Point lowest = points.get(0);

        for (int i = 1; i < points.size(); i++) {
            Point temp = points.get(i);

            if (temp.y < lowest.y || (temp.y == lowest.y && temp.x < lowest.x)) {
                lowest = temp;
            }
        }

        return lowest;
    }

    /**
     * Returns a sorted set of points from the list <code>points</code>. The
     * set of points are sorted in increasing order of the angle they and the
     * lowest point <tt>P</tt> make with the x-axis. If tow (or more) points
     * form the same angle towards <tt>P</tt>, the one closest to <tt>P</tt>
     * comes first.
     *
     * @param points the list of points to sort.
     * @return a sorted set of points from the list <code>points</code>.
     */
    protected static Set<Point> getSortedPointSet(ArrayList<Point> points) {
        Point lowest = getLowestPoint(points);

        TreeSet<Point> set = new TreeSet<>((a, b) -> {
            if (a == b || a.equals(b)) {
                return 0;
            }

            // use longs to guard against int-underflow
            double thetaA = Math.atan2((long) a.y - lowest.y, (long) a.x - lowest.x);
            double thetaB = Math.atan2((long) b.y - lowest.y, (long) b.x - lowest.x);

            if (thetaA < thetaB) {
                return -1;
            } else if (thetaA > thetaB) {
                return 1;
            } else {
                // collinear with the 'lowest' point, let the point closest to it come first

                // use longs to guard against int-over/underflow
                double distanceA = Math.sqrt((((long) lowest.x - a.x) * ((long) lowest.x - a.x)) +
                        (((long) lowest.y - a.y) * ((long) lowest.y - a.y)));
                double distanceB = Math.sqrt((((long) lowest.x - b.x) * ((long) lowest.x - b.x)) +
                        (((long) lowest.y - b.y) * ((long) lowest.y - b.y)));

                if (distanceA < distanceB) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        set.addAll(points);
        return set;
    }

    /**
     * Returns the GrahamScan#Turn formed by traversing through the
     * ordered points <code>a</code>, <code>b</code> and <code>c</code>.
     * More specifically, the cross product <tt>C</tt> between the
     * 3 points (vectors) is calculated:
     *
     * <tt>(b.x-a.x * c.y-a.y) - (b.y-a.y * c.x-a.x)</tt>
     * <p>
     * and if <tt>C</tt> is less than 0, the turn is CLOCKWISE, if
     * <tt>C</tt> is more than 0, the turn is COUNTER_CLOCKWISE, else
     * the three points are COLLINEAR.
     *
     * @param a the starting point.
     * @param b the second point.
     * @param c the end point.
     * @return the GrahamScan#Turn formed by traversing through the
     * ordered points <code>a</code>, <code>b</code> and
     * <code>c</code>.
     */
    protected static Turn getTurn(Point a, Point b, Point c) {
        // use longs to guard against int-over/underflow
        long crossProduct = (((long) b.x - a.x) * ((long) c.y - a.y)) -
                (((long) b.y - a.y) * ((long) c.x - a.x));

        if (crossProduct > 0) {
            return Turn.COUNTER_CLOCKWISE;
        } else if (crossProduct < 0) {
            return Turn.CLOCKWISE;
        } else {
            return Turn.COLLINEAR;
        }
    }

    /**
     * An enum denoting a directional-turn between 3 points (vectors).
     */
    protected enum Turn {CLOCKWISE, COUNTER_CLOCKWISE, COLLINEAR}

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
