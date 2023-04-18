import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class PRA3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String code = br.readLine();
        boolean good = balanced(code);
        if (good) {
            pw.println("YES");
        }
        else {
            pw.println("NO");
            if (code.length() <= 300) {
                StringBuilder s = new StringBuilder(code);
                ArrayList<Integer> skip = findSkip(code);
                for (int i = skip.size() - 1; i >= 0; i--) {
                    s.deleteCharAt(skip.get(i));
                }
                ArrayList<Integer> a1 = new ArrayList<>();
                ArrayList<Integer> a2 = new ArrayList<>();
                ArrayList<Integer> b1 = new ArrayList<>();
                ArrayList<Integer> b2 = new ArrayList<>();
                ArrayList<Integer> c1 = new ArrayList<>();
                ArrayList<Integer> c2 = new ArrayList<>();
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '(') {
                        a1.add(i);
                    }
                    else if (s.charAt(i) == '{') {
                        b1.add(i);
                    }
                    else if (s.charAt(i) == '[') {
                        c1.add(i);
                    }
                    else if (s.charAt(i) == ')') {
                        a2.add(i);
                    }
                    else if (s.charAt(i) == '}') {
                        b2.add(i);
                    }
                    else if (s.charAt(i) == ']') {
                        c2.add(i);
                    }
                }

                for (int i = Math.abs(a1.size() - a2.size()) + Math.abs(b1.size() - b2.size()) + Math.abs(c1.size() - c2.size()); i <= s.length(); i++) {
                    if (helper(s, i, false, a1.size(), a2.size(), b1.size(), b2.size(), c1.size(), c2.size())) {
                        pw.println(i);
                        break;
                    }
                }
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
            }
            else if (code.charAt(i) == '{') {
                s.push(new int[]{1, i});
            }
            else if (code.charAt(i) == '[') {
                s.push(new int[]{2, i});
            }
            else if (code.charAt(i) == ')') {
                if (s.size() != 0) {
                    int[] last = s.peek();
                    if (last[0] == 0) {
                        skip.add(i);
                        skip.add(last[1]);
                        s.pop();
                    }
                    else {
                        s.push(new int[]{3, i});
                    }
                }
            }
            else if (code.charAt(i) == '}') {
                if (s.size() != 0) {
                    int[] last = s.peek();
                    if (last[0] == 1) {
                        skip.add(i);
                        skip.add(i - 1);
                        s.pop();
                    }
                    else {
                        s.push(new int[]{4, i});
                    }
                }
            }
            else if (code.charAt(i) == ']') {
                if (s.size() != 0) {
                    int[] last = s.peek();
                    if (last[0] == 2) {
                        skip.add(i);
                        skip.add(i - 1);
                        s.pop();
                    }
                    else {
                        s.push(new int[]{6, i});
                    }
                }
            }
        }

        Collections.sort(skip);
        return skip;
    }

    private static boolean helper (StringBuilder code, int rem, boolean pos, int a1, int a2, int b1, int b2, int c1, int c2) {
        if (rem == 0) {
            if (a1 != a2 || b1 != b2 || c1 != c2) {
                return false;
            }
            return balanced(code.toString());
        }

        for (int i = 0; i < code.length(); i++) {
            StringBuilder copy = new StringBuilder(code);
            copy.deleteCharAt(i);
            if (code.charAt(i) == '(') {
                pos = helper(copy, rem - 1, pos, a1 - 1, a2, b1, b2, c1, c2);
            } else if (code.charAt(i) == '{') {
                pos = helper(copy, rem - 1, pos, a1, a2, b1 - 1, b2, c1, c2);
            } else if (code.charAt(i) == '[') {
                pos = helper(copy, rem - 1, pos, a1, a2, b1, b2, c1 - 1, c2);
            } else if (code.charAt(i) == ')') {
                pos = helper(copy, rem - 1, pos, a1, a2 - 1, b1, b2, c1, c2);
            } else if (code.charAt(i) == '}') {
                pos = helper(copy, rem - 1, pos, a1, a2, b1, b2 - 1, c1, c2);
            } else {
                pos = helper(copy, rem - 1, pos, a1, a2, b1, b2, c1, c2 - 1);
            }
            if (pos) {
                break;
            }
        }

        return pos;
    }

    private static boolean balanced(String code) {
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '(') {
                s.push(0);
            }
            else if (code.charAt(i) == '{') {
                s.push(1);
            }
            else if (code.charAt(i) == '[') {
                s.push(2);
            }
            else if (code.charAt(i) == ')') {
                if (s.size() == 0) {
                    return false;
                }
                int last = s.pop();
                if (last != 0) {
                    return false;
                }
            }
            else if (code.charAt(i) == '}') {
                if (s.size() == 0) {
                    return false;
                }
                int last = s.pop();
                if (last != 1) {
                    return false;
                }
            }
            else if (code.charAt(i) == ']') {
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
