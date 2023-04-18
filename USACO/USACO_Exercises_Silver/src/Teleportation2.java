import java.io.*;

public class Teleportation2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("teleport.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));

        int n = Integer.parseInt(br.readLine());
        int[][] points = new int[n][2];
        int max = 0;
        int max2 = 0;
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            points[i][0] = Integer.parseInt(line[0]);
            points[i][1] = Integer.parseInt(line[1]);
            if (points[i][0] > max) {
                max = points[i][0];
            }
            if (points[i][1] > max) {
                max = points[i][1];
            }
            if (points[i][0] < max2) {
                max2 = points[i][0];
            }
            if (points[i][1] < max2) {
                max2 = points[i][1];
            }
        }
        br.close();

        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 1; i <= max; i++) {
            for (int j = 0; j < n; j++) {
                int best = Math.abs(points[j][0] - points[j][1]);
                int use;
                if (Math.abs(points[j][0]) < Math.abs(points[j][0] - i)) {
                    use = Math.abs(points[j][0]) + Math.abs(points[j][1] - i);
                }
                else {
                    use = Math.abs(points[j][1]) + Math.abs(points[j][0] - i);
                }
                if (use < best) {
                    best = use;
                }
                sum += best;
            }
            if (sum < min) {
                min = sum;
            }
            sum = 0;
        }
//        for (int i = -1; i >= max2; i--) {
//            for (int j = 0; j < n; j++) {
//                int best = Math.abs(points[j][0] - points[j][1]);
//                int use;
//                if (Math.abs(points[j][0]) < Math.abs(points[j][0] - i)) {
//                    use = Math.abs(points[j][0]) + Math.abs(points[j][1] - i);
//                }
//                else {
//                    use = Math.abs(points[j][1]) + Math.abs(points[j][0] - i);
//                }
//                if (use < best) {
//                    best = use;
//                }
//                sum += best;
//            }
//            if (sum < min) {
//                min = sum;
//            }
//            sum = 0;
//        }

        pw.println(min);
        pw.close();
    }
}