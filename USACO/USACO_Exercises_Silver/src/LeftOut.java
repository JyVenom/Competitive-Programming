import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class LeftOut {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("leftout.in"));
        PrintWriter out = new PrintWriter(new File("leftout.out"));

        N = Integer.parseInt(br.readLine());
        boolean[][] cows = new boolean[N][N];

        String line;
        for (int i = 0; i < N; i++) {
            line = br.readLine();
            for (int j = 0; j < N; j++) {
                cows[i][j] = line.charAt(j) == 'R';
            }
        }

        int num = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cows[i][j]) {
                    num++;
                }
            }
            if (num <= (N / 2)) {
                flipRow(i, cows);
            } else {
                for (int j = 0; j < N; j++) {
                    if (cows[i][j]) {
                        flipCol(j, cows);
                    }
                }
            }
        }

        ArrayList<ArrayList<Integer>> possible = new ArrayList<>();
        boolean done = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cows[i][j]) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add((i + 1));
                    temp.add((j + 1));
                    possible.add(temp);
                    done = true;
                }
            }
        }
        if (!done) {
            out.println(-1);
        } else {
            possible.sort(Comparator.comparing(a -> a.get(0)));
            ArrayList<ArrayList<Integer>> sameRow = new ArrayList<>();
            int minRow = possible.get(0).get(0);
            for (ArrayList<Integer> pair : possible) {
                if (pair.get(0) == minRow) {
                    sameRow.add(pair);
                }
            }
            sameRow.sort(Comparator.comparing(a -> a.get(1)));
            int row = sameRow.get(0).get(0);
            int col = sameRow.get(0).get(1);
            out.println(row + " " + col);
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
}
