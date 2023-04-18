import java.io.*;

public class cowtip {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowtip.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowtip.out")));

        int n = Integer.parseInt(br.readLine());
        boolean[][] data = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                if (line.charAt(j) == '1') {
                    data[i][j] = true;
                }
            }
        }

        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (data[i][j]) {
                    flip(data, i, j);
                    count++;
                }
            }
        }

        pw.println(count);
        pw.close();
    }

    private static void flip(boolean[][] data, int x, int y) {
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                data[i][j] = !data[i][j];
            }
        }
    }
}
