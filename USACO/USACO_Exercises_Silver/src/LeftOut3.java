import java.io.*;

public class LeftOut3 {
    private static int N;
    private static boolean[][] cows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("leftout.in"));
        PrintWriter out = new PrintWriter(new File("leftout.out"));

        N = Integer.parseInt(br.readLine());
        cows = new boolean[N][N]; //true is 1, false is 0. looking for all 0s

        String line;
        for (int i = 0; i < N; i++) {
            line = br.readLine();
            for (int j = 0; j < N; j++) {
                cows[i][j] = line.charAt(j) == 'R';
            }
        }

        for (int i = 0; i < N; i++) {
            if (cows[0][i]){
                flipCol(i, cows);
            }
        }
        for (int i = 1; i < N; i++){
            if (cows[i][0]){
                flipRow(i, cows);
            }
        }

        if (filled(cows)){
            out.println("1 1");
        }
        else if (possible(cows)){
            out.println(-1);
        }
        else {
            boolean done = false;
            for (int i = 1; i < N; i++){
                if (filledRow(cows, i)){
                    done = true;
                    out.println((i + 1) + " 1");
                }
            }
            if (!done){
                for (int i = 1; i < N; i++){
                    if (filledCol(cows, i)){
                        done = true;
                        out.println("1 " + (i + 1));
                    }
                }
            }
            if (!done){
                if (numWrong() != 1) {
                    done = true;
                    out.println(-1);
                }
            }
            if (!done){
                for (int i = 1; i < N; i++){
                    for (int j = 1; j < N; j++){
                        if (cows[i][j]){
                            done = true;
                            out.println((i + 1) + " " + (j + 1));
                            break;
                        }
                    }
                    if (done){
                        break;
                    }
                }
            }
        }
        out.close();
    }

    private static void flipRow(int row, boolean[][] data) {
        for (int i = 0; i < N; i++) {
            data[row][i] = !data[row][i];
        }
    }

    private static void flipCol(int col, boolean[][] data) {
        for (int i = 0; i < N; i++) {
            data[i][col] = !data[i][col];
        }
    }

    private static boolean possible (boolean[][] data){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (data[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean filled (boolean[][] data){
        for (int i = 1; i < N; i++){
            for (int j = 1; j < N; j++){
                if (!data[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean filledRow (boolean[][] data, int row){
        for (int i = 1; i < N; i++){
            if (!data[row][i]){
                return false;
            }
        }
        return true;
    }

    private static boolean filledCol (boolean[][] data, int col){
        for (int i = 1; i < N; i++){
            if (!data[i][col]){
                return false;
            }
        }
        return true;
    }
    private static int numWrong() {
        int total = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (cows[i][j]) {
                    total++;
                }
            }
        }
        return total;
    }
}
