//n*m
//1<= n,m <= 1,000
//1,000,000

import java.io.*;
import java.util.Arrays;

public class BessiesDream {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("dream.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));
        String[] line = br.readLine().split(" ");

        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//            line = br.readLine().split(" ");
//            for (int j = 0; j < m; j++) {
//                map[i][j] = Integer.parseInt(line[j]);
//            }
        }

        boolean[][] visited = new boolean[n][m];
        int min = findAns(map, visited, false, 0, 0, -1, n - 1, m - 1, 0, Integer.MAX_VALUE);

        pw.println(min);
        pw.close();
    }

    private static int findAns(int[][] map, boolean[][] visited, boolean orange, int row, int col, int dir, int n, int m, int moves, int min) {
        if (row == n && col == m) {
            if (moves < min) {
                min = moves;
            }
            return min;
        }

        boolean ran = false;
        visited[row][col] = true;
        if (map[row][col] == 4) {
            orange = false;
            //dir = 0 means come from above
            //dir = 1 means come from left
            //dir = 2 means come from below
            //dir = 3 means come from right
            if (dir == 0) {
                if (row < n) {
                    if (!visited[row + 1][col]) {
                        if (map[row + 1][col] != 0 && map[row + 1][col] != 3) {
                            ran = true;
                            min = findAns(map, visited, false, row + 1, col, 0, n, m, moves + 1, min);
                        }
                    }
                }
            } else if (dir == 1) {
                if (row < n) {
                    if (!visited[row][col + 1]) {
                        if (map[row][col + 1] != 0 && map[row][col + 1] != 3) {
                            ran = true;
                            min = findAns(map, visited, false, row, col + 1, 1, n, m, moves + 1, min);
                        }
                    }
                }
            } else if (dir == 2) {
                if (!visited[row - 1][col]) {
                    if (map[row - 1][col] != 0 && map[row - 1][col] != 3) {
                        ran = true;
                        min = findAns(map, visited, false, row - 1, col, 2, n, m, moves + 1, min);
                    }
                }
            } else if (dir == 3) {
                if (row < n) {
                    if (!visited[row][col - 1]) {
                        if (map[row][col - 1] != 0 && map[row][col - 1] != 3) {
                            ran = true;
                            min = findAns(map, visited, false, row, col - 1, 3, n, m, moves + 1, min);
                        }
                    }
                }
            }
        } else if (map[row][col] == 2) {
            orange = true;
        }

        if (!ran) {
            if (row < n) {
                if (!visited[row + 1][col]) {
                    if (!(map[row + 1][col] == 0 || (map[row + 1][col] == 3 && !orange))) {
                        ran = true;
                        min = findAns(map, visited, orange, row + 1, col, 0, n, m, moves + 1, min);
                    }
                }
            }
            if (col < m) {
                if (!visited[row][col + 1]) {
                    if (!(map[row][col + 1] == 0 || (map[row][col + 1] == 3 && !orange))) {
                        ran = true;
                        min = findAns(map, visited, orange, row, col + 1, 1, n, m, moves + 1, min);
                    }
                }
            }
            if (row > 0) {
                if (!visited[row - 1][col]) {
                    if (!(map[row - 1][col] == 0 || (map[row - 1][col] == 3 && !orange))) {
                        ran = true;
                        min = findAns(map, visited, orange, row - 1, col, 2, n, m, moves + 1, min);
                    }
                }
            }
            if (col > 0) {
                if (!visited[row][col - 1]) {
                    if (!(map[row][col - 1] == 0 || (map[row][col - 1] == 3 && !orange))) {
                        ran = true;
                        min = findAns(map, visited, orange, row, col - 1, 3, n, m, moves + 1, min);
                    }
                }
            }

            if (!ran) {
                if (row < n) {
                    if (!(map[row + 1][col] == 0 || (map[row + 1][col] == 3 && !orange) || dir == 2)) {
                        ran = true;
                        min = findAns(map, visited, orange, row + 1, col, 0, n, m, moves + 1, min);
                    }
                }
                if (col < m) {
                    if (!(map[row][col + 1] == 0 || (map[row][col + 1] == 3 && !orange) || dir == 3)) {
                        ran = true;
                        min = findAns(map, visited, orange, row, col + 1, 1, n, m, moves + 1, min);

                    }
                }
                if (row > 0) {
                    if (!(map[row - 1][col] == 0 || (map[row - 1][col] == 3 && !orange) || dir == 0)) {
                        ran = true;
                        min = findAns(map, visited, orange, row - 1, col, 2, n, m, moves + 1, min);

                    }
                }
                if (col > 0) {
                    if (!(map[row][col - 1] == 0 || (map[row][col - 1] == 3 && !orange) || dir == 1)) {
                        ran = true;
                        min = findAns(map, visited, orange, row, col - 1, 3, n, m, moves + 1, min);
                    }

                }

                if (!ran) {
                    if (map[row][col] == 2) {
                        if (dir == 0) {
                            if (row < n) {
                                if (!(map[row + 1][col] == 0 || (map[row + 1][col] == 3 && !orange))) {
                                    min = findAns(map, visited, orange, row + 1, col, 0, n, m, moves + 1, min);
                                }

                            }
                        } else if (dir == 1) {
                            if (col < m) {
                                if (!(map[row][col + 1] == 0 || (map[row][col + 1] == 3 && !orange))) {
                                    min = findAns(map, visited, orange, row, col + 1, 1, n, m, moves + 1, min);
                                }

                            }
                        } else if (dir == 2) {
                            if (row > 0) {
                                if (!(map[row - 1][col] == 0 || (map[row - 1][col] == 3 && !orange))) {
                                    min = findAns(map, visited, orange, row - 1, col, 2, n, m, moves + 1, min);
                                }

                            }
                        } else if (dir == 3) {
                            if (col > 0) {
                                if (!(map[row][col - 1] == 0 || (map[row][col - 1] == 3 && !orange))) {
                                    min = findAns(map, visited, orange, row, col - 1, 3, n, m, moves + 1, min);
                                }

                            }
                        }
                    }
                }
            }

            //next try going to to already visited but not come from (3 directions) if none of above were used
            //finally try the come from square
        }

        return min;
    }
}
