import java.io.*;
import java.util.Random;

public class CowSteepleChaseII8 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowjump.in"));
        PrintWriter out = new PrintWriter(new File("cowjump.out"));

        long start = System.currentTimeMillis(); //jerry

        int n = Integer.parseInt(br.readLine());
        long[][] data = new long[n / 10][2];
        long[][] centroids = new long[10][2];
        long[][] clusters;
        int[] clusterNum = new int[data.length];

        int temp;
        String line;
        long maxX = 0, minX = 1000000000, maxY = 0, minY = 1000000000;
        int correct;
        for (int i = 0; i < n; i++){
            line = br.readLine();
            if ((i + 1) % 10 == 0){
                correct = ((i + 1) / 10) - 1;
                temp = line.indexOf(' ', line.indexOf(' ') + 1) + 1;
                data[correct][0] = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
                temp = line.lastIndexOf(' ') + 1;
                data[correct][1] = Integer.parseInt(line.substring(temp));
                maxX = Math.max(maxX, data[correct][0]);
                maxY = Math.max(maxY, data[correct][1]);
                minX = Math.min(minX, data[correct][0]);
                minY = Math.min(minY, data[correct][1]);
            }
        }

        for (int i = 0; i < centroids.length; i++){
            Random r = new Random();
            centroids[i][0] = (r.nextInt((int) ((maxX - minX) + 1)) + minX);
            centroids[i][1] = (r.nextInt((int) ((maxY - minY) + 1)) + minY);
        }

        long[] elem;
        long[][] oldCenters;
        do {
            oldCenters = centroids.clone();
            clusters = new long[data.length][2];
            for (int i = 0; i < data.length; i++) {
                int min = 0;
                for (int j = 0; j < centroids.length; j++) {
                    if (distance(centroids[j][0], centroids[j][1], data[i][0], data[i][1]) < distance(centroids[min][0], centroids[min][1], data[i][0], data[i][1])) {
                        min = j;
                    }
                }

                elem = new long[]{data[i][0], data[i][1]};
                add(clusters, elem);
                clusterNum[i] = min;
//                centroids[min] = center(clusters, clusterNum, min);
            }
            for (int i = 0; i < centroids.length; i++) {
                centroids[i] = center(clusters, clusterNum, i);
            }
        } while (notSame(centroids, oldCenters));

        System.out.println(System.currentTimeMillis() - start); //jerry

        out.close();
    }

    private static long distance (long x1, long y1, long x2, long y2) {
        return (long) (Math.pow(Math.abs(x2 - x1), 2) + Math.pow(Math.abs(y2 - y1), 2));
    }
    
    private static void add (long[][] data, long[] elem){
        for (int i = 0; i < data.length; i++){
            if (data[i][0] == 0){
                data[i] = elem;
                return;
            }
        }
    }

    private static long[] center (long[][] a1, int[] clusterNum, int cluster){
        int x = 0;
        int y = 0;
        int xDiv = a1.length;
        int yDiv = a1.length;
        for (int i = 0; i < a1.length; i++){
            if (clusterNum[i] == cluster) {
                if (a1[i][0] != 0) {
                    x += a1[i][0];
                } else {
                    if (i > 0) {
                        xDiv = Math.min(xDiv, i);
                    }
                }
                if (a1[i][1] != 0) {
                    y += a1[i][1];
                } else {
                    if (i > 0) {
                        yDiv = Math.min(yDiv, i);
                    }
                }
            }
        }
        return new long[]{(x + xDiv / 2) / xDiv, (y + yDiv / 2) / yDiv};
    }

    private static boolean notSame (long[][] a1, long[][] a2){
        for (int i = 0; i < a1.length; i++){
            if (a1[i][0] != a2[i][0] || a1[i][1] != a2[i][1]){
                return true;
            }
        }
        return false;
    }
}
