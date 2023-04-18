/*
ID: jerryya2
LANG: JAVA
TASK: pprime
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class pprime {
    private static boolean first;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("pprime.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        ArrayList<Integer> pos = new ArrayList<>();
        init(a, b, pos);

        for (int i = 0; i < pos.size(); i++) {
            boolean isPrime = true;
            int num = pos.get(i);
            if (num > b) {
                pos.remove(i);
                i--;
            }
            else {
                if (num % 2 == 0) {
                    pos.remove(i);
                    i--;
                }
                else {
                    int root = (int) Math.sqrt(num);
                    for (int j = 3; j <= root; j += 2) {
                        if (num % j == 0) {
                            isPrime = false;
                            break;
                        }
                    }
                    if (!isPrime) {
                        pos.remove(i);
                        i--;
                    }
                }
            }
        }

        for (Integer po : pos) {
            pw.println(po);
        }
        pw.close();
    }

    private static void init(int a, int b, ArrayList<Integer> pos) {
        StringBuilder A = new StringBuilder(Integer.toString(a));

        for (int i = 0; i <= A.length() / 2; i++) {
            int s = Character.getNumericValue(A.charAt(i));
            int e = Character.getNumericValue(A.charAt(A.length() - 1 - i));
            if (e > s) {
                A = new StringBuilder(Integer.toString(Integer.parseInt(A.toString()) + (int) Math.pow(10, i + 1)));
                s = Character.getNumericValue(A.charAt(i));
            }
            A.setCharAt(A.length() - 1 - i, (char)(s + '0'));
        }

        first = true;
        generatePalindromes(Integer.parseInt(A.toString()), b, 0, pos);

        StringBuilder cur = new StringBuilder("11");
        if (A.length() > 1){
            for (int i = 0; i < A.length() - 1; i++) {
                cur.insert(1, "0");
            }
        }
        for (int i = cur.length(); Math.pow(10, i - 1) < b; i++){
            first = true;
            generatePalindromes(Integer.parseInt(cur.toString()), b, 0, pos);
            cur.insert(1, "0");
        }
    }

    private static void generatePalindromes(int cur, int b, int place, ArrayList<Integer> pos) {
        StringBuilder Cur = new StringBuilder(Integer.toString(cur));
        int i;
        if (first) {
            i = Character.getNumericValue(Cur.charAt(place));
        }
        else {
            i = 0;
        }
        for (; i < 10; i++) {
            Cur.setCharAt(place, (char) (i + '0'));
            Cur.setCharAt(Cur.length() - 1 - place, (char) (i + '0'));
            int val = Integer.parseInt(Cur.toString());
            if (val > b) {
                return;
            }
            if (place < (Cur.length() - 1) / 2) {
                generatePalindromes(val, b, place + 1, pos);
            } else {
                pos.add(val);
                first = false;
            }
            for (int j = place + 1; j < Cur.length() - 1 - place; j++) {
                Cur.setCharAt(j, '0');
            }
        }
    }
}
