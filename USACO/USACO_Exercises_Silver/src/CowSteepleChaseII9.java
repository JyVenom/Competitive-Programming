import java.awt.geom.Line2D;
import java.io.*;
import java.util.Random;

public class CowSteepleChaseII9 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowjump.in"));
        PrintWriter out = new PrintWriter(new File("cowjump.out"));

        long start = System.currentTimeMillis(); //jerry
        long start2 = System.currentTimeMillis();

        int n = Integer.parseInt(br.readLine());
        long[][] data = new long[n][2];
        long[][] data2 = new long[n][4];
        long[][] centroids = new long[100][2];
        int[] clusterNum = new int[data.length];

        int temp;
        String line;
        long maxX = 0, minX = 1000000000, maxY = 0, minY = 1000000000;
        for (int i = 0; i < n; i++){
            line = br.readLine();
            data2[i][0] = Integer.parseInt(line.substring(0, line.indexOf(' ')));
            temp = line.indexOf(' ') + 1;
            data2[i][1] = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
            temp = line.indexOf(' ', temp) + 1;
            data2[i][2] = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
            temp = line.lastIndexOf(' ') + 1;
            data2[i][3] = Integer.parseInt(line.substring(temp));
            data[i][0] = data2[i][2];
            data[i][1] = data2[i][3];
            maxX = Math.max(maxX, data[i][0]);
            maxY = Math.max(maxY, data[i][1]);
            minX = Math.min(minX, data[i][0]);
            minY = Math.min(minY, data[i][1]);
        }
//        long start3 = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - start); //jerry

        int num;
        Random r = new Random();
        for (int i = 0; i < centroids.length; i++){
            num = r.nextInt(data.length);
            centroids[i][0] = data[num][0];
            centroids[i][1] = data[num][1];
        }

        start = System.currentTimeMillis(); //jerry
        int count = 0; //Jerry
        long[][] oldCenters;
        do {
            if (count == 20){
                break;
            }
            count++;
            oldCenters = centroids.clone();
            for (int i = 0; i < data.length; i++) {
                int min = 0;
                for (int j = 0; j < centroids.length; j++) {
                    if (distance(centroids[j][0], centroids[j][1], data[i][0], data[i][1]) < distance(centroids[min][0], centroids[min][1], data[i][0], data[i][1])) {
                        min = j;
                    }
                }

                clusterNum[i] = min;
            }
            for (int i = 0; i < centroids.length; i++) {
                long[] c = center(data, clusterNum, i);
                if (!(c[0] == 0 && c[1] == 0)) {
                    centroids[i] = c;
                }
            }
        } while (notSame(centroids, oldCenters));
        System.out.println(System.currentTimeMillis() - start); //jerry

//        System.out.println(clusterNum[99998] + " " + clusterNum[99999]);
//        count = 0;
//
//        int cur = clusterNum[0];
//        for (int i = 0; i < clusterNum.length; i++) {
//            if (clusterNum[i] == cur){
//                count++;
//            }
//            else {
//                cur = clusterNum[i];
//                if (count < 10){
//                    System.out.println(i);
//                }
//                count = 0;
//            }
//        }

//        long[][] boundingBox = new long[centroids.length][4];
//        for (int i = 0; i < centroids.length; i++){
//            boundingBox[i][0] = 1000000000;
//            boundingBox[i][3] = 1000000000;
//        }
//
//        for (int i = 0; i < data2.length; i++){
//            boundingBox[clusterNum[i]][0] = Math.min(boundingBox[clusterNum[i]][0], Math.min(data2[i][0], data2[i][2]));
//            boundingBox[clusterNum[i]][1] = Math.max(boundingBox[clusterNum[i]][1], Math.max(data2[i][1], data2[i][3]));
//            boundingBox[clusterNum[i]][2] = Math.max(boundingBox[clusterNum[i]][2], Math.max(data2[i][0], data2[i][2]));
//            boundingBox[clusterNum[i]][3] = Math.min(boundingBox[clusterNum[i]][3], Math.min(data2[i][1], data2[i][3]));
//        }

