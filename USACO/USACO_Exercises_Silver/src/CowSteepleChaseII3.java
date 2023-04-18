import java.awt.geom.Line2D;
import java.io.*;

public class CowSteepleChaseII3 {
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

        int[] count = new int[N];
        long x1, x2, x3, x4;
        long y1, y2, y3, y4;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
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
                    count[i]++;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++){
            if (count[i] > count[max]){
                max = i;
            }
        }

        out.println(max + 1);
        out.close();
    }
}
