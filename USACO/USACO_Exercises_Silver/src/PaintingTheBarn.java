import java.io.*;

public class PaintingTheBarn {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
        PrintWriter out = new PrintWriter(new File("paintbarn.out"));
        String line = br.readLine();
        int N = Integer.parseInt(line.substring(0, line.indexOf(' ')));
        int K = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
        int[][] data = new int[N][4];

        int maxX = 0;
        int maxY = 0;
        int prev;
        for (int i = 0; i < N; i++){
            prev = 0;
            line = br.readLine();
            for (int j = 0; j < 3; j++){
                data[i][j] = Integer.parseInt(line.substring(prev, line.indexOf(' ', prev)));
                prev = line.indexOf(' ', prev) + 1;
                if (j == 2){
                    maxX = Math.max(maxX, data[i][j]);
                }
            }
            data[i][3] = Integer.parseInt(line.substring(prev));
            maxY = Math.max(maxY, data[i][3]);
        }

        long start = System.currentTimeMillis(); //jerry
        int[][] side = new int[maxX + 1][maxY + 1];
        for (int i = 0; i < N; i++){
            for (int x = data[i][0]; x < data[i][2]; x++){
                for (int y = data[i][1]; y < data[i][3]; y++){
                    side[x][y]++;
                }
            }
        }
        System.out.println(System.currentTimeMillis() - start); //jerry

        int count = 0;
        for (int i = 1; i <= maxX; i++){
            for (int j = 1; j <= maxY; j++){
                if (side[i][j] == K){
                    count++;
                }
            }
        }

        out.println(count);
        out.close();
    }
}
