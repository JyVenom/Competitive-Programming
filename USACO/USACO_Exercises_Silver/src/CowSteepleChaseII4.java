import java.awt.geom.Line2D;
import java.io.*;

public class CowSteepleChaseII4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowjump.in"));
        PrintWriter out = new PrintWriter(new File("cowjump.out"));

        int N = Integer.parseInt(br.readLine());
        long[][] data = new long[N][4];

        int temp;
        String line;
        for (int i = 0; i < N; i++){
            line = br.readLine();
            data[i][0] = Integer.parseInt(line.substring(0, line.indexOf(' ')));
            temp = line.indexOf(' ') + 1;
            data[i][1] = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
            temp = line.indexOf(' ', temp) + 1;
            data[i][2] = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
            temp = line.indexOf(' ', temp) + 1;
            data[i][3] = Integer.parseInt(line.substring(temp));
        }

        int[] pair = new int[2];
        long x1 = 0, x2 = 0, x3 = 0, x4 = 0;
        long y1 = 0, y2 = 0, y3 = 0, y4 = 0;
        boolean good = false;
        for (int i = 0; i < N; i++){
            for (int j = i + 1; j < N; j++){
                if (j == i){
                    continue;
                }
                x1 = data[i][0];
                x2 = data[i][2];
                x3 = data[j][0];
                x4 = data[j][2];
                y1 = data[i][1];
                y2 = data[i][3];
                y3 = data[j][1];
                y4 = data[j][3];

                if (Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4)){
                    pair[0] = i;
                    pair[1] = j;
                    good = true;
                    break;
                }
            }
            if (good){
                break;
            }
        }

        good = false;
        for (int j = pair[1] + 1; j < N; j++){
            long x5 = data[j][0];
            long x6 = data[j][2];
            long y5 = data[j][1];
            long y6 = data[j][3];
            if (Line2D.linesIntersect(x1, y1, x2, y2, x5, y5, x6, y6)){
                out.println(pair[0] + 1);
                good = true;
                break;
            }
        }
        if (!good){
            for (int i = pair[0] + 1; i < N; i++){
                if (i == pair[1]){
                    continue;
                }
                x1 = data[i][0];
                x2 = data[i][2];
                y1 = data[i][1];
                y2 = data[i][3];

                if (Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4)){
                    out.println(pair[1] + 1);
                    good = true;
                    break;
                }
            }
        }
        if (!good){
            out.println(Math.min(pair[0], pair[1]) + 1);
        }

        out.close();
    }
}
