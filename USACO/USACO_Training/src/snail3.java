/*
ID: jerryya2
LANG: JAVA
TASK: snail
*/

//4n^2
//n <= 120
//57600

import java.io.*;
import java.util.Arrays;

public class snail3 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("snail.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("snail.out")));

        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int b = Integer.parseInt(params[1]);
        boolean[][] barriers = new boolean[n][n];
        for (int i = 0; i < b; i++) {
            String line = br.readLine();
            barriers[Integer.parseInt(line.substring(1)) - 1][line.charAt(0) - 'A'] = true;
        }

        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        int right = dfs(barriers, visited, 0, 0, 0, 0, 0); //dir: {0, 1, 2, 3) = {right, down, left, up}
        visited = new boolean[n][n];
        visited[0][0] = true;
        int down = dfs(barriers, visited, 0, 0, 1, 0, 0);
        int max = Math.max(right, down);

        pw.println(max);
        pw.close();
        System.out.println(System.currentTimeMillis() - start);
    }

    private static int dfs(boolean[][] map, boolean[][] visited, int row, int col, int dir, int dist, int max) {
        visited[row][col] = true;
        dist++;

        if (dir == 0) {
            if (col < map[0].length - 1) {
                if (!visited[row][col + 1]) {
                    if (!map[row][col + 1]) {
                        max = dfs(map, visited, row, col + 1, 0, dist, max);
                    } else {
                        boolean stuck = true;
                        if (row > 0) {
                            if (!visited[row - 1][col]) {
                                if (!map[row - 1][col]) {
                                    stuck = false;
                                    boolean[][] copy = new boolean[visited.length][visited.length];
                                    for (int i = 0; i < visited.length; i++) {
                                        copy[i] = Arrays.copyOf(visited[i], visited[i].length);
//                                        For Java versions prior to Java 6 use the next:
//                                        System.arraycopy(visited[i], 0, copy[i], 0, visited[i].length);
                                    }
                                    max = dfs(map, copy, row - 1, col, 3, dist, max);
                                }
                            }
                        }
                        if (row < map.length - 1) {
                            if (!visited[row + 1][col]) {
                                if (!map[row + 1][col]) {
                                    stuck = false;
                                    boolean[][] copy = new boolean[visited.length][visited.length];
                                    for (int i = 0; i < visited.length; i++) {
                                        copy[i] = Arrays.copyOf(visited[i], visited[i].length);
                                    }
                                    max = dfs(map, copy, row + 1, col, 1, dist, max);
                                }
                            }
                        }

                        if (stuck) {
                            if (dist > max) {
                                max = dist;
                            }
                        }
                    }
                } else {
                    if (dist > max) {
                        max = dist;
                    }
                }
            } else {
                boolean stuck = true;
                if (row > 0) {
                    if (!visited[row - 1][col]) {
                        if (!map[row - 1][col]) {
                            stuck = false;
                            boolean[][] copy = new boolean[visited.length][visited.length];
                            for (int i = 0; i < visited.length; i++) {
                                copy[i] = Arrays.copyOf(visited[i], visited[i].length);
                            }
                            max = dfs(map, copy, row - 1, col, 3, dist, max);
                        }
                    }
                }
                if (row < map.length - 1) {
                    if (!visited[row + 1][col]) {
                        if (!map[row + 1][col]) {
                            stuck = false;
                            boolean[][] copy = new boolean[visited.length][visited.length];
                            for (int i = 0; i < visited.length; i++) {
                                copy[i] = Arrays.copyOf(visited[i], visited[i].length);
                            }
                            max = dfs(map, copy, row + 1, col, 1, dist, max);
                        }
                    }
                }

                if (stuck) {
                    if (dist > max) {
                        max = dist;
                    }
                }
            }
        } else if (dir == 1) {
            if (row < map.length - 1) {
                if (!visited[row + 1][col]) {
                    if (!map[row + 1][col]) {
                        max = dfs(map, visited, row + 1, col, 1, dist, max);
                    } else {
                        boolean stuck = true;
                        if (col < map[0].length - 1) {
                            if (!visited[row][col + 1]) {
                                if (!map[row][col + 1]) {
                                    stuck = false;
                                    boolean[][] copy = new boolean[visited.length][visited.length];
                                    for (int i = 0; i < visited.length; i++) {
                                        copy[i] = Arrays.copyOf(visited[i], visited[i].length);
                                    }
                                    max = dfs(map, copy, row, col + 1, 0, dist, max);
                                }
                            }
                        }
                        if (col > 0) {
                            if (!visited[row][col - 1]) {
                                if (!map[row][col - 1]) {
                                    stuck = false;
                                    boolean[][] copy = new boolean[visited.length][visited.length];
                                    for (int i = 0; i < visited.length; i++) {
                                        copy[i] = Arrays.copyOf(visited[i], visited[i].length);
                                    }
                                    max = dfs(map, copy, row, col - 1, 2, dist, max);
                                }
                            }
                        }

                        if (stuck) {
                            if (dist > max) {
                                max = dist;
                            }
                        }
                    }
                } else {
                    if (dist > max) {
                        max = dist;
                    }
                }
            } else {
                boolean stuck = true;
                if (col < map[0].length - 1) {
                    if (!visited[row][col + 1]) {
                        if (!map[row][col + 1]) {
                            stuck = false;
                            boolean[][] copy = new boolean[visited.length][visited.length];
                            for (int i = 0; i < visited.length; i++) {
                                copy[i] = Arrays.copyOf(visited[i], visited[i].length);
                            }
                            max = dfs(map, copy, row, col + 1, 0, dist, max);
                        }
                    }
                }
                if (col > 0) {
                    if (!visited[row][col - 1]) {
                        if (!map[row][col - 1]) {
                            stuck = false;
                            boolean[][] copy = new boolean[visited.length][visited.length];
                            for (int i = 0; i < visited.length; i++) {
                                copy[i] = Arrays.copyOf(visited[i], visited[i].length);
                            }
                            max = dfs(map, copy, row, col - 1, 2, dist, max);
                        }
                    }
                }

                if (stuck) {
                    if (dist > max) {
                        max = dist;
                    }
                }
            }
        } else if (dir == 2) {
            if (col > 0) {
                if (!visited[row][col - 1]) {
                    if (!map[row][col - 1]) {
                        max = dfs(map, visited, row, col - 1, 2, dist, max);
                    } else {
                        boolean stuck = true;
                        if (row < map.length - 1) {
                            if (!visited[row + 1][col]) {
                                if (!map[row + 1][col]) {
                                    stuck = false;
                                    boolean[][] copy = new boolean[visited.length][visited.length];
                                    for (int i = 0; i < visited.length; i++) {
                                        copy[i] = Arrays.copyOf(visited[i], visited[i].length);
                                    }
                                    max = dfs(map, copy, row + 1, col, 1, dist, max);
                                }
                            }
                        }
                        if (row > 0) {
                            if (!visited[row - 1][col]) {
                                if (!map[row - 1][col]) {
                                    stuck = false;
                                    boolean[][] copy = new boolean[visited.length][visited.length];
                                    for (int i = 0; i < visited.length; i++) {
                                        copy[i] = Arrays.copyOf(visited[i], visited[i].length);
                                    }
                                    max = dfs(map, copy, row - 1, col, 3, dist, max);
                                }
                            }
                        }

                        if (stuck) {
                            if (dist > max) {
                                max = dist;
                            }
                        }
                    }
                } else {
                    if (dist > max) {
                        max = dist;
                    }
                }
            } else {
                boolean stuck = true;
                if (row < map.length - 1) {
                    if (!visited[row + 1][col]) {
                        if (!map[row + 1][col]) {
                            stuck = false;
                            boolean[][] copy = new boolean[visited.length][visited.length];
                            for (int i = 0; i < visited.length; i++) {
                                copy[i] = Arrays.copyOf(visited[i], visited[i].length);
                            }
                            max = dfs(map, copy, row + 1, col, 1, dist, max);
                        }
                    }
                }
                if (row > 0) {
                    if (!visited[row - 1][col]) {
                        if (!map[row - 1][col]) {
                            stuck = false;
                            boolean[][] copy = new boolean[visited.length][visited.length];
                            for (int i = 0; i < visited.length; i++) {
                                copy[i] = Arrays.copyOf(visited[i], visited[i].length);
                            }
                            max = dfs(map, copy, row - 1, col, 3, dist, max);
                        }
                    }
                }

                if (stuck) {
                    if (dist > max) {
                        max = dist;
                    }
                }
            }
        } else if (dir == 3) {
            if (row > 0) {
                if (!visited[row - 1][col]) {
                    if (!map[row - 1][col]) {
                        max = dfs(map, visited, row - 1, col, 3, dist, max);
                    } else {
                        boolean stuck = true;
                        if (col > 0) {
                            if (!visited[row][col - 1]) {
                                if (!map[row][col - 1]) {
                                    stuck = false;
                                    boolean[][] copy = new boolean[visited.length][visited.length];
                                    for (int i = 0; i < visited.length; i++) {
                                        copy[i] = Arrays.copyOf(visited[i], visited[i].length);
                                    }
                                    max = dfs(map, copy, row, col - 1, 2, dist, max);
                                }
                            }
                        }
                        if (col < map[0].length - 1) {
                            if (!visited[row][col + 1]) {
                                if (!map[row][col + 1]) {
                                    stuck = false;
                                    boolean[][] copy = new boolean[visited.length][visited.length];
                                    for (int i = 0; i < visited.length; i++) {
                                        copy[i] = Arrays.copyOf(visited[i], visited[i].length);
                                    }
                                    max = dfs(map, copy, row, col + 1, 0, dist, max);
                                }
                            }
                        }

                        if (stuck) {
                            if (dist > max) {
                                max = dist;
                            }
                        }
                    }
                } else {
                    if (dist > max) {
                        max = dist;
                    }
                }
            } else {
                boolean stuck = true;
                if (col > 0) {
                    if (!visited[row][col - 1]) {
                        if (!map[row][col - 1]) {
                            stuck = false;
                            boolean[][] copy = new boolean[visited.length][visited.length];
                            for (int i = 0; i < visited.length; i++) {
                                copy[i] = Arrays.copyOf(visited[i], visited[i].length);
                            }
                            max = dfs(map, copy, row, col - 1, 2, dist, max);
                        }
                    }
                }
                if (col < map[0].length - 1) {
                    if (!visited[row][col + 1]) {
                        if (!map[row][col + 1]) {
                            stuck = false;
                            boolean[][] copy = new boolean[visited.length][visited.length];
                            for (int i = 0; i < visited.length; i++) {
                                copy[i] = Arrays.copyOf(visited[i], visited[i].length);
                            }
                            max = dfs(map, copy, row, col + 1, 0, dist, max);
                        }
                    }
                }

                if (stuck) {
                    if (dist > max) {
                        max = dist;
                    }
                }
            }
        }

        return max;
    }
}
