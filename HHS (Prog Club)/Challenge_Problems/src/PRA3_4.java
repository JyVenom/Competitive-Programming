import java.io.*;
import java.util.Stack;

public class PRA3_4 {
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
            if (code.length() <= 1000) {
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
                for (int i = Math.abs(a2 - a1) + Math.abs(b2 - b1) + Math.abs(c2 - c1); i <= code.length(); i++) {
                    if (helper(code, 0, i, false)) {
                        pw.println(i);
                        break;
                    }
                }
            }
        }
        pw.close();
    }

    private static boolean helper (String code, int start, int rem, boolean pos) {
        if (rem == 0) {
            return balanced(code);
        }

        for (int i = start; i < code.length(); i++) {
            String copy = code.substring(0, i) + code.substring(i + 1);
            pos = helper(copy, i, rem - 1, pos);
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
