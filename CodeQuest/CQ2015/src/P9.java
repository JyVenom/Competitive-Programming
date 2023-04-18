import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class P9 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            String[] data = new String[n];
            for (int i = 0; i < n; i++) {
                data[i] = br.readLine();
            }

            String type = br.readLine();
            if (type.equals("X")) {
                while (--n >= 0) {
                    pw.println(data[n]);
                }
            } else if (type.equals("Y")) {
                for (int i = 0; i < n; i++) {
                    for (int j = data[i].length() - 1; j >= 0; j--) {
                        pw.print(data[i].charAt(j));
                    }
                    pw.println();
                }
            } else {
                int max = 0;
                for (int i = 0; i < n; i++) {
                    if (data[i].length() > max) {
                        max = data[i].length();
                    }
                }
                char[][] helper = new char[max][n];
                for (int i = 0; i < n; i++) {
                    int j = 0;
                    for (; j < data[i].length(); j++) {
                        helper[j][i] = data[i].charAt(j);
                    }
                    for (; j < max; j++) {
                        helper[j][i] = ' ';
                    }
                }
                for (int i = 0; i < max; i++) {
                    for (int j = 0; j < n; j++) {
                        pw.print(helper[i][j]);
                    }
                    pw.println();
                }
            }
        }

        pw.close();
    }
}
