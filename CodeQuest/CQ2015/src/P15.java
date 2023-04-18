import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashMap;

public class P15 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        HashMap<String, Integer> precedence = new HashMap<>(6);
        precedence.put("^", 4);
        precedence.put("*", 3);
        precedence.put("/", 3);
        precedence.put("+", 2);
        precedence.put("-", 2);
        precedence.put("(", 1);
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                ArrayDeque<helper> infix = new ArrayDeque<>();
                {
                    String[] tmp = br.readLine().split(" ");
                    for (String val : tmp) {
                        if (val.equals("^") || val.equals("*") || val.equals("/") || val.equals("+") || val.equals("-") || val.equals("(") || val.equals(")")) {
                            infix.add(new helper(false, val));
                        } else {
                            infix.add(new helper(true, val));
                        }
                    }
                }
                ArrayDeque<helper> stack = new ArrayDeque<>(infix.size());
                ArrayDeque<helper> ans = new ArrayDeque<>(infix.size());

                for (helper val : infix) {
                    if (val.isNumber) {
                        ans.add(val);
                    } else if (val.val.equals("(")) {
                        stack.add(val);
                    } else if (val.val.equals(")")) {
                        helper tmp;
                        while (!(tmp = stack.removeLast()).val.equals("(")) {
                            ans.add(tmp);
                        }
                    } else {
                        while (!stack.isEmpty()
                                && (precedence.get(stack.getLast().val) > precedence.get(val.val)
                                || (precedence.get(stack.getLast().val).equals(precedence.get(val.val)) && !val.val.equals("^")))) {
                            ans.add(stack.removeLast());
                        }
                        stack.add(val);
                    }
                }
                while (!stack.isEmpty()) {
                    ans.add(stack.removeLast());
                }

                for (helper val : ans) {
                    pw.print(val.val + " ");
                }
                pw.println();
            }
        }

        pw.close();
    }

    private static class helper {
        boolean isNumber;
        String val;

        public helper(boolean isNumber, String val) {
            this.isNumber = isNumber;
            this.val = val;
        }
    }
}
