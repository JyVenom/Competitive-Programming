import java.io.*;
import java.util.Stack;

public class PRA3_17 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String code = br.readLine();
        boolean good = balanced(code);
        if (good) {
            pw.println("YES");
        } else {
            pw.println("NO");
            if (code.length() <= 300) {
                int[][] data = new int[code.length()][code.length()];
                for (int i = code.length() - 2; i >= 0; i--) {
                    for (int j = i + 1; j < code.length(); j++) {
                        int max = data[i + 1][j - 1];
                        if ((code.charAt(i) == '(' && code.charAt(j) == ')') || (code.charAt(i) == '{' && code.charAt(j) == '}') || (code.charAt(i) == '[' && code.charAt(j) == ']')) {
                            max++;
                        }
                        for (int k = i + 1; k < j; k++) {
                            max = Math.max(max, data[i][k] + data[k + 1][j]);
                        }
                        data[i][j] = max;
                    }
                }
                pw.println(code.length() - 2 * data[0][code.length() - 1]);
            }
        }
        pw.close();
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
