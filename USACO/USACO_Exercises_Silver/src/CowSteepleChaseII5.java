import java.awt.geom.Line2D;
import java.io.*;

public class CowSteepleChaseII5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowjump.in"));
        PrintWriter out = new PrintWriter(new File("cowjump.out"));

        int N = Integer.parseInt(br.readLine());
        long[][] data = new long[N / 100][2];
        long[][][] regions = new long[24][N][4];

        int temp;
        String line;
        long maxX = 0, minX = 0, maxY = 0, minY = 0;
        for (int i = 0; i < N; i++){
            line = br.readLine();
            if ((i + 1) % 100 == 0){
                temp = line.indexOf(' ', line.indexOf(' ') + 1) + 1;
                data[i][0] = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
                temp = line.lastIndexOf(' ') + 1;
                data[i][1] = Integer.parseInt(line.substring(temp));
            }
            data[i][0] = Integer.parseInt(line.substring(0, line.indexOf(' ')));
            temp = line.indexOf(' ') + 1;
            data[i][1] = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
            temp = line.indexOf(' ', temp) + 1;
            data[i][2] = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
            temp = line.indexOf(' ', temp) + 1;
            data[i][3] = Integer.parseInt(line.substring(temp));
            maxX = Math.max(maxX, Math.max(data[i][0], data[i][2]));
            maxY = Math.max(maxY, Math.max(data[i][1], data[i][3]));
            minX = Math.min(minX, Math.min(data[i][0], data[i][2]));
            minY = Math.min(minY, Math.min(data[i][1], data[i][3]));
        }

        double[] choices = {1/24, 2/12, 3/8, 4/6, 6/4, 8/3, 12/2, 24/1};
        double x = maxX - minX;
        double y = maxY - minY;
        double min = 1;
        int index = 0;
        for (int i = 0; i < 8; i++){
            if (min > Math.min(min, Math.abs(min - choices[i]))){
                index = i;
                min = Math.abs(min - choices[i]);
            }
        }
        if (index == 0){
            for (int i = 0; i < N; i++){

            }
        }
        else if (index == 1){

        }
        else if (index == 2){

        }
        else if (index == 3){

        }
        else if (index == 4){

        }
        else if (index == 5){

        }
        else if (index == 6){

        }
        else {

        }

        for (int i = 0; i < 24; i++) {
            int[] pair = new int[2];
            long x1 = 0, x2 = 0, x3 = 0, x4 = 0;
            long y1 = 0, y2 = 0, y3 = 0, y4 = 0;
            boolean good = false;
            for (int j = 0; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (k == j) {
                        continue;
                    }
                    x1 = regions[i][j][0];
                    x2 = regions[i][j][2];
                    x3 = regions[i][k][0];
                    x4 = regions[i][k][2];
                    y1 = regions[i][j][1];
                    y2 = regions[i][j][3];
                    y3 = regions[i][k][1];
                    y4 = regions[i][k][3];

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
            for (int k = pair[1] + 1; k < N; k++) {
                long x5 = regions[i][k][0];
                long x6 = regions[i][k][2];
                long y5 = regions[i][k][1];
                long y6 = regions[i][k][3];
                if (Line2D.linesIntersect(x1, y1, x2, y2, x5, y5, x6, y6)) {
                    out.println(pair[0] + 1);
                    good = true;
                    break;
                }
            }
            if (good){
                break;
            }
            for (int j = pair[0] + 1; j < N; j++) {
                if (j == pair[1]) {
                    continue;
                }
                x1 = regions[i][j][0];
                x2 = regions[i][j][2];
                y1 = regions[i][j][1];
                y2 = regions[i][j][3];

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
            break;
        }

        out.close();
    }
}
