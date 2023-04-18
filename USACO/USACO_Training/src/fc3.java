/*
ID: jerryya2
LANG: JAVA
TASK: fc
*/

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class fc3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fc.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fc.out")));

        int n = Integer.parseInt(br.readLine());
        double[][] points = new double[n][2];
        for (int i = 0; i < n; i++) {
            points[i] = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        }

        double[][] ans = outerTrees(points);
        double sum = 0;
        for (int i = 1; i < ans.length; i++) {
            double ac = Math.abs(ans[i][1] - ans[i - 1][1]);
            double cb = Math.abs(ans[i][0] - ans[i - 1][0]);
            sum += Math.hypot(ac, cb);
        }
        double ac = Math.abs(ans[0][1] - ans[ans.length - 1][1]);
        double cb = Math.abs(ans[0][0] - ans[ans.length - 1][0]);
        sum += Math.hypot(ac, cb);

        String s = String.format("%.2f", sum);
        pw.println(s);
        pw.close();
    }

    public static double orientation(double[] p, double[] q, double[] r) {
        return (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
    }

    public static double[][] outerTrees(double[][] points) {
        Arrays.sort(points, (p, q) -> (int) (q[0] - p[0] == 0 ? q[1] - p[1] : q[0] - p[0]));
        Stack<double[]> hull = new Stack<>();
        for (double[] point : points) {
            while (hull.size() >= 2 && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), point) > 0)
                hull.pop();
            hull.push(point);
        }
        hull.pop();
        for (int i = points.length - 1; i >= 0; i--) {
            while (hull.size() >= 2 && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) > 0)
                hull.pop();
            hull.push(points[i]);
        }
        // remove redundant elements from the stack
        HashSet<double[]> ret = new HashSet<>(hull);
        return ret.toArray(new double[ret.size()][]);
    }
}
