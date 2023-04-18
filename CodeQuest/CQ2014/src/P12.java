import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class P12 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Prob12.in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        ArrayList<String> data = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            data.add(line);
        }

        int len = 0;
        for (String str : data) {
            len += str.length();
        }
        StringBuilder sb = new StringBuilder(len);
        for (String str : data) {
            sb.append(str);
        }
        char[] dear = new char[]{'D', 'e', 'a', 'r'};
        StringBuilder ans = new StringBuilder();
        loop:
        for (int i = 1; i <= len; i++) {
            int max2 = len / i;
            if (max2 * i == len) {
                int[][] arr = new int[i][max2];
                int count = 0;
                for (int k = 0; k < max2; k++) {
                    for (int l = 0; l < i; l++) {
                        arr[l][k] = sb.charAt(count++);
                    }
                }
                StringBuilder sb2 = new StringBuilder(len);
                for (int k = 0; k < i; k++) {
                    for (int l = 0; l < max2; l++) {
                        sb2.append((char) arr[k][l]);
                    }
                }
                count = sb2.length();
                while (--count >= 0) {
                    if (sb2.charAt(count) == 'X') {
                        sb2.deleteCharAt(count);
                    } else {
                        break;
                    }
                }
                for (int k = 0; k < 26; k++) {
                    for (int l = 3; l < sb2.length(); l++) {
                        count = 0;
                        boolean good = true;
                        for (int m = l - 3; m <= l; m++) {
                            if (sb2.charAt(m) != dear[count++]) {
                                good = false;
                                break;
                            }
                        }
                        if (good) {
                            ans = sb2;
                            break loop;
                        }
                    }


                    for (int l = 0; l < sb2.length(); l++) {
                        if ('A' <= sb2.charAt(l) && sb2.charAt(l) <= 'Z') {
                            sb2.replace(l, l + 1, "" + (char) (((sb2.charAt(l) - 'A' + 1) % 26) + 'A'));
                        } else {
                            sb2.replace(l, l + 1, "" + (char) (((sb2.charAt(l) - 'a' + 1) % 26) + 'a'));
                        }
                    }
                }
            }
        }
        int count = 0;
        loop:
        for (String str : data) {
            for (int i = 0; i < str.length(); i++) {
                if (count == ans.length()) {
                    break loop;
                }
                pw.print(ans.charAt(count++));
            }
            pw.println();
        }

        pw.close();
    }
}
