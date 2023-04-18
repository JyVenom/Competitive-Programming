import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class PRA3_18 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String code = br.readLine();
        br.close();
        boolean good = balanced(code);
        if (good) {
            pw.println("YES");
        } else {
            pw.println("NO");
            if (code.length() <= 300) {
                StringBuilder s = new StringBuilder(code);
                s.trimToSize();
                ArrayList<Integer> skip = findSkip(code);
                for (int i = skip.size() - 1; i >= 0; i--) {
                    s.deleteCharAt(skip.get(i));
                }
                s.trimToSize();
                int[][] data = new int[s.length()][s.length()];
                for (int i = s.length() - 2; i >= 0; i--) {
                    for (int j = i + 1; j < s.length(); j++) {
                        int max = data[i + 1][j - 1];
                        if ((s.charAt(i) == '(' && s.charAt(j) == ')') || (s.charAt(i) == '{' && s.charAt(j) == '}') || (s.charAt(i) == '[' && s.charAt(j) == ']')) {
                            max++;
                        }
                        for (int k = i; k < j; k++) {
                            max = Math.max(max, data[i][k] + data[k + 1][j]);
                        }
                        data[i][j] = max;
                    }
                }
                pw.println(s.length() - (2 * data[0][s.length() - 1]));
            }
        }
        pw.close();
    }

    private static ArrayList<Integer> findSkip(String code) {
        ArrayList<Integer> skip = new ArrayList<>();
        Stack<int[]> s = new Stack<>();
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '(') {
                s.push(new int[]{0, i});
            } else if (code.charAt(i) == '{') {
                s.push(new int[]{1, i});
            } else if (code.charAt(i) == '[') {
                s.push(new int[]{2, i});
            } else if (code.charAt(i) == ')') {
                if (s.size() != 0) {
                    int[] last = s.peek();
                    if (last[0] == 0) {
                        skip.add(i);
                        skip.add(last[1]);
                        s.pop();
                    } else {
                        s.push(new int[]{3, i});
                    }
                }
            } else if (code.charAt(i) == '}') {
                if (s.size() != 0) {
                    int[] last = s.peek();
                    if (last[0] == 1) {
                        skip.add(i);
                        skip.add(last[1]);
                        s.pop();
                    } else {
                        s.push(new int[]{4, i});
                    }
                }
            } else if (code.charAt(i) == ']') {
                if (s.size() != 0) {
                    int[] last = s.peek();
                    if (last[0] == 2) {
                        skip.add(i);
                        skip.add(last[1]);
                        s.pop();
                    } else {
                        s.push(new int[]{6, i});
                    }
                }
            }
        }

        Collections.sort(skip);
        return skip;
    }

    private static boolean balanced(String code) {
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '(') {
                s.push(0);
            } else if (code.charAt(i) == '{') {
                s.push(1);
            } else if (code.charAt(i) == '[') {
                s.push(2);
            } else if (code.charAt(i) == ')') {
                if (s.size() == 0) {
                    return false;
                }
                int last = s.pop();
                if (last != 0) {
                    return false;
                }
            } else if (code.charAt(i) == '}') {
                if (s.size() == 0) {
                    return false;
                }
                int last = s.pop();
                if (last != 1) {
                    return false;
                }
            } else if (code.charAt(i) == ']') {
                if (s.size() == 0) {
                    return false;
                }
                int last = s.pop();
                if (last != 2) {
                    return false;
                }
            }
        }

        return s.size() == 0;
    }
}
