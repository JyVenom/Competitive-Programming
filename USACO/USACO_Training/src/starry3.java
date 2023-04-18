/*
ID: jerryya2
LANG: JAVA
TASK: starry
*/

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class starry3 {
    private static final ArrayList<Shape> shapeList = new ArrayList<>();
    private static int[][] map;
    private static int W;
    private static int H;
    private static int[] rowStack1;
    private static int[] colStack1;
    private static int[] rowStack2;
    private static int[] colStack2;

    static String max(String s1, String s2) {
        if (s1 == null) return s2;
        return s1.compareTo(s2) <= 0 ? s1 : s2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("starry.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("starry.out")));

        W = Integer.parseInt(br.readLine());
        H = Integer.parseInt(br.readLine());
        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            String line = br.readLine();
            for (int j = 0; j < W && j < line.length(); j++) {
                if (line.charAt(j) == '1')
                    map[i][j] = 1;
            }
        }

        int SIZE = W * H;
        rowStack1 = new int[SIZE];
        rowStack2 = new int[SIZE];
        colStack1 = new int[SIZE];
        colStack2 = new int[SIZE];
        HashMap<String, Character> charMap = new HashMap<>();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 1) {
                    Shape shape = new Shape(shapeList.size());
                    shapeList.add(shape);
                    walkShape(shape, i, j);
                    Character cc = charMap.get(shape.getKey());
                    if (cc == null) {
                        cc = (char) ('a' + charMap.size());
                        charMap.put(shape.getKey(), cc);
                    }
                    shape.c = cc;
                }
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 0)
                    pw.print('0');
                else {
                    Shape shape = shapeList.get(map[i][j] - 256);
                    pw.print(shape.c);
                }
            }
            pw.println();
        }
        pw.close();
    }

    private static void walkShape(Shape shape, int row, int col) {
        int[] rows = rowStack1;
        int[] cols = colStack1;
        int size = 0;
        rows[size] = row;
        cols[size++] = col;
        while (size > 0) {
            int[] rows0 = rows;
            int[] cols0 = cols;
            int size0 = size;
            rows = rows0 == rowStack1 ? rowStack2 : rowStack1;
            cols = cols0 == colStack1 ? colStack2 : colStack1;
            size = 0;
            for (int i = 0; i < size0; i++) {
                row = rows0[i];
                col = cols0[i];
                if (row >= 0 && row < H && col >= 0 && col < W && map[row][col] == 1) {
                    shape.update(row, col);
                    map[row][col] = shape.id;
                    for (int dr = -1; dr <= 1; dr++) {
                        for (int dc = -1; dc <= 1; dc++) {
                            if (dr != 0 || dc != 0) {
                                rows[size] = row + dr;
                                cols[size++] = col + dc;
                            }
                        }
                    }
                }
            }
        }
    }

    private static class Shape {
        int index;
        int id;
        int minRow, maxRow, minCol, maxCol;
        int size;
        char c;
        String key;

        Shape(int index) {
            this.index = index;
            this.id = index + 256;
            this.minRow = Integer.MAX_VALUE;
            this.maxRow = -1;
            this.minCol = Integer.MAX_VALUE;
            this.maxCol = -1;
            this.size = 0;
        }

        void update(int row, int col) {
            if (row < minRow) minRow = row;
            if (row > maxRow) maxRow = row;
            if (col < minCol) minCol = col;
            if (col > maxCol) maxCol = col;
            this.size++;
        }

        String getKey() {
            if (this.key == null) {
                int width = this.maxCol - this.minCol;
                int height = this.maxRow - this.minRow;
                if (width >= height) {
                    for (int i = 0; i < 4; i++)
                        this.key = max(this.key, createKey1(i, width, height));
                }
                if (height >= width) {
                    for (int i = 0; i < 4; i++)
                        this.key = max(this.key, createKey2(i, width, height));
                }
            }
            return this.key;
        }

        String createKey2(int index, int width, int height) {
            StringBuilder sb = new StringBuilder();
            sb.append(height);
            sb.append(' ');
            sb.append(width);
            sb.append(' ');
            for (int col = 0; col <= width; col++) {
                for (int row = 0; row <= height; row++) {
                    int r = row;
                    int c = col;
                    switch (index) {
                        case 1:
                            r = height - row;
                            break;
                        case 2:
                            c = width - col;
                            break;
                        case 3:
                            r = height - row;
                            c = width - col;
                            break;
                    }
                    if (map[r + minRow][c + minCol] == id)
                        sb.append('1');
                    else
                        sb.append('0');
                }
            }
            return sb.toString();
        }

        String createKey1(int index, int width, int height) {
            StringBuilder sb = new StringBuilder();
            sb.append(width);
            sb.append(' ');
            sb.append(height);
            sb.append(' ');
            for (int row = 0; row <= height; row++) {
                for (int col = 0; col <= width; col++) {
                    int r = row;
                    int c = col;
                    switch (index) {
                        case 1:
                            r = height - row;
                            break;
                        case 2:
                            c = width - col;
                            break;
                        case 3:
                            r = height - row;
                            c = width - col;
                            break;
                    }
                    if (map[r + minRow][c + minCol] == id)
                        sb.append('1');
                    else
                        sb.append('0');
                }
            }
            return sb.toString();
        }
    }
}   