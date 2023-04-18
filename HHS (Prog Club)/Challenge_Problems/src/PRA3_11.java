import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class PRA3_11 {
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
            if (code.length() < 300) {
                ArrayList<Integer> skip = findSkip(code);
                for (int i = skip.size() - 1; i >= 0; i--) {
                    if (skip.get(i) == code.length()) {
                        code = code.substring(0, skip.get(i));
                    }
                    else {
                        code = code.substring(0, skip.get(i)) + code.substring(skip.get(i) + 1);
                    }
                }
                int extra = 0;
                int a1 = 0;
                int a2 = 0;
                int a3 = 0;
                int a4 = 0;
                int b1 = 0;
                int b2 = 0;
                int b3 = 0;
                int b4 = 0;
                int c1 = 0;
                int c2 = 0;
                int c3 = 0;
                int c4 = 0;
                ArrayList<Integer> del = new ArrayList<>();
                for (int i = 0; i < code.length(); i++) {
                    if (code.charAt(i) == '(') {
                        a1++;
                        a3++;
                    }
                    else if (code.charAt(i) == '{') {
                        b1++;
                        b3++;
                    }
                    else if (code.charAt(i) == '[') {
                        c1++;
                        c3++;
                    }
                    else if (code.charAt(i) == ')') {
                        a4++;
                        if (a1 > 0) {
                            a1--;
                        }
                        else {
                            a2++;
                            del.add(i);
                        }
                    }
                    else if (code.charAt(i) == '}') {
                        b4++;
                        if (b1 > 0) {
                            b1--;
                        }
                        else {
                            b2++;
                            del.add(i);
                        }
                    }
                    else if (code.charAt(i) == ']') {
                        c4++;
                        if (c1 > 0) {
                            c1--;
                        }
                        else {
                            c2++;
                            del.add(i);
                        }
                    }
                }
                for (int i = del.size() - 1; i >= 0; i--) {
                    if (del.get(i) == code.length()) {
                        code = code.substring(0, del.get(i));
                    }
                    else {
                        code = code.substring(0, del.get(i)) + code.substring(del.get(i) + 1);
                    }
                }
                extra += del.size();
                del = new ArrayList<>();
                a1 = 0;
                b1 = 0;
                c1 = 0;
                for (int i = 0; i < code.length(); i++) {
                    if (code.charAt(i) == '(') {
                        a1++;
                    }
                    else if (code.charAt(i) == '{') {
                        b1++;
                    }
                    else if (code.charAt(i) == '[') {
                        c1++;
                    }
                    else if (code.charAt(i) == ')') {
                        if (a1 > 0) {
                            a1--;
                        }
                        else {
                            del.add(i);
                        }
                    }
                    else if (code.charAt(i) == '}') {
                        if (b1 > 0) {
                            b1--;
                        }
                        else {
                            del.add(i);
                        }
                    }
                    else if (code.charAt(i) == ']') {
                        if (c1 > 0) {
                            c1--;
                        }
                        else {
                            del.add(i);
                        }
                    }
                }
                for (int i = del.size() - 1; i >= 0; i--) {
                    if (del.get(i) == code.length()) {
                        code = code.substring(0, del.get(i));
                    }
                    else {
                        code = code.substring(0, del.get(i)) + code.substring(del.get(i) + 1);
                    }
                }
                extra += del.size();
                for (int i = 0; i <= code.length(); i++) {
                    if (helper(code, 0, i, false, a3, a4, b3, b4, c3, c4)) {
                        pw.println(i + extra);
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

    private static boolean helper (String code, int start, int rem, boolean pos, int a1, int a2, int b1, int b2, int c1, int c2) {
        if (rem == 0) {
            if (a1 != a2 || b1 != b2 || c1 != c2) {
                return false;
            }
            return balanced(code);
        }

        for (int i = start; i < code.length(); i++) {
            String copy = code.substring(0, i) + code.substring(i + 1);
            if (code.charAt(i) == '(') {
                pos = helper(copy, i, rem - 1, pos, a1 - 1, a2, b1, b2, c1, c2);
            }
            else if (code.charAt(i) == '{') {
                pos = helper(copy, i, rem - 1, pos, a1, a2, b1 - 1, b2, c1, c2);
            }
            else if (code.charAt(i) == '[') {
                pos = helper(copy, i, rem - 1, pos, a1, a2, b1, b2, c1 - 1, c2);
            }
            else if (code.charAt(i) == ')') {
                pos = helper(copy, i, rem - 1, pos, a1, a2 - 1, b1, b2, c1, c2);
            }
            else if (code.charAt(i) == '}') {
                pos = helper(copy, i, rem - 1, pos, a1, a2, b1, b2 - 1, c1, c2);
            }
            else {
                pos = helper(copy, i, rem - 1, pos, a1, a2, b1, b2, c1, c2 - 1);
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
