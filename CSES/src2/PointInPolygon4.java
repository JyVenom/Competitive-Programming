import java.io.IOException;
import java.util.InputMismatchException;

public class PointInPolygon4 {
    static long INF = 0;

    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();

        int n = ir.readInt();
        int m = ir.readInt();
        Point[] poly = new Point[n];
        for (int i = 0; i < n; i++) {
            poly[i] = new Point(ir.readLong(), ir.readLong());
            INF = Math.max(INF, poly[i].x);
        }

        INF++;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            Point cur = new Point(ir.readLong(), ir.readLong());
            int temp = isInside(poly, n, cur);
            if (temp == -1)
                sb.append("OUTSIDE");
            else if (temp == 1)
                sb.append("INSIDE");
            else
                sb.append("BOUNDARY");
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static boolean onSegment(Point p, Point q, Point r) {
        return q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) && q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y);
    }

    private static int orientation(Point p, Point q, Point r) {
        long val1 = (q.y - p.y) * (r.x - q.x);
        long val2 = (q.x - p.x) * (r.y - q.y);

        if (val1 == val2)
            return 0;
        return (val1 > val2) ? 1 : 2;
    }

    private static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4)
            return true;
        else if (o1 == 0 && onSegment(p1, p2, q1))
            return true;
        else if (o2 == 0 && onSegment(p1, q2, q1))
            return true;
        else if (o3 == 0 && onSegment(p2, p1, q2))
            return true;

        return o4 == 0 && onSegment(p2, q1, q2);
    }

    private static int isInside(Point[] polygon, int n, Point p) {
        Point extreme = new Point(INF, p.y);

        int count = 0, i = 0;
        do {
            int next = (i + 1) % n;

            if (doIntersect(polygon[i], polygon[next], p, extreme)) {
                if (orientation(polygon[i], p, polygon[next]) == 0) {
                    if (onSegment(polygon[i], p, polygon[next])) {
                        return 2;
                    }
                }
                if (orientation(p, polygon[next], extreme) == 0) {
                    if (orientation(p, polygon[next], polygon[i]) != orientation(p, polygon[next], polygon[(next + 1) % n])) {
                        count--;
                    }
                }
                if (p.y == polygon[next].y && polygon[next].y == polygon[(next + 1) % n].y) {
                    if (orientation(p, polygon[next], polygon[i]) == orientation(p, polygon[(next + 1) % n], polygon[(next + 2) % n])) {
                        count++;
                    }
                }
                count++;
            }
            i = next;
        } while (i != 0);

        return (count % 2 == 1) ? 1 : -1;
    }

    private static class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class InputReader {
        private final byte[] buf = new byte[1 << 16];
        private int curChar;

        public InputReader() throws IOException {
            curChar = 0;
            System.in.read(buf);
            System.in.close();
        }

        private int readInt() {
            int c = buf[curChar++];
            while ((c == ' ' || c == '\n')) {
                c = buf[curChar++];
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = buf[curChar++];
            } while (!(c == ' ' || c == '\n'));
            return res;
        }

        private long readLong() {
            int c = buf[curChar++];
            while ((c == ' ' || c == '\n')) {
                c = buf[curChar++];
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = buf[curChar++];
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = buf[curChar++];
            } while (!(c == ' ' || c == '\n'));
            return res * sgn;
        }
    }
}
