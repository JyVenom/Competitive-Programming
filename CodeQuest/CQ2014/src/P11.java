import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P11 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Prob11.in.txt"));
        PrintWriter pw = new PrintWriter(System.out);


        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);

            while (st.hasMoreTokens()) {
                sb.append(st.nextToken()).append(" ");
            }
        }
        sb.deleteCharAt(sb.length() - 1);

        ArrayList<ArrayList<Character>> res = new ArrayList<>();
        if (sb.length() == 1) {
            pw.println(sb);
        } else if (sb.length() == 2) {
            pw.print(sb.charAt(1));
            pw.print(sb.charAt(0));
        } else {
            int dir = 2, row = 0, col = 0;
            res.add(new ArrayList<>());
            res.get(0).add(sb.charAt(1));
            res.get(0).add(sb.charAt(0));
            int at = 2;
            while (at < sb.length()) {
                if (dir == 0) {
                    row--;
                    if (row == -1) {
                        res.add(0, new ArrayList<>());
                        for (int i = 0; i < res.get(1).size(); i++) {
                            res.get(0).add(' ');
                        }
                        row = 0;
                        dir = 3;
                    }
                    res.get(row).set(col, sb.charAt(at++));
                } else if (dir == 1) {
                    col++;
                    if (col == res.get(0).size()) {
                        for (ArrayList<Character> arr : res) {
                            arr.add(' ');
                        }
                        dir = 0;
                    }
                    res.get(row).set(col, sb.charAt(at++));
                } else if (dir == 2) {
                    row++;
                    if (row == res.size()) {
                        int sz = res.size();
                        res.add(new ArrayList<>());
                        for (int i = 0; i < res.get(0).size(); i++) {
                            res.get(sz).add(' ');
                        }
                        dir = 1;
                    }
                    res.get(row).set(col, sb.charAt(at++));
                } else {
                    col--;
                    if (col == -1) {
                        for (ArrayList<Character> arr : res) {
                            arr.add(0, ' ');
                        }
                        col = 0;
                        dir = 2;
                    }
                    res.get(row).set(col, sb.charAt(at++));
                }
            }
        }

        if (res.size() < res.get(0).size()) {
            for (int i = 0; i < res.get(0).size(); i++) {
                pw.print(' ');
            }
            pw.println();
        }
        for (ArrayList<Character> arr : res) {
            for (Character character : arr) {
                pw.print(character);
            }
            pw.println();
        }
        pw.close();
    }
}
