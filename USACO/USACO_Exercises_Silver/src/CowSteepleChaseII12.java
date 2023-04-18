import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class CowSteepleChaseII12 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("cowjump.in"));
        PrintWriter out = new PrintWriter(new File("cowjump.out"));

        int n = Integer.parseInt(br.readLine());
        long[][] lines = new long[n][7];

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

            lines[i][4] = (lines[i][0] + lines[i][2]) / 2;
            lines[i][5] = (lines[i][1] + lines[i][3]) / 2;
            lines[i][6] = i;

            minX = Math.min(minX, lines[i][4]);
            maxX = Math.min(maxX, lines[i][4]);
            minY = Math.min(minY, lines[i][5]);
            maxY = Math.min(maxY, lines[i][5]);
        }

        if ((maxY - minY) > (maxX - minX)){
            long hold;
            for (int i = 0; i < n; i++){
                hold = lines[i][0];
                lines[i][0] = lines[i][1];
                lines[i][1] = hold;
                hold = lines[i][2];
                lines[i][2] = lines[i][3];
                lines[i][3] = hold;
                hold = lines[i][4];
                lines[i][4] = lines[i][5];
                lines[i][5] = hold;
            }
        }

        Arrays.sort(lines, Comparator.comparingLong(arr -> arr[4]));

        long[][] section = new long[100][7]; //2d array for current section
        int index = 0; //to hold index of intersecting lines
        boolean good = false;
        long x1 = 0, x2 = 0, x3, x4;
        long y1 = 0, y2 = 0, y3, y4;
        for (int sectionNum = 0; sectionNum < n / 100; sectionNum++){
            //fill in section with data
            System.arraycopy(lines, 100 * sectionNum, section, 0, 100);
            Arrays.sort(section, Comparator.comparingLong(arr -> arr[5]));
            for (int i = 0; i < 99; i ++){ //comparing lines
                //fill in x's and y's with data of the current lines to be checked
                x1 = section[i][0];
                x2 = section[i][2];
                y1 = section[i][1];
                y2 = section[i][3];

                x3 = section[i + 1][0];
                x4 = section[i + 1][2];
                y3 = section[i + 1][1];
                y4 = section[i + 1][3];

                if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)){
                    index = i;
                    good = true;
                    break;
                }
            }
            if (good){
                good = false;
                for (int i = 0; i < 99; i++){
                    if (i == index - 1 || i == index){
                        continue;
                    }
                    x3 = section[i + 1][0];
                    x4 = section[i + 1][2];
                    y3 = section[i + 1][1];
                    y4 = section[i + 1][3];
                    if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)){
                        out.println(section[index][6] + 1);
                        good = true;
                        break;
                    }
                }

                if (good){
                    break;
                }
                x1 = section[index + 1][0];
                x2 = section[index + 1][2];
                y1 = section[index + 1][1];
                y2 = section[index + 1][3];
                for (int i = 0; i < 99; i++) {
                    if (i == index - 1 || i == index){
                        continue;
                    }
                    x3 = section[i + 1][0];
                    x4 = section[i + 1][2];
                    y3 = section[i + 1][1];
                    y4 = section[i + 1][3];
                    if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)) {
                        out.println(section[index + 1][6] + 1);
                        good = true;
                        break;
                    }
                }
                if (good){
                    break;
                }
                out.println(section[index][6] + 1);
            }
        }
        if (!good) {
            section = new long[n % 100][7];
            for (int i = 0; i < (n % 100) - 1; i++) {
                //fill in x's and y's with data of the current lines to be checked
                System.arraycopy(lines, 0, section, 0, n % 100);
                Arrays.sort(section, Comparator.comparingLong(arr -> arr[5]));
                x1 = section[i][0];
                x2 = section[i][2];
                y1 = section[i][1];
                y2 = section[i][3];

                x3 = section[i + 1][0];
                x4 = section[i + 1][2];
                y3 = section[i + 1][1];
                y4 = section[i + 1][3];

                if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)){
                    index = i;
                    good = true;
                    break;
                }
            }
            if (good){
                good = false;
                for (int i = 0; i < (n % 100) - 1; i++){
                    if (i == index - 1 || i == index){
                        continue;
                    }
                    x3 = section[i + 1][0];
                    x4 = section[i + 1][2];
                    y3 = section[i + 1][1];
                    y4 = section[i + 1][3];
                    if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)){
                        out.println(section[index][6] + 1);
                        good = true;
                        break;
                    }
                }

                if (!good) {
                    x1 = section[index + 1][0];
                    x2 = section[index + 1][2];
                    y1 = section[index + 1][1];
                    y2 = section[index + 1][3];
                    for (int i = 0; i < (n % 100) - 1; i++) {
                        if (i == index - 1 || i == index) {
                            continue;
                        }
                        x3 = section[i + 1][0];
                        x4 = section[i + 1][2];
                        y3 = section[i + 1][1];
                        y4 = section[i + 1][3];
                        if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)) {
                            out.println(section[index + 1][6] + 1);
                            good = true;
                            break;
                        }
                    }
                }
                if (!good) {
                    out.println(section[index][6] + 1);
                }
            }
        }
        if (!good){
            section = new long[100][7];
            long[][] section2 = new long[100][7];
            long[][] boundingBoxes = new long[2][4];
            for (int i = 0; i < (n / 100) - 1; i++){
                boundingBoxes = new long[2][4];
                System.arraycopy(lines, 100 * i, section, 0, 100);
                System.arraycopy(lines, 100 * (i + 1), section2, 0, 100);
                for (int j = 0; j < 100; j++){
                    boundingBoxes[0][0] = Math.min(boundingBoxes[0][0], Math.min(section[j][0], section[j][2]));
                    boundingBoxes[0][1] = Math.max(boundingBoxes[0][1], Math.max(section[j][1], section[j][3]));
                    boundingBoxes[0][2] = Math.max(boundingBoxes[0][2], Math.max(section[j][0], section[j][2]));
                    boundingBoxes[0][3] = Math.min(boundingBoxes[0][3], Math.min(section[j][1], section[j][3]));
                }
                for (int j = 0; j < 100; j++){
                    boundingBoxes[1][0] = Math.min(boundingBoxes[1][0], Math.min(section2[j][0], section2[j][2]));
                    boundingBoxes[1][1] = Math.max(boundingBoxes[1][1], Math.max(section2[j][1], section2[j][3]));
                    boundingBoxes[1][2] = Math.max(boundingBoxes[1][2], Math.max(section2[j][0], section2[j][2]));
                    boundingBoxes[1][3] = Math.min(boundingBoxes[1][3], Math.min(section2[j][1], section2[j][3]));
                }
                CowSteepleChaseII12.Point l1 = new CowSteepleChaseII12.Point();
                l1.x = boundingBoxes[0][0];
                l1.y = boundingBoxes[0][1];
                CowSteepleChaseII12.Point r1 = new CowSteepleChaseII12.Point();
                r1.x = boundingBoxes[0][2];
                r1.y = boundingBoxes[0][3];
                CowSteepleChaseII12.Point l2 = new CowSteepleChaseII12.Point();
                l2.x = boundingBoxes[1][0];
                l2.y = boundingBoxes[1][1];
                CowSteepleChaseII12.Point r2 = new CowSteepleChaseII12.Point();
                r2.x = boundingBoxes[1][2];
                r2.y = boundingBoxes[1][3];
                if (doOverlap(l1, r1, l2, r2)){
                    long[][] sectionMerged = new long[200][7];
                    System.arraycopy(section, 0, sectionMerged, 0, 100);
                    System.arraycopy(section2, 0, sectionMerged, 100, 100);
                    Arrays.sort(sectionMerged, Comparator.comparingLong(arr -> arr[5]));
                    for (int j = 0; j < 199; j++){ //comparing lines
                        //fill in x's and y's with data of the current lines to be checked
                        x1 = sectionMerged[j][0];
                        x2 = sectionMerged[j][2];
                        y1 = sectionMerged[j][1];
                        y2 = sectionMerged[j][3];

                        x3 = sectionMerged[j + 1][0];
                        x4 = sectionMerged[j + 1][2];
                        y3 = sectionMerged[j + 1][1];
                        y4 = sectionMerged[j + 1][3];

                        if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)){
                            index = j;
                            good = true;
                            break;
                        }
                    }
                    if (good){
                        good = false;
                        for (int j = 0; j < 99; j++){
                            if (j == index - 1 || j == index){
                                continue;
                            }
                            x3 = sectionMerged[j + 1][0];
                            x4 = sectionMerged[j + 1][2];
                            y3 = sectionMerged[j + 1][1];
                            y4 = sectionMerged[j + 1][3];
                            if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)){
                                out.println(sectionMerged[index][6] + 1);
                                good = true;
                                break;
                            }
                        }

                        if (good){
                            break;
                        }
                        x1 = sectionMerged[index + 1][0];
                        x2 = sectionMerged[index + 1][2];
                        y1 = sectionMerged[index + 1][1];
                        y2 = sectionMerged[index + 1][3];
                        for (int j = 0; j < 99; j++) {
                            if (j == index - 1 || j == index){
                                continue;
                            }
                            x3 = sectionMerged[j + 1][0];
                            x4 = sectionMerged[j + 1][2];
                            y3 = sectionMerged[j + 1][1];
                            y4 = sectionMerged[j + 1][3];
                            if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)) {
                                out.println(sectionMerged[index + 1][6] + 1);
                                good = true;
                                break;
                            }
                        }
                        if (good){
                            break;
                        }
                        out.println(sectionMerged[index][6] + 1);
                    }
                }
            }
            if (n % 100 != 0){
                System.arraycopy(lines, 100 * (n / 100), section, 0, n % 100);
                for (int j = 0; j < n % 100; j++){
                    boundingBoxes[0][0] = Math.min(boundingBoxes[0][0], Math.min(section[j][0], section[j][2]));
                    boundingBoxes[0][1] = Math.min(boundingBoxes[0][1], Math.min(section[j][1], section[j][3]));
                    boundingBoxes[0][2] = Math.min(boundingBoxes[0][2], Math.min(section[j][0], section[j][2]));
                    boundingBoxes[0][3] = Math.min(boundingBoxes[0][3], Math.min(section[j][1], section[j][3]));
                }
                CowSteepleChaseII12.Point l1 = new CowSteepleChaseII12.Point();
                l1.x = boundingBoxes[0][0];
                l1.y = boundingBoxes[0][1];
                CowSteepleChaseII12.Point r1 = new CowSteepleChaseII12.Point();
                r1.x = boundingBoxes[0][2];
                r1.y = boundingBoxes[0][3];
                CowSteepleChaseII12.Point l2 = new CowSteepleChaseII12.Point();
                l2.x = boundingBoxes[1][0];
                l2.y = boundingBoxes[1][1];
                CowSteepleChaseII12.Point r2 = new CowSteepleChaseII12.Point();
                r2.x = boundingBoxes[1][2];
                r2.y = boundingBoxes[1][3];
                if (doOverlap(l1, r1, l2, r2)){
                    long[][] sectionMerged = new long[200][7];
                    System.arraycopy(section, 0, sectionMerged, 0, 100);
                    System.arraycopy(section2, 0, sectionMerged, 100, 100);
                    Arrays.sort(sectionMerged, Comparator.comparingLong(arr -> arr[5]));
                    for (int j = 0; j < 199; j++){ //comparing lines
                        //fill in x's and y's with data of the current lines to be checked
                        x1 = sectionMerged[j][0];
                        x2 = sectionMerged[j][2];
                        y1 = sectionMerged[j][1];
                        y2 = sectionMerged[j][3];

                        x3 = sectionMerged[j + 1][0];
                        x4 = sectionMerged[j + 1][2];
                        y3 = sectionMerged[j + 1][1];
                        y4 = sectionMerged[j + 1][3];

                        if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)){
                            index = j;
                            good = true;
                            break;
                        }
                    }
                    if (good){
                        good = false;
                        for (int j = 0; j < 99; j++){
                            if (j == index - 1 || j == index){
                                continue;
                            }
                            x3 = sectionMerged[j + 1][0];
                            x4 = sectionMerged[j + 1][2];
                            y3 = sectionMerged[j + 1][1];
                            y4 = sectionMerged[j + 1][3];
                            if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)){
                                out.println(sectionMerged[index][6] + 1);
                                good = true;
                                break;
                            }
                        }

                        if (!good) {
                            x1 = sectionMerged[index + 1][0];
                            x2 = sectionMerged[index + 1][2];
                            y1 = sectionMerged[index + 1][1];
                            y2 = sectionMerged[index + 1][3];
                            for (int j = 0; j < 99; j++) {
                                if (j == index - 1 || j == index) {
                                    continue;
                                }
                                x3 = sectionMerged[j + 1][0];
                                x4 = sectionMerged[j + 1][2];
                                y3 = sectionMerged[j + 1][1];
                                y4 = sectionMerged[j + 1][3];
                                if (jerryLineIntersect(x1, x2, x3, x4, y1, y2, y3, y4)) {
                                    out.println(sectionMerged[index + 1][6] + 1);
                                    good = true;
                                    break;
                                }
                            }
                        }
                        if (!good) {
                            out.println(sectionMerged[index][6] + 1);
                        }
                    }
                }
            }
        }
        out.close();
        System.out.println(System.currentTimeMillis() - start);
    }

    private static boolean jerryLineIntersect(long x1, long x2, long x3, long x4, long y1, long y2, long y3, long y4){
        CowSteepleChaseII12.Point l1 = new CowSteepleChaseII12.Point();
        l1.x = Math.min(x1, x2);
        l1.y = Math.max(y1, y2);
        CowSteepleChaseII12.Point r1 = new CowSteepleChaseII12.Point();
        r1.x = Math.max(x1, x2);
        r1.y = Math.min(y1, y2);
        CowSteepleChaseII12.Point l2 = new CowSteepleChaseII12.Point();
        l2.x = Math.min(x3, x4);
        l2.y = Math.max(y3, y4);
        CowSteepleChaseII12.Point r2 = new CowSteepleChaseII12.Point();
        r2.x = Math.max(x3, x4);
        r2.y = Math.min(y3, y4);

        if (doOverlap(l1, r1, l2, r2)) {
            return linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
        }
        return false;
    }

    // Returns true if two rectangles (l1, r1) and (l2, r2) overlap
    private static boolean doOverlap(CowSteepleChaseII12.Point l1, CowSteepleChaseII12.Point r1, CowSteepleChaseII12.Point l2, CowSteepleChaseII12.Point r2) {
        // If one rectangle is on left side of other
        if (l1.x > r2.x || l2.x > r1.x) {
            return false;
        }

        // If one rectangle is above other
        return l1.y >= r2.y && l2.y >= r1.y;
    }

    // Returns if two lines intersect
    public static boolean linesIntersect(long x1, long y1,
                                         long x2, long y2,
                                         long x3, long y3,
                                         long x4, long y4) {
        return ((relativeCCW(x1, y1, x2, y2, x3, y3) *
                relativeCCW(x1, y1, x2, y2, x4, y4) <= 0)
                && (relativeCCW(x3, y3, x4, y4, x1, y1) *
                relativeCCW(x3, y3, x4, y4, x2, y2) <= 0));
    }

    // Helper class for method above (linesIntersect)
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

    static class Point {
        long x, y;
    }
}
