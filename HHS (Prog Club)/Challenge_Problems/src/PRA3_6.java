import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class PRA3_6 {
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
            if (code.length() < 1000) {
                int a1 = 0;
                int a2 = 0;
                int b1 = 0;
                int b2 = 0;
                int c1 = 0;
                int c2 = 0;
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
                        a2++;
                    }
                    else if (code.charAt(i) == '}') {
                        b2++;
                    }
                    else if (code.charAt(i) == ']') {
                        c2++;
                    }
                }
                ArrayList<Integer> skip = findSkip(code);
                for (int i = Math.abs(a2 - a1) + Math.abs(b2 - b1) + Math.abs(c2 - c1); i <= code.length(); i++) {
                    if (helper(code, 0, i, false, skip)) {
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

    private static boolean helper (String code, int start, int rem, boolean pos, ArrayList<Integer> skip) {
        if (rem == 0) {
            return balanced(code);
        }

        for (int i = start; i < code.length(); i++) {
            if (Collections.binarySearch(skip, i) >= 0) {
                continue;
            }
            String copy = code.substring(0, i) + code.substring(i + 1);
            pos = helper(copy, i, rem - 1, pos, skip);
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
