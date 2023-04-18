import java.awt.geom.Line2D;
import java.io.*;

public class CowSteepleChaseII10 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowjump.in"));
        PrintWriter out = new PrintWriter(new File("cowjump.out"));

        long start = System.currentTimeMillis(); //jerry
        long start2 = System.currentTimeMillis();

        int n = Integer.parseInt(br.readLine());
        long[][] data = new long[n][2];
        long[][] data2 = new long[n][4];

        int temp;
        String line;
        long maxX = 0, minX = 1000000000, maxY = 0, minY = 1000000000;
        for (int i = 0; i < n; i++) {
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
        System.out.println(System.currentTimeMillis() - start); //jerry

        int prev = 0;
        boolean good;
        start = System.currentTimeMillis(); //jerry
        int[] pair = new int[2];
        long x1 = 0, x2 = 0, x3 = 0, x4 = 0;
        long y1 = 0, y2 = 0, y3 = 0, y4 = 0;
        good = false;
        long count = 0; //jerry
        n=10000;//yifengy
        for (int j = prev; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                if (count == 10000000){
                    System.out.println("yyf"+(System.currentTimeMillis() - start)); //jerry
                    start = System.currentTimeMillis();
                    count = 0;
                }
                count++;
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

        good = false;
        for (int k = pair[1] + 1; k < n; k++) {
            long x5 = data2[k][0];
            long x6 = data2[k][2];
            long y5 = data2[k][1];
            long y6 = data2[k][3];
            if (Line2D.linesIntersect(x1, y1, x2, y2, x5, y5, x6, y6)) {
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
        }
        if (!good) {
            out.println(Math.min(pair[0], pair[1]) + 1);
        }

        out.close();
        System.out.println(System.currentTimeMillis() - start2); //jerry
        System.out.println(count);
    }
}
