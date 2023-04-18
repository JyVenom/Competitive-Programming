/*
ID: jerryya2
LANG: JAVA
TASK: camelot
*/

import java.io.*;
import java.util.*;

public class camelot {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("camelot.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Piece king = new Piece(st.nextToken().charAt(0) - 'A', Integer.parseInt(st.nextToken()) - 1);
        ArrayList<Piece> knights = new ArrayList<>();
        String line = br.readLine();
        while (line != null && !line.equals("")) {
            String[] knight = line.split(" ");
            int size = knight.length;
            for (int i = 0; i < size; i += 2) {
                knights.add(new Piece(knight[i].charAt(0) - 'A', Integer.parseInt(knight[i + 1]) - 1));
            }
            line = br.readLine();
        }
        br.close();

        int[] changeRow = new int[] { -2, -2, -1, 1, 2, 2, 1, -1};
        int[] changeCol = new int[] { -1, 1, 2, 2, 1, -1, -2, -2};
        int[][][][] dist = new int[r][c][r][c];
        for (int[][][] ints : dist) {
            for (int[][] anInt : ints) {
                for (int[] value : anInt) {
                    Arrays.fill(value, Integer.MAX_VALUE / 2);
                }
            }
        }
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                ArrayList<Piece> queue = new ArrayList<>();
                queue.add(new Piece(j, i));
                dist[i][j][i][j] = 0;
                while(!queue.isEmpty()) {
                    Piece piece = queue.get(0);
                    queue.remove(0);
                    for(int stepType = 0; stepType < changeRow.length; stepType++) {
                        int newRow = piece.row + changeRow[stepType];
                        int newCol = piece.col + changeCol[stepType];
                        if(newRow >= 0 && newCol >= 0 && newRow < r && newCol < c && dist[i][j][newRow][newCol] == Integer.MAX_VALUE / 2) {
                            dist[i][j][newRow][newCol] = dist[i][j][piece.row][piece.col] + 1;
                            queue.add(new Piece(newCol, newRow));
                        }
                    }
                }
            }
        }
        int kingStartRow = Math.max(0, king.row - 2);
        int kingStartCol = Math.max(0, king.col - 2);
        int kingEndRow = Math.min(r - 1, king.row + 2);
        int kingEndCol = Math.min(c - 1, king.col + 2);
        long minStep = Long.MAX_VALUE / 2;
        boolean good = false;
        for(int row = 0; row < r; row++) {
            for(int col = 0; col < c; col++) {
                long step = 0;
                for (Piece knight : knights) {
                    step += dist[knight.row][knight.col][row][col];
                }
                long min = Math.max(Math.abs(king.row - row), Math.abs(king.col - col));
                for(int kingRow = kingStartRow; kingRow <= kingEndRow; kingRow++) {
                    for(int kingCol = kingStartCol; kingCol <= kingEndCol; kingCol++) {
                        for (Piece knight : knights) {
                            long temp = Math.max(Math.abs(king.row - kingRow), Math.abs(king.col - kingCol)) - dist[knight.row][knight.col][row][col] + dist[kingRow][kingCol][row][col] + dist[knight.row][knight.col][kingRow][kingCol];
                            min = Math.min(temp, min);
                        }
                    }
                }
                if (!(min < 0 || step < 0 || min + step < 0)) {
                    minStep = Math.min(min + step, minStep);
                }
                if(minStep == 0) {
                    good = true;
                    break;
                }
            }
            if (good) {
                break;
            }
        }
        
        pw.println(minStep);
        pw.close();
    }


    private static class Piece {
        int row, col;

        private Piece(int a, int b) {
            col = a;
            row = b;
        }
    }
}