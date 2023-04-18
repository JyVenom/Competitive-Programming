import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.StringTokenizer;

public class Prob25 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String key = st.nextToken();

            HashSet<Character> used = new HashSet<>();
            char[][] table = new char[5][5];
            int row = 0;
            int col = 0;
            for (char c : key.toCharArray()) {
                if (used.contains(c)) {
                    continue;
                }

                table[row][col] = c;
                used.add(c);
                col++;
                if (col == 5){
                    row++;
                    col = 0;
                }
            }
            for (int i = 0; i < 26; i++) {
                char c = (char) ('A' + i);
                if (c == 'J') {
                    continue;
                }
                if (used.contains(c)) {
                    continue;
                }

                table[row][col] = c;
                col++;
                if (col == 5){
                    row++;
                    col = 0;
                }
            }

            HashMap<Character, int[]> map = new HashMap<>();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    map.put(table[i][j], new int[]{i, j});
                }
            }

            while (x-- > 0) {
                String s = br.readLine();

                StringBuilder sb = new StringBuilder(s.length());
                for (int i = 0; i < s.length(); i+=2) {
                    char a = s.charAt(i);
                    char b = s.charAt(i+1);

                    int[] locA = map.get(a);
                    int[] locB = map.get(b);

                    if (locA[0] == locB[0]) {
                        sb.append(table[locA[0]][(locA[1] + 4) % 5]);
                        sb.append(table[locB[0]][(locB[1] + 4) % 5]);
                    }
                    else if (locA[1] == locB[1]){
                        sb.append(table[(locA[0] + 4) % 5][locA[1]]);
                        sb.append(table[(locB[0] + 4) % 5][locB[1]]);
                    }
                    else {
                        sb.append(table[locA[0]][locB[1]]);
                        sb.append(table[locB[0]][locA[1]]);
                    }
                }

                pw.println(sb.toString().toLowerCase(Locale.ROOT));
            }
        }

        pw.close();
    }
}