//        ArrayList<Integer> clusters = new ArrayList<>();
//        for (int i = 0; i < centroids.length - 1; i++){
//            for (int j = i + 1; j < centroids.length; j++){
//                Point l1 = new Point();
//                l1.x = boundingBox[i][0];
//                l1.y = boundingBox[i][1];
//                Point r1 = new Point();
//                r1.x = boundingBox[i][2];
//                r1.y = boundingBox[i][3];
//                Point l2 = new Point();
//                l2.x = boundingBox[j][0];
//                l2.y = boundingBox[j][1];
//                Point r2 = new Point();
//                r2.x = boundingBox[j][2];
//                r2.y = boundingBox[j][3];
//                if (doOverlap(l1, r1, l2, r2)){
//                    if (!clusters.contains(i)){
//                        clusters.add(i);
//                    }
//                    if (!clusters.contains(j)){
//                        clusters.add(j);
//                    }
//                }
//            }
//        }

        boolean good;
        int[] pair;
        long x1, x2, x3, x4;
        long y1, y2, y3, y4;
        long count2 = 0;
        long[] startTimes = new long[centroids.length];
        start = System.currentTimeMillis(); //jerry

        for (int i = 0; i < centroids.length; i++){
            pair = new int[2];
            x1 = 0;
            x2 = 0;
            x3 = 0;
            x4 = 0;
            y1 = 0;
            y2 = 0;
            y3 = 0;
            y4 = 0;
//            long x1 = 0, x2 = 0, x3 = 0, x4 = 0;
//            long y1 = 0, y2 = 0, y3 = 0, y4 = 0;
            good = false;
            for (int j = (int) startTimes[i]; j < n; j++) {
                if (clusterNum[j] != clusterNum[(int) startTimes[i]]){
                    break;
                }
                for (int k = j + 1; k < n; k++) {
                    if (clusterNum[k] != clusterNum[j]){
                        startTimes[i + 1] = k;
                        break;
                    }

                    if (count2%10000000 == 0){
                        System.out.println("count: " + count2);
                        System.out.println("time: "+(System.currentTimeMillis() - start));
                    }

                    count2++;
                    
                    x1 = data2[j][0];
                    x2 = data2[j][2];
                    x3 = data2[k][0];
                    x4 = data2[k][2];
                    y1 = data2[j][1];
                    y2 = data2[j][3];
                    y3 = data2[k][1];
                    y4 = data2[k][3];

                    if (Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4)) {
                        pair[0] = j;
                        pair[1] = k;
                        good = true;
                        break;
                    }
                }
                if (good) {
                    break;
                }
            }

            if (!good){
                continue;
            }
            good = false;
            long x5, x6, y5, y6;
            for (int k = pair[1] + 1; k < n; k++) {
                x5 = data2[k][0];
                x6 = data2[k][2];
                y5 = data2[k][1];
                y6 = data2[k][3];
                if (Line2D.linesIntersect(x1, y1, x2, y2, x5, y5, x6, y6)) {
                    out.println(pair[0] + 1);
                    good = true;
                    break;
                }
            }
            if (good){
                break;
            }
            for (int j = pair[0] + 1; j < n; j++) {
                if (j == pair[1]) {
                    continue;
                }
                x1 = data2[j][0];
                x2 = data2[j][2];
                y1 = data2[j][1];
                y2 = data2[j][3];

                if (Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4)) {
                    out.println(pair[1] + 1);
                    good = true;
                    break;
                }
            }
            if (good){
                break;
            }
            out.println(Math.min(pair[0], pair[1]) + 1);
        }

        out.close();
        System.out.println(System.currentTimeMillis() - start); //jerry
        System.out.println(System.currentTimeMillis() - start2); //jerry
        System.out.println(count2);
    }

    private static long distance (long x1, long y1, long x2, long y2) {
        return (long) (Math.pow(Math.abs(x2 - x1), 2) + Math.pow(Math.abs(y2 - y1), 2));
    }

    private static long[] center (long[][] a1, int[] clusterNum, int cluster){
        int x = 0;
        int y = 0;
        int xDiv = 0;
        int yDiv = 0;
        for (int i = 0; i < a1.length; i++){
            if (clusterNum[i] == cluster) {
                if (a1[i][0] != 0) {
                    x += a1[i][0];
                    xDiv++;
                }
                if (a1[i][1] != 0) {
                    y += a1[i][1];
                    yDiv++;
                }
            }
        }

//        if (xDiv == 0){
//            if (yDiv == 0){
//                return new long[]{0, 0};
//            }
//            else {
//                return new long[]{0, (y + yDiv / 2) / yDiv};
//            }
//        }
//        if (yDiv == 0){
//            return new long[]{(x + xDiv / 2) / xDiv, 0};
//        }
        if (xDiv ==0 || yDiv == 0){
            return new long[]{0, 0};
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
