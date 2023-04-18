import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class CowSteepleChaseII11 {
    private static long x1, x2, x3, x4, x5, x6;
    private static long y1, y2, y3, y4, y5, y6;
    private static long start, t1, t2, t3, t4, tin1, tin2, tin3, tin4;
    private static final boolean needPrint = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowjump.in"));
        PrintWriter out = new PrintWriter(new File("cowjump.out"));

        start = System.currentTimeMillis(); //jerry

        int n = Integer.parseInt(br.readLine());
        long[][] endPoints = new long[n][2];
        long[][] lines = new long[n][4];
        long[][] centroids = new long[n / 250][2];
        int[] clusterNum = new int[endPoints.length];

        int temp;
        String line;
        long maxX = 0, minX = 1000000000, maxY = 0, minY = 1000000000;
        for (int i = 0; i < n; i++) {
            line = br.readLine();
            lines[i][0] = Integer.parseInt(line.substring(0, line.indexOf(' ')));
            temp = line.indexOf(' ') + 1;
            lines[i][1] = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
            temp = line.indexOf(' ', temp) + 1;
            lines[i][2] = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
            temp = line.lastIndexOf(' ') + 1;
            lines[i][3] = Integer.parseInt(line.substring(temp));
            maxX = Math.max(maxX, Math.max(lines[i][0], lines[i][2]));
            maxY = Math.max(maxY, Math.max(lines[i][1], lines[i][3]));
            minX = Math.min(minX, Math.min(lines[i][0], lines[i][2]));
            minY = Math.min(minY, Math.min(lines[i][1], lines[i][3]));
        }

        for (int i = 0; i < n; i++){
            lines[i][0] += minX;
            lines[i][2] += minX;
            lines[i][1] += minY;
            lines[i][3] += minY;
            endPoints[i][0] = Math.max(lines[i][0], lines[i][2]);
            endPoints[i][1] = Math.max(lines[i][1], lines[i][3]);
        }
        if (centroids.length >= 2) {
            PrintWriter out2 = new PrintWriter(new File("debug.out")); //4 dbg
            int num;
            Random r = new Random();
            for (int i = 0; i < centroids.length; i++) {
                num = r.nextInt(endPoints.length);
                centroids[i][0] = endPoints[num][0];
                centroids[i][1] = endPoints[num][1];
                out2.println(centroids[i][0] + " " + centroids[i][1]); //dbg
            }
            out2.close(); //dbg
            //4 dbg
//            BufferedReader br2 = new BufferedReader(new FileReader("debug.out"));
//            for (int i = 0; i < centroids.length; i++) {
//                line = br2.readLine();
//                centroids[i][0] = Integer.parseInt(line.substring(0, line.indexOf(' ')));
//                centroids[i][1] = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
//            }


            //**********************************************
            //clustering yifengy
            t1 = System.currentTimeMillis(); //yifengy
//            int count = 0; //Jerry

            clustering(centroids, endPoints, clusterNum);
            ArrayList<ArrayList<Long>> clusterElements = new ArrayList<>();
            for (int i = 0; i < centroids.length; i++){
                clusterElements.add(new ArrayList<>());
            }
            for (int i = 0; i < n; i++){
                clusterElements.get(clusterNum[i]).add((long) i);
            }
//            int cur = clusterNum[0];
//            for (int i = 0; i < clusterNum.length; i++) {
//                if (clusterNum[i] == cur) {
//                    count++;
//                } else {
//                    cur = clusterNum[i];
//                    count = 0;
//                }
//            }





            //**********************************************
            // inside clusters comparison yifengy
            boolean good = false;
            t2 = System.currentTimeMillis(); //yifengy
            int[] pair;

            for (int i = 0; i < centroids.length; i++) {// screen each clusters
                tin1 = System.nanoTime(); //jerrySystem.nanoTime()
                pair = new int[2];
                x1 = 0; x2 = 0; x3 = 0; x4 = 0; y1 = 0; y2 = 0; y3 = 0; y4 = 0;
                good = false;
                for (int j = 0; j < clusterElements.get(i).size(); j++) {// inside cluster comparison
                    for (int k = j + 1; k < clusterElements.get(i).size(); k++) {
                        x1 = lines[Math.toIntExact(clusterElements.get(i).get(j))][0]; x2 = lines[Math.toIntExact(clusterElements.get(i).get(j))][2]; x3 = lines[Math.toIntExact(clusterElements.get(i).get(k))][0]; x4 = lines[Math.toIntExact(clusterElements.get(i).get(k))][2];
                        y1 = lines[Math.toIntExact(clusterElements.get(i).get(j))][1]; y2 = lines[Math.toIntExact(clusterElements.get(i).get(j))][3]; y3 = lines[Math.toIntExact(clusterElements.get(i).get(k))][1]; y4 = lines[Math.toIntExact(clusterElements.get(i).get(k))][3];

                        //check if bounding box overlap
                        tin3 = System.nanoTime();
                        if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)) {
                                tin4 = System.nanoTime();
                                pair[0] = j;
                                pair[1] = k;
                                good = true;//1st found xo inside cluster!
                                if ((tin4 - tin3) >= 1)
                                    if (needPrint)
                                        System.out.println("   --> found xo pairs, single compare take time " + j + " &" + k + ": " + (tin4 - tin3)+"ns");//yifengy
                                break;
                        }
                    }
                    if (good) {
                        break;//break out of -- for (int j = 0; j < n; j++) {
                    }
                }
                //  jump here no matter found xo OR not
                tin2 = System.nanoTime(); //jerry
                if (needPrint)
                    System.out.println("time inside clusting single loop " + (tin2 - tin1));
                if (!good) {
                    continue; // goto for (int i = 0; i < centroids.length; i++) {
                }

                // if found xo inside cluster, go further check ...
                good = false;


                for (int k = pair[1] + 1; k < n; k++) {
                    x5 = lines[k][0];
                    x6 = lines[k][2];
                    y5 = lines[k][1];
                    y6 = lines[k][3];


                    if (jerryLineIntersect(x1, x2, x5, x6, y1, y2, y5, y6)) {
                        out.println(clusterElements.get(i).get(pair[0]) + 1);
                        good = true;
                        if (needPrint)
                            System.out.println(i + " out of" + clusterElements.size());
                        break;
                    }
                }
                if (good) {
                    break;
                }
                for (int j = pair[0] + 1; j < n; j++) {
                    if (j == pair[1]) {
                        continue;
                    }
                    x1 = lines[j][0];
                    x2 = lines[j][2];
                    y1 = lines[j][1];
                    y2 = lines[j][3];


                    if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)){
                        out.println(clusterElements.get(i).get(pair[1]) + 1);
                        good = true;
                        if (needPrint)
                            System.out.println(i + " out of" + clusterElements.size());
                        break;
                    }
                }
                if (good) {
                    break;
                }
                out.println(Math.min(clusterElements.get(i).get(pair[0]), clusterElements.get(i).get(pair[1])) + 1);
                if (needPrint)
                    System.out.println(i + " out of" + clusterElements.size());
            }
            //**********************************************


            t3 = System.currentTimeMillis(); //yifengy
            //**********************************************
            //cross section comparison yifengy
            if (!good) {
                long[][] boundingBox = new long[centroids.length][4];
                for (int i = 0; i < centroids.length; i++) {
                    boundingBox[i][0] = 2000000000;
                    boundingBox[i][3] = 2000000000;
                }

                for (int i = 0; i < lines.length; i++) {
                    boundingBox[clusterNum[i]][0] = Math.min(boundingBox[clusterNum[i]][0], Math.min(lines[i][0], lines[i][2]));
                    boundingBox[clusterNum[i]][1] = Math.max(boundingBox[clusterNum[i]][1], Math.max(lines[i][1], lines[i][3]));
                    boundingBox[clusterNum[i]][2] = Math.max(boundingBox[clusterNum[i]][2], Math.max(lines[i][0], lines[i][2]));
                    boundingBox[clusterNum[i]][3] = Math.min(boundingBox[clusterNum[i]][3], Math.min(lines[i][1], lines[i][3]));
                }

                //start = System.currentTimeMillis();
                ArrayList<Integer> clusters = new ArrayList<>();
                for (int i = 0; i < centroids.length - 1; i++) {
                    for (int j = i + 1; j < centroids.length; j++) {
                        Point l1 = new Point();
                        l1.x = boundingBox[i][0];
                        l1.y = boundingBox[i][1];
                        Point r1 = new Point();
                        r1.x = boundingBox[i][2];
                        r1.y = boundingBox[i][3];
                        Point l2 = new Point();
                        l2.x = boundingBox[j][0];
                        l2.y = boundingBox[j][1];
                        Point r2 = new Point();
                        r2.x = boundingBox[j][2];
                        r2.y = boundingBox[j][3];
                        if (doOverlap(l1, r1, l2, r2)) {
                            if (Math.abs(clusters.indexOf(clusterNum[i]) - clusters.indexOf(clusterNum[j])) != 1) {
                                clusters.add(i);
                                clusters.add(j);
                            }
                        }
                    }
                }
                long count = 0; //num times compare for cross section
                for (int i = 0; i < clusters.size() / 2; i += 2) {
                    int c1 = clusters.get(i);
                    int c2 = clusters.get(i + 1);
                    pair = new int[2];
                    long x1 = 0, x2 = 0, x3 = 0, x4 = 0;
                    long y1 = 0, y2 = 0, y3 = 0, y4 = 0;
                    int j = i + 1;
                    for (int k = 0; k < clusterElements.get(c1).size(); k++) {
                        if (clusterNum[k] != clusters.get(i)) {
                            continue;
                        }
                        for (int l = 0; l < clusterElements.get(c2).size(); l++) {
                            if (clusterNum[l] != clusters.get(j)) {
                                continue;
                            }
                            x1 = lines[Math.toIntExact(clusterElements.get(c1).get(k))][0];
                            x2 = lines[Math.toIntExact(clusterElements.get(c1).get(k))][2];
                            x3 = lines[Math.toIntExact(clusterElements.get(c2).get(l))][0];
                            x4 = lines[Math.toIntExact(clusterElements.get(c2).get(l))][2];
                            y1 = lines[Math.toIntExact(clusterElements.get(c1).get(k))][1];
                            y2 = lines[Math.toIntExact(clusterElements.get(c1).get(k))][3];
                            y3 = lines[Math.toIntExact(clusterElements.get(c2).get(l))][1];
                            y4 = lines[Math.toIntExact(clusterElements.get(c2).get(l))][3];
                            count++;
                            if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)) {
                                pair[0] = k;
                                pair[1] = l;
                                good = true;
                                break;
                            }
                        }
                        if (good) {
                            break;
                        }
                    }

                    if (!good) {
                        continue;
                    }
                    good = false;
                    long x5, x6, y5, y6;
                    for (int k = pair[1] + 1; k < clusterElements.get(c2).size(); k++) {
                        x5 = lines[Math.toIntExact(clusterElements.get(c2).get(k))][0];
                        x6 = lines[Math.toIntExact(clusterElements.get(c2).get(k))][2];
                        y5 = lines[Math.toIntExact(clusterElements.get(c2).get(k))][1];
                        y6 = lines[Math.toIntExact(clusterElements.get(c2).get(k))][3];

                        if (jerryLineIntersect(x1, x2, x5, x6, y1, y2, y5, y6)) {
                            out.println(clusterElements.get(c1).get(pair[0]) + 1);
                            good = true;
                            break;
                        }
                    }
                    if (good) {
                        break;
                    }
                    for (int k = pair[0] + 1; k < clusterElements.get(c1).size(); k++) {
                        if (k == pair[1]) {
                            continue;
                        }
                        x1 = lines[Math.toIntExact(clusterElements.get(c1).get(k))][0];
                        x2 = lines[Math.toIntExact(clusterElements.get(c1).get(k))][2];
                        y1 = lines[Math.toIntExact(clusterElements.get(c1).get(k))][1];
                        y2 = lines[Math.toIntExact(clusterElements.get(c1).get(k))][3];

                        if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)) {
                            out.println(clusterElements.get(c2).get(pair[1]) + 1);
                            good = true;
                            break;
                        }
                    }
                    if (good) {
                        break;
                    }
                    out.println(Math.min(clusterElements.get(c1).get(pair[0]), clusterElements.get(c2).get(pair[1])) + 1);
                }
                if (needPrint)
                    System.out.println("X section # compare: " + count);
            }

            //end of inside cluster comparison
            t4 = System.currentTimeMillis(); //yifengy
            if (needPrint) {
                System.out.println("get endPoints time in ms: " + (t1 - start));
                System.out.println("clustering time in ms: " + (t2 - t1));
                System.out.println("in sect compare time in ms: " + (t3 - t2));
                System.out.println("xo section compare time in ms: " + (t4 - t3));
                System.out.println("total time in ms: " + (t4 - start));
            }
        } else {
            int prev = 0;
            boolean good;
            int[] pair = new int[2];
            long x1 = 0, x2 = 0, x3 = 0, x4 = 0;
            long y1 = 0, y2 = 0, y3 = 0, y4 = 0;
            good = false;
            for (int j = prev; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    x1 = lines[j][0];
                    x2 = lines[j][2];
                    x3 = lines[k][0];
                    x4 = lines[k][2];
                    y1 = lines[j][1];
                    y2 = lines[j][3];
                    y3 = lines[k][1];
                    y4 = lines[k][3];

                    if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)) {
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

            good = false;
            for (int k = pair[1] + 1; k < n; k++) {
                long x5 = lines[k][0];
                long x6 = lines[k][2];
                long y5 = lines[k][1];
                long y6 = lines[k][3];
                if (jerryLineIntersect(x1, x2, x5, x6, y1, y2, y5, y6)) {
                    out.println(pair[0] + 1);
                    good = true;
                    break;
                }
            }
            if (!good) {
                for (int j = pair[0] + 1; j < n; j++) {
                    if (j == pair[1]) {
                        continue;
                    }
                    x1 = lines[j][0];
                    x2 = lines[j][2];
                    y1 = lines[j][1];
                    y2 = lines[j][3];

                    if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)) {
                        out.println(pair[1] + 1);
                        good = true;
                        break;
                    }
                }
            }
            if (!good) {
                out.println(Math.min(pair[0], pair[1]) + 1);
            }
        }

        out.close();
