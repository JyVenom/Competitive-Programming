/*
ID: jerryya2
LANG: JAVA
TASK: fc
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class fc {
    // To find orientation of ordered triplet (p, q, r).
    // The function returns following values
    // 0 --> p, q and r are collinear
    // 1 --> Clockwise
    // 2 --> Counterclockwise
    public static int orientation(Point p, Point q, Point r) {
        double val = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);

        if (val == 0) return 0;  // collinear
        return (val > 0) ? 1 : 2; // clock or counterclockwise
    }

    // Prints convex hull of a set of n points.
    public static ArrayList<Point> convexHull(Point[] points, int n) {
        // Initialize Result
        ArrayList<Point> hull = new ArrayList<>();
        // There must be at least 3 points
        if (n < 3) return hull;


        // Find the leftmost point
        int l = 0;
        for (int i = 1; i < n; i++)
            if (points[i].x < points[l].x)
                l = i;

        // Start from leftmost point, keep moving
        // counterclockwise until reach the start point
        // again. This loop runs O(h) times where h is
        // number of points in result or output.
        int p = l, q;
        do {
            // Add current point to result
            hull.add(points[p]);

            // Search for a point 'q' such that
            // orientation(p, x, q) is counterclockwise
            // for all points 'x'. The idea is to keep
            // track of last visited most counterclockwise
            // point in q. If any point 'i' is more
            // counterclockwise than q, then update q.
            q = (p + 1) % n;

            for (int i = 0; i < n; i++) {
                // If i is more counterclockwise than
                // current q, then update q
                if (orientation(points[p], points[i], points[q])
                        == 2)
                    q = i;
            }

            // Now q is the most counterclockwise with
            // respect to p. Set p as q for next iteration,
            // so that q is added to result 'hull'
            p = q;

        } while (p != l);  // While we don't come to first
        // point

        // Print Result
        return hull;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fc.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fc.out")));

        int n = Integer.parseInt(br.readLine());
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            double[] temp = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
            points[i] = new Point(temp[0], temp[1]);
        }

        ArrayList<Point> ans = convexHull(points, n);
        double sum = 0;
        for (int i = 1; i < ans.size(); i++) {
            double ac = Math.abs(ans.get(i).y - ans.get(i - 1).y);
            double cb = Math.abs(ans.get(i).x - ans.get(i - 1).x);
            sum += Math.hypot(ac, cb);
        }
        double ac = Math.abs(ans.get(0).y - ans.get(ans.size() - 1).y);
        double cb = Math.abs(ans.get(0).x - ans.get(ans.size() - 1).x);
        sum += Math.hypot(ac, cb);

        String s = String.format("%.2f", sum);
        pw.println(s);
        pw.close();
    }

    static class Point {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
