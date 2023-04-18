/*
ID: jerryya2
LANG: JAVA
PROG: barn1
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class barn1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("barn1.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
//        int s = Integer.parseInt(st.nextToken());
        st.nextToken();
        int c = Integer.parseInt(st.nextToken());

        ArrayList<int[]> boards = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            int start = Integer.parseInt(br.readLine());
            int[] board = new int[2];
            board[0] = start;
            board[1] = start + 1;
            boards.add(board);
        }
        br.close();
        boards.sort(Comparator.comparingInt(a -> a[0]));

        while (boards.size() > m) {
            int min = 0;
            int minSize = Integer.MAX_VALUE;
            for (int i = 0; i < boards.size() - 1; i++) {
                int size = boards.get(i + 1)[0] - boards.get(i)[1];
                if (size < minSize) {
                    min = i;
                    minSize = size;
                }
            }
            boards.get(min)[1] = boards.get(min + 1)[1];
            boards.remove(min + 1);
        }

        int sum = 0;
        for (int i = 0; i < boards.size(); i++) {
            int[] board = boards.get(i);
            sum += board[1] - board[0];
        }
        pw.println(sum);

        pw.close();
    }
}