//        //System.out.println(System.currentTimeMillis() - start); //jerry
        //System.out.println(System.currentTimeMillis() - start2); //jerry
//        System.out.println(count2);
    }

    private static long distance(long x1, long y1, long x2, long y2) {
        return (long) (Math.pow(Math.abs(x2 - x1), 2) + Math.pow(Math.abs(y2 - y1), 2));
    }

    private static long[] center(long[][] a1, int[] clusterNum, int cluster) {
        long x = 0;
        long y = 0;
        long xDiv = 0;
        long yDiv = 0;
        for (int i = 0; i < a1.length; i++) {
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
        if (xDiv == 0 || yDiv == 0) {
            return new long[]{0, 0};
        }
        return new long[]{(x + xDiv / 2) / xDiv, (y + yDiv / 2) / yDiv};
    }

    private static boolean notSame(long[][] a1, long[][] a2) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i][0] != a2[i][0] || a1[i][1] != a2[i][1]) {
                return true;
            }
        }
        return false;
    }

    static class Point {

        long x, y;
    }

    // Returns true if two rectangles (l1, r1) and (l2, r2) overlap
    private static boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {
        // If one rectangle is on left side of other
        if (l1.x > r2.x || l2.x > r1.x) {
            return false;
        }

        // If one rectangle is above other
        return l1.y >= r2.y && l2.y >= r1.y;
    }
    //***************************************************
    public static boolean linesIntersect(long x1, long y1,
                                         long x2, long y2,
                                         long x3, long y3,
                                         long x4, long y4) {
        return ((relativeCCW(x1, y1, x2, y2, x3, y3) *
                relativeCCW(x1, y1, x2, y2, x4, y4) <= 0)
                && (relativeCCW(x3, y3, x4, y4, x1, y1) *
                relativeCCW(x3, y3, x4, y4, x2, y2) <= 0));
    }

    public static int relativeCCW(long x1, long y1,
                                  long x2, long y2,
                                  long px, long py) {
        x2 -= x1;
        y2 -= y1;
        px -= x1;
        py -= y1;
        long ccw = px * y2 - py * x2;
        if (ccw == 0.0) {
            ccw = px * x2 + py * y2;
            if (ccw > 0.0) {
                px -= x2;
                py -= y2;
                ccw = px * x2 + py * y2;
                if (ccw < 0.0) {
                    ccw = (long) 0.0;
                }
            }
        }
        return Long.compare(ccw, (long) 0.0);
    }

    private static boolean jerryLineIntersect(long x1, long x2, long x3, long x4, long y1, long y2, long y3, long y4){
        Point l1 = new Point();
        l1.x = Math.min(x1, x2);
        l1.y = Math.max(y1, y2);
        Point r1 = new Point();
        r1.x = Math.max(x1, x2);
        r1.y = Math.min(y1, y2);
        Point l2 = new Point();
        l2.x = Math.min(x3, x4);
        l2.y = Math.max(y3, y4);
        Point r2 = new Point();
        r2.x = Math.max(x3, x4);
        r2.y = Math.min(y3, y4);

        if (doOverlap(l1, r1, l2, r2)) {
            return linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
        }
        return false;
    }

    private static void clustering(long[][] centroids, long[][] endPoints, int[] clusterNum){
        int count = 0;
        long[][] oldCenters;
        do {
            if (count == 20){
                break;
            }
            count++;
            oldCenters = centroids.clone();
            for (int i = 0; i < endPoints.length; i++) {
                int min = 0;
                for (int j = 0; j < centroids.length; j++) {
                    if (distance(centroids[j][0], centroids[j][1], endPoints[i][0], endPoints[i][1]) < distance(centroids[min][0], centroids[min][1], endPoints[i][0], endPoints[i][1])) {
                        min = j;
                    }
                }

                clusterNum[i] = min;
            }
            for (int i = 0; i < centroids.length; i++) {
                long[] c = center(endPoints, clusterNum, i);
                if (!(c[0] == 0 && c[1] == 0)) {
                    centroids[i] = c;
                }
            }
        } while (notSame(centroids, oldCenters));
    }
}