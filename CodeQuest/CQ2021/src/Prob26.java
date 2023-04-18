import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Prob26 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int x = Integer.parseInt(br.readLine());

            String[] tmp = new String[x];
            int max = 0;
            for (int i = 0; i < x; i++) {
                tmp[i] = br.readLine();
                if (tmp[i].length() > max) {
                    max = tmp[i].length();
                }
            }

            boolean[][] data = new boolean[2 * x - 1][max];
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < tmp[i].length(); j++) {
                    if (tmp[i].charAt(j) == '|') {
                        data[2 * i - 1][j] = true;
                    } else if (tmp[i].charAt(j) == '_') {
                        data[2 * i][j] = true;
                    }
                }
            }

            int count = 0;
            for (int i = 0; i < data.length; i += 2) {
                for (int j = 0; j < max; j += 2) {
                    for (int k = i + 2, l = j + 2; k < data.length && l < max; k += 2, l += 2) {
                        if (checkSquare(data, i, j, k, l)) {
                            count++;
                        }
                    }
                }
            }
            pw.println(count);
        }

        pw.close();
    }

    private static boolean checkSquare(boolean[][] data, int i, int j, int k, int l) {
        for (int m = i + 1; m < k; m += 2) {
            if (!data[m][j] || !data[m][l]) {
                return false;
            }
        }
        for (int m = j + 1; m < l; m += 2) {
            if (!data[i][m] || !data[k][m]) {
                return false;
            }
        }
        return true;
    }
}
