import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class PRA3_16 {
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
//                int sum = 0;
//                int start = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '(') {
                        a1++;
                        a3++;
                    }
                    else if (s.charAt(i) == '{') {
                        b1++;
                        b3++;
                    }
                    else if (s.charAt(i) == '[') {
                        c1++;
                        c3++;
                    }
                    else if (s.charAt(i) == ')') {
                        a4++;
                        if (a1 > 0) {
                            a1--;
//                            if (a1 == 0) {
//                                int I = i + 1;
//                                StringBuilder sub = new StringBuilder(s.substring(start, I));
//                                for (int j = a1 + a2 + b1 + b2 + c1 + c2; j <= sub.length(); j += 2) {
//                                    if (helper(sub, 0, j, false, a1, a2, b1, b2, c1, c2, a3, a4, b3, b4, c3, c4)) {
//                                        sum += j;
//                                        start = I;
//                                        b1 = 0;
//                                        c1 = 0;
//                                        a2 = 0;
//                                        b2 = 0;
//                                        c2 = 0;
//                                        break;
//                                    }
//                                }
//                            }
                        }
                        else {
                            a2++;
                        }
                    }
                    else if (s.charAt(i) == '}') {
                        b4++;
                        if (b1 > 0) {
                            b1--;
//                            if (b1 == 0) {
//                                int I = i + 1;
//                                StringBuilder sub = new StringBuilder(s.substring(start, I));
//                                for (int j = a1 + a2 + b1 + b2 + c1 + c2; j <= sub.length(); j += 2) {
//                                    if (helper(sub, 0, j, false, a1, a2, b1, b2, c1, c2, a3, a4, b3, b4, c3, c4)) {
//                                        sum += j;
//                                        start = I;
//                                        a1 = 0;
//                                        c1 = 0;
//                                        a2 = 0;
//                                        b2 = 0;
//                                        c2 = 0;
//                                        break;
//                                    }
//                                }
//                            }
                        }
                        else {
                            b2++;
                        }
                    }
                    else if (s.charAt(i) == ']') {
                        c4++;
                        if (c1 > 0) {
                            c1--;
//                            if (c1 == 0) {
//                                int I = i + 1;
//                                StringBuilder sub = new StringBuilder(s.substring(start, I));
//                                for (int j = a1 + a2 + b1 + b2 + c1 + c2; j <= sub.length(); j += 2) {
//                                    if (helper(sub, 0, j, false, a1, a2, b1, b2, c1, c2, a3, a4, b3, b4, c3, c4)) {
//                                        sum += j;
//                                        start = I;
//                                        a1 = 0;
//                                        b1 = 0;
//                                        a2 = 0;
//                                        b2 = 0;
//                                        c2 = 0;
//                                        break;
//                                    }
//                                }
//                            }
                        }
                        else {
                            c2++;
                        }
                    }
                }
                if (code.length() <= 20 || (b3 == 0 && b4 == 0 && c3 == 0 && c4 == 0)) {
                    for (int i = a1 + a2 + b1 + b2 + c1 + c2; i <= s.length(); i += 2) {
                        if (helper(s, 0, i, false, a1, a2, b1, b2, c1, c2, a3, a4, b3, b4, c3, c4)) {
                            pw.println(i);
                            break;
                        }
                    }
                }
                else {
                    int temp;
                    if ((a1 + a2 + b1 + b2 + c1 + c2) % 2 == 0) {
                        if (s.length() % 2 == 0) {
                            temp = s.length();
                        }
                        else {
                            temp = s.length() - 1;
                        }
                    }
                    else {
                        if (s.length() % 2 == 0) {
                            temp = s.length() - 1;
                        }
                        else {
                            temp = s.length();
                        }

                    }
                    for (int i = temp; i >= 0; i--) {
                        if (!helper(s, 0, i, false, a1, a2, b1, b2, c1, c2, a3, a4, b3, b4, c3, c4)) {
                            pw.println(i + 1);
                            break;
                        }
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
                        skip.add(last[1]);
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
                        skip.add(last[1]);
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

    private static boolean helper (StringBuilder code, int start, int rem, boolean pos, int a1, int a2, int b1, int b2, int c1, int c2, int a3, int a4, int b3, int b4, int c3, int c4) {
        if (rem == 0) {
            if (a1 > 0 || a2 > 0 || b1 > 0 || b2 > 0 || c1 > 0 || c2 > 0 || a3 != a4 || b3 != b4 || c3 != c4) {
                return false;
            }
            return balanced(code.toString());
        }

        for (int i = start; i < code.length(); i++) {
            StringBuilder copy = new StringBuilder(code);
            copy.deleteCharAt(i);
            if (code.charAt(i) == '(') {
                pos = helper(copy, i, rem - 1, pos, a1 - 1, a2, b1, b2, c1, c2, a3 - 1, a4, b3, b4, c3, c4);
            }
            else if (code.charAt(i) == '{') {
                pos = helper(copy, i, rem - 1, pos, a1, a2, b1 - 1, b2, c1, c2, a3, a4, b3 - 1, b4, c3, c4);
            }
            else if (code.charAt(i) == '[') {
                pos = helper(copy, i, rem - 1, pos, a1, a2, b1, b2, c1 - 1, c2, a3, a4, b3, b4, c3 - 1, c4);
            }
            else if (code.charAt(i) == ')') {
                pos = helper(copy, i, rem - 1, pos, a1, a2 - 1, b1, b2, c1, c2, a3, a4 - 1, b3, b4, c3, c4);
            }
            else if (code.charAt(i) == '}') {
                pos = helper(copy, i, rem - 1, pos, a1, a2, b1, b2 - 1, c1, c2, a3, a4, b3, b4 - 1, c3, c4);
            }
            else {
                pos = helper(copy, i, rem - 1, pos, a1, a2, b1, b2, c1, c2 - 1, a3, a4, b3, b4, c3, c4 - 1);
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
