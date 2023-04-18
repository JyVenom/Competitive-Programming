import java.io.*;
import java.util.ArrayList;

public class BovineGenomics {
    private static int n;
    private static int m;
    private static int[][] plain;
    private static int[][] spots;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter out = new PrintWriter(new File("cownomics.out"));
        String line = br.readLine();
        n = Integer.parseInt(line.substring(0, line.indexOf(' ')));
        m = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
        plain = new int[n][m];
        spots = new int[n][m];

        for (int i = 0; i < n; i++){
            line = br.readLine();
            for (int j = 0; j < m; j++) {
                char gene = line.charAt(j);
                if (gene == 'A'){
                    spots[i][j] = 0;
                }
                else if (gene == 'C'){
                    spots[i][j] = 1;
                }
                else if (gene == 'G'){
                    spots[i][j] = 2;
                }
                else if (gene == 'T'){
                    spots[i][j] = 3;
                }
            }
        }

        for (int i = 0; i < n; i++){
            line = br.readLine();
            for (int j = 0; j < m; j++) {
                char gene = line.charAt(j);
                if (gene == 'A'){
                    plain[i][j] = 0;
                }
                else if (gene == 'C'){
                    plain[i][j] = 1;
                }
                else if (gene == 'G'){
                    plain[i][j] = 2;
                }
                else if (gene == 'T'){
                    plain[i][j] = 3;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < m; i++){
            for (int j = i + 1; j < m; j++){
                for (int k = j + 1; k < m; k++){
                    if (testLocation(i, j, k)){
                        answer++;
                    }
                }
            }
        }

        out.println(answer);
        out.close();
    }

    private static boolean testLocation (int i, int j, int k){
        boolean good = true;
        ArrayList<Integer> combinations = new ArrayList<>();
        for (int i2 = 0; i2 < n; i2++){
            combinations.add(spots[i2][i] * 100 + spots[i2][j] * 10 + spots[i2][k]);
        }
        for (int i2 = 0; i2 < n; i2++){
            int temp = plain[i2][i] * 100 + plain[i2][j] * 10 + plain[i2][k];
            if (combinations.contains(temp)){
                good = false;
                break;
            }
        }
        return good;
    }
}
