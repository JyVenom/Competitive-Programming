import java.io.*;

public class PaintingTheBarn2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
        int[][] barn = new int[1001][1001];
        String line = br.readLine();
        int N = Integer.parseInt(line.substring(0, line.indexOf(' ')));
        int K = Integer.parseInt(line.substring(line.indexOf(' ') + 1));

        for (int i = 0; i < N; i++){
            int temp;
            line = br.readLine();
            int x = Integer.parseInt(line.substring(0, line.indexOf(' ')));
            temp = line.indexOf(' ') + 1;
            int y = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
            temp = line.indexOf(' ', temp) + 1;
            int X = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
            temp = line.indexOf(' ', temp) + 1;
            int Y = Integer.parseInt(line.substring(temp));
            for (int j = x; j < X; j++) {
                barn[j][y]++;
                barn[j][Y]--;
            }
        }
        
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (barn[i][j] == K) {
                    count++;
                }
                barn[i][j + 1] += barn[i][j];
            }
        }

        out.println(count);
        out.close();
    }
}
