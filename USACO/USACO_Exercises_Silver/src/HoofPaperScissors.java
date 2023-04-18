import java.io.*;
import java.util.Arrays;

public class HoofPaperScissors {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hps.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));

        int n = Integer.parseInt(br.readLine());

//        String[] moves = new String[n];
//        for (int i = 0; i < n; i++) {
//            moves[i] = br.readLine();
//        }
        int[][] moves = new int[n][3];
        String first = br.readLine();
        if (first.equals("H")) {
            moves[0][0]++;
        }
        else if (first.equals("P")) {
            moves[0][1]++;
        }
        else if (first.equals("S")) {
            moves[0][2]++;
        }
        for (int i = 1; i < n; i++) {
            String move = br.readLine();
            moves[i] = moves[i - 1].clone();
            switch (move) {
                case "H":
                    moves[i][0]++;
                    break;
                case "P":
                    moves[i][1]++;
                    break;
                case "S":
                    moves[i][2]++;
                    break;
            }
        }

        int[] copy = moves[n - 1].clone();
        Arrays.sort(copy);
        int max = copy[0];
        for (int i = 1; i < n; i++) {
            int[] prev = moves[i - 1].clone();
            int[] rem = moves[n - 1].clone();
            for (int j = 0; j < 3; j++) {
                rem[j] -= prev[j];
            }
            Arrays.sort(prev);
            Arrays.sort(rem);
            int sum = prev[2] + rem[2];
            if (sum > max) {
                max = sum;
            }
        }

        pw.println(max);
        pw.close();
    }
}
