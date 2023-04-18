/*
ID: jerryya2
LANG: JAVA
TASK: msquare
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class msquare3 {
    private final int[] squares;
    public String str;

    public msquare3(int[] squares, String str) {
        this.squares = squares;
        this.str = str;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("msquare.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));

        int[] target = new int[8];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 8; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        if (Arrays.equals(target, new int[]{1, 2, 3, 4, 5, 6, 7, 8})) {
            pw.println(0);
            pw.println();
        }
        else {
            int[] temp = {1, 2, 3, 4, 5, 6, 7, 8};
            msquare3 og = new msquare3(temp, "");
            msquare3 og1 = new msquare3(target, "");
            ArrayList<msquare3> possible = new ArrayList<>();
            possible.add(og);
            boolean found = false;
            while (!found) {
                ArrayList<msquare3> altered = new ArrayList<>();
                for (msquare3 s : possible) {
                    //System.out.println(s.str);
                    altered.add(s.A());
                    altered.add(s.B());
                    altered.add(s.C());
                }

                altered.sort(new mCompare());
                for (msquare3 s : altered) {
                    if (s.same(og1)) {
                        pw.println(s.str.length());
                        pw.println(s.str);
                        found = true;
                        break;
                    }
                }

                possible = new ArrayList<>(altered);
            }
        }

        pw.close();
    }

    public msquare3 A() {
        int[] res = new int[8];
        for (int i = 0; i < 8; i++)
            res[i] = this.squares[7 - i];
        return new msquare3(res, str + 'A');
    }

    public msquare3 B() {
        int[] res = new int[8];
        System.arraycopy(this.squares, 0, res, 1, 3);
        res[0] = this.squares[3];
        System.arraycopy(this.squares, 5, res, 4, 3);
        res[7] = this.squares[4];
        return new msquare3(res, str + 'B');
    }

    public msquare3 C() {
        int[] res = new int[8];
        System.arraycopy(this.squares, 0, res, 0, 8);
        res[1] = this.squares[6];
        res[2] = this.squares[1];
        res[5] = this.squares[2];
        res[6] = this.squares[5];
        return new msquare3(res, str + 'C');
    }

    public boolean same(msquare3 other) {
        for (int i = 0; i < 8; i++)
            if (this.squares[i] != other.squares[i])
                return false;
        return true;
    }

    static class mCompare implements Comparator<msquare3>{
        public int compare(msquare3 m1, msquare3 m2){
            return m1.str.compareTo(m2.str);
        }
    }
}
