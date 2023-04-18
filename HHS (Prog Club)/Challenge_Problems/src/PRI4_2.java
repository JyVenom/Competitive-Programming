import java.io.*;
import java.util.*;

public class PRI4_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        double[][] points = new double[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        ArrayList<double[]> dist = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double[] temp = new double[]{i, j, dist(points[i][0], points[i][1], points[j][0], points[j][1])};
                dist.add(temp);
            }
        }
        dist.sort(Comparator.comparingDouble(a -> a[2]));
        double max = 0;
        int maxNum = 0;
        for (int i = 0; i < N; i++) {
            for (double[] doubles : dist) {
                if (doubles[0] != i && doubles[1] != i) {
                    if (doubles[2] > max) {
                        max = doubles[2];
                        maxNum = i;
                    }
                    break;
                }
            }
        }

        pw.println(maxNum + 1);
        pw.close();
    }

    private static double dist(double ax, double ay, double bx, double by) {
        return (ax - bx) * (ax - bx) + (ay - by) * (ay - by);
    }
}
