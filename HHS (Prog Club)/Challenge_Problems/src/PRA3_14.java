import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class PRA3_14 {
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
                long start = System.currentTimeMillis();
                StringBuilder s = new StringBuilder(code);
                ArrayList<Integer> skip = findSkip(code);
                for (int i = skip.size() - 1; i >= 0; i--) {
                    s.deleteCharAt(skip.get(i));
                }
                System.out.println(System.currentTimeMillis() - start);
                start = System.currentTimeMillis();
                ArrayList<Integer> a5 = new ArrayList<>();
                ArrayList<Integer> a6 = new ArrayList<>();
                ArrayList<Integer> b5 = new ArrayList<>();
                ArrayList<Integer> b6 = new ArrayList<>();
                ArrayList<Integer> c5 = new ArrayList<>();
                ArrayList<Integer> c6 = new ArrayList<>();
                int a1 = 0;
                int a2 = 0;
                int b1 = 0;
                int b2 = 0;
                int c1 = 0;
                int c2 = 0;
//                for (int i = 0; i < s.length(); i++) {
//                    if (s.charAt(i) == '(') {
//                        a1++;
//                    }
//                    else if (s.charAt(i) == '{') {
//                        b1++;
//                    }
//                    else if (s.charAt(i) == '[') {
//                        c1++;
//                    }
//                    else if (s.charAt(i) == ')') {
//                        if (a1 > 0) {
//                            a1--;
//                        }
//                        else {
//                            s.deleteCharAt(i);
//                            i--;
//                        }
//                    }
//                    else if (s.charAt(i) == '}') {
//                        if (b1 > 0) {
//                            b1--;
//                        }
//                        else {
//                            s.deleteCharAt(i);
//                            i--;
//                        }
//                    }
//                    else if (s.charAt(i) == ']') {
//                        if (c1 > 0) {
//                            c1--;
//                        }
//                        else {
//                            s.deleteCharAt(i);
//                            i--;
//                        }
//                    }
//                }
//                a1 = 0;
//                b1 = 0;
//                c1 = 0;
//                for (int i = s.length() - 1; i >= 0; i--) {
//                    if (s.charAt(i) == ')') {
//                        a1++;
//                    }
//                    else if (s.charAt(i) == '}') {
//                        b1++;
//                    }
//                    else if (s.charAt(i) == ']') {
//                        c1++;
//                    }
//                    else if (s.charAt(i) == '(') {
//                        if (a1 > 0) {
//                            a1--;
//                        }
//                        else {
//                            s.deleteCharAt(i);
//                        }
//                    }
//                    else if (s.charAt(i) == '{') {
//                        if (b1 > 0) {
//                            b1--;
//                        }
//                        else {
//                            s.deleteCharAt(i);
//                        }
//                    }
//                    else if (s.charAt(i) == '[') {
//                        if (c1 > 0) {
//                            c1--;
//                        }
//                        else {
//                            s.deleteCharAt(i);
//                        }
//                    }
//                }
//                a1 = 0;
//                b1 = 0;
//                c1 = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '(') {
                        a1++;
                        a5.add(i);
                    }
                    else if (s.charAt(i) == '{') {
                        b1++;
                        b5.add(i);
                    }
                    else if (s.charAt(i) == '[') {
                        c1++;
                        c5.add(i);
                    }
                    else if (s.charAt(i) == ')') {
                        a6.add(i);
                        if (a1 > 0) {
                            a1--;
                        }
                        else {
                            a2++;
                        }
                    }
                    else if (s.charAt(i) == '}') {
                        b6.add(i);
                        if (b1 > 0) {
                            b1--;
                        }
                        else {
                            b2++;
                        }
                    }
                    else if (s.charAt(i) == ']') {
                        c6.add(i);
                        if (c1 > 0) {
                            c1--;
                        }
                        else {
                            c2++;
                        }
                    }
                }
                System.out.println(System.currentTimeMillis() - start);
                int max = s.length() / 2;
                for (int i = 0; i <= max; i++) {
                    if (delA(i, a5, a6, b5, b6, c5, c6, a1, a2, b1, b2, c1, c2, s)) {
                        pw.println(i + a1 + a2 + b1 + b2 + c1 + c2);
                        break;
                    }
//                    if (helper(s, i, false, a3, a4, b3, b4, c3, c4)) {
//                        pw.println(i);
//                        break;
//                    }
                }
            }
        }
        pw.close();
    }

    private static boolean delA(int rem, ArrayList<Integer> a5, ArrayList<Integer> a6, ArrayList<Integer> b5, ArrayList<Integer> b6, ArrayList<Integer> c5, ArrayList<Integer> c6, int a1, int a2, int b1, int b2, int c1, int c2, StringBuilder s) {
        for (int i = 0; i <= rem; i++) {
            if (i + a1 > a5.size() || i + a2 > a6.size()) {
                break;
            }
            if (delB(rem - i, i, a5, a6, b5, b6, c5, c6, a1, a2, b1, b2, c1, c2, s)) {
                return true;
            }
        }
        return false;
    }

    private static boolean delB(int rem, int a, ArrayList<Integer> a5, ArrayList<Integer> a6, ArrayList<Integer> b5, ArrayList<Integer> b6, ArrayList<Integer> c5, ArrayList<Integer> c6, int a1, int a2, int b1, int b2, int c1, int c2, StringBuilder s) {
        for (int i = 0; i <= rem; i++) {
            if (i + b1 > b5.size() || i + b2 > b6.size()) {
                break;
            }
            if (delC(rem - i, a, i, a5, a6, b5, b6, c5, c6, a1, a2, b1, b2, c1, c2, s)) {
                return true;
            }
        }
        return false;
    }

    private static boolean delC(int rem, int a, int b, ArrayList<Integer> a5, ArrayList<Integer> a6, ArrayList<Integer> b5, ArrayList<Integer> b6, ArrayList<Integer> c5, ArrayList<Integer> c6, int a1, int a2, int b1, int b2, int c1, int c2, StringBuilder s) {
        if (rem + a1 > a5.size() || rem + a2 > a6.size()) {
            return false;
        }
        ArrayList<Integer> del = new ArrayList<>();
        return helperA1(a, b, rem, a5, a6, b5, b6, c5, c6, a2, b1, b2, c1, c2, 0, del, a + a1, s);
    }

    private static boolean helperA1(int a, int b, int c, ArrayList<Integer> a5, ArrayList<Integer> a6, ArrayList<Integer> b5, ArrayList<Integer> b6, ArrayList<Integer> c5, ArrayList<Integer> c6, int a2, int b1, int b2, int c1, int c2, int start, ArrayList<Integer> del, int rem, StringBuilder s) {
        if (rem == 0) {
            return helperA2(b, c, a6, b5, b6, c5, c6, b1, b2, c1, c2, 0, del, a + a2, s);
        }

        for (int i = start; i < a5.size(); i++) {
            del.add(a5.get(i));
            if (helperA1(a, b, c, a5, a6, b5, b6, c5, c6, a2, b1, b2, c1, c2, i + 1, del, rem - 1, s)) {
                return true;
            }
            del.remove(del.size() - 1);
        }

        return false;
    }

    private static boolean helperA2(int b, int c, ArrayList<Integer> a6, ArrayList<Integer> b5, ArrayList<Integer> b6, ArrayList<Integer> c5, ArrayList<Integer> c6, int b1, int b2, int c1, int c2, int start, ArrayList<Integer> del, int rem, StringBuilder s) {
        if (rem == 0) {
            return helperB1(b, c, b5, b6, c5, c6, b2, c1, c2, 0, del, b + b1, s);
        }

        for (int i = start; i < a6.size(); i++) {
            del.add(a6.get(i));
            if (helperA2(b, c, a6, b5, b6, c5, c6, b1, b2, c1, c2, i + 1, del, rem - 1, s)) {
                return true;
            }
            del.remove(del.size() - 1);
        }

        return false;
    }

    private static boolean helperB1(int b, int c, ArrayList<Integer> b5, ArrayList<Integer> b6, ArrayList<Integer> c5, ArrayList<Integer> c6, int b2, int c1, int c2, int start, ArrayList<Integer> del, int rem, StringBuilder s) {
        if (rem == 0) {
            return helperB2(c, b6, c5, c6, c1, c2, 0, del, b + b2, s);
        }

        for (int i = start; i < b5.size(); i++) {
            del.add(b5.get(i));
            if (helperB1(b, c, b5, b6, c5, c6, b2, c1, c2, i + 1, del, rem - 1, s)) {
                return true;
            }
            del.remove(del.size() - 1);
        }

        return false;
    }

    private static boolean helperB2(int c, ArrayList<Integer> b6, ArrayList<Integer> c5, ArrayList<Integer> c6, int c1, int c2, int start, ArrayList<Integer> del, int rem, StringBuilder s) {
        if (rem == 0) {
            return helperC1(c, c5, c6, c2, 0, del, c + c1, s);
        }

        for (int i = start; i < b6.size(); i++) {
            del.add(b6.get(i));
            if (helperB2(c, b6, c5, c6, c1, c2, i + 1, del, rem - 1, s)) {
                return true;
            }
            del.remove(del.size() - 1);
        }

        return false;
    }

    private static boolean helperC1(int c, ArrayList<Integer> c5, ArrayList<Integer> c6, int c2, int start, ArrayList<Integer> del, int rem, StringBuilder s) {
        if (rem == 0) {
            return helperC2(c6, 0, del, c + c2, s);
        }

        for (int i = start; i < c5.size(); i++) {
            del.add(c5.get(i));
            if (helperC1(c, c5, c6, c2, i + 1, del, rem - 1, s)) {
                return true;
            }
            del.remove(del.size() - 1);
        }

        return false;
    }

    private static boolean helperC2(ArrayList<Integer> c6, int start, ArrayList<Integer> del, int rem, StringBuilder s) {
        if (rem == 0) {
            Collections.sort(del);
            StringBuilder copy = new StringBuilder(s);
            for (int i = del.size() - 1; i >= 0; i--) {
                if (del.get(i) == copy.length()) {
                    System.out.println();
                }
                copy.deleteCharAt(del.get(i));
            }
            return balanced(copy.toString());
        }

        for (int i = start; i < c6.size(); i++) {
            del.add(c6.get(i));
            if (helperC2(c6, i + 1, del, rem - 1, s)) {
                return true;
            }
            del.remove(del.size() - 1);
        }

        return false;
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
