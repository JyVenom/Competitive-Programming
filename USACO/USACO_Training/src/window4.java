/*
ID: jerryya2
LANG: JAVA
TASK: window
*/

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This is my solution to USACO Training problem "Window Area" of section 5.3
 * using 2D RLE (Run Length Encoding but both horizontally and vertically)
 *
 * @author jerry
 * @version 1.0
 * @since 2020-10-04
 */
public class window4 {
    int I;
    int top, bottom, left, right;

    private window4(int I, int x, int y, int X, int Y) {
        this.I = I;
        if (x < X) {
            left = x;
            right = X;
        } else {
            left = X;
            right = x;
        }

        if (y < Y) {
            top = Y;
            bottom = y;
        } else {
            top = y;
            bottom = Y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("window.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("window.out")));

        ArrayList<window4> windows = new ArrayList<>(); //bottom to top order (so index 0 is bottom, index x is x from bottom)
        String line = br.readLine();
        while (line != null) {
            if (line.charAt(0) == 'w') {
                char I = line.charAt(2);
                int start = 4;
                int end = line.indexOf(',', start);
                int x = Integer.parseInt(line.substring(start, end));
                start = end + 1;
                end = line.indexOf(',', start);
                int y = Integer.parseInt(line.substring(start, end));
                start = end + 1;
                end = line.indexOf(',', start);
                int X = Integer.parseInt(line.substring(start, end));
                start = end + 1;
                end = line.indexOf(')', start);
                int Y = Integer.parseInt(line.substring(start, end));
                window4 window = new window4(I, x, y, X, Y);
                windows.add(window);
            } else if (line.charAt(0) == 't') {
                for (int i = 0; i < windows.size(); i++) {
                    if (windows.get(i).I == line.charAt(2)) {
                        window4 temp = windows.get(i);
                        windows.remove(i);
                        windows.add(temp);
                        break;
                    }
                }
            } else if (line.charAt(0) == 'b') {
                for (int i = 0; i < windows.size(); i++) {
                    if (windows.get(i).I == line.charAt(2)) {
                        window4 temp = windows.get(i);
                        windows.remove(i);
                        windows.add(0, temp);
                        break;
                    }
                }
            } else if (line.charAt(0) == 'd') {
                for (int i = 0; i < windows.size(); i++) {
                    if (windows.get(i).I == line.charAt(2)) {
                        windows.remove(i);
                        break;
                    }
                }
            } else if (line.charAt(0) == 's') {
                DecimalFormat df = new DecimalFormat("0.000");
                pw.println(df.format(helper(windows, line.charAt(2)) * 100));
            }

            line = br.readLine();
        }

        pw.close();
    }

    private static double helper(ArrayList<window4> windows, int I) {
        int index = -1;
        for (int i = 0; i < windows.size(); i++) {
            window4 window = windows.get(i);
            if (window.I == I) {
                index = i;
//                total = Math.abs((window.right - window.left) * (window.top - window.bottom));
                break;
            }
        }

        ArrayList<window4> onTop = new ArrayList<>();
        for (int i = index + 1; i < windows.size(); i++) {
            onTop.add(windows.get(i));
        }
//        onTop.add(new window('Z', 9, 80, 120, 10));
        onTop.sort((o1, o2) -> o2.top - o1.top);
//        onTop.sort(Comparator.comparingInt(o -> o.top));
        ArrayList<ArrayList<int[]>> skip = new ArrayList<>();
        int num = windows.get(index).top - windows.get(index).bottom + 1;
        for (int i = 0; i < num; i++) {
            skip.add(new ArrayList<>());
        }
        for (window4 window : onTop) {
            if (window.top > windows.get(index).bottom) {
                if (window.bottom < windows.get(index).top) {
                    if (window.right > windows.get(index).left) {
                        if (window.left < windows.get(index).right) {
                            int top = Math.min(window.top, windows.get(index).top) - windows.get(index).bottom;
                            int bottom = Math.max(window.bottom - windows.get(index).bottom, 0);
                            int right = Math.min(window.right, windows.get(index).right) - windows.get(index).left;
                            int left = Math.max(window.left - windows.get(index).left, 0);
                            skip.get(top).add(new int[]{left, right});
                            skip.get(top).sort(Comparator.comparingInt(a -> a[0]));
                            for (int i = top; i > bottom; i--) {
                                skip.get(i).add(new int[]{left, right});
                                skip.get(i).sort(Comparator.comparingInt(a -> a[0]));
                            }
                        }
                    }
                }
            }
        }

        int[] del = new int[num];
        int width = windows.get(index).right - windows.get(index).left;
        long total = width * (--num);
        long visible = total;
        for (int i = num; i >= 0; i--) {
            int left = 0;
            for (int[] cur : skip.get(i)) {
                left = Math.max(left, cur[0]);
                int rem = cur[1] - left;
                if (rem > 0) {
                    del[i] += rem;
                }
                left = Math.max(left, cur[1]);
            }
            visible -= del[i];
        }

        return (double) visible / total;
    }
}