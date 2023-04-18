/*
ID: jerryya2
LANG: JAVA
TASK: ttwo
*/

import java.io.*;

public class ttwo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ttwo.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));

        int[][] map = new int[10][10];
        int[] c = new int[3];
        int[] f = new int[3];
        for (int i = 0; i < 10; i++) {
            String line = br.readLine();
            String[] squares = line.split("");
            for (int j = 0; j < 10; j++) {
                String square = squares[j];
                switch (square) {
                    case "*" -> map[i][j] = 1;
                    case "C" -> {
                        c[0] = i;
                        c[1] = j;
                    }
                    case "F" -> {
                        f[0] = i;
                        f[1] = j;
                    }
                }
            }
        }
        br.close();

        boolean[][][][][][] visited = new boolean[10][10][4][10][10][4];
        boolean good = true;
        int count = 0;
        while (!(c[0] == f[0] && c[1] == f[1])) {
            count++;
            //Move c
            if (c[2] == 0) {
                if (c[0] == 0 || map[c[0] - 1][c[1]] == 1) {
                    c[2] = (c[2] + 1) % 4;
                } else {
                    c[0]--;
                }
            } else if (c[2] == 1) {
                if (c[1] == 9 || map[c[0]][c[1] + 1] == 1) {
                    c[2] = (c[2] + 1) % 4;
                } else {
                    c[1]++;
                }
            } else if (c[2] == 2) {
                if (c[0] == 9 || map[c[0] + 1][c[1]] == 1) {
                    c[2] = (c[2] + 1) % 4;
                } else {
                    c[0]++;
                }
            } else {
                if (c[1] == 0 || map[c[0]][c[1] - 1] == 1) {
                    c[2] = (c[2] + 1) % 4;
                } else {
                    c[1]--;
                }
            }

            //Move f
            if (f[2] == 0) {
                if (f[0] == 0 || map[f[0] - 1][f[1]] == 1) {
                    f[2] = (f[2] + 1) % 4;
                } else {
                    f[0]--;
                }
            } else if (f[2] == 1) {
                if (f[1] == 9 || map[f[0]][f[1] + 1] == 1) {
                    f[2] = (f[2] + 1) % 4;
                } else {
                    f[1]++;
                }
            } else if (f[2] == 2) {
                if (f[0] == 9 || map[f[0] + 1][f[1]] == 1) {
                    f[2] = (f[2] + 1) % 4;
                } else {
                    f[0]++;
                }
            } else {
                if (f[1] == 0 || map[f[0]][f[1] - 1] == 1) {
                    f[2] = (f[2] + 1) % 4;
                } else {
                    f[1]--;
                }
            }

            if (visited[c[0]][c[1]][c[2]][f[0]][f[1]][f[2]]) {
                good = false;
                break;
            } else {
                visited[c[0]][c[1]][c[2]][f[0]][f[1]][f[2]] = true;
            }
        }

        if (good) {
            pw.println(count);
        } else {
            pw.println(0);
        }
        pw.close();
    }
}
