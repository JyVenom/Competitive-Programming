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
public class window9 {
    int I;
    int top, bottom, left, right;

    private window9(int I, int x, int y, int X, int Y) {
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

        ArrayList<Integer> order = new ArrayList<>(); //bottom to top order (so index 0 is bottom, index x is x from bottom)
        window9[] windows = new window9[123];
        String line = br.readLine();
        while (line != null) {
            if (line.charAt(0) == 'w') {
                int I = line.charAt(2);
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
                windows[I] = new window9(I, x, y, X, Y);
                order.add(I);
            } else if (line.charAt(0) == 't') {
                int c = line.charAt(2);
                for (int i = 0; i < order.size(); i++) {
                    if (order.get(i) == c) {
                        order.add(order.get(i));
                        order.remove(i);
                        break;
                    }
                }
            } else if (line.charAt(0) == 'b') {
                int c = line.charAt(2);
                for (int i = 0; i < order.size(); i++) {
                    if (order.get(i) == c) {
                        order.add(0, order.get(i));
                        order.remove(i + 1);
                        break;
                    }
                }
            } else if (line.charAt(0) == 'd') {
                int c = line.charAt(2);
                for (int i = 0; i < order.size(); i++) {
                    if (order.get(i) == c) {
                        order.remove(i);
                        break;
                    }
                }
            } else if (line.charAt(0) == 's') {
                DecimalFormat df = new DecimalFormat("0.000");
                pw.println(df.format(helper(order, windows, line.charAt(2)) * 100));
            }

            line = br.readLine();
        }

        pw.close();
    }

    private static double helper(ArrayList<Integer> order, window9[] windows, int I) {
        int index = -1;
        for (int i = 0; i < order.size(); i++) {
            if (order.get(i) == I) {
                index = i;
                break;
            }
        }

        ArrayList<window9> onTop = new ArrayList<>();
        for (int i = index + 1; i < order.size(); i++) {
            onTop.add(windows[order.get(i)]);
        }
        window9 curWindow = windows[order.get(index)];
        onTop.sort((o1, o2) -> o2.top - o1.top);
        ArrayList<ArrayList<int[]>> skip = new ArrayList<>();
        skip.add(new ArrayList<>());
        int num = curWindow.top - curWindow.bottom + 1;
        int[] layer = new int[num];
        for (window9 window : onTop) {
            if (window.top > curWindow.bottom) {
                if (window.bottom < curWindow.top) {
                    if (window.right > curWindow.left) {
                        if (window.left < curWindow.right) {
                            int top = Math.min(window.top, curWindow.top) - curWindow.bottom;
                            int bottom = Math.max(window.bottom - curWindow.bottom, 0);
                            int right = Math.min(window.right, curWindow.right) - curWindow.left;
                            int left = Math.max(window.left - curWindow.left, 0);
                            int curLayer = skip.size();
                            skip.add(new ArrayList<>(skip.get(layer[top])));
                            skip.get(curLayer).add(new int[]{left, right});
                            skip.get(curLayer).sort(Comparator.comparingInt(a -> a[0]));
                            int prev = layer[top];
                            for (int i = top; i > bottom; i--) {
                                if (layer[i] != prev) {
                                    prev = layer[top];
                                    curLayer = skip.size();
                                    skip.add(new ArrayList<>(skip.get(layer[i])));
                                    skip.get(curLayer).add(new int[]{left, right});
                                    skip.get(curLayer).sort(Comparator.comparingInt(a -> a[0]));
                                }
                                layer[i] = curLayer;
                            }
                        }
                    }
                }
            }
        }

        int[] del = new int[skip.size()];
        long total = ( curWindow.right - curWindow.left) * (--num);
        long visible = total;
        for (int i = num; i >= 0; i--) {
            if (layer[i] != 0 && del[layer[i]] == 0) {
                int left = 0;
                for (int[] cur : skip.get(layer[i])) {
                    left = Math.max(left, cur[0]);
                    int rem = cur[1] - left;
                    if (rem > 0) {
                        del[layer[i]] += rem;
                    }
                    left = Math.max(left, cur[1]);
                }
            }
            visible -= del[layer[i]];
        }

        return (double) visible / total;
    }
}