/*
ID: jerryya2
LANG: JAVA
PROG: namenum
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class namenum2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("namenum.in"));
        BufferedReader dict = new BufferedReader(new FileReader("dict.txt"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));

        ArrayList<String> d = new ArrayList<>();
        String line;
        while ((line = dict.readLine()) != null){
            d.add(line);
        }
        String num = br.readLine();
        ArrayList<String> pos = getComb(num);
        Collections.sort(pos);
        ArrayList<String> res = new ArrayList<>();
        int start = 0;
        for (String name : pos) {
            for (int j = start; j < d.size(); j++) {
                String cur = d.get(j);
                int compare = name.compareTo(cur);
                if (compare < 0) {
                    start = j;
                    break;
                } else if (compare == 0) {
                    res.add(name);
                    start = j;
                    break;
                }
            }
        }
        for (String re : res) {
            pw.println(re);
        }
        if (res.size() == 0) {
            pw.println("NONE");
        }
        pw.close();
    }

    private static ArrayList<String> getComb (String num) {
        ArrayList<String> combs = new ArrayList<>();
        int val = Character.getNumericValue(num.charAt(0));
        char[] pos = new char[3];
        if (val == 2) {
            pos[0] = 'A';
            pos[1] = 'B';
            pos[2] = 'C';
        }
        else if (val == 3) {
            pos[0] = 'D';
            pos[1] = 'E';
            pos[2] = 'F';
        }
        else if (val == 4) {
            pos[0] = 'G';
            pos[1] = 'H';
            pos[2] = 'I';
        }
        else if (val == 5) {
            pos[0] = 'J';
            pos[1] = 'K';
            pos[2] = 'L';
        }
        else if (val == 6) {
            pos[0] = 'M';
            pos[1] = 'N';
            pos[2] = 'O';
        }
        else if (val == 7) {
            pos[0] = 'P';
            pos[1] = 'R';
            pos[2] = 'S';
        }
        else if (val == 8) {
            pos[0] = 'T';
            pos[1] = 'U';
            pos[2] = 'V';
        }
        else if (val == 9) {
            pos[0] = 'W';
            pos[1] = 'X';
            pos[2] = 'Y';
        }
        for (int i = 0; i < 3; i++) {
            String temp = "" + pos[i];
            if (num.length() > 1 ){
                ArrayList<String> rest = getComb(num.substring(1));
                for (String one : rest) {
                    String temp2 = temp + one;
                    combs.add(temp2);
                }
            }
            else {
                combs.add(temp);
            }
        }
        return combs;
    }
}