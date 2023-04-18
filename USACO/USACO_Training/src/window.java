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
public class window {
    int I;
    int top, bottom, left, right;

    private window(int I, int x, int y, int X, int Y) {
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

        ArrayList<window> windows = new ArrayList<>(); //bottom to top order (so index 0 is bottom, index x is x from bottom)
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
                window window = new window(I, x, y, X, Y);
                windows.add(window);
            } else if (line.charAt(0) == 't') {
                for (int i = 0; i < windows.size(); i++) {
                    if (windows.get(i).I == line.charAt(2)) {
                        window temp = windows.get(i);
                        windows.remove(i);
                        windows.add(temp);
                        break;
                    }
                }
            } else if (line.charAt(0) == 'b') {
                for (int i = 0; i < windows.size(); i++) {
                    if (windows.get(i).I == line.charAt(2)) {
                        window temp = windows.get(i);
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

    private static double helper(ArrayList<window> windows, int I) {
        int index = -1;
        for (int i = 0; i < windows.size(); i++) {
            window window = windows.get(i);
            if (window.I == I) {
                index = i;
//                total = Math.abs((window.right - window.left) * (window.top - window.bottom));
                break;
            }
        }

        ArrayList<window> onTop = new ArrayList<>(windows.subList(index + 1, windows.size()));
        onTop.sort((o1, o2) -> o2.top - o1.top);
        ArrayList<ArrayList<int[]>> skip = new ArrayList<>();
        skip.add(new ArrayList<>());
        int num = windows.get(index).top - windows.get(index).bottom + 1;
        ArrayList<int[]> layers = new ArrayList<>(); //start row, end row, layer #
        for (window window : onTop) {
            if (window.top > windows.get(index).bottom) {
                if (window.bottom < windows.get(index).top) {
                    if (window.right > windows.get(index).left) {
                        if (window.left < windows.get(index).right) {
                            int top = Math.min(window.top, windows.get(index).top) - windows.get(index).bottom;
                            int bottom = Math.max(window.bottom - windows.get(index).bottom, 0);
                            int right = Math.min(window.right, windows.get(index).right) - windows.get(index).left;
                            int left = Math.max(window.left - windows.get(index).left, 0);
                            if (layers.size() == 0) {
                                layers.add(new int[]{top, bottom, 1});
                                skip.add(new ArrayList<>());
                                skip.get(1).add(new int[]{left, right});
                            } else {
                                for (int i = 0; i < layers.size(); i++) {
                                    int curLayer = skip.size();
                                    if (layers.get(i)[1] >= top) {
                                        continue;
                                    }
                                    if (layers.get(i)[0] <= bottom) {
                                        break;
                                    }

                                    if (layers.get(i)[0] > top) {
                                        if (layers.get(i)[1] < bottom) {
                                            layers.add(new int[]{bottom, layers.get(i)[1], layers.get(i)[2]});
                                            layers.get(i)[1] = top;
                                            layers.add(new int[]{top, bottom, curLayer});
                                            skip.add(new ArrayList<>(skip.get(layers.get(i)[2])));
                                            skip.get(curLayer).add(new int[]{left, right});
                                            skip.get(curLayer).sort(Comparator.comparingInt(a -> a[0]));
                                            top = bottom;
                                            break;
                                        } else if (layers.get(i)[1] == bottom) {
                                            layers.get(i)[1] = top;
                                            layers.add(new int[]{top, bottom, curLayer});
                                            skip.add(new ArrayList<>(skip.get(layers.get(i)[2])));
                                            skip.get(curLayer).add(new int[]{left, right});
                                            skip.get(curLayer).sort(Comparator.comparingInt(a -> a[0]));
                                            top = bottom;
                                            break;
                                        } else {
                                            layers.add(new int[]{top, layers.get(i)[1], curLayer});
                                            skip.add(new ArrayList<>(skip.get(layers.get(i)[2])));
                                            skip.get(curLayer).add(new int[]{left, right});
                                            skip.get(curLayer).sort(Comparator.comparingInt(a -> a[0]));
                                            int temp = layers.get(i)[1];
                                            layers.get(i)[1] = top;
                                            top = temp;
                                        }
                                    } else {
                                        if (layers.get(i)[0] < top) {
                                            layers.add(new int[]{top, layers.get(i)[0], curLayer});
                                            skip.add(new ArrayList<>());
                                            skip.get(curLayer).add(new int[]{left, right});
                                            top = layers.get(i)[0];
                                            curLayer = skip.size();
                                        }

                                        if (layers.get(i)[1] < bottom) {
                                            layers.get(i)[0] = bottom;
                                            layers.add(new int[]{top, bottom, curLayer});
                                            skip.add(new ArrayList<>(skip.get(layers.get(i)[2])));
                                            skip.get(curLayer).add(new int[]{left, right});
                                            skip.get(curLayer).sort(Comparator.comparingInt(a -> a[0]));
                                            top = bottom;
                                            break;
                                        } else if (layers.get(i)[1] == bottom) {
                                            skip.add(new ArrayList<>(skip.get(layers.get(i)[2])));
                                            layers.get(i)[2] = curLayer;
                                            skip.get(curLayer).add(new int[]{left, right});
                                            skip.get(curLayer).sort(Comparator.comparingInt(a -> a[0]));
                                            top = bottom;
                                            break;
                                        } else {
                                            skip.add(new ArrayList<>(skip.get(layers.get(i)[2])));
                                            layers.get(i)[2] = curLayer;
                                            skip.get(curLayer).add(new int[]{left, right});
                                            skip.get(curLayer).sort(Comparator.comparingInt(a -> a[0]));
                                            top = layers.get(i)[1];
                                        }
                                    }
                                }
                                int curLayer = skip.size();
                                if (top > bottom) {
                                    layers.add(new int[]{top, bottom, curLayer});
                                    skip.add(new ArrayList<>());
                                    skip.get(curLayer).add(new int[]{left, right});
                                }
                                layers.sort((o1, o2) -> o2[0] - o1[0]);
                            }
                        }
                    }
                }
            }
        }

        int[] del = new int[skip.size()];
        long total = (windows.get(index).right - windows.get(index).left) * (--num);
        long visible = total;
        for (int[] block : layers) {
            if (del[block[2]] == 0) {
                int left = 0;
                for (int[] cur : skip.get(block[2])) {
                    left = Math.max(left, cur[0]);
                    int rem = cur[1] - left;
                    if (rem > 0) {
                        del[block[2]] += rem;
                        left = cur[1];
                    }
                }
            }
            visible -= (block[0] - block[1]) * del[block[2]];
        }

        return (double) visible / total;
    }
}